<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인홈</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  	
  	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
  	 
  	<link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
	<div class="d-flex">
		<div class="card col-4">
			<div class="border-bottom">
				<img src="https://mblogthumb-phinf.pstatic.net/20150203_225/hkjwow_1422965971196EfkMV_JPEG/%C4%AB%C5%E5%C7%C1%BB%E7_31.jpg?type=w210" width="100">
			</div>
			<div>
				<b>nickname</b>
			</div>
			<div>
				상태매세지
				<button>프로필 편집</button>
			</div>
		</div>
		
			<div class="col-7">
			<!-- 투자 게시글 1 -->
			<div class="card mt-3">
							
				<!-- 글 시작부분 -->
				<div class="d-flex justify-content-between p-2 border-bottom">
					
					<!-- 글쓴이 -->
					<div>
						<img src="https://mblogthumb-phinf.pstatic.net/20150203_225/hkjwow_1422965971196EfkMV_JPEG/%C4%AB%C5%E5%C7%C1%BB%E7_31.jpg?type=w210" width="30">
						홍길동
					</div>
					
					<!-- 좋아요 -->
					<div>
						<i class="bi bi-heart-fill heart-icon text-danger"></i>
						<i class="bi bi-heart heart-icon text-dark" ></i>	
						<span class="middle-size ml-1"> 좋아요 2개 </span>
					</div>
					
					<%-- 글 의 userId 와 세션의 userId 가 일치하면 더보기 버튼 노출 --%>
					<div class="more-icon" >
						<a href="#" class="text-dark moreBtn"> 
							<i class="bi bi-three-dots-vertical"></i> 
						</a>
					</div>
					
				</div>
				<!-- /글 시작부분 -->
				
				<div class="d-flex">
					<button>카카오게임즈</button>
					<button>단타x</button>
					<button>buy</button>
					<button>분석,공부</button>
				</div>
				
				<!-- 내용 -->
				<div class="middle-size m-2">
					떡상각
					<img src="https://cdn.pixabay.com/photo/2018/01/12/16/16/growth-3078543_960_720.png" width="100">
				</div>
				
				<!-- 댓글 -->
				<div class="mt-2">
				
					<div class="border-bottom m-2">
						<span class="middle-size">댓글</span>
					</div>
					
					<!--  댓글현황  -->
					<div class="middle-size m-2">
						<div class="mt-1">
							<b>원빈</b> 진짜?
						</div>
						<div class="mt-1">
							<b>이나영</b> 나도 살까?
						</div>
					</div>
					
					<!-- 댓글 입력 -->
					<div class="d-flex mt-2 border-top">
						<input type="text" class="form-control border-0 ">
						<button class="btn btn-info ml-2 commentBtn" >게시</button>
					</div>
					
				</div>
				<!-- /댓글 -->	
			</div>
			<!-- /투자 게시글 1 -->
	
	</div>
	
	
</body>
</html>