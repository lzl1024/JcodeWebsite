<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-head.jsp" />

<div class="row-fluid">
<jsp:include page="template-blog-nav.jsp" />

        
<div class="span9">
<p>
<% 	databeans.Blog[] blogs = (databeans.Blog[])request.getAttribute("bloglist");
		int begin = (Integer)request.getAttribute("begin");
        for (int i=(begin-1)*10; i<begin*10 && i < blogs.length; i++) { 
%>
		<div class="span9" style=" width:800px; word-wrap: break-word; word-break: normal;">
			<br>
			<a style="display:block;"
			href="viewblog.do?id=<%=blogs[i].getId()%>">
			<font size="5"><%=blogs[i].getTitle()%></font></a>
			<table>
			<tr><td>
			<img  style="display:block; margin-top:5px; margin-bottom:5px; width:100px;height:80px;" src="image.do?email=<%=blogs[i].getEmail()%>">
			</td><td>
			<p style="margin-left:10px; margin-right:5px; width:700px; word-wrap: break-word; word-break: normal;">
			<font color="#95aba9"><%=blogs[i].getShortDes()%>...</font>
			<strong class="pull-right" style="display:block; margin-bottom:15px"><a href="viewblog.do?id=<%=blogs[i].getId()%>">See Details &raquo</a></strong>			
			</p>
			</td></tr>
			</table>
		</div>
		<div class="span9" style="width:800px;
			word-wrap: break-word; word-break: normal;
				background: #eff6fb;
				line-height: 10px;"	>
			<br>&nbsp Comments(<%=blogs[i].getCommentNum()%>)
			&nbsp &nbsp &nbsp<a href="viewprofile.do?email=<%=blogs[i].getEmail()%>"><%=blogs[i].getUser()%></a>  @ <%=blogs[i].getDate()%></strong>
		</div>
		<%}%>

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

<script>
	$(document).ready(function() {
		$("#blog").addClass("active");
		$("#allblog").addClass("active");
	});
</script>


<jsp:include page="template-bottom.jsp" />
