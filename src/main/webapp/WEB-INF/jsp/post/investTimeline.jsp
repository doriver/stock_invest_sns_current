<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>투자게시판</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  	
  	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
  	 
  	<link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
	<header class="d-flex">
		<c:import url="/WEB-INF/jsp/include/viewList.jsp" />
		<div class="col-4 d-flex justify-content-center">
			<h2 class="pt-3 text-danger">투자게시판</h2>
		</div>
		<c:import url="/WEB-INF/jsp/include/userSector.jsp" />
	</header>
	<hr>
	<section class="d-flex">
		<div class="col-2">
			<div class="card">
				<form method="post" action="/post/invest_view_filtering">
					<select name="investStyleForFiltering">
		          			<option value="">투자스타일</option>
		          			<option>단타x</option>
		          			<option>단타</option>
		      		</select>
		      	
					<select name="stockItemNameForFiltering">
		          			<option value="">관심종목</option>
		          			<option>카카오게임즈</option>
		          			<option>펄어비스</option>
		      		</select>
		      	
					<select name="investmentOpinionForFiltering">
		          			<option value="">투자의견</option>
		          			<option>buy</option>
		          			<option>hold</option>
		          			<option>sell</option>
		      		</select>
		      	
					<select name="investmentProcessForFiltering">
		          			<option value="">투자과정</option>
		          			<option>분석,공부</option>
		          			<option>매수</option>
		          			<option>매도</option>
		          			<option>영감</option>
		      		</select>
		      
		      		<button type="submit" id="filteringBtn" class="btn">필터링</button>
				</form>
			</div>
		</div>
		
		<div class="col-8 d-flex justify-content-center">
			<div class="post-timeline-box">
				<c:forEach var="postWithOthers" items="${postList }">
					<!-- 보여지는 투자 게시글 -->
					<div class="card mt-3">			
						<!-- 글 시작부분 -->
						<div class="d-flex justify-content-between p-1 border-bottom">
							
							<!-- 글쓴이 -->
							<div>
								<c:choose>
									<c:when test="${!empty postWithOthers.writerProfileImage }" >
										<img src="${postWithOthers.writerProfileImage }" width="30" height="30">
									</c:when>
									<c:otherwise>
										<img src="https://mblogthumb-phinf.pstatic.net/20150203_225/hkjwow_1422965971196EfkMV_JPEG/%C4%AB%C5%E5%C7%C1%BB%E7_31.jpg?type=w210" width="30">	
									</c:otherwise>
								</c:choose>							
								<a href="/post/individual_home_view?userId=${postWithOthers.investPost.userId }" class="font-weight-bold text-dark">
									${postWithOthers.investPost.userNickName }
								</a>
							</div>
							
							<!-- 좋아요 -->
							<div>
								<a href="#" class="likeBtn" data-post-id="${postWithOthers.investPost.id }">
									<c:choose>
										<c:when test="${postWithOthers.like }" >
											<i class="bi bi-heart-fill heart-icon text-danger" data-status="like" id="heartIcon-${postWithOthers.investPost.id }"></i>
										</c:when>
										<c:otherwise>
											<i class="bi bi-heart heart-icon text-dark" id="heartIcon-${postWithOthers.investPost.id }"></i>	
										</c:otherwise>
									</c:choose>
								</a>
								<span class="middle-size ml-1"> 
									좋아요 <span id="likeCount-${postWithOthers.investPost.id }" >${postWithOthers.likeCount }</span>개 
								</span>
							</div>
							
							<%-- 글 의 userId 와 세션의 userId 가 일치하면 더보기 버튼 노출 --%>
							<c:if test="${postWithOthers.investPost.userId eq userId}">
								<div class="more-icon" >
									<a href="#" class="text-dark moreBtn" data-toggle="modal" data-target="#postEditModal" data-post-id="${postWithOthers.investPost.id }"> 
										<i class="bi bi-three-dots-vertical"></i> 
									</a>
								</div>
							</c:if>
							
						</div>
						<!-- /글 시작부분 -->
						
						<div class="d-flex justify-content-around border-bottom p-1">
							<div class="font-weight-bold">${postWithOthers.investPost.investStyle }</div>
							<div class="font-weight-bold">${postWithOthers.investPost.stockItemName }</div>
							<div class="font-weight-bold">${postWithOthers.investPost.investmentOpinion }</div>
							<div class="font-weight-bold">${postWithOthers.investPost.investmentProcess }</div>
						</div>
						
						<!-- 내용 -->
						<div class="middle-size m-2 d-flex justify-content-between">
							<div>
								${postWithOthers.investPost.content }
							</div>
							<div>
								<img src="${postWithOthers.investPost.imagePath }" width="285">
							</div>
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
								<input type="text" class="form-control border-0 " id="commentInput-${postWithOthers.investPost.id }">
								<button class="btn btn-info ml-2 commentBtn" data-post-id="${postWithOthers.investPost.id }">게시</button>
							</div>
							
						</div>
						<!-- /댓글 -->	
					</div>
					<!-- /보여지는 투자 게시글 -->
				</c:forEach>
				
			</div>
		</div>
		<div class="col-2">
		</div>

	</section>
	
	<c:import url="/WEB-INF/jsp/include/footer.jsp" />
	
	<!-- 글쓰기Modal -->
	<div class="modal fade" id="writeModal" tabindex="-1" role="dialog" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-body text-center">
	        <!--  게시글 작성  -->
			<div>
				<h3>투자게시글 작성</h3>
				<div class="d-flex">
					<select id="investStyle">
            			<option>투자스타일</option>
            			<option>단타x</option>
            			<option>단타</option>
        			</select>
					<select id="stockItemName">
            			<option>관심종목</option>
            			<option>카카오게임즈</option>
            			<option>펄어비스</option>
        			</select>
					<select id="investmentOpinion">
            			<option>투자의견</option>
            			<option>buy</option>
            			<option>hold</option>
            			<option>sell</option>
        			</select>
					<select id="investmentProcess">
            			<option>투자과정</option>
            			<option>분석,공부</option>
            			<option>매수</option>
            			<option>매도</option>
            			<option>영감</option>
        			</select>
				</div>
			
				<div class="border rounded mt-1">
					<textarea class="form-control w-100 border-0 non-resize" rows=4 id="contentInput"></textarea>			
					<!--  이미지  -->
					<div class="d-flex justify-content-between m-2">
						<input type="file" class="input-control d-none" id="fileInput">
						<a href="#" id="imageUploadBtn"><i class="bi bi-image image-upload-icon"></i></a>
						<button class="btn btn-sm btn-info" id="uploadBtn">업로드</button>
					</div>
				</div>		
			</div>
			<!--  /게시글 작성  -->
	      </div>
	    </div>
	  </div>
	</div>
	<!-- /글쓰기Modal -->
	
	<!-- 모달의 a태그에 data-post-id 의 값을 더보기 클릭시마다 바꿔준다.  -->
	<!-- 글수정Modal -->
	<div class="modal fade" id="postEditModal" tabindex="-1" role="dialog" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-body text-center">
	        <a href="#" id="deleteBtn" >삭제하기 </a>
	      </div>
	  
	    </div>
	  </div>
	</div>
	<!-- /글수정Modal -->	
	
	<script>
		$(document).ready(function() {

			
	        
			
	        // <글쓰기 버튼 눌렀을때>
			$("#writeBtn").on("click", function() {
		    	var investStyle;
		    	var stockItemName;
		    	var investmentOpinion;
		    	var investmentProcess;
		    	
		        $("#investStyle").on("change", function() {
		        	investStyle = $("#investStyle option:selected").val();
		        });
		        $("#stockItemName").on("change", function() {
		        	stockItemName = $("#stockItemName option:selected").val();
		        });
		        $("#investmentOpinion").on("change", function() {
		        	investmentOpinion = $("#investmentOpinion option:selected").val();
		        });
		        $("#investmentProcess").on("change", function() {
		        	investmentProcess = $("#investmentProcess option:selected").val();
		        });
				
				$("#uploadBtn").on("click", function() {
					let content = $("#contentInput").val().trim();
						
					if(content == null || content == "") {
						alert("내용을 입력하세요");
						return ;
					}
				
					var formData = new FormData();
					formData.append("file", $("#fileInput")[0].files[0]);
					formData.append("content", content);
					formData.append("investStyle", investStyle);
					formData.append("stockItemName", stockItemName);
					formData.append("investmentOpinion", investmentOpinion);
					formData.append("investmentProcess", investmentProcess);
					
					$.ajax({
						enctype: 'multipart/form-data', // 필수
						type:"POST",
						url:"/post/create/invest",
						processData: false, // 필수 
			        	contentType: false, // 필수 
						data:formData,
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
				
				$("#imageUploadBtn").on("click", function() {
					$("#fileInput").click();
				});
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
					url:"/comment/create/invest",
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
					url:"/post/like/invest",
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