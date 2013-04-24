<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
<html lang="en">
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="${pageContext.request.contextPath}/patterns/css/bootstrap.min.css" rel="stylesheet" media="screen"/>
	
	<script src="http://d1n0x3qji82z53.cloudfront.net/src-min-noconflict/ace.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/patterns/js/jquery.js"></script>
	
	
	<title>JCode-- For Every Technical Interviewer</title>
	
    <!-- CSS -->
    <link href="${pageContext.request.contextPath}/patterns/css/bootstrap.css" rel="stylesheet"/>
    
    <style type="text/css">
body {
	background-color: #EBEAEA;
}

.carousel-caption h1,.carousel-caption .lead {
	margin: 0;
	line-height: 1.25;
	color: #fff;
	text-shadow: 0 1px 1px rgba(0, 0, 0, .4);
}

#upper {
	position: relative;
	margin-bottom: 50px;
}

#lower {
	position: fixed;
	bottom: 0;
	margin: 0;
	width: 100%;
}

#bar {
	padding: 5px;
	font-size: 20px;
	text-align: center;
	color: #ffffff;
	text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
	background-color: #272f36;
}

#bar:hover {
	padding: 5px;
	font-size: 20px;
	text-align: center;
	color: #ffffff;
	text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
	background-color: #272f36;
	opacity: 0.7;
	filter: alpha(opacity = 70); /* For IE8 and earlier */
}

#panel {
	display: none;
	height: 400px;
	background-color: #BDBDBD;
	padding: 10px;
}

#editor-container {
	position: relative;
	height: 250px;
}

#editor-mask {
	height: 250px;
	overflow-y: hidden;
}

#editor,#editor1,#editor2 {
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
}

/* Sticky footer styles
      -------------------------------------------------- */
html,body {
	height: 100%;
	/* The html and body elements cannot have any padding or margin. */
}

/* Wrapper for page content to push down footer */
#wrap {
	min-height: 100%;
	height: auto !important;
	height: 100%;
	/* Negative indent footer by it's height */
	margin: 0 auto -60px;
}

/* Set the fixed height of the footer here */
#push,#footer {
	height: 60px;
}

#footer {
	background-color: #f5f5f5;
}

/* Lastly, apply responsive CSS fixes as necessary */
@media ( max-width : 767px) {
	#footer {
		margin-left: -20px;
		margin-right: -20px;
		padding-left: 20px;
		padding-right: 20px;
	}
}

#wrap>.container {
	padding-top: 60px;
}

.container .credit {
	margin: 20px 0;
}

code {
	font-size: 80%;
}

.form-signin {
	max-width: 300px;
	padding: 19px 29px 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin input[type="text"],.form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}

.ContentTable {
	margin:0px;padding:0px;
	width:100%;
	box-shadow: 10px 10px 5px #888888;
	border:1px solid #ffffff;
	
	-moz-border-radius-bottomleft:10px;
	-webkit-border-bottom-left-radius:10px;
	border-bottom-left-radius:10px;
	
	-moz-border-radius-bottomright:10px;
	-webkit-border-bottom-right-radius:10px;
	border-bottom-right-radius:10px;
	
	-moz-border-radius-topright:10px;
	-webkit-border-top-right-radius:10px;
	border-top-right-radius:10px;
	
	-moz-border-radius-topleft:10px;
	-webkit-border-top-left-radius:10px;
	border-top-left-radius:10px;
}.ContentTable table{
	width:100%;
	margin:0px;padding:0px;
}.ContentTable tr:last-child td:last-child {
	-moz-border-radius-bottomright:10px;
	-webkit-border-bottom-right-radius:10px;
	border-bottom-right-radius:10px;
}
.ContentTable table tr:first-child td:first-child {
	-moz-border-radius-topleft:10px;
	-webkit-border-top-left-radius:10px;
	border-top-left-radius:10px;
}
.ContentTable table tr:first-child td:last-child {
	-moz-border-radius-topright:10px;
	-webkit-border-top-right-radius:10px;
	border-top-right-radius:10px;
}.ContentTable tr:last-child td:first-child{
	-moz-border-radius-bottomleft:10px;
	-webkit-border-bottom-left-radius:10px;
	border-bottom-left-radius:10px;
}.ContentTable tr:hover td{
	
}
.ContentTable tr:nth-child(odd){ background-color:#cee6ff; }
.ContentTable tr:nth-child(even)    { background-color:#ffffff; }
.ContentTable td{
	vertical-align:middle;
	
	
	border:1px solid #ffffff;
	border-width:0px 1px 1px 0px;
	text-align:center;
	padding:7px;
	font-size:14px;
	font-family:Helvetica;
	font-weight:normal;
	color:#000000;
}.ContentTable tr:last-child td{
	border-width:0px 1px 0px 0px;
}.ContentTable tr td:last-child{
	border-width:0px 0px 1px 0px;
}.ContentTable tr:last-child td:last-child{
	border-width:0px 0px 0px 0px;
}
.ContentTable tr:first-child td{
		background:-o-linear-gradient(bottom, #0088cc 5%, #0088cc 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #0088cc), color-stop(1, #0088cc) );
	background:-moz-linear-gradient( center top, #0088cc 5%, #0088cc 100% );
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr="#0088cc", endColorstr="#0088cc");	background: -o-linear-gradient(top,#0088cc,0088cc);

	background-color:#0088cc;
	border:0px solid #ffffff;
	text-align:center;
	border-width:0px 0px 1px 1px;
	font-size:15px;
	font-family:Helvetica;
	font-weight:bold;
	color:#ffffff;
}
.ContentTable tr:first-child:hover td{
	background:-o-linear-gradient(bottom, #0088cc 5%, #0088cc 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #0088cc), color-stop(1, #0088cc) );
	background:-moz-linear-gradient( center top, #0088cc 5%, #0088cc 100% );
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr="#0088cc", endColorstr="#0088cc");	background: -o-linear-gradient(top,#0088cc,0088cc);

	background-color:#0088cc;
}
.ContentTable tr:first-child td:first-child{
	border-width:0px 0px 1px 0px;
}
.ContentTable tr:first-child td:last-child{
	border-width:0px 0px 1px 1px;
}

</style>
    <link href="${pageContext.request.contextPath}/patterns/css/bootstrap-responsive.css" rel="stylesheet"/>

    <!--touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${pageContext.request.contextPath}/patterns/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${pageContext.request.contextPath}/patterns/ico/apple-touch-icon-114-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${pageContext.request.contextPath}/patterns/ico/apple-touch-icon-72-precomposed.png">
                    <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/patterns/ico/apple-touch-icon-57-precomposed.png">
                                   <link rel="shortcut icon" href="pic/icon.jpeg">
 
 
  </head>

  <body>
	<div id="wrap">
	
    	<div class="navbar navbar-inverse navbar-fixed-top">
    	
      	<div class="navbar-inner">
        	<div class="container">
          	<button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
           	 	<span class="icon-bar"></span>
            	<span class="icon-bar"></span>
            	<span class="icon-bar"></span>
          	</button>
          	<a class="brand" href="manage.do">JCode</a>
          	<div class="nav-collapse collapse">
            	<ul class="nav">
              		<li id="home"><a href="manage.do">Home</a></li>
              		<li id="blog"><a href="allblog.do">Blog</a></li>
              		<li id="oj"><a href="allproblem.do">OnlineJudge</a></li>
              	</ul>
              	<ul class = "nav pull-right">
              	
					<c:choose>
						<c:when test="${ (empty user) }">
							<!--  
							<li><a href="login.do">Login</a></li>
							<li><a href="register.do">Register</a></li>
							-->
							<a href="#myModal" role="button" class="btn" data-toggle="modal">Sign Up</a>
						</c:when>
              			<c:otherwise>
              			<li class="dropdown" id="profile">
                  		<a href="#" class="dropdown-toggle" data-toggle="dropdown">Profile <b class="caret"></b></a>
                  		<ul class="dropdown-menu">
                    		<li class="nav-header">Hi, ${user.userName}</li>
                    		<li class="divider"></li>
                   	 		<li><a href="editprofile.do?edit=1">Edit Profile</a></li>
                    		<li><a href="change-pwd.do">Change Password</a></li>
                    		<li class="divider"></li>
                    		<li class="nav-header">Your Statistic</li>
                    		<li><a href="topcoder.do">Top Coder</a></li>
                    		<li><a href="statistic.do">Sumbit Statistic</a></li>
                  		</ul>
                		</li>
                		<li><a href="logout.do">Logout</a></li>
                		</c:otherwise>
                	</c:choose>
            	</ul>
          	</div><!--/.nav-collapse -->
        	</div>
      	</div>
    	</div>

		<!-- Modal -->
		<div id="myModal" class="modal hide fade" tabindex="-1"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">x</button>
				<h3 id="myModalLabel">Sign Up</h3>
			</div>
			<div class="modal-body">
				<ul class="nav nav-tabs" id="signUpTab">
					<li class="active"><a href="#signin" data-toggle="tab">Sign
							In</a></li>
					<li><a href="#register" data-toggle="tab">Register</a></li>
				</ul>

				<div class="tab-content">
					<div class="tab-pane active" id="signin">
						<form class="form-horizontal" name ="frm">
							<div class="control-group">
								<label class="control-label" for="signinEmail">Email:</label>
								<div class="controls">
									<input type="text" style="min-height: 30px;" placeholder="Email address" name="email" id="signinEmail" value="${loginForm.email}"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="signinPassword">Password:</label>
								<div class="controls">
								    <input type="password" style="min-height: 30px;" placeholder="Password" name="password" id="signinPassword" value=""/>
								
								</div>
							</div>
							<div class="control-group">
								<div class="controls">
									<button type="button" class="btn btn-primary" id="signinSubmit">Sign in</button>
								</div>
							</div>
						</form>
						<div id="signinMsg"></div>
					</div>
					<div class="tab-pane" id="register">
						<form class="form-horizontal" action="register.do" name ="frm">
							<div class="control-group">
								<label class="control-label" for="registerEmail">Email</label>
								<div class="controls" id="checkstatus">
        							<input type="text" style="min-height: 30px;" placeholder="Email address" name="email" id="registerEmail" value="${regForm.email}">
									<div id="status"></div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="registerUserName">User Name</label>
								<div class="controls">
									<input type="text" style="min-height: 30px;" placeholder="User Name" name="userName" id="registerUserName" value="${regForm.userName}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="registerPassword">Password</label>
								<div class="controls">
									<input type="password" style="min-height: 30px;" placeholder="Password" name="password" id="registerPassword" value=""/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="registerConPassword">Conform Password</label>
								<div class="controls">
									<input type="password" style="min-height: 30px;" placeholder="Confirm Password" name="conPassword" id="registerConPassword" value=""/>
								</div>
							</div>
							<div class="control-group">
								<div class="controls">
									<button type="button" class="btn btn-primary" id="registerSubmit">Register</button>
								</div>
							</div>
						</form>
						<div id="registerMsg"></div>
					</div>
				</div>
			</div>
		</div>

		<script>
		$(document).ready(
				function() {
					$('#signinSubmit').click(
							function() {
								$.ajax({
									type : 'POST',
									url :  'login.do',
									data : 'email='
											+ $('#signinEmail').val()
											+ '&password='
											+ $('#signinPassword').val(),
									success : function(msg) {
										msg = $.trim(msg);
										if (msg == "success") {
											window.location.href = 'manage.do';
										} else {
											$('#signinPassword').val("");
											$('#signinMsg').html(msg);
										}
									}
								})
							}), 
					$('#registerSubmit').click(
							
							function() {
								$.ajax({
									type : 'POST',
									url :  'register.do',
									data : 'email='
											+ $('#registerEmail').val()
											+ '&userName='
											+ $('#registerUserName').val()
											+ '&password='
											+ $('#registerPassword').val()
											+ '&confirm='
											+ $('#registerConPassword').val(),
									success : function(msg) {
										
										msg = $.trim(msg);
										if (msg == "success") {
											window.location.href = 'manage.do';
										} else {
											$('#registerPassword').val("");
											$('#registerConPassword').val("");
											$('#registerMsg').html(msg);
										}
									}
								})
							})
				});
		</script>
		
		

		<div class="container">