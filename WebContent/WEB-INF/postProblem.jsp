<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-head.jsp" />

<div class="row-fluid">
<jsp:include page="template-navlist.jsp" />

<div class="span9">
<c:choose>
	<c:when test="${edit == 1}">
	<form method="post" class="form-horizontal" action="editblog.do">
	<input type="hidden" name="id" value="${id}"/>
	</c:when>
    <c:otherwise>
	<form method="post" class="form-horizontal" action="postProblem.do">
	</c:otherwise>
</c:choose>
        <h2 class="form-signin-heading">Post a new problem</h2>
        <input type="text" class="input-block-level" placeholder="Title" name="title" value="${form.title}">
        <p> </p>
        <textarea class="input-block-level" rows="5" placeholder="Content" name="content">${form.content}</textarea>
        <p> </p>
        <textarea class="input-block-level" rows="5" placeholder="StartCode" name="startCode">${form.startCode}</textarea>
        <p> </p>
        <textarea class="input-block-level" rows="5" placeholder="TestCode" name="testCode">${form.testCode}</textarea>
        <p> </p>
        <textarea class="input-block-level" rows="5" placeholder="ReferRes" name="referRes">${form.referRes}</textarea>
        <p> </p>
        <button class="btn btn-large btn-primary" type="submit">Submit</button>
		<jsp:include page="error-list.jsp" />
</form>
</div> <!-- span -->
</div>

<jsp:include page="template-bottom.jsp" />