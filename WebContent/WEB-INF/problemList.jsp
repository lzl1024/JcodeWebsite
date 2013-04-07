<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-head.jsp" />

<div class="row-fluid">
<jsp:include page="template-problem-nav.jsp" />

        
<div class="span9">
<p>
	<table class="table table-striped">
<% 	databeans.Problem[] problems = (databeans.Problem[])request.getAttribute("problemlist");
		int begin = (Integer)request.getAttribute("begin");
        for (int i=(begin-1)*10; i<begin*10 && i < problems.length; i++) { 
			if (i % 2 == 0) {%>
		<tr class="success">
			<%}else {%>
		<tr class ="info">
			<%} %>
			<td>
			<strong>Title:  <a href="oj.do?id=<%=problems[i].getId()%>"><%=problems[i].getTitle()%></a></strong>
			<strong class="pull-right">Posted @ <%=problems[i].getDate()%></strong>
			<p><%=problems[i].getShortDes()%>... 
			<strong class="pull-right"><a href="oj.do?id=<%=problems[i].getId()%>">See Details &raquo</a></strong>
			<br><strong >Comments(<%=problems[i].getCommentNum()%>)</strong>
			</td>
		</tr>
		<%}%>
	</table>
	
	<div class="pagination pagination-centered">
		<ul>
		<li><a href="allproblem.do?begin=<%= begin >1 ? begin-1:1%>">Prev</a></li>
		<%  int k = (problems.length-1)/10+1;
        	for (Integer i= 1; i<=k; i++)
        	if (i!=begin){%>
        		<li><a href="allproblem.do?begin=<%=i%>"><%=i%></a></li>
        	<%} else{%>
        		<li class="disabled"><a href="allproblem.do?begin=<%=i%>"><%=i%></a></li>
        	<%}%>
        <li><a href="allproblem.do?begin=<%= begin<k ? begin+1:k%>">Next</a></li>
        </ul>
	</div>
	
		
</div>
</div>


<jsp:include page="template-bottom.jsp" />
