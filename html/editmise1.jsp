<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
	<head>
		<title>体重管理 - 店編集</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
		<link rel="stylesheet" type="text/css" href="hatena.css">
	</head>

	<body>
		<h1>体重管理 - 店編集</h1>

		<div class=hatena-body>
		<div class=main>
		<div class=day>

			<s:form action="editMise2">
				<table>
					<tr>
						<td>ID</td>
						<td><input name="id" value="<s:property value="id" />" readonly="readonly"></td>
					</tr>
					<tr>
						<td>名前</td>
						<td><input type="text" name="name" value="<s:property value="name" />"></td>
					</tr>
					<tr>
						<td>住所</td>
						<td><input type="text" name="address" value="<s:property value="address" />" size="30"></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><s:submit value="更新" /></td>
					</tr>
				</table>
			</s:form>

		</div>
		</div>
		</div>

	</body>
</html>
