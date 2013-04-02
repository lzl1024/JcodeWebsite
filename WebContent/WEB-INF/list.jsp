<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-head.jsp" />

<div class="row-fluid">
<jsp:include page="template-navlist.jsp" />

<div class="span10">
<p>
	<table class="table table-striped">
<% 	databeans.Blog[] blogs = (databeans.Blog[])request.getAttribute("bloglist");
		int begin = (Integer)request.getAttribute("begin");
        for (int i=(begin-1)*10; i<begin*10 && i < blogs.length; i++) { 
			if (i % 2 == 0) {%>
		<tr class="success">
			<%}else {%>
		<tr class ="info">
			<%} %>
			<td>
			<strong>Title:  <a href="view.do?id=<%=blogs[i].getId()%>"><%=blogs[i].getTitle()%></a></strong>
			<strong class="pull-right">Posted by: <a href="viewprofile.do?email=<%=blogs[i].getEmail()%>"><%=blogs[i].getUser()%></a>  @ <%=blogs[i].getDate()%></strong>
			<p><%=blogs[i].getShortDes()%>... 
			<strong class="pull-right"><a href="view.do?id=<%=blogs[i].getId()%>">See Details &raquo</a></strong>
			<br><strong >Comments(<%=blogs[i].getCommentNum()%>)</strong>
			</td>
		</tr>
		<%}%>
	</table>
	
	<div class="pagination pagination-centered">
		<ul>
		<li><a href="allblog.do?begin=<%= begin >1 ? begin-1:1%>">Prev</a></li>
		<%  int k = (blogs.length-1)/10+1;
        	for (Integer i= 1; i<=k; i++)
        	if (i!=begin){%>
        		<li><a href="allblog.do?begin=<%=i%>"><%=i%></a></li>
        	<%} else{%>
        		<li class="disabled"><a href="allblog.do?begin=<%=i%>"><%=i%></a></li>
        	<%}%>
        <li><a href="allblog.do?begin=<%= begin<k ? begin+1:k%>">Next</a></li>
        </ul>
	</div>
	
		
</div>
	<a class="btn pull-right" href="blog.do">Wirte a blog &raquo;</a>
</div>


<jsp:include page="template-bottom.jsp" />
