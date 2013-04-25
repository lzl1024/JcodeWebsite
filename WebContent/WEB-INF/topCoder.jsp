<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-head.jsp" />

<script src="http://code.highcharts.com/highcharts.js"></script>

<% databeans.User[] users = (databeans.User[])request.getAttribute("users"); %>
<script>
$(function () {
        $('#picture').highcharts({
            chart: {
                type: 'column',
                margin: [50, 50, 100, 80]
            },
            title: {
                text: 'Top Coder Ranking (top 20)'
            },
            xAxis: {
                categories: [
                    <%
                    for(int i = 0; i < users.length; i++) {
                    	%>'<%=users[i].getUserName()%>',<%
                    }
                    %>

                ],
                labels: {
                    rotation: -45,
                    align: 'right',
                    style: {
                        fontSize: '13px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Score'
                }
            },
            legend: {
                enabled: false
            },
            tooltip: {
                formatter: function() {
                    return '<b>'+ this.x +'</b><br/>'+
                        'Score: '+ Highcharts.numberFormat(this.y, 0);
                }
            },
            series: [{
                name: 'Score',
                data: [
                    <%
                    for(int i = 0; i < users.length; i++) {
                    	%><%=users[i].getScore()%>,<%
                    }
                    %>
                    ],
                dataLabels: {
                    enabled: true,
                    rotation: -90,
                    color: '#FFFFFF',
                    align: 'right',
                    x: 4,
                    y: 10,
                    style: {
                        fontSize: '13px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                }
            }]
        });
    });
    
</script>


<div class="row-fluid">
	<div id="picture" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
	<br>
	<br>
	<div class="ContentTable">
	<table>
	<tr>
		<td><strong>Rank</strong>
		<td><strong>UserName</strong></td>
		<td><strong>Accepted</strong></td>
		<td><strong>Denied</strong></td>
		<td><strong>Scores</strong></td>	
	</tr>
<% 	
        for (int i=0; i< users.length; i++) { 
        %>
		<tr>
			<td><%=i+1 %></td>
			<td><a href="viewprofile.do?email=<%=users[i].getEmail()%>"><%=users[i].getUserName()%></a></td>
			<td><%=users[i].getAccept()%></td>
			<td><%=users[i].getDeny()%></td>
			<td><%=users[i].getScore()%></td>
		</tr>
	<%}%>
	</table>
	</div>
	<br>
	<br>
</div>

<jsp:include page="template-bottom.jsp" />
