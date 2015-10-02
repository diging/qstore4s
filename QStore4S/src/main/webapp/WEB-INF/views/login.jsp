<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

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

<!-- Navigation bar start-->
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">QStore4s</a>
    </div>
  </div>
</nav>
<!-- Navigation bar end-->

<!-- start page -->
<div id="page">
	<!-- start content -->
		<div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h1 class="title"><img src="<%=request.getContextPath()%>/resources/imgs/Logo1.png" width="100px" style="vertical-align: middle;">QStore4S is Up!</h1>
                <p class="lead">Hey there, you did it! QStore4S is up and running</p>
            </div>
        </div>
        <!-- /.row -->
       </div>	  
	<!-- end content -->
</div>
<!-- end page -->

<span onload='document.f.j_username.focus();'>
<div class="container" align = "center">
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
  <form class="form-inline" role="form" name='f' action="<c:url value='/j_spring_security_check'/>" method='POST'>
    <div class="form-group">
     <h2>Login</h2>
      <input type='text' class="form-control" placeholder="<spring:message code="label.user"/>" name='j_username' value='' required autofocus >
    <br></br>
      <label class="sr-only"><spring:message code="label.password" />:</label>
      <input type="password" class="form-control" id="pwd" placeholder="<spring:message code="label.password"/>" name='j_password'>
    <br></br>
    <button type="submit" class="btn btn-primary btn-block" >Submit</button>
    <br></br>
    </div>
  </form>
</div> <!-- /container -->
</span>
</body>
</html>
