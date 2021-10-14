<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<header class="d-flex">
		<c:import url="/WEB-INF/jsp/include/viewList.jsp" />
		<div class="col-4 d-flex justify-content-center">
			<h2 class="pt-3 text-danger">개인홈</h2>
		</div>
		<c:import url="/WEB-INF/jsp/include/userSector.jsp" />
	</header>
	<hr>
	<section>
		<div class="d-flex">
			
			<div class="col-2 d-flex">
				<!-- 개인 프로필 -->
				<div class="profile-box">
					<h3>프로필</h3>
					<div class="card">
						<div class="p-2">
							<c:choose>
								<c:when test="${!empty userInfo.profileImage }" >
									<img src="${userInfo.profileImage }" width="200" height="200">
								</c:when>
								<c:otherwise>
									<img src="https://mblogthumb-phinf.pstatic.net/20150203_225/hkjwow_1422965971196EfkMV_JPEG/%C4%AB%C5%E5%C7%C1%BB%E7_31.jpg?type=w210" width="200" height="200">
								</c:otherwise>
							</c:choose>
						</div>
						<div class="pl-2 border-bottom">
							<b class="user">${userInfo.nickName }</b>
							<c:if test="${userId eq userInfo.id }">
									<a href="#" data-toggle="modal" data-target="#profileEditModal"> 
										프로필 편집
									</a>
							</c:if>
						</div>
						<div>
							<div class="status-message-box border-bottom">
								<c:choose>
									<c:when test="${!empty userInfo.profileStatusMessage }" >
										${userInfo.profileStatusMessage }
									</c:when>
									<c:otherwise>
										상태메세지
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					
					<h4 class="mt-4">위치정보</h4>
					<div class="card">				
						<c:choose>
							<c:when test="${!empty userInfo.location }" >
								<b>설정된 위치 <br>: ${userInfo.location }</b>
							</c:when>
							<c:otherwise>
								<b>위치설정 안되있음</b>
							</c:otherwise>
						</c:choose>

						<c:if test="${userId eq userInfo.id }">
							<!-- 다음 우편번호서비스 변형 -->
						    <div class="input-group location-setting">
							    <span class="input-group-btn">
									<button type="button" class="btn" onclick="sample2_execDaumPostcode()">주소찾기</button>
							    </span>
							    <input type="text" class="form-control" id="locationInput" placeholder="주소">
						    </div><!-- /input-group -->
							<input type="button" id="locationCompletion" value="위치설정 완료">
		
							<!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
							<div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
							<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
							</div>
							
							<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
							<script>
							    // 우편번호 찾기 화면을 넣을 element
							    var element_layer = document.getElementById('layer');
							
							    function closeDaumPostcode() {
							        // iframe을 넣은 element를 안보이게 한다.
							        element_layer.style.display = 'none';
							    }
							
							    function sample2_execDaumPostcode() {
							        new daum.Postcode({
							            oncomplete: function(data) {
							                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
											// data는 사용자가 선택한 주소 정보를 담고 있는 객체
							                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
							                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
							                var addr = ''; // 주소 변수
							                var extraAddr = ''; // 참고항목 변수
							
							                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
							                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
							                    addr = data.roadAddress;
							                } else { // 사용자가 지번 주소를 선택했을 경우(J)
							                    addr = data.jibunAddress;
							                }
							
							                // 우편번호와 주소 정보를 해당 필드에 넣는다.
						
							                document.getElementById("locationInput").value = addr;
							                // 커서를 상세주소 필드로 이동한다.
						
							
							                // iframe을 넣은 element를 안보이게 한다.
							                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
							                element_layer.style.display = 'none';
							            },
							            width : '100%',
							            height : '100%',
							            maxSuggestItems : 5
							        }).embed(element_layer);
							
							        // iframe을 넣은 element를 보이게 한다.
							        element_layer.style.display = 'block';
							
							        // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
							        initLayerPosition();
							    }
							
							    // 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
							    // resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
							    // 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
							    function initLayerPosition(){
							        var width = 300; //우편번호서비스가 들어갈 element의 width
							        var height = 400; //우편번호서비스가 들어갈 element의 height
							        var borderWidth = 5; //샘플에서 사용하는 border의 두께
							
							        // 위에서 선언한 값들을 실제 element에 넣는다.
							        element_layer.style.width = width + 'px';
							        element_layer.style.height = height + 'px';
							        element_layer.style.border = borderWidth + 'px solid';
							        // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
							        element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
							        element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
							    }
							</script>
							<!-- /다음 우편번호서비스 변형 -->	
						</c:if>

						
					</div>
					
				</div>
				<!-- /개인 프로필 -->
			</div>
			
			<div class="col-8 d-flex justify-content-center">
				<!-- 개인의 투자 게시글 -->
				<div class="post-timeline-box">
					<h3>${userInfo.nickName }님의 개인홈</h3>
					<c:forEach var="postWithOthers" items="${postList }">
						<div class="card mt-3">			
							<!-- 글 시작부분 -->
							<div class="d-flex justify-content-between p-2 border-bottom">
								
								<!-- 작성,업데이트 시간 -->
								<div>
									작성시간:<fmt:formatDate value="${postWithOthers.investPost.createdAt }" pattern="yy년 M월 d일 HH시 mm분" /> 	
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
								${postWithOthers.investPost.content }
								<img src="${postWithOthers.investPost.imagePath }" width="285">
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
	
	<!-- 프로필Modal -->
	<div class="modal fade" id="profileEditModal" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
	    	<div class="modal-content">
	      		<div class="modal-body text-center">
					<div>
						<b>프로필 이미지</b>
						<input type="file" class="input-control" id="profileImageInput">
						<br>
						<b>프로필 상태메세지</b>
						<textarea class="form-control w-100 non-resize" rows=4 id="profileStatusMessageInput">
							텍스트 쓰는곳
						</textarea>
						<button class="btn" id="profileCompletion">편집완료</button>			
					</div>
	      		</div>
	    	</div>
		</div>
	</div>
	<!-- /프로필Modal -->	
	
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

			// <위치설정>
			$("#locationCompletion").on("click", function() {
				var locationInput = $("#locationInput").val().trim();
				var array = locationInput.split(" ");
				
				var userLocation="";

				for (var i = 0; i < array.length-1; i++) {
					userLocation += (array[i] + " ");
				}
				
				$.ajax({
					type:"get",
					url:"/user/location",
					data:{"location":userLocation},
					success:function(data) {
						if(data.result == "success") {
							alert("위치설정 성공");
							location.reload();
						} else {
							alert("위치설정 실패");
						}
					},
					error:function(e) {
						alert("error");
					}
				});
			});
			// </위치설정>
			
			// <프로필 설정>
			$("#profileCompletion").on("click", function() {
				let profileStatusMessage = $("#profileStatusMessageInput").val().trim();
				
				var formData = new FormData();
				formData.append("file", $("#profileImageInput")[0].files[0]);
				formData.append("profileStatusMessage", profileStatusMessage);
				
				$.ajax({
					enctype: 'multipart/form-data', // 필수
					type:"post",
					url:"/user/profile",
					processData: false, // 필수 
					contentType: false, // 필수
					data:formData,
					success:function(data) {
						if(data.result == "success") {
							alert("프로필설정 성공");
							location.reload();
						} else {
							alert("프로필설정 실패");
						}
					},
					error:function(e) {
						alert("error");
					}
				});
			});
			
			// </프로필 설정>			
			
		});
	</script>
</body>
</html>