<jsp:include page="template-head.jsp" />

<form method="post" class="form-signin" action="register.do">
        <h2 class="form-signin-heading">Please Register</h2>
        <input type="hidden" name="use" value="0"/>
        <input type="text" class="input-block-level" placeholder="Email address" name="email" value="${form.email}">
        <input type="text" class="input-block-level" placeholder="User Name" name="userName" value="${form.userName}">
        <input type="password" class="input-block-level" placeholder="Password" name="password" value=""/>
        <input type="password" class="input-block-level" placeholder="Comfirm Password" name="confirm" value=""/>
        <button class="btn btn-large btn-primary" type="submit">Register</button>   
		<jsp:include page="error-list.jsp" />
</form>

<jsp:include page="template-bottom.jsp" />