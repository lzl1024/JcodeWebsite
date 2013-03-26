<jsp:include page="template-head.jsp" />

<div class="row-fluid">
<jsp:include page="template-navlist.jsp" />

<div class="span10 alert alert-block">

        <p><h2 class="text-center">${blog.title}</h2>
        <hr>

        <div>
        <p>${blog.readableCon} </p>
        </div>
</div>
		<jsp:include page="error-list.jsp" />

</div>

<jsp:include page="template-bottom.jsp" />