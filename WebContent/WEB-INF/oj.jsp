<jsp:include page="template-head.jsp" />

<div class="row-fluid">
	<jsp:include page="template-problem-nav.jsp" />

<div class="span9 alert-info alert-block">
        <p><h2 class="text-center">${problem.title}</h2>
        <hr>

        <div>
        <p>${problem.readableCon} </p>
        </div>
<hr>  
&nbsp &nbsp<strong>Accepted(${problem.accept})</strong>
&nbsp &nbsp<strong>Denied(${problem.deny})</strong>
<hr>    
</div>
<div class="span9 pull-right alert alert-success">   
<h3 class="form-signin-heading">Comments</h3> 

<% 	databeans.PComment[] comments = (databeans.PComment[])request.getAttribute("commentlist");
	int begin = 1;
	if (comments == null || comments.length == 0){%>
	There is no comments right now.
	<hr>
	<%}else{
		begin = (Integer)request.getAttribute("begin");
        for (int i=(begin-1)*10; i<begin*10 && i < comments.length; i++) {%>

		<h3 class="badge"><%=i+1%></h3>
		<h3 class="label">Posted By: <%=comments[i].getUser()%></h3>
    	<h3 class="label pull-right"> <%=comments[i].getDate()%></h3>
   		<p><%=comments[i].getReadableCon()%></p>
   		<hr>
	<%}}%>
	<div class="pagination pagination-centered">
		<ul>
		<li><a href="oj.do?begin=<%= begin >1 ? begin-1:1%>&id=${problem.id}">Prev</a></li>
		<%  int k = (comments.length-1)/10+1;
        	for (Integer i= 1; i<=k; i++)
        	if (i!=begin){%>
        		<li><a href="oj.do?begin=<%=i%>&id=${problem.id}"><%=i%></a></li>
        	<%} else{%>
        		<li class="disabled"><a href="oj.do?begin=<%=i%>&id=${problem.id}"><%=i%></a></li>
        	<%}%>
        <li><a href="oj.do?begin=<%= begin<k ? begin+1:k%>&id=${problem.id}">Next</a></li>
        </ul>
	</div>
	       
<hr>                
<form method="post" class="form-horizontal" action="commentproblem.do">
        <h3 class="form-signin-heading">Write your Comment</h3>
        <input type="hidden" name="problemid" value="${problem.id}"/>
        <textarea class="input-block-level" rows="5" placeholder="Write your Comment" name="content"></textarea>
        <p> </p>
        <button class="btn btn-large btn-primary" type="submit">Submit</button>
</form>

</div>
		<jsp:include page="error-list.jsp" />

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


