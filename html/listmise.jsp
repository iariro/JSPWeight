<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="hatena.css">
		<title>体重管理</title>
	</head>
	<body>

		<h1>店一覧</h1>
		<div class=hatena-body>
		<div class=main>
		<div class=day>

		<table>
			<tr>
				<th>ID</th>
				<th>店名</th>
				<th>住所</th>
				<th>操作</th>
			</tr>

			<s:iterator value="miseCollection">
				<tr>
				<td><s:property value="id" /></td>
				<td><s:property value="name" /></td>
				<td><s:property value="address" /></td>
				<td>
					<s:form action="editMise1">
						<input type="hidden" name="id" value="<s:property value="id" />">
						<s:submit value="編集" />
					</s:form>
				</td>
				</tr>
			</s:iterator>
		</table>

		</div>
		</div>
		</div>

	</body>
</html>
