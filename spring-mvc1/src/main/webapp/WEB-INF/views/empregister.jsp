<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form modelAttribute="e" action="saveemp" method="post">
	
		<form:label path="id">DeptId</form:label>
		<form:input path="id" />
		<br>
		<form:label path="name">EmpName</form:label>
		<form:input path="name" />
		<br>
		<form:label path="phone">PhoneNo</form:label>
		<form:input path="phone" />
		<br>
		<form:label path="email">Email</form:label>
		<form:input path="email" />
		<br>
		<form:label path="password">Password</form:label>
		<form:input path="password" />
		<br>
		<form:button>Save</form:button>
	</form:form>
</body>
</html>