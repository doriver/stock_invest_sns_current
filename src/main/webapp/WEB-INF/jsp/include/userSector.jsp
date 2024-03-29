<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 
<div class="col-4 d-flex justify-content-end">
 	<div class="pt-4 user">
	 	<b>${userNickName }님</b>
 	</div>
	<div class="dropdown">
	  <a class="btn dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">				
		<c:choose>
			<c:when test="${!empty myInfo.profileImage }" >
				<img src="${myInfo.profileImage }" width="50" height="50">
			</c:when>
			<c:otherwise>
				<img src="https://mblogthumb-phinf.pstatic.net/20150203_225/hkjwow_1422965971196EfkMV_JPEG/%C4%AB%C5%E5%C7%C1%BB%E7_31.jpg?type=w210" width="50" height="50">
			</c:otherwise>
		</c:choose> 
	  </a>
	  <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuLink">
	    <a class="dropdown-item" href="#" id="writeBtn" data-toggle="modal" data-target="#writeModal"> 
				글쓰기 
		</a>
	    <a class="dropdown-item" href="/sign-out">로그아웃</a>
	  </div>
	</div>
</div>