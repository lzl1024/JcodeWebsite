<div class="span3">
          <div class="well">
            <ul class="nav nav-list">
              <li class="nav-header">Operations</li>
              <li id="allblog"><a href="allblog.do">All Blogs</a></li>
              <li id="yourblog"><a href="yourblog.do">Your Blogs</a></li>
              <li id="postblog"><a href="blog.do">Write a Blog</a></li>
              <li class="nav-header">Search Blog</li>
              <li>
              <form method="post" action = "search.do">
              <div class="input-prepend input-block-level">
						<span class="add-on"><i class="icon-search"></i>
						</span> <input id="inputIcon" type="text" class="span10" placeholder="Search" name="keyword">
					</div>
              </form>   
              </li>
            </ul>
          </div><!--/.well -->
          
          <!-- Author's info -->
          <div class="well">
          	<div align="center" style="display:block;">
            	<img style="display:block;" class="img-polaroid" width="70%" height="70%" src="image.do?email=${blogOwner.email}">
            </div>
            <table align="center" style="font-size: 110%; display:block;">
            	<tr>
            		<td colspan="2" align="center"><a href="viewprofile.do?email=${blogOwner.email}">${blogOwner.userName}</a></td>
            	</tr>
            	<tr>
            		<td colspan="2" align="center"><a href="mailto:${blogOwner.email}">${blogOwner.email}</a></td>
            	</tr>
            	<tr>
            		<td align="center">Accept: </td>
            		<td align="left">${blogOwner.accept}</td>
            	</tr>
            	<tr>
            		<td align="center">Deny: </td>
            		<td align="left">${blogOwner.deny}</td>
            	</tr>
            	<tr>
            		<td align="center">Score: </td>
            		<td align="left">${blogOwner.score}</td>
            	</tr>
            </table>
            
          </div><!--/.well -->
          
          <!-- Blogs posted by the author-->
          <div class="well" style="word-wrap: break-word;  
          word-break: normal; ">
			<div align="center" style="padding: 8px 14px; margin: 0; font-size: 18px; font-weight: normal; line-height: 18px; background-color: #f7f7f7; border-bottom: 1px solid #ebebeb;">
				Archives
			</div>
			
			<ul>
			<% 	databeans.Blog[] archives = (databeans.Blog[])request.getAttribute("archives");
				if(archives == null || archives.length == 0) {
					%>No other blog<%
				} else {
					for(int i = 0; i < archives.length; i++) {
						%><li><a href="viewblog.do?id=<%=archives[i].getId()%>"><%=archives[i].getTitle()%></a></li><%
					}
				}
			%>
			</ul>

		</div>
		<!--/.well -->
          
          
        </div><!--/span-->
