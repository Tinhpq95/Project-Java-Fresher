var app = angular.module('mainApp', []);

app.controller('MainCtrl', function($scope,$http) {
	 $scope.records ={};
  $http.get('http://localhost:8080/task/getAll').then(function(data){
	  $scope.records = data.data;
  })
});
