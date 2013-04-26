<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-head.jsp" />

<div class="row-fluid">
<jsp:include page="template-navprofile.jsp" />

<script src="${pageContext.request.contextPath}/patterns/js/jquery.Jcrop.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/patterns/css/jquery.Jcrop.css" type="text/css" />

<style type="text/css">

/* Apply these styles only when #preview-pane has
   been placed within the Jcrop widget */
.jcrop-holder #preview-pane {
  display: block;
  position: absolute;
  z-index: 2000;
  top: 200px;
  right: -200px;
  padding: 6px;
  border: 1px rgba(0,0,0,.4) solid;
  background-color: white;

  -webkit-border-radius: 6px;
  -moz-border-radius: 6px;
  border-radius: 6px;

  -webkit-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
  -moz-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
  box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
}

/* The Javascript code will set the aspect ratio of the crop
   area based on the size of the thumbnail preview,
   specified here */
#preview-pane .preview-container {
  width: 120px;
  height: 120px;
  overflow: hidden;
}

</style>

<script>
jQuery(function($){

    // Create variables (in this scope) to hold the API and image size
    var jcrop_api,
        boundx,
        boundy,

        // Grab some information about the preview pane
        $preview = $('#preview-pane'),
        $pcnt = $('#preview-pane .preview-container'),
        $pimg = $('#preview-pane .preview-container img'),

        xsize = $pcnt.width(),
        ysize = $pcnt.height();
    
    console.log('init',[xsize,ysize]);
    $('#target').Jcrop({
      onChange: updatePreview,
      onSelect: updatePreview, 
      aspectRatio: xsize / ysize
    },function(){
      // Use the API to get the real image size
      var bounds = this.getBounds();
      boundx = bounds[0];
      boundy = bounds[1];
      // Store the API in the jcrop_api variable
      jcrop_api = this;

      // Move the preview into the jcrop container for css positioning
      $preview.appendTo(jcrop_api.ui.holder);
    });

    function updatePreview(c)
    {
      if (parseInt(c.w) > 0)
      {
    	  $('#x1').val(c.x);
          $('#y1').val(c.y);
          $('#x2').val(c.x2);
          $('#y2').val(c.y2);
          $('#w').val(c.w);
          $('#h').val(c.h);
          
        var rx = xsize / c.w;
        var ry = ysize / c.h;

        $pimg.css({
          width: Math.round(rx * boundx) + 'px',
          height: Math.round(ry * boundy) + 'px',
          marginLeft: '-' + Math.round(rx * c.x) + 'px',
          marginTop: '-' + Math.round(ry * c.y) + 'px'
        });
      }
    };
    

  });
</script>

<div class="span9 alert alert-block">
    <h2 class="form-signin-heading">Change Photo</h2>
	
	<img src="image.do?email=${profile.email}" id="target" />
	<hr>
	<div id="preview-pane">
    <div class="preview-container">
      <img src="image.do?email=${profile.email}" class="jcrop-preview" alt="Preview" />
    </div>
  </div>
 
    <br>
    
	<form method="post" class="form-horizontal" action="uploadPhoto.do" name="frm" enctype="multipart/form-data">
    	<input type="file" name="file" value=""/>
       	<br>
        <button class="btn btn-info" type="submit">Upload</button>
	</form>
	<hr>
	
	<form method="post" id="coords" action="changePhoto.do">

    <input type="hidden" id="x1" name="x1" />
    <input type="hidden" id="y1" name="y1" />
    <input type="hidden" id="x2" name="x2" />
    <input type="hidden" id="y2" name="y2" />
    <input type="hidden" id="w" name="w" />
    <input type="hidden" id="h" name="h" />
	<input class="btn btn-primary btn-large" type="submit" name="change" value="Submit"/>
    
  	</form>
  
</div> <!-- span -->
</div>

<script>
	$(document).ready(function() {
		$("#profile").addClass("active");
		$("#editprofile").addClass("active");
	});
	
	
</script>

<jsp:include page="template-bottom.jsp" />