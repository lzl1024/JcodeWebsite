<div class="span3">
	<%@ page import="databeans.User"
			 import="databeans.Statistic" %>
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

	<div class="well" align="center">
		<div align="center" style="padding: 8px 14px; margin: 0; font-size: 15px; font-weight: normal; line-height: 18px; background-color: #f7f7f7; border-bottom: 1px solid #ebebeb;">
				High Score for This Problem
		</div>
		<div align="center" style="font-size: 110%; display: block; width: 70%">

		<% Statistic[] stat = (Statistic[])request.getAttribute("stat");
		   for(int i = 0; i < stat.length; i++) {
		%>
				<div class="pull-left"><%= i+1%>.&nbsp &nbsp
				<a href="viewprofile.do?email=<%=stat[i].getUserEmail()%>"><%=stat[i].getUserName()%></a>
				</div> 
				<div class="pull-right"><%=stat[i].getScore() %></div>
				<br>
		<%} %>
		</div>
		<hr>
	</div>
	<!--/.well -->


</div>
<!--/span-->