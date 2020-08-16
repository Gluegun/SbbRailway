<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>Welcome to your account info</title>
		<link rel="stylesheet"
		      href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
		      integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
		      crossorigin="anonymous">
	</head>
	<body>
		<%@include file="header.jsp" %>
		<div class="user">
			<security:authorize access="hasAnyAuthority('ADMIN', 'USER')">
				<hr>
				<h4 class="text-body">Hello, ${user.firstName} ${user.lastName}</h4>
				<security:authorize access="hasAuthority('ADMIN')">
					Role: <security:authentication property="principal.authorities"/>
				</security:authorize>
				<div class="container-fluid mt-3">
					<table class="table">
						<tr>
							<th>Train</th>
							<th>Departure time</th>
							<th>Delete ticket</th>
						</tr>
						<c:forEach var="ticket" items="${tickets}">
							<tr>
								<td>
									<a href="${pageContext.request.contextPath}/trains/${ticket.train.id}">${ticket.train.trainNumber}</a>
								</td>
								<td>
									<p>
										<c:out value="${ticket.departureTime}"/>
									</p>
								</td>
								<td>
									<a href="<c:url value="/deleteTicket/${ticket.id}"/>">Delete ticket</a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</security:authorize>
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
