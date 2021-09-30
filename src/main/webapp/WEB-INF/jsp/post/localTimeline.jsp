<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지역커뮤니티</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  	
  	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
  	 
  	<link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
	<header class="d-flex">
		<h2>투자SNS</h2>
		<a href="/post/invest_view">투자게시판//</a>
		<a href="/post/local_view">지역커뮤니티//</a>
		<a href="/post/gossip_view">가십게시판</a>
		
		<h3>가십게시판</h3>
		
		<div class="mr-4">${userNickName }님 <a href="/user/sign_out">로그아웃</a> </div>
		
		<img src="https://cdn.pixabay.com/photo/2021/04/23/19/57/yorkshire-terrier-6202621_960_720.jpg" width="30">
		
		<a href="#" id="writeBtn" data-toggle="modal" data-target="#writeModal"> 
			글쓰기 
		</a>
		<a href="/post/individual_home_view?userId=${userId }">개인 홈</a>		
	</header>
	
	<hr>
	
	<section>
        <h3>${userLocation }</h3>
        
		<c:forEach var="postWithOthers" items="${postList }">
			<!-- 보여지는 가십 게시글 -->
			<div class="card mt-3">			
				<!-- 글 시작부분 -->
				<div class="d-flex justify-content-between p-2 border-bottom">
					
					<!-- 글쓴이 -->
					<div>
						<img src="https://mblogthumb-phinf.pstatic.net/20150203_225/hkjwow_1422965971196EfkMV_JPEG/%C4%AB%C5%E5%C7%C1%BB%E7_31.jpg?type=w210" width="30">
						<a href="/post/individual_home_view?userId=${postWithOthers.localPost.userId }" class="homeLink">
							${postWithOthers.localPost.userNickName }
						</a>
					</div>
					${postWithOthers.localPost.userLocation }
					<!-- 좋아요 -->
					<div>
						<a href="#" class="likeBtn" data-post-id="${postWithOthers.localPost.id }">
							<c:choose>
								<c:when test="${postWithOthers.like }" >
									<i class="bi bi-heart-fill heart-icon text-danger" data-status="like" id="heartIcon-${postWithOthers.localPost.id }"></i>
								</c:when>
								<c:otherwise>
									<i class="bi bi-heart heart-icon text-dark" id="heartIcon-${postWithOthers.localPost.id }"></i>	
								</c:otherwise>
							</c:choose>
						</a>
						<span class="middle-size ml-1"> 
							좋아요 <span id="likeCount-${postWithOthers.localPost.id }" >${postWithOthers.likeCount }</span>개 
						</span>
					</div>
					
					<%-- 글 의 userId 와 세션의 userId 가 일치하면 더보기 버튼 노출 --%>
					<c:if test="${postWithOthers.localPost.userId eq userId}">
						<div class="more-icon" >
							<a href="#" class="text-dark moreBtn"> 
								<i class="bi bi-three-dots-vertical"></i> 
							</a>
						</div>
					</c:if>
					
				</div>
				<!-- /글 시작부분 -->
				
				<!-- 내용 -->
				<div class="middle-size m-2">
					${postWithOthers.localPost.content }
				</div>
				
				<!-- 댓글 -->
				<div class="mt-2">
				
					<div class="border-bottom m-2">
						<span class="middle-size">댓글</span>
					</div>
					
					<!--  댓글현황  -->
					<div class="middle-size m-2">
						<c:forEach var="comment" items="${postWithOthers.commentList }" >
								<div class="mt-1">
									<b>${comment.userNickName }</b> ${comment.content }
								</div>
						</c:forEach>
					</div>
					
					<!-- 댓글 입력 -->
					<div class="d-flex mt-2 border-top">
						<input type="text" class="form-control border-0 " id="commentInput-${postWithOthers.localPost.id }">
						<button class="btn btn-info ml-2 commentBtn" data-post-id="${postWithOthers.localPost.id }">게시</button>
					</div>
					
				</div>
				<!-- /댓글 -->	
			</div>
			<!-- /보여지는 가십 게시글 -->
		</c:forEach>
	</section>
	
	<footer>
	<hr>
		copyright ~~
	</footer>
	
	<!-- 모달의 a태그에 data-post-id 의 값을 더보기 클릭시마다 바꿔준다.   -->
	<!-- Modal -->
	<div class="modal fade" id="writeModal" tabindex="-1" role="dialog" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-body text-center">
	        <!--  게시글 작성  -->
			<div>
				<h3>지역커뮤니티 게시글 작성</h3>
			
				<div class="border rounded mt-1">
					<textarea class="form-control w-100 non-resize" rows=4 id="contentInput">
						텍스트 쓰는곳
					</textarea>			
				</div>
				
				<!--  이미지  -->
				<div class="image-input-box border rounded mt-1">
					이미지 파일 반영되는곳<br>
					<input type="file" class="input-control" id="fileInput">
					<a href="#" id="imageUploadBtn"><i class="bi bi-image"></i></a>
				</div>
								
				<button class="btn btn-sm btn-info" id="uploadBtn">업로드</button>
			</div>
			<!--  /게시글 작성  -->

	      </div>
	  
	    </div>
	  </div>
	</div>
	<!-- /Modal -->
	
	<script>
		$(document).ready(function() {
			var corporation;
	        $("#corporation").on("change", function() {
	        	//corporation = $("#corporation option:selected").val();
	        	corporation = $(this).val();
	        	
	        	location.href="/post/gossip_view?corporation=" + corporation;
	        });
	        
	        // <글쓰기 버튼 눌렀을때>
			$("#writeBtn").on("click", function() {
		    	
				var corporationSelect;
		    
		        $("#corporationSelect").on("change", function() {
		        	corporationSelect = $(this).val();
		        });
		     	// <업로드>
		        $("#uploadBtn").on("click", function() {
					let content = $("#contentInput").val().trim();
						
					if(content == null || content == "") {
						alert("내용을 입력하세요");
						return ;
					}
					
					$.ajax({
						type:"POST",
						url:"/post/create/local",
						data:{"content":content, "corporation":corporationSelect},
						success:function(data) {
							if(data.result == "success") {
								alert("글쓰기 성공");
								location.reload();
							} else {
								alert("글쓰기에 실패했습니다.");
							}
							
						}, error:function(e) {
							alert("error ");
						}
					});
					
				});		
		     	// </업로드>
	        });
			// </글쓰기 버튼 눌렀을때>

			// <댓글 입력>
			$(".commentBtn").on("click", function() {
				var postId = $(this).data("post-id");
				
				// $("#commentInput-1")
				var comment = $("#commentInput-" + postId).val().trim();
				
				if(comment == null || comment == "") {
					alert("내용을 입력하세요");
					return;
				}
				
				$.ajax({
					type:"post",
					url:"/comment/create/gossip",
					data:{"postId":postId, "content":comment},
					success:function(data) {
						if(data.result == "success") {
							location.reload();
						} else {
							alert("댓글 작성 실패");
						}
					},
					error:function(e) {
						alert("error");
					}
				});
			});
			// </댓글 입력>
			
			// <좋아요 버튼>
			$(".likeBtn").on("click", function(e) {
				e.preventDefault();
				var postId = $(this).data("post-id");
				
				$.ajax({
					type:"get",
					url:"/post/like/gossip",
					data:{"postId": postId},
					success:function(data) {
						// 좋아요
						if(data.like) {
							
							$("#heartIcon-" + postId).removeClass("bi-heart");
							$("#heartIcon-" + postId).addClass("bi-heart-fill");
							
							$("#heartIcon-" + postId).removeClass("text-dark");
							$("#heartIcon-" + postId).addClass("text-danger");
						} else { // unlike
							$("#heartIcon-" + postId).addClass("bi-heart");
							$("#heartIcon-" + postId).removeClass("bi-heart-fill");
							
							$("#heartIcon-" + postId).addClass("text-dark");
							$("#heartIcon-" + postId).removeClass("text-danger");
						}
						
						$("#likeCount-" + postId).text(data.likeCount);
						
						//location.reload();
							
					},
					error:function(e) {
						alert("error");
					}
					
				});
				
			});
			// </좋아요 버튼>

		});
	</script>
</body>
</html>