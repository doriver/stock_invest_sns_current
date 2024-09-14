package com.sns.invest.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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

import lombok.extern.slf4j.Slf4j;

import com.sns.invest.common.ApiResponse;
import com.sns.invest.common.argumentResolver.UserInfo;
import com.sns.invest.user.bo.UserBO;

@RestController
@Slf4j
public class UserRestController {
	
	@Autowired
	private UserBO userBO;
	
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
			log.info("회원가입 검증 오류 발생 errors={}", bindingResult);
			return ApiResponse.fail("입력값이 잘못됐습니다.");
		}
		
		int count = userBO.signUp(form.getLoginId(), form.getPassword(), form.getNickName(), form.getEmail());
		
		if (count == 1) {
			return ApiResponse.success();
		} else { // 이경우는 구체적으로 언제냐
			return ApiResponse.fail("회원가입 실패");
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
		} else { // 이경우 언제냐?
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
		} else { // 이경우 언제냐?
			return ApiResponse.fail("프로필 설정 실패");
		}
		
	}
	
}
