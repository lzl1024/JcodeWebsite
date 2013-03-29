<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
<html lang="en">
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="${pageContext.request.contextPath}/patterns/css/bootstrap.min.css" rel="stylesheet" media="screen"/>
	
	<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
	
	<title>JCode-- For Every Technical Interviewer</title>
	
    <!-- CSS -->
    <link href="${pageContext.request.contextPath}/patterns/css/bootstrap.css" rel="stylesheet"/>
    
    <style type="text/css">
    	
      #upper {
        position: relative;
        margin-bottom: 50px;
    }

    #lower {
        width: 90%;
        position: fixed;
        bottom: 0;
    }

    #bar {
        padding: 5px;
        font-size:25px;
        text-align:center;
        color: #ffffff;
  		text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
  		background-color: #006dcc;
    }

    #panel {
        display: none;
        /*    height: 100px;*/
        background-color: #BDBDBD;
        padding: 10px;
    }
	   
      /* Sticky footer styles
      -------------------------------------------------- */

      html,
      body {
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
      #push,
      #footer {
        height: 60px;
      }
      #footer {
        background-color: #f5f5f5;
      }

      /* Lastly, apply responsive CSS fixes as necessary */
      @media (max-width: 767px) {
        #footer {
          margin-left: -20px;
          margin-right: -20px;
          padding-left: 20px;
          padding-right: 20px;
        }
      }


      #wrap > .container {
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
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }

    </style>
    <link href="${pageContext.request.contextPath}/patterns/css/bootstrap-responsive.css" rel="stylesheet"/>

    <!--touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${pageContext.request.contextPath}/patterns/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${pageContext.request.contextPath}/patterns/ico/apple-touch-icon-114-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${pageContext.request.contextPath}/patterns/ico/apple-touch-icon-72-precomposed.png">
                    <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/patterns/ico/apple-touch-icon-57-precomposed.png">
                                   <link rel="shortcut icon" href="${pageContext.request.contextPath}/patterns/ico/favicon.png">
 
 	<script>

    $(document).ready(function(){
        $("#bar").click(function() {
        	if($("#switcher").val() == "0") {
        		$("#switcher").val("1");
        		$("#code").val("WORI 1");
        	} else {
        		$("#switcher").val("0");
        		$("#code").val("WORI 0");
        	}
            $("#panel").slideToggle("slow");
   
});
    });
    </script>
 
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
          	<a class="brand" href="#">JCode</a>
          	<div class="nav-collapse collapse">
            	<ul class="nav">
              		<li class="active"><a href="manage.do">Home</a></li>
              		<li><a href="allblog.do">Blog</a></li>
              		<li><a href="oj.do">OnlineJudge</a></li>
              	</ul>
              	<ul class = "nav pull-right">
              	
					<c:choose>
						<c:when test="${ (empty user) }">
							<li><a href="login.do">Login</a></li>
							<li><a href="register.do">Register</a></li>
						</c:when>
              			<c:otherwise>
              			<li class="dropdown">
                  		<a href="#" class="dropdown-toggle" data-toggle="dropdown">Profile <b class="caret"></b></a>
                  		<ul class="dropdown-menu">
                   	 		<li><a href="#">Edit Profile</a></li>
                    		<li><a href="#">Change Password</a></li>
                    		<li class="divider"></li>
                    		<li class="nav-header">Your Statistic</li>
                    		<li><a href="#">Top Coder</a></li>
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

    	<div class="container">

<div id="upper">
            <p>Problem Set 1</p>

        </div>
    
        <div id="lower">

            <div id="bar">CLick Me to Show/Hide JCode Coding Pannel</div>

            <div id="panel">
                <form method="post" class="form-horizontal" action="oj.do" name ="frm">
                    <input type="hidden" name="switcher" id="switcher" value="${form.switcher}">
                    <textarea class="input-block-level" rows="10" name="code">${form.code}</textarea>
                    <button class="btn btn-large btn-primary" type="submit" onclick="run()">Run</button>
                    <textarea class="input-block-level" rows="5" name="result">${result}</textarea>
                </form>
            </div>

        </div>
        
        <script>
        	if($("#switcher").val() == "1") {
        		$("#panel").slideToggle(0);
        	}
        		
        </script>

<jsp:include page="template-bottom.jsp" />