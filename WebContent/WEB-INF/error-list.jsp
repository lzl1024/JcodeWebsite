<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<div>
		<p>  </p>
		<c:forEach var="error" items="${errors}">	
			<p style="font-size:medium" class ="alert alert-error"><b> ${error} </b> </p>
		</c:forEach>
		</div>