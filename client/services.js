(function(angular, SockJS, Stomp, _, undefined) {
  angular.module("sportsMatchApp.services").service("SportsMatchService", function($http, $q, $timeout) {
    
    var service = {}, listener = $q.defer(), socket = {
      client: null,
      stomp: null
    }, sportsMatchIds = [];

    service.RECONNECT_TIMEOUT = 30000;
    service.SOCKET_URL = "http://127.0.0.1:8080/live";
    service.CHAT_TOPIC = "/topic/bids";

    service.receive = function() {
      return listener.promise;
    };
    
    service.placeBet = function(sportsMatchName, betType, coefficient) {
        //placebet to REST
        var request = {
                sportsMatchName : sportsMatchName,
                betAmount : 20,
                betType : betType,
                coefficient : coefficient,
        };

        console.log("placing bet to server... ");
        console.log(request)

        var response = $http.post('http://127.0.0.1:8080/rest/bet', request);
        response.success(function(data, status, headers, config) {
            alert( "success message: " + JSON.stringify({data: data}));
            //TODO: success message: {"data":{"status":0,"newCoefficient":3.3}}
            //TODO: parse new coefficient and place bet again
        });
        response.error(function(data, status, headers, config) {
            alert( "failure message: " + JSON.stringify({data: data}));
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