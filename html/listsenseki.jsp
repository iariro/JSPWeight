<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="hatena.css">
		<title>体重管理</title>
	</head>
	<body>

	<h1>来店一覧</h1>
	<div class=hatena-body>
	<div class=main>
	<div class=day>

		<table>
			<tr><th>日付</th><th>間隔</th><th>店</th><th>前</th><th>後</th><th>差</th></tr>
			<s:iterator value="raitenCollection">
				<tr>
					<td><s:property value="dateForList" /></td>
					<td align="right"><s:property value="dateDiff" /></td>
					<td><s:property value="mise" /></td>
					<td><s:property value="before" />kg</td>
					<td><s:property value="after" />kg</td>
					<td><s:property value="diff" />kg</td>
				</tr>
			</s:iterator>
		</table>
		<s:property value="count" />件

	</div>
	</div>
	</div>

	</body>
</html>
