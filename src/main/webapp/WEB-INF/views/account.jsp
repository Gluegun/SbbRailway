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
		<div class="user ml-3">
			<security:authorize access="hasAnyAuthority('ADMIN', 'USER')">
				<hr>
				<h4 class="text-body">Hello, ${user.firstName} ${user.lastName}</h4>
				<security:authorize access="hasAuthority('ADMIN')">
					<h6 class="mb-2">Role: <security:authentication property="principal.authorities"/></h6>
				</security:authorize>

				<div id="accordion">
					<div class="card">
						<div class="card-header" id="headingOne">
							<h5 class="mb-0">
								<button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseOne"
								        aria-expanded="true" aria-controls="collapseOne">
									Посмотреть билеты
								</button>
							</h5>
						</div>
						<div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
							<div class="card-body">
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
												<a href="<c:url value="/deleteTicket/${ticket.id}"/>">X</a>
											</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</div>
				</div>
			</security:authorize>
			<div class="contentContainer ml-2 mt-2">
				<security:authorize access="isAuthenticated()">
					<form:form action="${pageContext.request.contextPath}/logout" method="post">
						<input type="submit" value="Logout"/>
					</form:form>
				</security:authorize>
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
