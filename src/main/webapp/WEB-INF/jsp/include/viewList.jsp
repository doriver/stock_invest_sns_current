<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<ul class="nav nav-fill">
    <li class="nav-item"><a href="/local-view" class="nav-link">지역커뮤니티</a></li>
    <li class="nav-item"><a href="/invest-view" class="nav-link">투자게시판</a></li>
    <li class="nav-item"><a href="/gossip-view" class="nav-link">가십게시판</a></li>
    <li class="nav-item"><a href="/individual-home-view?userId=${userId }" class="nav-link">나의 개인홈</a></li>
</ul>
