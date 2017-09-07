var app = angular.module('mainApp', []);

app.service('sharedProperties', function() {
	var projectname = 'SuitProject001';

	return {
		getProjectName : function() {
			return projectname;
		},
		setProjectName : function(name) {
			projectname = name;
			
		}
	};
});

app.controller('MainCtrl', function($scope, $http, sharedProperties) {
	$scope.records = {};
	$http.get('http://localhost:7000/project/getAllProjectName').then(
			function(data) {
				$scope.records = data.data;
			})

	$scope.setCurrentProject = function(name) {

		sharedProperties.setProjectName(name);
		console.log(name);

	}
});

app.controller('PieChart', function($scope, $http, sharedProperties) {

	google.charts.load('current', {
		'packages' : [ 'corechart' ]
	});

	// Function draw pie chart
	google.charts.setOnLoadCallback(drawPieChart);

	function drawPieChart() {
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'Status');
		data.addColumn('number', 'Quantity');
		data.addRows($scope.Excution);

		var options = {
			title : 'Test Execution',
			is3D : true,
		};

		var chart = new google.visualization.PieChart(document
				.getElementById('piechart_3d'));
		chart.draw(data, options);
	}

	// Get content from JSON url
	$scope.Excution = [];
	$scope.Get = function() {
		$http(
				{
					method : "GET",
					url : "http://localhost:7000/project/"
							+ sharedProperties.getProjectName()
				}).then(
				function mySuccess(response) {

					$scope.Excution = [ [ "designed", response.data.designed ],
							[ "ready", response.data.ready ] ];
					drawPieChart();
				});
	}

	$scope.Get();
});

app.controller('columnchart', function($scope, $http, sharedProperties) {

	google.charts.load('current', {
		'packages' : [ 'corechart' ]
	});

	// Function draw pie chart
	google.charts.setOnLoadCallback(drawColumnchart);
	function drawColumnchart() {
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'Status');
		data.addColumn('number', 'Quantity');
		data.addRows($scope.Excution);

		var options = {
			title : "Density of Precious Metals, in g/cm^3",
			width : 600,
			height : 400,
			bar : {
				groupWidth : "95%"
			},
			legend : {
				position : "none"
			},
		};

		var chart = new google.visualization.ColumnChart(document
				.getElementById("columnchart_values"));
		chart.draw(data, options);
	}

	// Get content from JSON url
	$scope.Excution = [];
	$scope.Get = function() {
		$http(
				{
					method : "GET",
					url : "http://localhost:7000/project/"
							+ sharedProperties.getProjectName()
				}).then(
				function mySuccess(response) {

					$scope.Excution = [ [ "auto", response.data.automated ],
							[ "manual", response.data.manual ] ];
					drawColumnchart();
				});
	}

	$scope.Get();
});
