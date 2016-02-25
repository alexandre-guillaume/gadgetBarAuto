<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--Load the AJAX API-->
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
</head>
<body>         
<script type="text/javascript">
	// Load the Visualization API and the piechart package.
	google.load('visualization', '1.1', {
		'packages' : [ 'bar' ]
	});
	// Set a callback to run when the Google Visualization API is loaded.
	google.setOnLoadCallback(reload);
	// Callback that creates and populates a data table,  instantiates the pie chart, passes in the data and draws it.
	
	var refreshTime = '<%= session.getAttribute("refreshTime") %>';
		 
	function drawChart(jsondata) {
		
		 var couleur1 = '<%= session.getAttribute("couleur1") %>';
		 var couleur2 = '<%= session.getAttribute("couleur2") %>';
		 

		 var data = new google.visualization.DataTable();
		 
	      data.addColumn('string', 'Heure');
	      data.addColumn('number', 'J');
	      data.addColumn('number', 'J-7');     
	      
	      var arr = [];

			for(var i = 0; i < jsondata.length; i++) {
	    	    var obj = jsondata[i];
				arr[i] = [];
				arr[i][0] = obj.heure;
				arr[i][1] = obj.today;
				arr[i][2] = obj.lastWeek;
			}
			
			data.addRows(arr);	          

	        var options = {
	        		colors: [couleur1,couleur2],
	        		chart: { subtitle: 'Auto' },
	        		legend: { position: 'top' },
	        		chartArea: {
	        			width : 300
	        		}     
	        };

	        var chart = new google.charts.Bar(document.getElementById('chart_div'));

	        chart.draw(data, options);
	      }
	
	
	function reload() {
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				drawChart(this.response);
			}
		}
		xhttp.responseType = 'json';
		xhttp.open("GET", "RafraichirAuto", true);
		xhttp.send();

		setTimeout("reload()", refreshTime);

	}

	    
</script>
	<div id="chart_div" style="width: 900; height: 400"></div>
</body>
</html>