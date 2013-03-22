<jsp:include page="template-head.jsp" />

<script>
<!--
function sign(){
	frm.action = "login.do";
}
function reg() {	
	frm.action = "register.do";
}
</script>

<form method="post" class="form-signin" action="" name ="frm">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="text" class="input-block-level" placeholder="Email address" name="email" value="${form.email}">
        <input type="password" class="input-block-level" placeholder="Password" name="password" value=""/>
        <input type="hidden" name="use" value="1"/>
        <button class="btn btn-large btn-primary" type="submit" onclick="sign()">Sign in</button>
        <button class="btn btn-large btn-primary pull-right" type="submit" onclick="reg()">Register</button>
		<jsp:include page="error-list.jsp" />
</form>

<jsp:include page="template-bottom.jsp" />