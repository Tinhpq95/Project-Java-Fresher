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

app.controller('MainCtrl',
		function($rootScope, $scope, $http, sharedProperties) {
			$scope.records = {};
			$scope.nameProject = 'SuitProject001';
			$http.get('http://localhost:7000/project/getAllProjectName').then(
					function(data) {
						$scope.records = data.data;
					})

			$scope.setCurrentProject = function(name) {

				sharedProperties.setProjectName(name);
				$scope.nameProject = sharedProperties.getProjectName();
				$rootScope.Get();
				$rootScope.Get2();

			}
		});

app.controller('PieChart',
		function($rootScope, $scope, $http, sharedProperties) {

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
					title : 'Automation',
					is3D : true,
					width : 550,
					height : 425,
					chartArea : {
						left : 10,
						top : 120,
					},
				};

				var chart = new google.visualization.PieChart(document
						.getElementById('piechart_3d'));
				chart.draw(data, options);
			}

			// Get content from JSON url
			$scope.Excution = [];
			$rootScope.Get = function() {
				$http(
						{
							method : "GET",
							url : "http://localhost:7000/project/"
									+ sharedProperties.getProjectName()
						}).then(
						function mySuccess(response) {

							$scope.Excution = [
									[ "Manual", response.data.manual ],
									[ "Automated", response.data.automated ] ];
							drawPieChart();
						});
			}
			$rootScope.Get();

		});

app.controller('columnchart', function($rootScope, $scope, $http,
		sharedProperties) {

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
			title : "Design Status",
			backgroundColor :{
				fill:'#CFD8DC',
				opacity:100,
			},
			width : 400,
			height : 400,
			legend : {
				position : "none"
			},
			chartArea : {
				left : 80,
				top : 100,
			},
		};

		var chart = new google.visualization.ColumnChart(document
				.getElementById("columnchart_values"));
		chart.draw(data, options);
	}

	// Get content from JSON url
	$scope.Excution = [];
	$rootScope.Get2 = function() {
		$http(
				{
					method : "GET",
					url : "http://localhost:7000/project/"
							+ sharedProperties.getProjectName()
				}).then(
				function mySuccess(response) {

					$scope.Excution = [ [ "Designed", response.data.designed ],
							[ "Ready", response.data.ready ] ];
					drawColumnchart();
				});
	}
	$rootScope.Get2();

});
