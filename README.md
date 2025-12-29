![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)  

# Invest SNS
* 투자(주식)라는 주제에 집중한 SNS, 투자를 하면서 사람들과 소통하면 재밌을꺼 같아 만들었다.
* 간단한 웹 서비스지만, 기술들을 연습하고 적용해 본다는 의의를 가지고 만들었다.
## 아키텍쳐
<img width="910" height="378" alt="image" src="https://github.com/user-attachments/assets/fbf60e82-0b2a-41af-8687-adeef7dd3b42" />

* 로컬에서 docker-compose로 Nginx, SpringBoot 실행    
MySQL은 로컬 호스트(Window)에 설치, Redis는 WSL(Windows Subsystem for Linux)에 설치

## 미리 보기
* 회원가입,로그인,로그아웃
* 프로필설정과 프로필 이미지 반영
<div>
  <img src="demo_gif/회원가입,로그인,로그아웃.gif" alt="demo1" width="370" height="250">
  &nbsp&nbsp&nbsp
  <img src="demo_gif/프로필설정.gif" alt="demo3" width="370" height="250">  
</div>
     
* 이미지 첨부 글쓰기, 좋아요,댓글기능, 글 삭제기능    
* 게시글 필터링
<div>
  <img src="demo_gif/글쓰기,좋아요,댓글,글삭제.gif" alt="demo2" width="370" height="250">
  &nbsp&nbsp&nbsp
  <img src="demo_gif/필터링.gif" alt="demo4" width="370" height="250">  
</div>

* 위치정보 설정과 지역커뮤니티
* 다른사람의 개인홈 , 가십게시판    
<div>
  <img src="demo_gif/위치설정.gif" alt="demo5" width="370" height="250">
  &nbsp&nbsp&nbsp
  <img src="demo_gif/개인홈,가십게시판.gif" alt="demo6" width="370" height="250">  
</div>

## 인증, 권한 처리( Security + JWT + Redis )
* SecurityFilterChain을 사용해 JWT인증방식을 사용     
* AccessJWT는 Cookie에서, RefreshJWT는 Redis에서 관리

