<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv=Content-Style-Type content=text/css>
		<link rel="stylesheet" type="text/css" href="hatena.css">
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<script type="text/javascript" src="http://code.highcharts.com/highcharts.js"></script>
		<title>Weight graph</title>
	</head>

	<body>
		<div id="chart" style="width:1300px; height:650px"></div>
		<script type="text/javascript">
		function draw()
		{
			options =
			{
				chart: {renderTo: 'chart', zoomType:'xy', plotBackgroundColor: 'lightgray'},
				title: {text: 'Weight graph'},
				xAxis: {title: null, type: 'datetime'},
				series: [ <s:property value='chartPoints' /> ]
			};
			chart = new Highcharts.Chart(options);
		};
		document.body.onload = draw();
		</script>
	</body>
</html>
