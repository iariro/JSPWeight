<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
	<head>
		<title>体重管理 - データ登録</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
		<link rel="stylesheet" type="text/css" href="hatena.css">
	</head>

	<body>
		<h1>体重管理 - データ登録</h1>

		<div class=hatena-body>
		<div class=main>
		<div class=day>

			以下を登録しました。<br>
			<br>
			<table>
				<tr>
					<td>日付</td>
					<td><s:property value="date" /></td>
				</tr>
				<tr>
					<td>店</td>
					<td><s:property value="miseId" /></td>
				</tr>
				<tr>
					<td>入浴前</td>
					<td><s:property value="before" /></td>
				</tr>
				<tr>
					<td>入浴後</td>
					<td><s:property value="after" /></td>
				</tr>
			</table>

		</div>
		</div>
		</div>

	</body>
</html>
