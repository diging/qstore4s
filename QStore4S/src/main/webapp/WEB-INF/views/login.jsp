<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>QStore4S</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>

<body>
<script>
$(document).ready(function() {
	$("input[type=submit]").button().click(function(event) {

	});
});
</script>
<span onload='document.f.j_username.focus();'>
    <c:if test="${not empty successmsg }">
        <div class="ui-state-success-text">
            ${ successmsg }
        </div>
    </c:if>
	<c:if test="${not empty error}">
		<div class="ui-state-error-text">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
	
	 <div class="container">    
    	<form name='f' action="<c:url value='/j_spring_security_check' />"
		method='POST'>
		<table>
		  <h2 class="form-signin-heading">Login </h2>
			<tr>
				<td><spring:message code="label.user" />:</td>
				<td><input type='text' class="form-control" placeholder="Username" name='j_username' value='' required autofocus >
				</td>
			</tr>
			<tr>
				<td><spring:message code="label.password" />:</td>
				<td><input type='password' name='j_password' class="form-control" placeholder="Password" required autofocus/>
				</td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit" value="Login" />
				</td>
			</tr>
		</table>
	</form>
    </div> <!-- /container -->
</span>
</body>
</html>
