<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="template-head.jsp" />

<div class="row-fluid">
<jsp:include page="template-navlist.jsp" />

<div class="span10 alert alert-block">

        <p><h2 class="text-center">${blog.title}</h2>
        <hr>

        <div>
        <p>${blog.readableCon} </p>
        </div>
<hr>  <hr>    
</div>
<div class="span10 pull-right alert alert-success">   
<h3 class="form-signin-heading">Comments</h3> 

<c:if test = "${fn:length(commentlist) > 0}">
<c:forEach var="i" begin="0" end="${fn:length(commentlist)-1}">
	<h3 class="badge">${i+1}</h3>
	<h3 class="label">Posted By: ${commentlist[i].user}</h3>
    <h3 class="label pull-right">  ${commentlist[i].date}</h3>
   <p>${commentlist[i].readableCon}</p>
   <hr>
</c:forEach>    
</c:if>        
<hr>                
<form method="post" class="form-horizontal" action="comment.do">
        <h3 class="form-signin-heading">Write your Comment</h3>
        <input type="hidden" name="blogid" value="${blog.id}"/>
        <textarea class="input-block-level" rows="10" placeholder="Write your Comment" name="content"></textarea>
        <p> </p>
        <button class="btn btn-large btn-primary" type="submit">Submit</button>
</form>
</div>
		<jsp:include page="error-list.jsp" />

</div>

<jsp:include page="template-bottom.jsp" />