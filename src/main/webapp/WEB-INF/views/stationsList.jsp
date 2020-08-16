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
		<title>Stations</title>
	</head>
	<body>
		<%@include file="header.jsp" %>
		<div class="container-fluid mt-3">
			<h2>Stations</h2>
			<div class="table">
				<table>
					<tr>
						<th>Station name</th>
					</tr>
					<c:forEach var="tempStation" items="${stations}">
						<tr>
							<td>
								<a href="${pageContext.request.contextPath}/stations/${tempStation.id}"> ${tempStation.name} </a>
							</td>
							<security:authorize access="hasAuthority('ADMIN')">
								<td>
									<a href="<c:url value="/stations/editStation/${tempStation.id}"/>">Update
										station</a>
								</td>
								<td>
									<a href="<c:url value="/stations/deleteStation/${tempStation.id}"/>">Delete
										station</a>
								</td>
							</security:authorize>
						</tr>
					</c:forEach>
				</table>
			</div>
			<security:authorize access="hasAuthority('ADMIN')">
				<button type="button" class="btn btn-light">
					<a href="${pageContext.request.contextPath}/stations/add">Add new station</a>
				</button>
			</security:authorize>
		</div>
		<%@include file="footer.jsp" %>
	</body>
</html>