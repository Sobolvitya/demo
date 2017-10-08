var app = angular.module('app', ['ngRoute','ngResource']);
app.config(function($routeProvider){
    $routeProvider
        .when('/login',{
            templateUrl: 'views/login.html',
            controller: 'loginController'
        })
        .when('/tutorials', {
            templateUrl: 'views/tutorial.html',
            controller: 'tutorialController'
        })
        .when('/problems', {
            templateUrl: 'views/problems.html',
            controller: 'problemController'
        })
        .when('/problem/:id', {
            templateUrl: 'views/problem.html',
            controller: 'problemController'
        })
        .when('/createProblem', {
            templateUrl: 'views/createProblem.html',
            controller: 'problemController'
        })
        .when("/signUp", {
            templateUrl: 'views/sign_up.html',
            controller: 'loginController'
        })
        .when('/profile', {
            templateUrl: 'views/profile.html',
            controller: 'profileController'
        })
        .otherwise(
            { redirectTo: '/problems'}
        );
}).controller("MainController", ['$scope','$http', function($scope, $http) {
    var url = "https://frost-db.herokuapp.com/controller/user";
    
    $scope.categories = ["Problems", "Tutorials"];

    $scope.activeCategory = "Problems";

    $scope.setActiveCategory = function (category) {
        $scope.activeCategory = category;
    };

    $scope.user = undefined;
    $scope.showUser = false;

    $http.get(url + '/currentUser').then(function (response) {
        console.log(response);
        $scope.showUser = true;
        $scope.user = response.data;
    });

    $scope.logOut = function () {
        $http.post(url + '/logout').then(function (response) {
            $scope.showUser = false;
            $scope.user = undefined;
        })
    }


}]);

