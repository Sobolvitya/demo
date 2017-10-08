app.controller('tutorialController', ["$scope", "$http", "tutorialService", function($scope, $http, tutorialService) {

    tutorialService.getTutorials().then(function (response) {
        console.log(response.data);
        $scope.tutorials = response.data;
    });

}]);