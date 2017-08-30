<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>体重管理</title>
		<link rel="stylesheet" type="text/css" href="hatena.css">
	</head>

	<body>
		<h3>戦績</h3>
			<s:form action="addSenseki1" theme="simple">
				<s:submit value="戦績入力" />
			</s:form>
			<s:form action="svgGraph" theme="simple">
				<s:select name="range" list="#{ '200':'200','800':'800','1600':'1600' }"/>
				<s:submit value="戦績グラフ SVG" />
			</s:form>
			<s:form action="highchartsGraph" theme="simple">
				<s:submit value="戦績グラフ Highcharts" />
			</s:form>
			<s:form action="listSenseki" theme="simple">
				<s:submit value="戦績一覧" />
			</s:form>
		<h3>店</h3>
		<ul>
		<li><a href="addmise1.jsp">店追加</a>
		<li>
			<s:form action="listMise" theme="simple">
				<input type="hidden" name="order" value="name" />
				<s:submit value="店一覧" />
			</s:form>
		</ul>
	</body>
</html>
