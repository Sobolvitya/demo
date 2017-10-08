app.controller('problemController',["$rootScope", "$scope", "$location", "problemService", function ($rootScope, $scope, $location, problemService) {

    var id = $location.path().split(/[\s/]+/).pop();
    if(!isNaN(id)) {
        problemService.getTask(id).then(function (response) {
            $scope.currentTask = response.data;
        });
    }
    else {
        problemService.getTasks().then(function (response){
            $scope.tasks = response.data;
        });
    }


    $scope.removeTask = function (id) {
        problemService.removeTask(id)
    };


    $scope.addTask = function (task) {
        var name = task.name;
        var level = parseInt(task.level);
        var max_score = parseInt(task.maxScore);
        var description = task.description;
        problemService.createTask(name, level, max_score, description).then(
           function (response) {
               $location.path("#/problems");
           },
           function (response) {
             console.log(response)
           }

       );
    };

    $scope.editMode = false;
    $scope.searchable = undefined;
    
    $scope.search = function (searchable) {
        problemService.searchForTask(searchable).then(function (response) {
            $scope.tasks = response.data;
            if($scope.tasks.length == 0)
                $scope.message = "EMPTY";
        },
        function () {
            problemService.getTasks().then(function (response){
                $scope.tasks = response.data;
            });
        })
    };
    
    $scope.updateTask = function (task) {
        problemService.updateTask(task.id, task.name, task.level, task.maxScore, task.description);
    };
    
    $scope.changeEditMode = function () {
        $scope.editMode = !$scope.editMode;
    }
}]);