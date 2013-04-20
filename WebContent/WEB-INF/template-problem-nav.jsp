<div class="span3">
	<%@ page import="databeans.User"%>
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

			<li class="nav-header">Search Problems</li>
			<li><form method="post" action="searchproblem.do">
					<div class="input-prepend">
						<span class="add-on"><i class="icon-search"></i></span> 
						<input id="inputIcon" type="text" class="input-block-level span10" placeholder="Search" name="keyword">
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
					<div class="input-prepend">
						<span class="add-on"><i class="icon-search"></i>
						</span> <input id="inputIcon" type="text" class="input-block-level span10" placeholder="Search" name="keyword">
					</div>
				</form></li>

		</ul>
	</div>
	<% } %>
</div>
<!--/span-->