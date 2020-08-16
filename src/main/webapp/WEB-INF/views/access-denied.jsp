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
		<title>Access denied</title>
	</head>
	<body>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/" style="color: red">Railway</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
			        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active">
						<a class="nav-link" href="${pageContext.request.contextPath}/">Home <span class="sr-only">(current)</span></a>
					</li>
					<security:authorize access="hasAuthority('ADMIN')">
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.request.contextPath}/trains">Trains</a>
						</li>
					</security:authorize>
					<li class="nav-item">
						<a class="nav-link" href="${pageContext.request.contextPath}/stations">Stations</a>
					</li>
					<security:authorize access="isAnonymous()">
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
						</li>
					</security:authorize>
					<security:authorize access="isAuthenticated()">
						<li class="nav-item">
							<form:form action="${pageContext.request.contextPath}/logout" method="post">
								<input type="submit" value="Logout"/>
							</form:form>
						</li>
					</security:authorize>
				</ul>
			</div>
		</nav>

		<div class="alert alert-danger" role="alert">
			You have no access to view this page
			<p>
				<a href="${pageContext.request.contextPath}/">Return home</a>
			</p>
		</div>

		<footer class="navbar fixed-bottom">
			<div class="footer-copyright text-center py-3">© <%= LocalDate.now().getYear() %> Copyright: Gluegun Ltd.
			</div>
		</footer>
	</body>
</html>