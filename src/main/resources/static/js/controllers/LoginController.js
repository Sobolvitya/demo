app.controller('loginController', ["$http", "$scope", "$location",
    function ($http, $scope, $location) {

    var url = "https://frost-db.herokuapp.com/controller/user";

    $scope.user_email = undefined;
    $scope.password = undefined;
    $scope.fullName = undefined;

    $scope.canProceed = true;

    $scope.login = function () {
        return $http.post(url + '/login', {email: $scope.user_email, passHash: $scope.password}).then(function () {
            $location.path("#/problems");
        }).catch(function (){
            $scope.canProceed = false;
        })
    };

    $scope.signUp = function () {
        return $http.post(url + '/registration',
            {
                email: $scope.user_email,
                passHash: $scope.password,
                fullName: $scope.fullName,
                level: 1,
                type: "user",
                registrationDate: getCurrentDate()
        })
        .then(function (response) {
            $location.path("#/problems");
        }).catch(function (response){
            $scope.canProceed = false;
        })
    };
        
    function getCurrentDate() {
        var date = new Date(Date.now());
        var month = date.getMonth() < 9 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var day = date.getDay() < 10 ? "0" + date.getDay() : date.getDay();
        return date.getFullYear() + "-" + month + "-" + day;
    }

}]);