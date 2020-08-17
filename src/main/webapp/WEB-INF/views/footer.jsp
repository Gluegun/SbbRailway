<%@ page import="java.time.LocalDate" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<footer class="navbar fixed-bottom">
	<div class="footer-copyright text-center py-3">© <%= LocalDate.now().getYear() %> Copyright: WhereFrom Railways
		Ltd.
	</div>
</footer>