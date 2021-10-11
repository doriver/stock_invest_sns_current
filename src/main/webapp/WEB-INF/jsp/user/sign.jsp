<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>투자SNS - 로그인/회원가입</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  	
  	<link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
	<header class="d-flex justify-content-between">
		<div class="d-flex pt-1 pl-2">
			<img src="https://cdn.pixabay.com/photo/2021/09/09/04/26/coins-6609452_960_720.jpg" width="50" height="50">
			<h2 class="text-danger mt-2">투자SNS</h2>
		</div>
		<a href="/post/guest_view" class="text-danger mr-2 mt-3">비로그인화면</a>
	</header>
	<hr>
	
	<section class="d-flex justify-content-center">
		
		<form id="loginForm">
			<h2>로그인</h2>
			<input type="text" id="idForLogin" class="form-control mt-3" placeholder="아이디">
			<input type="password" id="passwordForLogin" class="form-control mt-3" placeholder="패스워드">
			<button id="loginBtn" type="submit" class="btn btn-primary btn-block mt-3">로그인</button>
		</form>
		<img src="https://cdn.pixabay.com/photo/2016/11/11/10/41/forex-1816354_960_720.jpg" width="400" height="600">
		<form id="signUpForm">
			<h2>회원가입</h2>
			<input type="text" id="loginIdInput" class="form-control" placeholder="아이디">
			<button type="button" class="btn btn-info btn-sm" id="isDuplicateBtn">중복확인</button>
			
			<div id="duplicateDiv" class="d-none"><small class="text-danger">중복된 ID 입니다.</small></div>
			<div id="noneDuplicateDiv" class="d-none"><small class="text-success">사용 가능한 ID 입니다.</small></div>
			
			<input type="password" id="passwordInput" class="form-control mt-3" placeholder="패스워드">
			<input type="password" id="passwordConfirmInput" class="form-control mt-3" placeholder="패스워드 확인">
			<small id="errorPassword" class="text-danger d-none">비밀번호가 일치하지 않습니다.</small>
			<input type="text" id="nickNameInput" class="form-control mt-3" placeholder="nickName">
			<input type="text" id="emailInput" class="form-control mt-3" placeholder="이메일">
			
			<button type="submit" id="signUpBtn" class="btn btn-info btn-block mt-3">회원가입</button>
		</form>
	</section>
	
	<c:import url="/WEB-INF/jsp/include/footer.jsp" />
	
	<script type="text/javascript">
		$(document).ready(function() {
			// <로그인>
			$("#loginForm").on("submit", function(e) {
				
				e.preventDefault();
				
				var idForLogin = $("#idForLogin").val();
				var passwordForLogin = $("#passwordForLogin").val();
				
				if(idForLogin == null || idForLogin == "") {
					alert("아이디를 입력해주세요");
					return ;
				}
				
				if(passwordForLogin == null || passwordForLogin == "") {
					alert("비밀번호를 입력해주세요");
					return ;
				}
				
				$.ajax({
					type:"post",
					url:"/user/sign_in",
					data:{"idForLogin":idForLogin, "passwordForLogin":passwordForLogin},
					success:function(data) {
						if(data.result == "success") {
							alert("로그인 성공");
							location.href="/post/invest_view";
						} else {
							alert("아이디 비밀번호를 확인하세요");
						}
					}
					, error:function(e) {
						alert("로그인실패");
					}
				});
			});
			// </로그인>
			
			var isIdCheck = false;
			var isDuplicateId = true;
			
			// <회원가입>
			$("#signUpForm").on("submit", function(e) {
				
				e.preventDefault();
				
				var loginId = $("#loginIdInput").val();
				var password = $("#passwordInput").val();
				var passwordConfirm = $("#passwordConfirmInput").val();
				var nickName = $("#nickNameInput").val().trim();
				var email = $("#emailInput").val().trim();
				
				if(loginId == null || loginId == "") {
					alert("아이디를 입력하세요");
					return ;
				}
				
				if(password == null || password == "") {
					alert("비밀번호를 입력하세요");
					return ;
				}
				
				if(password != passwordConfirm) {
					$("#errorPassword").removeClass("d-none");
					return ;
				}
				
				if(nickName == null || nickName == "") {
					alert("nickName을 입력하세요");
					return ;
				}
				
				if(email == null || email == "") {
					alert("이메일을 입력하세요");
					return ;
				}
				
				// 중복체크 했는지?
				if(isIdCheck == false) {
					alert("중복체크를 진행하세요");
					return ;
				}
						
				// 중복이 되었는지 안되었는지?
				if(isDuplicate == true) {
					alert("아이디가 중복되었습니다.");
					return ;
				}
				
				$.ajax({
					type:"post",
					url:"/user/sign_up",
					data:{"loginId":loginId, "password":password, "nickName":nickName, "email":email},
					success:function(data) {
						if(data.result == "success") {
							alert("회원가입 성공");
							location.href="/user/sign_view";
							
						} else {
							alert("회원 가입 실패");
						}
					}, 
					error:function(e) {
						alert("회원 가입 실패");
					}
					
					
				});
			});
			// </회원가입>
			
			// <중복확인> 
			$("#isDuplicateBtn").on("click", function() {
			
				var loginId = $("#loginIdInput").val();
				
				if(loginId == null || loginId == "") {
					alert("아이디를 입력하세요");
					return ;
				}
				
				$.ajax({
					type:"get",
					url:"/user/is_duplicate_id",
					data:{"loginId":loginId},
					success:function(data) {
						isIdCheck = true;
						
						if(data.is_duplicate) {
							isDuplicate = true;
							$("#duplicateDiv").removeClass("d-none");
							$("#noneDuplicateDiv").addClass("d-none");
						} else {
							isDuplicate = false;
							$("#duplicateDiv").addClass("d-none");
							$("#noneDuplicateDiv").removeClass("d-none");
						}
						//isDuplicate = data.is_duplicate;
						
					},
					error:function(e){
						alert("중복확인 실패");
					}
					
					
				});
			})
			// </중복확인>
		});
	
	</script>
</body>
</html>