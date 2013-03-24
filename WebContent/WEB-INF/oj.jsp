<jsp:include page="template-head.jsp" />

<script>
function run(){
	frm.action = "oj.do";
}
function reg() {	
	frm.action = "register.do";
}
</script>

<form method="post" class="form-horizontal" action="" name ="frm">
        <h2>Code:</h2>
        <textarea class="input-block-level" rows="20" name="code">${form.code}</textarea>
        <input type="hidden" name="use" value="1"/>
        <button class="btn btn-large btn-primary" type="submit" onclick="run()">Run</button>
        <textarea class="input-block-level" rows="10" name="result">${result}</textarea>
		<jsp:include page="error-list.jsp" />
</form>

<jsp:include page="template-bottom.jsp" />