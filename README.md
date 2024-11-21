![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)  

# Invest SNS
* 투자(주식)라는 주제에 집중한 SNS, 투자를 하면서 사람들과 소통하면 재밌을꺼 같아 만들었다.
* 간단한 웹 서비스지만, 기술들을 연습하고 적용해 본다는 의의를 가지고 만들었다.

## 사용된 기술들
* [Docker Compose](https://github.com/doriver/DockerTest01/tree/master/compose/03)
도커 컨테이너 운영에 필요한 것들( buildContext, volume등을 compose.yaml이 있는 디렉토리에서 모아서 관리 )
* [Redis with Spring](https://github.com/doriver/SpringRedis01) : 여기서 연습, 테스트 해보고 프로젝트에 적용
  * [RedisConfig.java](https://github.com/doriver/stock_invest_sns_current/blob/master/src/main/java/com/sns/invest/config/RedisConfig.java) 설정파일, [RedisDAO.java](https://github.com/doriver/stock_invest_sns_current/blob/master/src/main/java/com/sns/invest/post/dao/RedisDAO.java) 자료구조set관련 crud
* [Nginx](https://github.com/doriver/DockerTest01/blob/master/compose/03/webServer/etcNginx/conf.d/default.conf) : nginx설정 파일( 리버스 프록시로서 로드밸런싱, 캐싱기능, 무중단 배포를 구현 )
* [Querydsl사용 부분](https://github.com/doriver/stock_invest_sns_current/blob/master/src/main/java/com/sns/invest/post/dao/custom/InvestPostRepositoryCustomImpl.java)  ‘게시글 필터링 기능’에서 필터링 조건에 따라 sql문을 동적으로 처리

## 인증, 권한 처리( Security + JWT + Redis )
[SecurityConfig.java](https://github.com/doriver/stock_invest_sns_current/blob/master/src/main/java/com/sns/invest/config/SecurityConfig.java) : SecurityFilterChain을 사용해 JWT인증방식을 사용     
[src/main/java/com/sns/invest/security](https://github.com/doriver/stock_invest_sns_current/tree/master/src/main/java/com/sns/invest/security)
* AccessJWT는 Cookie에서, RefreshJWT는 Redis에서 관리
* UserDetailsService를 통해 사용자 정보를 로드
* [JwtAuthenticationFilter.java](https://github.com/doriver/stock_invest_sns_current/blob/master/src/main/java/com/sns/invest/security/jwt/JwtAuthenticationFilter.java) : JWT에따라 인증로직 처리 
* [JwtTokenProvider.java](https://github.com/doriver/stock_invest_sns_current/blob/master/src/main/java/com/sns/invest/security/jwt/JwtTokenProvider.java) : JWT의 생성, 복호화, 검증

## Rest API
#### 예외 처리 custom
[ExControllerAdvice.java](https://github.com/doriver/stock_invest_sns_current/blob/master/src/main/java/com/sns/invest/exception/advice/ExControllerAdvice.java) : @ControllerAdvice에서 @ExceptionHandler로 공통예외처리
#### api 응답형식
* 예외 발생했을때 : [ErrorResult.java](https://github.com/doriver/stock_invest_sns_current/blob/master/src/main/java/com/sns/invest/exception/ErrorResult.java)
* 정상 : [ApiResponse.java](https://github.com/doriver/stock_invest_sns_current/blob/master/src/main/java/com/sns/invest/common/ApiResponse.java)
#### BeanValidation : [메서드signUp](https://github.com/doriver/stock_invest_sns_current/blob/master/src/main/java/com/sns/invest/user/UserRestController.java#L62)
#### 컨트롤러 메서드 매개변수 custom
[UserInfoArgumentResolver.java](https://github.com/doriver/stock_invest_sns_current/blob/master/src/main/java/com/sns/invest/common/argumentResolver/UserInfoArgumentResolver.java) : HandlerMethodArgumentResolver구현해서 UserInfo를 매개변수로 받을수 있게 함

## 요청마다 'ip주소'로 구분
[CmnFilterInterceptor.java](https://github.com/doriver/stock_invest_sns_current/blob/master/src/main/java/com/sns/invest/common/CmnFilterInterceptor.java) : ip주소 얻는 메소드 있음    
[LogInterceptor.java](https://github.com/doriver/stock_invest_sns_current/blob/master/src/main/java/com/sns/invest/common/interceptor/LogInterceptor.java) : Interceptor에서 요청마다 ip주소와 id값(UUID)을 부여하고 log로 남김

## 타임라인
* [Format에 데이터들 담기](https://github.com/doriver/stock_invest_sns_current/blob/master/src/main/java/com/sns/invest/post/bo/PostBO.java#L79)
* 이미지 첨부 게시글 작성 : [메소드investPostCreate](https://github.com/doriver/stock_invest_sns_current/blob/master/src/main/java/com/sns/invest/post/PostCreateController.java#L40)
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



