<jsp:include page="template-head.jsp" />

<form method="post" class="form-horizontal" action="oj.do" name ="frm">
        <h2>Code:</h2>
        <textarea class="input-block-level" rows="20" name="code">${form.code}</textarea>
        <p> </p>
        <button class="btn btn-large btn-primary" type="submit" onclick="run()">Run</button>
        <p> </p>
        <textarea class="input-block-level" rows="10" name="result">${result}</textarea>
		<jsp:include page="error-list.jsp" />
</form>

<jsp:include page="template-bottom.jsp" />