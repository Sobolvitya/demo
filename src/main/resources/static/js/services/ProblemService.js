app.service("problemService", ["$http", function ($http) {
    var url = "http://localhost:8080/controller/task";
    return {
        getTasks: function() {
            return $http.get(url + "/list/");
        },

        getTask: function (id) {
            return $http.get(url +"/" + id);
        },

        searchForTask: function (name) {
            return $http.get(url + "/findByName/" + name);
        },

        createTask: function(name, level, max_score, description) {
            return $http.post(url +"/addTask", data = {name: name, level: level, maxScore: max_score, description: description});
        },

        removeTask: function (id) {
            return $http.delete(url + "/removeTask/" + id);
        },
        
        updateTask: function (id, name, level, max_score, description) {
            return $http.put(url + "/updateTask/" + id, data = {name: name, level: level, maxScore: max_score, description: description});
        }
    }
}]);