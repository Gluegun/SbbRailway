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
		<title>Edit station ${station.name}</title>
	</head>
	<body>
		<%@include file="header.jsp" %>
		<div class="container-fluid mt-3">
			<div class="col-sm-4">
				<c:url var="action" value="/stations/updateStation"/>
				<form:form method="post" action="${action}" modelAttribute="stationDto">
					<table class="table">
						<tr>
							<td>
								<form:hidden path="id" value="${id}"/>
								<form:label path="name">Station name</form:label></td>
							<td><form:input onfocus="this.value=''" path="name" placeholder="${station.name}"
							                value="${station.name}"/></td>
						</tr>
						<tr>
							<td>
								<button class="btn btn-primary" type="submit">Update station</button>
							</td>
						</tr>
					</table>
				</form:form>
			</div>

			<div class="col-sm-4">
				<form:form action="${pageContext.request.contextPath}/stations/${id}/addTrain" method="get"
				           modelAttribute="stationDto">
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<label class="input-group-text" for="inputGroupSelect12">Trains</label>
						</div>
						<select class="custom-select" id="inputGroupSelect12" name="train">
							<c:forEach var="tempTrain" items="${allTrains}">
								<option selected>${tempTrain.trainNumber}</option>
							</c:forEach>
						</select>
					</div>
					<input type="hidden" name="station" value="${schedule.get(0).station.name}">
					Departure Time
					<label>
						<input type="time" name="departureTime" min="00:00" max="23:59" value="00:00"/>
					</label>
					Arrival Time
					<label>
						<input type="time" name="arrivalTime" min="00:00" max="23:59" value="00:00"/>
					</label>
					<br>
					<button type="submit" class="btn btn-primary">Add train</button>
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
