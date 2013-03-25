<jsp:include page="template-head.jsp" />

<div class="row-fluid">
<jsp:include page="template-navlist.jsp" />

<div class="span9">
<form method="post" class="form-horizontal" action="blog.do">
        <h2 class="form-signin-heading">Write your blog</h2>
        <input type="text" class="input-block-level" placeholder="Title" name="title" value="${form.title}">
        <p> </p>
        <textarea class="input-block-level" rows="10" placeholder="Content" name="content">${form.content}</textarea>
        <p> </p>
        <button class="btn btn-large btn-primary" type="submit">Submit</button>
		<jsp:include page="error-list.jsp" />
</form>
</div> <!-- span -->
</div>

<jsp:include page="template-bottom.jsp" />