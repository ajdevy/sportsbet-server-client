(function(angular) {
    angular.module("sportsMatchApp.controllers").controller("SportsMatchController", function($scope, SportsMatchService) {
        $scope.matches = [];

//        var bet = {};
//        bet.sportsMatchName = "name";
//        $scope.bet = bet;


        SportsMatchService.receive().then(null, null, function(matches) {
        //      console.log("saving matches to scope " + matches);
                $scope.matches=matches;
        });

        $scope.onClickPlaceBet = function(bet) {
                console.log("onClickPlaceBet bet = ");
                console.log(bet);
                //check coefficient locally
//                angular.forEach($scope.matches, function(value, key) {
//                  this.push(key + ': ' + value);
//                  if(key.name == bet.sportsMatchName) {
//                        if(bet.coefficient != key.coefficient) {
//                            //coefficient changed, notify user
//                            if(confirm("The coefficient has changed to " + key.coefficient  + " would you still like to bet with the new coefficient?")) {
//
//                            }
//                        }
//                  }
//                });


                 SportsMatchService.placeBet(bet);
        };

        var createBetObjectAndSendToRest = function(sportsMatch, betType) {
                var bet = {};
                switch(betType) {
                    case 'win':
                        bet.coefficient = sportsMatch.win;
                        break;
                    case 'draw':
                        bet.coefficient = sportsMatch.draw;
                        break;
                    case 'lose':
                        bet.coefficient = sportsMatch.lose;
                        break;
                }
                bet.amount = 20;
                bet.sportsMatchName = sportsMatch.name;
                bet.betType = betType;

                $scope.bet = bet;
        };

        $scope.populateDb = function() {
                console.log("populateDb");
                SportsMatchService.populateDb();
        };

        $scope.placeWinBet = function(sportsMatch) {
                createBetObjectAndSendToRest(sportsMatch,'win');
        };
        $scope.placeDrawBet = function(sportsMatch) {
                var bet = {};
                bet.coefficient = sportsMatch.coefficient;
                bet.sportsMatchName = sportsMatch.name;
                bet.betType = 'draw';

                $scope.bet = bet;
                console.log("placeDrawBet on sportsMatch = ");
                console.log(bet);
                SportsMatchService.placeBet(bet);
        };
        $scope.placeLoseBet = function(sportsMatch) {
                var bet = {};
                bet.coefficient = sportsMatch.coefficient;
                bet.sportsMatchName = sportsMatch.name;
                bet.betType = 'lose';

                $scope.bet = bet;
                console.log("placeLoseBet on sportsMatch = ");
                console.log(bet);
                SportsMatchService.placeBet(bet);
        };

    });
})(angular);