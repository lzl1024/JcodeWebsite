<div class="span3">
	<%@ page import="databeans.User"
			 import="databeans.Statistic" %>
			 
	<script src="http://code.highcharts.com/highcharts.js"></script>
	<script src="http://code.highcharts.com/modules/exporting.js"></script>


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
	
	<%
		Statistic[] stat = (Statistic[])request.getAttribute("stat");
	%>
	
	
	<script>
	$(function () {
        $('#highScore').highcharts({
            chart: {
            	backgroundColor: '#f5f5f5',
                type: 'bar'
            },
            title: {
                text: 'High Score'
            },
            xAxis: {
                categories: [
                             <% for(int i = 0; i < stat.length; i++) {
                             %> '<%=stat[i].getUserName()%>',
                             <% }%>
                             ]
            },
            yAxis: {
                min: 0,
                max: 100,
                title: {
                    text: 'Top 10 score'
                }

            },
            legend: {
                backgroundColor: '#f5f5f5',
                reversed: true
            },
            plotOptions: {
                series: {
                    stacking: 'normal'
                }
            },
                series: [{
                name: 'Score',
                pointPadding: 0,
                data: [
						<% for(int i = 0; i < stat.length; i++) {
    					%> <%=stat[i].getScore()%>,
    					<% }%>
                       ]
            }]
        });
    });
    
	</script>
	
	
	<div class="well" align="center">
			<%if(stat == null || stat.length == 0)  {%>
				<h5>No submission record</h5>
			<%}else {%>
			<div id="highScore" style="min-width: 200px; height: <%=40*stat.length + 100 %>px; margin: 0 auto"></div>
			<%} %>
	</div>
	<!--/.well -->


</div>
<!--/span-->