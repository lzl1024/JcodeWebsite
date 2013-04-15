<% 
	String status = (String)request.getAttribute("status");
	String msg = (String)request.getAttribute("msg");

	if(status.equals("success")) {
		out.println(msg);
	} else {
		java.util.ArrayList<String> list = (java.util.ArrayList<String>) request.getAttribute("errors");
		for(int i = 0; i < list.size(); i++) {
		%>
			<p style="font-size:medium" class ="alert alert-error"><b> <%=list.get(i) %> </b> </p>
		<%	
		}
	}

%>