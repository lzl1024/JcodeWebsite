<jsp:include page="template-head.jsp" />

<div class="row-fluid">
<jsp:include page="template-navprofile.jsp" />

<div class="span9 alert alert-block" style="word-wrap: break-word;  
          word-break: normal; ">

        <div class="span8">
        <div ><img src="image.do?email=${profile.email}" style="width:250px;height:250px;" /></div>  
        <br>
        <table class="table">   
        <tbody>  
          <tr>  
            <td>User Name</td>  
            <td>${profile.realName}</td>  
          </tr>  
          <tr>  
            <td>Email Addr</td>  
            <td>${profile.email}</td>   
          </tr>  
          <tr>  
            <td>Status</td>  
            <td>${profile.status}</td>  
          </tr>
          <tr>  
            <td>Occupation</td>  
            <td>${profile.occupation}</td>  
          </tr> 
          <tr>  
            <td>Interest of Area</td>  
            <td>${profile.interest}</td>  
          </tr>
          <tr>  
            <td>Introduction</td>  
            <td>${profile.introduction}</td>  
          </tr>
        </tbody>  
      </table>  
        
        </div>
  
</div>
</div>

<%@ page import="databeans.User" 
		 import="databeans.Profile"%>
<% 
	User user = (User)session.getAttribute("user");
	Profile profile = (Profile)request.getAttribute("profile");
	if(user.getEmail().equals(profile.getEmail())) {
		%>
		<script>
			$(document).ready(function() {
			$("#profile").addClass("active");
			$("#myprofile").addClass("active");
		});
		</script>
		<%
	} else {
		%>
		<script>
			$(document).ready(function() {
			$("#profile").addClass("active");
		});
		</script>
		<%
	}
	
 
%>



<jsp:include page="template-bottom.jsp" />