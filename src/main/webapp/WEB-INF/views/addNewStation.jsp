<%@ page import="java.time.LocalDate" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet"
		      href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
		      integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
		      crossorigin="anonymous">
		<title>Add new station</title>
	</head>
	<body>

		<%@include file="header.jsp" %>
		<div class="container-fluid mt-3">
			<h3 class="text-black-50">Add new station</h3>
			<form:form method="POST" action="" modelAttribute="stationDto">
			<form:label path="name">Name</form:label>
			<form:input path="name"/><p>
			<input type="submit" value="Submit"/>
			</form:form>
		</div>
		<footer class="navbar fixed-bottom">
			<div class="footer-copyright text-center py-3">© <%= LocalDate.now().getYear() %> Copyright: Gluegun Ltd.
			</div>
		</footer>
	</body>
</html>
