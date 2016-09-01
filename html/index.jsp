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
		<ul>
			<li>
				<s:form action="addSenseki1">
					<s:submit value="戦績入力" />
				</s:form>
			<li>
				<s:form action="graph1">
					<s:submit value="戦績グラフ200日" />
				</s:form>
			<li>
				<s:form action="graph2">
					<s:submit value="戦績グラフ800日" />
				</s:form>
			<li>
				<s:form action="graph3">
					<s:submit value="戦績グラフ1,600日" />
				</s:form>
			<li>
				<s:form action="listSenseki">
					<s:submit value="戦績一覧" />
				</s:form>
		</ul>
		<h3>店</h3>
		<ul>
		<li><a href="addmise1.jsp">店追加</a>
		<li>
			<s:form action="listMise">
				<input type="hidden" name="order" value="name" />
				<s:submit value="店一覧" />
			</s:form>
		</ul>
	</body>
</html>
