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
	
		<section class="d-flex justify-content-center">
			<div class="w-75 my-5">
				<h1 class="text-center">종목명 가십</h1>
				<table class="table text-center">
					<thead>
						<tr>
							<th>조회수</th>
							<th>제목</th>
							<th>글쓴이</th>
							<th>좋아요 개수</th>									
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>112</td>
							<td>간다</td>
							<td>하정우</td>
							<td>10</td>
						</tr>	
						<tr>
							<td>14</td>
							<td>살려줘</td>
							<td>ㄴㅁㅇㄹ</td>
							<td>5</td>
						</tr>	
					</tbody>
				</table>	
			</div>
		</section>
	
	<footer>
	<hr>
		copyright ~~
	</footer>
</body>
</html>