<%@ page import="java.time.LocalDate" %>
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
		<title>Error</title>
	</head>

	<body>
		<h3>Please enter the correct details</h3>
		<table>
			<tr>
				<td><a href="${pageContext.request.contextPath}/trains">Retry</a></td>
			</tr>
		</table>
	</body>
	<footer class="navbar fixed-bottom">
		<div class="footer-copyright text-center py-3">© <%= LocalDate.now().getYear() %> Copyright: Gluegun Ltd.
		</div>
	</footer>
</html>