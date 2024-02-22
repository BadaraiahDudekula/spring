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
	<form:form modelAttribute="d" action="save" method="post">
		<form:label path="name">DeptName</form:label>
		<form:input path="name" />
		<br>
		<form:label path="no">DeptNo</form:label>
		<form:input path="no" />
		<br>
		<form:label path="location">DeptLocation</form:label>
		<form:input path="location" />
		<br>
		<form:button>Save</form:button>
	</form:form>
</body>
</html>