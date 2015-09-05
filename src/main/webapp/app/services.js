(function(angular, SockJS, Stomp, _, undefined) {
  angular.module("sportsMatchApp.services").service("SportsMatchService", function($http, $q, $timeout) {
    
    var service = {}, listener = $q.defer(), socket = {
      client: null,
      stomp: null
    }, sportsMatchIds = [];

    service.RECONNECT_TIMEOUT = 30000;
    service.SOCKET_URL = "/live";
    service.CHAT_TOPIC = "/topic/bids";

    service.receive = function() {
      return listener.promise;
    };

    service.populateDb = function() {
        $http.get('/rest/insertdata');
    }

    service.placeBet = function(bet) {
        //place bet to REST
        var request = {
                sportsMatchName : bet.sportsMatchName,
                betAmount : bet.amount,
                betType : bet.betType,
                coefficient : bet.coefficient,
        };
        console.log("placing bet to REST, request = ");
        console.log(request);

        var response = $http.post('/rest/bet', request);
        response.success(function(data, status, headers, config) {
            console.log(data);

            //check if bet was placed
            if(data.status == 0){
                //get new coefficient and display to user
                if(confirm("The coefficient has changed to " + data.newCoefficient  + " would you still like to bet with the new coefficient?")){
                    bet.coefficient=data.newCoefficient;
                    service.placeBet(bet);
                }
            } else {
                alert("Bet placed!");
            }
        });
        response.error(function(data, status, headers, config) {
            alert("Could not place a bet: " + JSON.stringify({data: data}));
        });
    };

    var reconnect = function() {
      $timeout(function() {
        initialize();
      }, this.RECONNECT_TIMEOUT);
    };
    
    var parseData = function(data) {
      var matches = JSON.parse(data);
      console.log("getSportsMatch data = " + matches);
      return matches;
    };

    var startListener = function() {
      socket.stomp.subscribe(service.CHAT_TOPIC, function(data) {
        // console.log("got a sportsMatch " + data.body);
        listener.notify(parseData(data.body));
      });
    };
    
    var initialize = function() {
      socket.client = new SockJS(service.SOCKET_URL);
      socket.stomp = Stomp.over(socket.client);
      socket.stomp.connect({}, startListener);
      socket.stomp.onclose = reconnect;
    };
    
    initialize();
    return service;
  });
})(angular, SockJS, Stomp, _, undefined);