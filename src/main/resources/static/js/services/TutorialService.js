app.service("tutorialService", ["$http", function ($http) {
    var url = "http://localhost:8080/controller/tutorial";

    return {
        getTutorials: function() {
            return $http.get(url + "/list/")
        },

        getTutorial: function (id) {
            return $http.get(url + '/' + id)
        },

        createTutorial: function(name, level) {
            return $http.post(url + "/addTutorial", data = {name: name, level: level})
        }
    }
}]);