'use strict';

app.controller('UserCtrl', ['$scope','$http', function($scope, $http) {
    $http.get("vendor/jquery/datatables/datatable.json")
        .success(function (response) {$scope.list = response.aaData;});

}]);