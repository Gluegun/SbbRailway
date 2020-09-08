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
        <title>Buy ticket</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container-fluid mt-3">
            <div class="col-sm-5">
                <form:form action="${pageContext.request.contextPath}/suitableTrainList" method="get">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="inputGroupSelect01">From</label>
                        </div>
                        <select class="custom-select" id="inputGroupSelect01" name="fromStation">
                            <c:forEach var="tempStation" items="${stations}">
                                <option selected>${tempStation.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="inputGroupSelect02">To</label>
                        </div>
                        <select class="custom-select" id="inputGroupSelect02" name="toStation">
                            <c:forEach var="tempStation" items="${stations}">
                                <option selected>${tempStation.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    From <input type="time" name="fromTime"
                                min="00:00" max="23:59" value="00:00"> to <input type="time" name="toTime"
                                                                                 min="00:00" max="23:59" value="23:59">
                    <br>
                    <br>
                    <button type="submit" class="btn btn-primary">Find trains</button>
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
