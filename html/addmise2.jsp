<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
	<head>
		<title>体重管理 - 店追加</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
		<link rel="stylesheet" type="text/css" href="hatena.css">
	</head>

	<body>
		<h1>体重管理 - 店追加</h1>

		<div class=hatena-body>
		<div class=main>
		<div class=day>

			以下を登録しました。<br>
			<br>
			<table>
				<tr>
					<td>ID</td>
					<td><s:property value="newid" /></td>
				</tr>
				<tr>
					<td>店名</td>
					<td><s:property value="name" /></td>
				</tr>
				<tr>
					<td>住所</td>
					<td><s:property value="address" /></td>
				</tr>
			</table>

		</div>
		</div>
		</div>

	</body>
</html>
