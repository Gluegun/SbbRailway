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
		<title>Train</title>
	</head>
	<body>
		<%@include file="header.jsp" %>
		<div class="container-fluid mt-3">
			<table class="table">
				<tr>
					<th>Train number</th>
					<th>Seats amount</th>
					<th>Stations</th>
					<th>Departure time</th>
				</tr>
				<tr>
					<td><c:out value="${train.trainNumber}"/></td>
					<td><c:out value="${train.seatsAmount}"/></td>
					<td>
						<c:forEach var="tempSchedule" items="${schedules}">
							<p><a href="/stations/${tempSchedule.station.id}">${tempSchedule.station.name}</a></p>
						</c:forEach>
					</td>
					<td>
						<c:forEach var="tempSchedule" items="${schedules}">
							<p>
								${tempSchedule.departureTime}
							</p>
						</c:forEach>
					</td>
				</tr>
			</table>
			<security:authorize access="hasAuthority('ADMIN')">
				<div id="accordion">
					<div class="card">
						<div class="card-header" id="headingOne">
							<h5 class="mb-0">
								<button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseOne"
								        aria-expanded="true" aria-controls="collapseOne">
									Список зарегистрированных пассажиров на рейс
								</button>
							</h5>
						</div>
						<div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
							<div class="card-body">
								<c:forEach var="tempPassenger" items="${passengers}">
									<p>
										<a href="/passengers/${tempPassenger.id}"><c:out
												value="${tempPassenger.firstName} ${tempPassenger.lastName}"/></a>
									</p>
								</c:forEach>
							</div>
						</div>
					</div>
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