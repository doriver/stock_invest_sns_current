<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가십게시판</title>
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
		<c:forEach var="postWithOthers" items="${postList }">
			<!-- 보여지는 가십 게시글 -->
			<div class="card mt-3">			
				<!-- 글 시작부분 -->
				<div class="d-flex justify-content-between p-2 border-bottom">
					
					<!-- 글쓴이 -->
					<div>
						<img src="https://mblogthumb-phinf.pstatic.net/20150203_225/hkjwow_1422965971196EfkMV_JPEG/%C4%AB%C5%E5%C7%C1%BB%E7_31.jpg?type=w210" width="30">
						<a href="/post/individual_home_view?userId=${postWithOthers.investPost.userId }" class="homeLink">
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
							<a href="#" class="text-dark moreBtn"> 
								<i class="bi bi-three-dots-vertical"></i> 
							</a>
						</div>
					</c:if>
					
				</div>
				<!-- /글 시작부분 -->
				
				<!-- 내용 -->
				<div class="middle-size m-2">
					${postWithOthers.investPost.content }
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
			<!-- /보여지는 가십 게시글 -->
		</c:forEach>
	</section>
	<footer>
	<hr>
		copyright ~~
	</footer>
</body>
</html>