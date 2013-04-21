<jsp:include page="template-head.jsp" />

<div class="row-fluid">
<jsp:include page="template-navprofile.jsp" />

<div class="span9 alert alert-block">

        <div class="span8">
        <div ><img src="image.do?email=${profile.email}" style="width:250px;height:250px;" /></div>  
         <p></p>
        <h4>User Name:      &nbsp &nbsp ${profile.realName}</h4>
        <p></p>      
        <h4>Email Addr:    &nbsp &nbsp  ${profile.email}</h4>
        <p></p>      
        <h4>Status: 	&nbsp &nbsp${profile.status}</h4>
        <p></p>      
        <h4>Occupation:     &nbsp &nbsp ${profile.occupation}</h4>
        <p></p>      
        <h4>Interest of Area: &nbsp &nbsp${profile.interest}</h4>
        <p></p>      
        <h4>Introduction:  &nbsp &nbsp  ${profile.introduction}</h4>
        </div>

        <div class="span5">
        
        
        </div>   
</div>
</div>

<script>
	$(document).ready(function() {
		$("#profile").addClass("active");
		$("#myprofile").addClass("active");
	});
</script>

<jsp:include page="template-bottom.jsp" />