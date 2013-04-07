<jsp:include page="template-head.jsp" />

<div class="row-fluid">
	<jsp:include page="template-problem-nav.jsp" />

<div class="span9 alert-info alert-block">
        <p><h2 class="text-center">${problem.title}</h2>
        <hr>

        <div>
        <p>${problem.readableCon} </p>
        </div>
<hr>  <hr>    
</div>


</div>
</div>		<!-- let the container div ends here -->

<div id="lower">

	<div id="bar">Click Me to Show/Hide JCode Coding Panel</div>

	<div id="panel">
		<div id="editor-mask">
			<div id="editor-container">
				<div id="editor" style="position: absolute">${ojForm.code}</div>
				<script>
                    		var editor = ace.edit("editor");
                    		editor.setTheme("ace/theme/monokai");
                    		editor.getSession().setMode("ace/mode/java");
                    		editor.getSession().setUseWrapMode(true);
                    		editor.setShowPrintMargin(false);
                    		document.getElementById('editor').style.fontSize='14px';
                		</script>
			</div>
		</div>
		<form method="post" class="form-horizontal"
			action="oj.do?id=${problem.id}" name="frm">
			<input type="hidden" name="problemId" value="${problem.id}">
			<input type="hidden" name="switcher" id="switcher" value="${ojForm.switcher}"> 
			<input type="hidden" id="code" name="code" value="${ojForm.code}">
			<div style="text-align: center">
				<button class="btn btn-large btn-primary" type="submit" name="submit" value="run" onmousedown="run(editor,this.form)" style="margin-right: 50px">Run</button>
				<button class="btn btn-large btn-info" type="submit" name="submit" value="verify" onmousedown="run(editor,this.form)" style="margin-left: 50px">Verify</button>
			</div>
			<textarea class="input-block-level" rows="5" name="result" readonly="yes" style="color: white; background-color: #272822;">${result}</textarea>
		</form>
	</div>

</div>

<script>
        	if($("#switcher").val() == "1") {
        		$("#panel").slideToggle(0);
        	}
        		
</script>


