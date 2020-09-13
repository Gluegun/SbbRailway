<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="${pageContext.request.contextPath}/" style="color: red">Railway</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
	        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active">
				<a class="nav-link" href="${pageContext.request.contextPath}/">Home <span
						class="sr-only">(current)</span></a>
			</li>
			<security:authorize access="hasAuthority('ADMIN')">
				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/trains">Trains</a>
				</li>
			</security:authorize>
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/stations">Stations</a>
			</li>
			<security:authorize access="isAuthenticated()">
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/buy_ticket">Buy tickets</a>
			</li>
			</security:authorize>
			<security:authorize access="isAnonymous()">
				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
				</li>
			</security:authorize>
			<security:authorize access="isAuthenticated()">
				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/account">Account</a>
				</li>
			</security:authorize>
		</ul>
	</div>
</nav>
