<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  	
  	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
  	 
  	<link rel="stylesheet" href="/static/css/style.css">
</head>
		
	<a href="#" data-toggle="modal" data-target="#writeModal"> 
		글쓰기 
	</a>
	
	<!-- Modal -->
	<div class="modal fade" id="writeModal" tabindex="-1" role="dialog" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-body text-center">

	      </div>
	  
	    </div>
	  </div>
	</div>
	<!-- /Modal -->
	
	<div class="more-icon" >
		<a class="text-dark moreBtn" href="#"  data-toggle="modal" data-target="#deleteModal" data-post-id="${postWithComments.post.id }"> 
			<i class="bi bi-three-dots-vertical"></i> 
		</a>
	</div>
	
	<!-- 모달의 a태그에 data-post-id 의 값을 더보기 클릭시마다 바꿔준다.   -->
	<!-- Modal -->
	<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-body text-center">
	        <a href="#" id="deleteBtn" >삭제하기 </a>
	      </div>
	  
	    </div>
	  </div>
	</div>
	

<body>
     <script>
    $(document).ready(function() {
		$(".moreBtn").on("click", function() {
			// postId를 모델에 삭제 버튼에 주입한다. 
			
			var postId = $(this).data("post-id");
							
			$("#deleteBtn").data("post-id", postId);
			
		});
		
		$("#deleteBtn").on("click", function(e) {
			e.preventDefault();
			var postId = $(this).data("post-id");
			
			$.ajax({
				type:"get",
				url:"/post/delete",
				data:{"postId":postId},
				success:function(data) {
					if(data.result == "success") {
						location.reload();
					} else {
						alert("삭제 실패");
					}
				}, 
				error:function(e) {
					alert("error");	
				}
			})
		});

    });
    </script>
</body>
</html>