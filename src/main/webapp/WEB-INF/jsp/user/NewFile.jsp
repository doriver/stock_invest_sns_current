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
<body>
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

</body>				
</html>