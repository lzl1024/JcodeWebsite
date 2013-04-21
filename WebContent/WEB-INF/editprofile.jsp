<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-head.jsp" />

<div class="row-fluid">
<jsp:include page="template-navprofile.jsp" />

<div class="span9 alert alert-block">
	<form method="post" class="form-horizontal" action="editprofile.do" enctype="multipart/form-data">
        <h2 class="form-signin-heading">Edit your profile</h2>
        <input type="file" name="file" value=""/>
        <p> </p>
        <h4>Status: </h4>
        <input type="text" class="input-block-level" placeholder="Status" name="status" value="${form.status}">
        <p> </p>
        <h4>Occupation: </h4>
        <input type="text" class="input-block-level" placeholder="Occupation" name="occupation" value="${form.occupation}">
        <p> </p>
        <h4>Interest of Area: </h4>
        <input type="text" class="input-block-level" placeholder="Interest of Area" name="interest" value="${form.interest}">
        <p> </p>
        <h4>Introduction: </h4>
        <input type="text" class="input-block-level" placeholder="Introduction" name="introduction" value="${form.introduction}">
        <p> </p>
        <button class="btn btn-large btn-primary" type="submit">Upload</button>
		<jsp:include page="error-list.jsp" />
</form>
</div> <!-- span -->
</div>

<script>
	$(document).ready(function() {
		$("#profile").addClass("active");
		$("#editprofile").addClass("active");
	});
</script>

<jsp:include page="template-bottom.jsp" />