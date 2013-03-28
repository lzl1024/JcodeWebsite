<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-head.jsp" />

<script>
<!--
function deleteblog(blogid) {	
	var r=confirm("Are you sure to delte this blog?");
	if (r==true)
	  {
		window.location.assign("deleteblog.do?blogid="+blogid);
	  }
	
}
</script>

<div class="row-fluid">
<jsp:include page="template-navlist.jsp" />

<div class="span10">
<p>
	<table class="table table-striped">
	<tr><td><strong>Title</strong></td>
		<td><strong>Author</strong></td>
		<td><strong>Date</strong></td>
		<td><strong>Operations</strong></td>	
	</tr>
<%int i = 0;%>
	<c:forEach var="blog" items="${bloglist}">
	<%if (i % 2 == 0) {%>
		<tr class="success">
	<%}else {%>
		<tr class ="info">
	<%} %>
			<td><a href="view.do?id=${blog.id}">${blog.title}</a></td>
			<td>${blog.user}</td>
			<td>${blog.date}</td>
			<td><a onclick="deleteblog(${blog.id})" href="javascript::deleteblog(${blog.id})">Delete</a>
			&nbsp &nbsp <a href="editblog.do?id=${blog.id}">Edit</a></td>
		</tr>
	<% i++; %>
	</c:forEach>
	</table>
	
		
</div>
	<a class="btn pull-right" href="blog.do">Wirte a blog &raquo;</a>
</div>


<jsp:include page="template-bottom.jsp" />