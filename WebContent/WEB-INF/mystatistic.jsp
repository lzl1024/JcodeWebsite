<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-head.jsp" />


<div class="row-fluid">
<p>
	<table class="table table-striped">
	<tr><td><strong>Problem</strong></td>
		<td><strong>Accepted</strong></td>
		<td><strong>Denied</strong></td>
		<td><strong>Scores</strong></td>	
	</tr>
<% 	databeans.Statistic[] stats = (databeans.Statistic[])request.getAttribute("stats");
        for (int i=0; i< stats.length; i++) { 
			if (i % 2 == 0) {%>
		<tr class="success">
	<%}else {%>
		<tr class ="info">
	<%} %>
			<td><%=stats[i].getProblemTitle()%></td>
			<td><%=stats[i].getAccept()%></td>
			<td><%=stats[i].getDeny()%></td>
			<td><%=stats[i].getScore()%></td>
		</tr>
	<%}%>
	</table>
</div>

<jsp:include page="template-bottom.jsp" />