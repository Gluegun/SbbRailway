<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<title>Welcome to WhereFrom Railway company web site</title>
	</head>
	<body>
		<%@include file="header.jsp" %>
		<div class="container-fluid mt-3">
			<h5 class="mb-2">Welcome to WhereFrom Railways!</h5>
			<security:authorize access="isAnonymous()">
				<h5>
					<a href="${pageContext.request.contextPath}/buy_ticket" onclick="return false;">Buy ticket</a>
				</h5>
				<p>To buy tickets you need to log in</p>
			</security:authorize>
			<security:authorize access="hasAnyAuthority('ADMIN', 'USER')">
				<a href="${pageContext.request.contextPath}/buy_ticket">Buy ticket</a>
			</security:authorize>
		</div>
		<div class="card ml-3" style="width: 40rem">
			<img class="card-img-top" src="../../static/img/schema.jpg" alt="schema">
			<div class="card-body">
				<p class="card-text">Schema for stations</p>
			</div>
		</div>
		<%@include file="footer.jsp" %>
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		        crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		        crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
		        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
		        crossorigin="anonymous"></script>
	</body>
</html>
