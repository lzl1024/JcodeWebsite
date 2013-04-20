<jsp:include page="template-head.jsp" />

      <!-- Main hero unit for a primary marketing message or call to action -->
      
      <h1>JCode</h1>
      
      <div id="myCarousel" class="carousel slide">
      <ol class="carousel-indicators">
    	<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
    	<li data-target="#myCarousel" data-slide-to="1"></li>
    	<li data-target="#myCarousel" data-slide-to="2"></li>
    	<li data-target="#myCarousel" data-slide-to="3"></li>

	  </ol>
    
      <div class="carousel-inner">
        <div class="item active">
          <img src="pic/coding.jpg" alt="" width="1280" height="600">
          <div class="container">
            <div class="carousel-caption">
              <h1>Online Judge</h1>
              <p class="lead">Welcome to Online Judge system. Code, run and enjoy!</p>
              <a class="btn btn-large btn-primary" href="allproblem.do">Online Judge</a>
            </div>
          </div>
        </div>
        
        <div class="item">
          <img src="pic/blog.jpg" alt="" width="1280" height="600">
          <div class="container">
            <div class="carousel-caption">
              <h1>Blog</h1>
              <p class="lead">Hey, smart brain. Show your idea to the world!</p>
              <br>
              <a class="btn btn-large btn-primary" href="allblog.do">Blog</a>
            </div>
          </div>
        </div>
        
        <div class="item">
          <img src="pic/statistics.jpg" alt="" width="1280" height="600">
          <div class="container">
            <div class="carousel-caption">
              <h1>Ranking</h1>
              <p class="lead">Code with the world's top coders. Or be one of them!</p>
			  <br>
              <a class="btn btn-large btn-primary" href="allstatistics.do">Statistics</a>
            </div>
          </div>
        </div>
        
        <div class="item">
          <img src="pic/github.jpg" alt="" width="1280" height="600">
          <div class="container">
            <div class="carousel-caption">
              <h1>GitHub</h1>
              <p class="lead">Wonder how this cool system works? This is an open source project!</p>
			  <br>
              <a class="btn btn-large btn-primary" href="https://github.com/lzl1024/Leetcode.git">View Source</a>
            </div>
          </div>
        </div>
      </div>
        
      <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
      <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
    </div><!-- /.carousel -->
    

	<script>
	$(document).ready(
			function() {
				$('#myCarousel').carousel();
			});
	
	$(document).ready(function() {
		$("#home").addClass("active");
	});

	</script>

<jsp:include page="template-bottom.jsp" />