package com.sns.invest.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.invest.user.model.User;
import com.sns.invest.user.model.UserSaveForm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.sns.invest.common.ApiResponse;
import com.sns.invest.common.CmnValidation;
import com.sns.invest.common.argumentResolver.UserInfo;
import com.sns.invest.post.bo.PostBO;
import com.sns.invest.security.jwt.JwtToken;
import com.sns.invest.security.jwt.RedisDAO;
import com.sns.invest.user.bo.UserBO;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserRestController {
	
	private final UserBO userBO;
	private final CmnValidation cmnValidation;
	private final RedisDAO redisDao;
	
	@PostMapping("/users/sign-in")
    public String signIn(@RequestParam("username") String username, @RequestParam("password") String password
    		, HttpServletResponse response) {
		
		JwtToken jwtToken = userBO.signIn(username, password);
		log.info("jwtToken accessToken = {}, refreshToken = {}", jwtToken.getAccessToken(), jwtToken.getRefreshToken());
		
		redisDao.saveWithTTL(
				jwtToken.getAccessToken(), jwtToken.getRefreshToken()
        		, 5, TimeUnit.MINUTES);
		
		/*ACCESS TOKEN 쿠키로 발급*/
        Cookie accessCookie = new Cookie("Authorization", jwtToken.getAccessToken());
        accessCookie.setHttpOnly(true);
        accessCookie.setMaxAge(90 * 60); // 90분 동안 유효
        
        response.addCookie(accessCookie);
        
		return "redirect:/invest-view";
	}
	
	// 아이디 중복확인 기능 - 입력받은id를 db에서 조회(select where) 
	@GetMapping("/users/{loginId}")
	public ApiResponse<Map<String, Boolean>> isDuplicateId (
			@PathVariable("loginId") String loginId) {
		
		Map<String, Boolean> result = new HashMap<>();
			
		if(userBO.isDuplicateId(loginId)) {
			result.put("is_duplicate", true);
		} else {
			result.put("is_duplicate", false);
		}
		
		return ApiResponse.success(result);
	}
	
	
	// 회원가입 기능 - 입력받은 정보들을 db에 저장(insert)
	@PostMapping("/users")
	public ApiResponse<?> signUp( @Validated @RequestBody UserSaveForm form
			, BindingResult bindingResult ) {
		
		if (bindingResult.hasErrors()) {
			Map<String, String> validationMessage 
						= cmnValidation.getValidationMessage(bindingResult);
			
			return ApiResponse.fail("failValidation", validationMessage);
		}
		
		int count = userBO.signUp(form.getLoginId(), form.getPassword(), form.getNickName(), form.getEmail());
		
		if (count == 1) {
			return ApiResponse.success();
		} else { // 암호화 실패경우
			return ApiResponse.fail("비밀번호 암호화 실패");
		}
		
	}
	
	// 사용자의 위치설정 기능
	@PatchMapping("/users/location")
	public ApiResponse<?> userLocation(
			@RequestParam("location") String location
			, UserInfo userInfo) {
		
		int count = userBO.editLocation(userInfo.getUserId(), location);
		
		if (count == 1) {
			return ApiResponse.success();
		} else {
			return ApiResponse.fail("위치 설정 실패");
		}

	}
	
	
	// 사용자의 프로필 설정 기능
	@PatchMapping("/users/profile")
	public ApiResponse<?> userProfile(
			@RequestParam("profileStatusMessage") String profileStatusMessage
			, @RequestParam(value = "file", required = false) MultipartFile file
			, UserInfo userInfo) {
		
		int count = userBO.editProfile(userInfo.getUserId(), file, profileStatusMessage);
		
		if (count == 1) {
			return ApiResponse.success();
		} else {
			return ApiResponse.fail("프로필 설정 실패");
		}
		
	}
	
}
