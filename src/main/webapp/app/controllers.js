(function(angular) {
    angular.module("sportsMatchApp.controllers").controller("SportsMatchController", function($scope, SportsMatchService) {
        $scope.matches = [];

        SportsMatchService.receive().then(null, null, function(matches) {
                $scope.matches=matches;
        });

        $scope.onClickPlaceBet = function(bet) {
                 SportsMatchService.placeBet(bet);
        };

        $scope.onClickPopulateDb = function() {
                SportsMatchService.populateDb();
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

        $scope.placeWinBet = function(sportsMatch) {
                createBetObjectAndSendToRest(sportsMatch,'win');
        };
        $scope.placeDrawBet = function(sportsMatch) {
                 createBetObjectAndSendToRest(sportsMatch,'draw');
        };
        $scope.placeLoseBet = function(sportsMatch) {
                createBetObjectAndSendToRest(sportsMatch,'lose');
        };

    });
})(angular);