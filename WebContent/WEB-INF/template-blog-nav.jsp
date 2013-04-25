<div class="span3">
	<%@ page import="databeans.Blog"
	%>
	<div class="well">
		<ul class="nav nav-list">
			<li class="nav-header">Operations</li>
			<li id="allblog"><a href="allblog.do">All Blogs</a></li>
			<li id="yourblog"><a href="yourblog.do">Your Blogs</a></li>
			<li id="postblog"><a href="blog.do">Write a Blog</a></li>
			<li class="nav-header">Search Blog</li>
			<li>
				<form method="post" action="search.do">
					<div class="input-prepend input-block-level">
						<span class="add-on"><i class="icon-search"></i> </span> <input
							id="inputIcon" type="text" class="span10" placeholder="Search"
							name="keyword">
					</div>
				</form>
			</li>
		</ul>
	</div>
	<!--/.well -->

	<!-- hot problem -->
	<div class="well">
		<div align="center"
			style="padding: 8px 14px; margin: 0; font-size: 18px; font-weight: normal; line-height: 18px; background-color: #f7f7f7; border-bottom: 1px solid #ebebeb;">
			Hot Blog</div>
		<%
		Blog[] hotBlog = (Blog[])request.getAttribute("hotblog");
		if(hotBlog == null || hotBlog.length == 0) {
		%>
		No Hot Blog
		<%
		} else { %>

		<ul>
			<%
				for (int i = 0; i < hotBlog.length; i++) {
			%><li><a href="viewblog.do?id=<%=hotBlog[i].getId()%>"><%=hotBlog[i].getTitle()%></a></li>
			<%
				}
				}
			%>
		</ul>

	</div>
	<!--/.well -->
</div>
<!--/span-->
