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
  <script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.5/d3.min.js"></script>
  <link rel="stylesheet" src="${pageContext.servletContext.contextPath}/resources/css/app.css">
  <style>
	.node {
  	stroke: #fff;
  	stroke-width: 1.5px;
	}
	.link {
  	stroke: #999;
  	stroke-opacity: .6;
	}
	.bar {
    display: inline-block;
    width: 20px;
    height: 75px;   /* We'll override this later */
    background-color: teal;
	}
  </style>
</head>

<body>
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
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="/qstore4s/auth/welcome">Home</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="<c:url value="/j_spring_security_logout" />"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
<script type="text/javascript">
	var dataset = [ 5, 10, 15, 20, 25 ];
	var h = 50
	var svg = d3.select("body")
				.append("svg")
				.attr("width", 500)
				.attr("height", 50);
				
	var circles = svg.selectAll("circle")
					  .data(dataset)
					  .enter()
					  .append("circle");
	
	circles.attr("cx", function(d, i){
        return (i * 50) + 25;
    })
   .attr("cy", h/2)
   .attr("r", function(d) {
        return d;
   }).attr("fill", "yellow")
   .attr("stroke", "orange")
   .attr("stroke-width", function(d) {
       return d/2;
   });
   var numbers = [1,2,3,4,5,6]	
	for (var i = 0; i < numbers.length; i++) {
	    console.log(numbers[i]);  //Print value to console
	}
   
   <script src="//d3js.org/d3.v3.js" charset="utf-8"></script>    
   <script src="//cdn.jsdelivr.net/filesaver.js/0.1/FileSaver.min.js"></script>
   <script src="${pageContext.servletContext.contextPath}/resources/js/graph-creator.js"></script>
</script>
</body>
</html>
