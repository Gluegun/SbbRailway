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
		<title>Trains</title>
	</head>
	<body>
		<%@include file="header.jsp" %>
		<div class="container-fluid mt-3">
			<h2>Trains</h2>
			<div class="table">
				<table>
					<tr>
						<th>Train number</th>
						<th>Seats amount</th>
						<th>Buy ticket</th>
						<th>Comments</th>
						<security:authorize access="hasAuthority('ADMIN')">
							<th>Action</th>
						</security:authorize>
					</tr>
					<c:forEach items="${trains}" var="tempTrain">
						<tr>
							<td>
								<a href="${pageContext.request.contextPath}/trains/${tempTrain.id}">${tempTrain.trainNumber}</a>
							</td>
							<td>${tempTrain.seatsAmount} </td>
							<c:if test="${ticketsPassenger[tempTrain.id] eq true }">
								<td>
									<a href="${pageContext.request.contextPath}/trains/buy/${tempTrain.id}?fromStation=${fromStation}"
									   onclick="return false">Buy ticket</a>
								</td>
							</c:if>
							<c:if test="${ticketsPassenger[tempTrain.id] != true }">
								<c:if test="${fromStation == null }">
									<td>
										<a href="${pageContext.request.contextPath}/buy_ticket">Buy ticket</a>
									</td>
								</c:if>

								<c:if test="${fromStation != null }">
									<td>
										<a href="${pageContext.request.contextPath}/trains/buy/${tempTrain.id}?fromStation=${fromStation}">Buy
											ticket</a>
									</td>
								</c:if>
							</c:if>
							<security:authorize access="hasAuthority('ADMIN')">
								<td>
									<a href="<c:url value="/trains/edit/${tempTrain.id}"/>">Update train</a>
								</td>
								<td>
									<a href="<c:url value="/trains/delete/${tempTrain.id}"/>">Delete train</a>
								</td>
							</security:authorize>
						</tr>
					</c:forEach>
				</table>
				<security:authorize access="hasAuthority('ADMIN')">
					<button type="button" class="btn btn-light">
						<a href="${pageContext.request.contextPath}/trains/add">Add new train</a>
					</button>
				</security:authorize>
			</div>
		</div>
		<%@include file="footer.jsp" %>
	</body>
</html>

				