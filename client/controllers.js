(function(angular) {
    angular.module("sportsMatchApp.controllers").controller("SportsMatchController", function($scope, SportsMatchService) {
        $scope.matches = [];

        SportsMatchService.receive().then(null, null, function(matches) {
        //      console.log("saving matches to scope " + matches);
                $scope.matches=matches;
        });

        $scope.placeWinBet = function(sportsMatch) {
                console.log("placeWinBet on sportsMatch = ");
                console.log(sportsMatch);
                SportsMatchService.placeBet(sportsMatch.name,'win',sportsMatch.win);
        };
        $scope.placeDrawBet = function(sportsMatch) {
                    console.log("placeDrawBet on sportsMatch = ");
                    console.log(sportsMatch);
                    SportsMatchService.placeBet(sportsMatch.name,'draw',sportsMatch.draw);
        };
        $scope.placeLoseBet = function(sportsMatch) {
                    console.log("placeLoseBet on sportsMatch = ");
                    console.log(sportsMatch);
                    SportsMatchService.placeBet(sportsMatch.name,'lose',sportsMatch.lose);
        };

    });
})(angular);