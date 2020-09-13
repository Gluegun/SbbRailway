<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<link rel="stylesheet"
		      href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
		      integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
		      crossorigin="anonymous">
		<title>Edit train ${train.trainNumber}</title>
	</head>
	<body>
		<%@include file="header.jsp" %>
		<div class="container-fluid mt-3">
			<div class="col-sm-4 mt-3">
				<h5>Edit train</h5>
				<c:url var="action" value="/trains/update"/>
				<form:form method="post" action="${action}" modelAttribute="trainDto">
					<table class="table">
						<tr>
							<td>
								<form:hidden path="id" value="${id}"/>
								<form:label path="trainNumber">Train number</form:label></td>
							<td><form:input onfocus="this.value=''" path="trainNumber"
							                placeholder="${train.trainNumber}" value="${train.trainNumber}"/></td>
						</tr>
						<tr>
							<td><form:label path="seatsAmount">Seats amount</form:label></td>
							<td><form:input onfocus="this.value=''" path="seatsAmount"
							                placeholder="${train.seatsAmount}" value="${train.seatsAmount}"/></td>
						</tr>
						<tr>
							<td>
								<button class="btn btn-primary" type="submit">Update</button>
							</td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
		<div class="container-fluid mt-3">
			<div class="col-sm-4">
				<h5>Add station to route</h5>
				<form:form action="${pageContext.request.contextPath}/trains/${id}/addStation"
				           method="get">
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<label class="input-group-text" for="inputGroupSelect13">Stations</label>
						</div>
						<select class="custom-select" id="inputGroupSelect13" name="station">
							<c:forEach var="tempStation" items="${allStations}">
								<option selected>${tempStation.name}</option>
							</c:forEach>
						</select>
					</div>
					Departure Time <label>
					<input type="time" name="departureTime" min="00:00" max="23:59" value="00:00"/>
				</label>
					<br>
					Arrival Time <label>
					<input type="time" name="arrivalTime" min="00:00" max="23:59" value="00:00">
				</label>
					<br>
					<button type="submit" class="btn btn-primary mt-2">Add station</button>
				</form:form>
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
