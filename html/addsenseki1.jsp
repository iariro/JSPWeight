<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
	<head>
	<title>体重管理 - 戦績登録</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
		<link rel="stylesheet" type="text/css" href="hatena.css">
	</head>

	<body>
		<h1>体重管理 - 戦績登録</h1>

		<div class=hatena-body>
		<div class=main>
		<div class=day>

		<s:form action="addSenseki2" theme="simple">
			<table>
			<tr>
				<td>日付</td>
				<td><input type="text" name="date" value="<s:property value="today" />"></td>
			</tr>
			<tr>
				<td>店</td>
				<td>
					<s:select name="miseId" list="miseCollection" listKey="id" listValue="name" />
				</td>
			</tr>
			<tr>
				<td>入浴前</td>
				<td><input type="text" name="before" value="66.66"></td>
			</tr>
			<tr>
				<td>入浴後</td>
				<td><input type="text" name="after" value="66.66"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<s:submit value="登録" />
				</td>
			</tr>
			</table>
		</s:form>

		</div>
		</div>
		</div>

	</body>
</html>
