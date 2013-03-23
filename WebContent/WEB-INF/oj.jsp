<jsp:include page="template-head.jsp" />

<script>
function run(){
	frm.action = "oj.do";
}
function reg() {	
	frm.action = "register.do";
}
</script>

<form method="post" class="form-signin" action="" name ="frm">
        <h2 class="form-signin-heading">Please sign in</h2>
        <textarea cols="80" rows="10" name="code">${form.code}</textarea>
        <input type="hidden" name="use" value="1"/>
        <button class="btn btn-large btn-primary" type="submit" onclick="run()">Run</button>
        <textarea cols="80" rows="5" name="result">${form.result}</textarea>
		<jsp:include page="error-list.jsp" />
</form>

<jsp:include page="template-bottom.jsp" />