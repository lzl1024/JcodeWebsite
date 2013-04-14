<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-head.jsp" />


<div class="row-fluid">
<p>
	<table class="table table-striped">
	<tr><td><strong>UserName</strong></td>
		<td><strong>Accepted</strong></td>
		<td><strong>Denied</strong></td>
		<td><strong>Scores</strong></td>	
	</tr>
<% 	databeans.User[] users = (databeans.User[])request.getAttribute("users");
        for (int i=0; i< users.length; i++) { 
			if (i % 2 == 0) {%>
		<tr class="success">
	<%}else {%>
		<tr class ="info">
	<%} %>
			<td><%=users[i].getUserName()%></td>
			<td><%=users[i].getAccept()%></td>
			<td><%=users[i].getDeny()%></td>
			<td><%=users[i].getScore()%></td>
		</tr>
	<%}%>
	</table>
</div>

<jsp:include page="template-bottom.jsp" />