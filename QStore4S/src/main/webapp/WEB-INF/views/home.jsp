<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--
Design by TEMPLATED
http://templated.co
Released for free under the Creative Commons Attribution License

Name       : FronzenAge
Description: A two-column, fixed-width template suitable for business sites and blogs.
Version    : 1.0
Released   : 20071108

-->
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
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="/stats">Statistics<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Page 1-1</a></li>
          </ul>
        </li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="/qstore4s/logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav>

<!-- start page -->
<div id="page">
	<!-- start content -->
		<div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h1 class="title"><img src="<%=request.getContextPath()%>/resources/imgs/Logo1.png" width="100px" style="vertical-align: middle;">QStore4S is Up!</h1>
                <p class="lead">Hey ${username}, you did it! QStore4S is up and running</p>
            </div>
        </div>
        <!-- /.row -->
    </div>	
	<!-- end content -->
</div>
<!-- end page -->
</body>
</html>
