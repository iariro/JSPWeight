<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>体重管理 - 店追加</title>
		<link rel="stylesheet" type="text/css" href="hatena.css">
	</head>

	<body>
		<h1>体重管理 - 店追加</h1>

		<div class=hatena-body>
		<div class=main>
		<div class=day>

			<s:form action="addMise" theme="simple">
				<table>
					<tr><td>店名</td><td><input type="text" name="name"></td></tr>
					<tr><td>住所</td><td><input type="text" name="address"></td></tr>
					<tr><td colspan="2" align="center"><s:submit value="追加" /></td></tr>
				</table>
			</s:form>

		</div>
		</div>
		</div>

	</body>
</html>
