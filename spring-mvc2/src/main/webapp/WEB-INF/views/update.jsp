<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form modelAttribute="m" action="update" method="post">
		<form:label path="id">Id</form:label>
		<form:input path="id" />
		<br>
		<form:label path="name">Name</form:label>
		<form:input path="name" />
		<br>
		<form:label path="phone">Phone</form:label>
		<form:input path="phone" />
		<br>
		<form:label path="email">Email</form:label>
		<form:input path="email" />
		<br>
		<form:label path="location">Location</form:label>
		<form:input path="location" />
		<br>
		<form:button>Save</form:button>
	</form:form>
</body>
</html>