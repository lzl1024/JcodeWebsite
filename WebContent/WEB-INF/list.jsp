<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-head.jsp" />

<div class="row-fluid">
<jsp:include page="template-navlist.jsp" />

<div class="span10">
<p>
	<table class="table table-striped">
	<tr><td><strong>Title</strong></td>
		<td><strong>Author</strong></td>
		<td><strong>Date</strong></td>	
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
		</tr>
	<% i++; %>
	</c:forEach>
	</table>
	
		
</div>
	<a class="btn pull-right" href="blog.do">Wirte a blog &raquo;</a>
</div>


<jsp:include page="template-bottom.jsp" />
