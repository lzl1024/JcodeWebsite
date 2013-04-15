<jsp:include page="template-head.jsp" />

 
<script>

$(document).ready(
	function() {
		$('#registerPageSubmit').click(
				function() {
					$.ajax({
						type : 'POST',
						url :  'register.do',
						data : 'email='
								+ $('#registerPageEmail').val()
								+ '&userName='
								+ $('#registerPageUserName').val()
								+ '&password='
								+ $('#registerPagePassword').val()
								+ '&confirm='
								+ $('#registerPageConPassword').val(),
						success : function(msg) {
							msg = $.trim(msg);
							if (msg == "success") {
								window.location.href = 'manage.do';
							} else {
								$('#registerPageMsg').html(msg);
							}
						}
					});
				
	});
});
</script>

 
<form class="form-signin">
        <h2 class="form-signin-heading">Please register</h2>
        <label class="control-label">Email:</label>
        <input type="text" class="input-block-level" placeholder="Email address" name="email" id="registerPageEmail" />
        <label class="control-label">User Name:</label>
        <input type="text" class="input-block-level" placeholder="User Name" name="userName" id="registerPageUserName" />
        <label class="control-label">Password:</label>
        <input type="password" class="input-block-level" placeholder="Password" name="password" id="registerPagePassword" />
        <label class="control-label">Confirm Password:</label>
        <input type="password" class="input-block-level" placeholder="Confirm Password" name="confirm" id="registerPageConPassword" />
        <button class="btn btn-large btn-primary " type="button" id="registerPageSubmit">Register</button>
		<div id="registerPageMsg"></div>
</form>



<jsp:include page="template-bottom.jsp" />