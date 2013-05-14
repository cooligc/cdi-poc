<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test SQL Injection</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/demosqlinjection" method="post">
		<table>
			<tr>
				<td colspan="2"><h2>Let's demonstrate some SQL-Injection</h2></td>
			</tr>
			<tr>
				<td>Username:</td>
				<td>
					<input type="text" id="username" name="username" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="submit" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>