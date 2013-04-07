<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="template-head.jsp" />

<div class="row-fluid">
<jsp:include page="template-blog-nav.jsp" />

        
<div class="span9 alert-block alert-info">

        <p><h2 class="text-center">${blog.title}</h2>
        <hr>

        <div>
        <p>${blog.readableCon} </p>
        </div>
<hr>  <hr>    
</div>

<div class="span9 pull-right alert alert-success">   
<h3 class="form-signin-heading">Comments</h3> 

<% 	databeans.Comment[] comments = (databeans.Comment[])request.getAttribute("commentlist");
	int begin = 1;
	if (comments == null || comments.length == 0){%>
	There is no comments right now.
	<hr>
	<%}else{
		begin = (Integer)request.getAttribute("begin");
        for (int i=(begin-1)*10; i<begin*10 && i < comments.length; i++) {%>

		<h3 class="badge"><%=i+1%></h3>
		<h3 class="label">Posted By: <%=comments[i].getUser()%></h3>
    	<h3 class="label pull-right"> <%=comments[i].getDate()%></h3>
   		<p><%=comments[i].getReadableCon()%></p>
   		<hr>
	<%}}%>
	<div class="pagination pagination-centered">
		<ul>
		<li><a href="viewblog.do?begin=<%= begin >1 ? begin-1:1%>&id=${blog.id}">Prev</a></li>
		<%  int k = (comments.length-1)/10+1;
        	for (Integer i= 1; i<=k; i++)
        	if (i!=begin){%>
        		<li><a href="viewblog.do?begin=<%=i%>&id=${blog.id}"><%=i%></a></li>
        	<%} else{%>
        		<li class="disabled"><a href="viewblog.do?begin=<%=i%>&id=${blog.id}"><%=i%></a></li>
        	<%}%>
        <li><a href="viewblog.do?begin=<%= begin<k ? begin+1:k%>&id=${blog.id}">Next</a></li>
        </ul>
	</div>
	       
<hr>                
<form method="post" class="form-horizontal" action="comment.do">
        <h3 class="form-signin-heading">Write your Comment</h3>
        <input type="hidden" name="blogid" value="${blog.id}"/>
        <textarea class="input-block-level" rows="5" placeholder="Write your Comment" name="content"></textarea>
        <p> </p>
        <button class="btn btn-large btn-primary" type="submit">Submit</button>
</form>
</div>
		<jsp:include page="error-list.jsp" />

</div>

<jsp:include page="template-bottom.jsp" />