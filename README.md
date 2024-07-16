![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
![jQuery](https://img.shields.io/badge/jquery-%230769AD.svg?style=for-the-badge&logo=jquery&logoColor=white)
![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)
![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)  
![Eclipse](https://img.shields.io/badge/Eclipse-FE7A16.svg?style=for-the-badge&logo=Eclipse&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white)

## Invest SNS
### 프로젝트 설명
* 투자(주식)라는 주제에 집중한 SNS
* 본인은 주식과 같은 투자를 즐기고 있다. 투자를 하면서 사람들과 소통하면 재밌을꺼 같아 만들었다.
* 간단한 웹 서비스지만, 처음으로 웹서비스를 0상태 부터 aws배포까지 혼자힘으로 만들어 보았다는데 의의를 가지고 만들었다. 앞으로 계속 프로젝트를 발전시켜나갈 계획이다

### 설계
* DB, URL 설계   
https://docs.google.com/spreadsheets/d/1_QpRGkAXKIM6abuEIAi9KrX9PICSgYqVNsSVod2_kko/edit?usp=sharing
* View 설계   
https://docs.google.com/document/d/1esscSpkAixA-uKDUyxY8e8KNMoBwK3G4M7FvQ2fzp9U/edit?usp=sharing
### 주요기능
* 회원가입,로그인,로그아웃
  * package : src/main/java/com/sns/invest/user
  * view
    * src/main/webapp/WEB-INF/jsp/user/sign.jsp
    * src/main/webapp/WEB-INF/jsp/include/userSector.jsp(로그아웃)
    
* 프로필설정과 프로필이미지 반영
  * package : src/main/java/com/sns/invest/user
  * view : src/main/webapp/WEB-INF/jsp/post/individualHome.jsp
* 이미지 첨부 글쓰기 , 글삭제 기능
  * package
    * src/main/java/com/sns/invest/post
    * src/main/java/com/sns/invest/comment/bo
  * view
    * src/main/webapp/WEB-INF/jsp/post/investTimeline.jsp
    * src/main/webapp/WEB-INF/jsp/include/userSector.jsp(글삭제) 
* 타임라인 기능 + 좋아요 , 댓글
  * package
    * src/main/java/com/sns/invest/post(타임라인, 댓글)
    * src/main/java/com/sns/invest/comment
  * view : src/main/webapp/WEB-INF/jsp/post/investTimeline.jsp
* 게시글 필터링
  * package : src/main/java/com/sns/invest/post
  * view : src/main/webapp/WEB-INF/jsp/post/filteredInvestTimeline.jsp
* open api(다음 우편번호 서비스)이용한 위치정보 설정 + 지역커뮤니티
  * package : src/main/java/com/sns/invest/user
  * view : src/main/webapp/WEB-INF/jsp/post/individualHome.jsp
* 나의 개인홈과 다른사람의 개인홈
  * package
    * src/main/java/com/sns/invest/post
    * src/main/java/com/sns/invest/user/bo
  * view : src/main/webapp/WEB-INF/jsp/post/individualHome.jsp


### 데모
* 회원가입,로그인,로그아웃
![demo1](demo_gif/회원가입,로그인,로그아웃.gif)

* 이미지 첨부 글쓰기, 좋아요,댓글기능, 글 삭제기능
![demo2](demo_gif/글쓰기,좋아요,댓글,글삭제.gif)

* 프로필설정과 프로필 이미지 반영
![demo3](demo_gif/프로필설정.gif)

* 게시글 필터링
![demo4](demo_gif/필터링.gif)

* 위치정보 설정과 지역커뮤니티
![demo5](demo_gif/위치설정.gif)

* 다른사람의 개인홈 , 가십게시판
![demo6](demo_gif/개인홈,가십게시판.gif)



### 외부 라이브러리 라이센스
* Tomcat [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0) 
* Mysql [GPLv2 or proprietary](https://www.gnu.org/licenses/gpl-3.0.html)
* Spring framework [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0)  
* Mybatis [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0)
* Bootstrap [MIT License](https://opensource.org/licenses/MIT)
* jQuery [MIT License](https://opensource.org/licenses/MIT)
* 이미지 출처 : [pixabay](https://pixabay.com/ko/)
### 향후계획
* 팔로우 기능
* 주식 종목들 가져오기
* spring security
* ip주소 반영한 위치정보 기능
* 주식 계좌 연동(수익 인증)

