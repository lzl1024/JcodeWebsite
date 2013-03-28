<jsp:include page="template-head.jsp" />

<form method="post" class="form-signin" action="change-pwd.do">
        <h2 class="form-signin-heading">Change Password</h2>
        <input type="password" class="input-block-level" placeholder="Old Password" name="oldPassword" value=""/>
        <input type="password" class="input-block-level" placeholder="Password" name="newPassword" value=""/>
        <input type="password" class="input-block-level" placeholder="Confirm Password" name="confirmPassword" value=""/>
        <button class="btn btn-large btn-primary" type="submit">Confirm</button>
		<jsp:include page="error-list.jsp" />
</form>

<jsp:include page="template-bottom.jsp" />