<jsp:include page="template-head.jsp" />

 
<script>

$(document).ready(
	function() {
		$('#signinPageSubmit').click(
				function() {
					$.ajax({
						type : 'POST',
						url :  'login.do',
						data : 'email='
								+ $('#signinPageEmail').val()
								+ '&password='
								+ $('#signinPagePassword').val(),
						success : function(pageMsg) {
							pageMsg = $.trim(pageMsg);
							if (pageMsg == "success") {
								window.location.href = 'manage.do';
							} else {
								$('#signinPageMsg').html(pageMsg);
							}
						}
					})
	})
}); 
</script>

 
<form class="form-signin">
        <h2 class="form-signin-heading">Please sign in</h2>
		<label class="control-label">Email:</label>
        <input type="text" class="input-block-level" placeholder="Email address" name="email" id="signinPageEmail" />
        <label class="control-label">Password:</label>
        <input type="password" class="input-block-level" placeholder="Password" name="password" id="signinPagePassword" />
        <button class="btn btn-large btn-primary" type="button" id="signinPageSubmit">Sign in</button>
        <a class="btn btn-large btn-primary pull-right" type="button" href="registerPage.do">Register</a>
		<div id="signinPageMsg"></div>
</form>



<jsp:include page="template-bottom.jsp" />