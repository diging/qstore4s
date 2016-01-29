<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>QStore4S</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="../resources/css/style.css">
</head>

<body>
	<div class="wrapper">
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#myNavbar">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">QStore4s</a>
				</div>
				<div class="collapse navbar-collapse" id="myNavbar">
					<ul class="nav navbar-nav">
						<li class="active"><a href="/qstore4s/auth/welcome">Home</a></li>
						<!--  
          <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="/stats">Statistics<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Page 1-1</a></li>
          </ul>
        </li>
        -->
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="<c:url value="/j_spring_security_logout" />"><span
								class="glyphicon glyphicon-log-in"></span> Logout</a></li>
					</ul>
				</div>
			</div>
		</nav>
		<span onload='document.f.j_username.focus();'>
			<div class="container">
				<div>
					<ul>
						<li>Appellation events: X</li>
						<li>Relational Events: Y</li>
						<li>Nodes : Z</li>
					</ul>
				</div>

			</div> <!-- /container -->
		</span>

		<div class="push"></div>
	</div>
	<div class="footer">
		<p class="legal">
			<c:set var="PR" value="${pullrequest}" />
			Version: ${buildNumber}
			<c:if test="${not empty PR}">, Pull Request: ${pullrequest}</c:if>
		</p>
	</div>

</body>

</html>
