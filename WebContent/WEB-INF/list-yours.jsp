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
<% 	databeans.Blog[] blogs = (databeans.Blog[])request.getAttribute("bloglist");
		int begin = (Integer)request.getAttribute("begin");
        for (int i=(begin-1)*10; i<begin*10 && i < blogs.length; i++) { 
			if (i % 2 == 0) {%>
		<tr class="success">
	<%}else {%>
		<tr class ="info">
	<%} %>
			<td><a href="viewblog.do?id=<%=blogs[i].getId()%>"><%=blogs[i].getTitle()%></a></td>
			<td><%=blogs[i].getUser()%></td>
			<td><%=blogs[i].getDate()%></td>
			<td><a onclick="deleteblog(<%=blogs[i].getId()%>)" href="javascript::deleteblog(<%=blogs[i].getId()%>)">Delete</a>
			&nbsp &nbsp <a href="editblog.do?id=<%=blogs[i].getId()%>">Edit</a></td>
		</tr>
	<%}%>
	</table>
	<div class="pagination pagination-centered">
		<ul>
		<li><a href="yourblog.do?begin=<%= begin >1 ? begin-1:1%>">Prev</a></li>
		<%  int k = (blogs.length-1)/10+1;
        	for (Integer i= 1; i<=k; i++)
        	if (i!=begin){%>
        		<li><a href="yourblog.do?begin=<%=i%>"><%=i%></a></li>
        	<%} else{%>
        		<li class="disabled"><a href="yourblog.do?begin=<%=i%>"><%=i%></a></li>
        	<%}%>
        <li><a href="yourblog.do?begin=<%= begin<k ? begin+1:k%>">Next</a></li>
        </ul>
	</div>
	
		
</div>
	<a class="btn pull-right" href="blog.do">Wirte a blog &raquo;</a>
</div>



<jsp:include page="template-bottom.jsp" />