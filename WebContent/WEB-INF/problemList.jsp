<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-head.jsp" />

<div class="row-fluid">
<jsp:include page="template-problem-nav.jsp" />

        
<div class="span9">
<p>
<% 	databeans.Problem[] problems = (databeans.Problem[])request.getAttribute("problemlist");
		int begin = (Integer)request.getAttribute("begin");
        for (int i=(begin-1)*10; i<begin*10 && i < problems.length; i++) {   
%>
	<div class="span9" style=" width:800px; word-wrap: break-word; word-break: normal;">
			<br>
			<a style="display:block;"
			href="problem.do?id=<%=problems[i].getId()%>">
			<font size="5"><%=problems[i].getTitle()%></font></a>
			<p style="margin-left:10px; margin-right:5px; width:700px; word-wrap: break-word; word-break: normal;">
			<font color="#95aba9"><%=problems[i].getShortDes()%>...</font>
			<strong class="pull-right" style="display:block; margin-bottom:15px"><a href="problem.do?id=<%=problems[i].getId()%>">See Details &raquo</a></strong>			
			</p>
		</div>
		<div class="span9" style="width:800px;
			word-wrap: break-word; word-break: normal;
				background: #eff6fb;
				line-height: 10px;"	>
			<br>&nbsp Comments(<%=problems[i].getCommentNum()%>)
			&nbsp &nbsp Accepted(<%=problems[i].getAccept()%>)
			&nbsp &nbsp Denied(<%=problems[i].getDeny()%>)
			&nbsp &nbsp &nbsp @ <%=problems[i].getDate()%></strong>
		</div>	
		<%}%>
	
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

<script>
	$(document).ready(function() {
		$("#oj").addClass("active");
		$("#allproblem").addClass("active");
	});
</script>

<jsp:include page="template-bottom.jsp" />
