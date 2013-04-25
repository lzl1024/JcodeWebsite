<div class="span3">
	<%@ page import="databeans.User"
			 import="databeans.Problem"
	%>
	<% 
		User user = (User) session.getAttribute("user");
		String group = null;
		if(user != null) {
			group = user.getUserGroup();
		}
		
		if(group != null && group.equals("user")) {
		%>
	<div class="well sidebar-nav">
		<ul class="nav nav-list">
			<li class="nav-header">Operations</li>
			<li id="allproblem"><a href="allproblem.do">All Problems</a></li>
			<li id="topcoder"><a href="topcoder.do">Top Coder</a>
			<li id="mystatistic"><a href="statistic.do">My Submission</a>
			<li class="nav-header">Search Problems</li>
			<li><form method="post" action="searchproblem.do">
					<div class="input-prepend">
						<span class="add-on"><i class="icon-search"></i></span> 
						<input id="inputIcon" type="text" class="span10" placeholder="Search" name="keyword">
					</div>
				</form></li>

		</ul>
	</div>

	<%	
		} else if(group != null && group.equals("admin")) {
		%>
	<div class="well sidebar-nav">
		<ul class="nav nav-list">
			<li class="nav-header">Operations</li>
			<li id="allproblem"><a href="allproblem.do">All Problems</a></li>
			<li id="manageproblem"><a href="manageProblem.do">Manage</a></li>
			<li id="postproblem"><a href="postProblem.do">Post Problem</a></li>
			<li class="nav-header">Search Problems</li>
			<li><form method="post" action="searchproblem.do">
					<div class="input-prepend input-block-level">
						<span class="add-on"><i class="icon-search"></i>
						</span> <input id="inputIcon" type="text" class="span10" placeholder="Search" name="keyword">
					</div>
				</form>
			</li>

		</ul>
	</div>
	<% } %>

	<!-- hot problem -->
	<div class="well">
	<div align="center"
		style="padding: 8px 14px; margin: 0; font-size: 18px; font-weight: normal; line-height: 18px; background-color: #f7f7f7; border-bottom: 1px solid #ebebeb;">
			Hot Problems
	</div>
	<%
		Problem[] hotProblem = (Problem[])request.getAttribute("hotproblem");
		if(hotProblem == null || hotProblem.length == 0) {
		%>	No Hot Problem <%
		} else { %>
		
		<ul>
			<%
				for (int i = 0; i < hotProblem.length; i++) {
					int accept = hotProblem[i].getAccept();
					int deny = hotProblem[i].getDeny();
			%><li><a href="problem.do?id=<%=hotProblem[i].getId()%>"><%=hotProblem[i].getTitle()%></a>&nbsp(<%=accept%>/<%=accept+deny%>)</li>
			<%
				}
				}
			%>
		</ul>

	</div>
	<!--/.well -->
</div>
<!--/span-->