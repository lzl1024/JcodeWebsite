<jsp:include page="template-head.jsp" />

<div class="row-fluid">
<jsp:include page="template-navprofile.jsp" />

<div class="span10 alert alert-block">

        <div class="span8">
        <img src="image.do?email=${profile.email}"  width="254" height="39"/>
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
<jsp:include page="template-bottom.jsp" />