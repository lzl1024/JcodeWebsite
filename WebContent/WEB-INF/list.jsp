<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-head.jsp" />

<div class="row-fluid">
<jsp:include page="template-navlist.jsp" />

<div class="span10">
<p>
	<table class="table table-striped">
<%int i = 0;%>
	<c:forEach var="blog" items="${bloglist}">
	<%if (i % 2 == 0) {%>
		<tr class="success">
	<%}else {%>
		<tr class ="info">
	<%} %>
			<td>
			<strong>Title:  <a href="view.do?id=${blog.id}">${blog.title}</a></strong>
			<strong class="pull-right">Posted by: <a href="viewprofile.do?email=${blog.email}">${blog.user}</a>  @ ${blog.date}</strong>
			<p>${blog.shortDes}... 
			<strong class="pull-right"><a href="view.do?id=${blog.id}">See Details &raquo</a></strong>
			<br><strong >Comments(${blog.commentNum})</strong>
			</td>
		</tr>
	<% i++; %>
	</c:forEach>
	</table>
	
		
</div>
	<a class="btn pull-right" href="blog.do">Wirte a blog &raquo;</a>
</div>


<jsp:include page="template-bottom.jsp" />
