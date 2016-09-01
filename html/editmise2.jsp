<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
	<head>
		<title>体重管理 - 店情報変更</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
		<link rel="stylesheet" type="text/css" href="hatena.css">
	</head>

	<body>
		<h1>体重管理 - 店情報変更</h1>

		<div class=hatena-body>
		<div class=main>
			<table cellpadding="5">
				<tr bgcolor="#eeeeff">
					<td>ID</td>
					<td><s:property value="id" /></td>
				</tr>
				<tr bgcolor="#eeeeff">
					<td>店名</td>
					<td><s:property value="name" /></td>
				</tr>
				<tr bgcolor="#eeeeff">
					<td>住所</td>
					<td><s:property value="address" /></td>
				</tr>
			</table>
		</div>
		</div>

	</body>
</html>
