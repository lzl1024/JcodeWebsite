<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-head.jsp" />

<div class="row-fluid">
<jsp:include page="template-navprofile.jsp" />

<div class="span9 alert alert-block">
	<form method="post" class="form-horizontal" action="editprofile.do" enctype="multipart/form-data">
        <h2 class="form-signin-heading">Edit your profile</h2>
        <input type="file" name="file" value=""/>
        <p> </p>
        <input type="text" class="input-block-level" placeholder="Status" name="status" value="${form.status}">
        <p> </p>
        <input type="text" class="input-block-level" placeholder="Occupation" name="occupation" value="${form.occupation}">
        <p> </p>
        <input type="text" class="input-block-level" placeholder="Interest of Area" name="interest" value="${form.interest}">
        <p> </p>
        <input type="text" class="input-block-level" placeholder="Introduction" name="introduction" value="${form.introduction}">
        <p> </p>
        <button class="btn btn-large btn-primary" type="submit">Upload</button>
		<jsp:include page="error-list.jsp" />
</form>
</div> <!-- span -->
</div>

<jsp:include page="template-bottom.jsp" />