package com.sns.invest.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.invest.user.model.User;
import com.sns.invest.user.bo.UserBO;

@RestController
public class UserRestController {
	
	@Autowired
	private UserBO userBO;
	
	// 아이디 중복확인 기능
	@GetMapping("/users/{loginId}")
	public Map<String, Boolean> isDuplicateId (
			@RequestParam("loginId") String loginId) {
		
		Map<String, Boolean> result = new HashMap<>();
			
		if(userBO.isDuplicateId(loginId)) {
			result.put("is_duplicate", true);
		} else {
			result.put("is_duplicate", false);
		}
		
//		result.put("is_duplicate", userBO.isDuplicateId(loginId));
		
		return result;
	}
	
	
	// 회원가입 기능
	@PostMapping("/users")
	public Map<String, String> signUp(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, @RequestParam("nickName") String nickName
			, @RequestParam("email") String email
			) {
		
		Map<String, String> result = new HashMap<>();
		int count = userBO.signUp(loginId, password, nickName, email);
		
		if (count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}
	
	
	// 로그인 기능
	@PostMapping("/users/sign-in")
	public Map<String, String> signIn(
			@RequestParam("idForLogin") String idForLogin
			, @RequestParam("passwordForLogin") String passwordForLogin
			, HttpServletRequest request) {
		
		Map<String, String> result = new HashMap<>();
		User user = userBO.signIn(idForLogin, passwordForLogin);
		if(user != null) {
			result.put("result", "success");
			
			HttpSession session = request.getSession();
			session.setAttribute("userId", user.getId());
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userNickName", user.getNickName());
			session.setAttribute("userLocation", user.getLocation());
			
		} else {
			result.put("result", "fail");
		}
		return result;	
	}
	
	
	// 사용자의 위치설정 기능
	@PatchMapping("/users/location")
	public Map<String, String> userLocation(
			@RequestParam("location") String location
			, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		
		int count = userBO.editLocation(userId, location);
		Map<String, String> result = new HashMap<>();
		
		if (count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}
	
	
	// 사용자의 프로필 설정 기능
	@PatchMapping("/users/profile")
	public Map<String, String> userProfile(
			@RequestParam("profileStatusMessage") String profileStatusMessage
			, @RequestParam(value = "file", required = false) MultipartFile file
			, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		
//		System.out.println("file:"+file);
		int count = userBO.editProfile(userId, file, profileStatusMessage);
		Map<String, String> result = new HashMap<>();
		
		if (count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;

	}
	
}
