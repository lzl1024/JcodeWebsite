<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="template-head.jsp" />

<script>
	function post(editor1,editor2,form) {
	      var startCode = editor1.getValue(); // get start code from editor1
	      var testCode = editor2.getValue();  // get test code from editor2
	      form.startCode.value = startCode;
	      form.testCode.value = testCode;
	}
</script>
<div class="row-fluid">
	<jsp:include page="template-problem-nav.jsp" />

	<div class="span9">

		<c:choose>
			<c:when test="${edit == 1}">
				<form method="post" class="form-horizontal" action="editproblem.do">
				<input type="hidden" name="id" value="${id}"/>
				<h2> Edit Problem</h2>
			</c:when>
    		<c:otherwise>
				<form method="post" class="form-horizontal" action="postProblem.do">
				<Post new Problem>
			</c:otherwise>
		</c:choose>
		
		<h3>Title</h3> 
		<input type="text" class="input-block-level" placeholder="Title" name="title" value="${postForm.title}">
		<p></p>
		<h3>Description</h3>
		<textarea class="input-block-level" rows="5" placeholder="Descriptin" name="content">${postForm.content}</textarea>
		<p></p>
		<h3>Start Code</h3>
		<input type="hidden" id="startCode" name="startCode" value="${postForm.startCode}">

		<div id="editor-mask">
			<div id="editor-container">
				<div id="editor1" style="position: absolute">${postForm.startCode}</div>
				<script>
					var editor1 = ace.edit("editor1");
					editor1.setTheme("ace/theme/XCode");
					editor1.getSession().setMode("ace/mode/java");
					editor1.getSession().setUseWrapMode(true);
					editor1.setShowPrintMargin(false);
					document.getElementById('editor1').style.fontSize = '14px';
				</script>
			</div>
		</div>
		
		<p></p>
		<h3>Test Code</h3>
		<input type="hidden" id="testCode" name="testCode" value="${postForm.testCode}">
		<p></p>
		
		<div id="editor-mask">
			<div id="editor-container">
				<div id="editor2" style="position: absolute">${postForm.testCode}</div>
				<script>
					var editor2 = ace.edit("editor2");
					editor2.setTheme("ace/theme/XCode");
					editor2.getSession().setMode("ace/mode/java");
					editor2.getSession().setUseWrapMode(true);
					editor2.setShowPrintMargin(false);
					document.getElementById('editor2').style.fontSize = '14px';
				</script>
			</div>
		</div>


		<h3>Reference Result</h3>
		<textarea class="input-block-level" rows="5" placeholder="Reference Result" name="referRes">${postForm.referRes}</textarea>
		<p></p>
		<button class="btn btn-large btn-primary" type="submit" onmousedown="post(editor1, editor2, this.form)">Submit</button>
		<jsp:include page="error-list.jsp" />
		</form>
	</div>
	<!-- span -->
</div>

<script>
	$(document).ready(function() {
		$("#oj").addClass("active");
		$("#postproblem").addClass("active");
	});
</script>

<jsp:include page="template-bottom.jsp" />