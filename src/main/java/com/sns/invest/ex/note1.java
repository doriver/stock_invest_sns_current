package com.sns.invest.ex;

import com.sns.invest.user.model.User;

public class note1 {

}
//			UserBO에서
//	* User userInformation(int userId) 
//	selectUserByUserId , user테이블에서 입력받은 id에 해당하는 모든 정보를 가져옴 
//
//	* User signIn(String idForLogin, String passwordForLogin)
//	selectUserByIdPassword, user테이블에서 입력받은 로그인id, 패스워드에 해당하는 모든 정보를 가져옴
	
	
	
//			UserRestController에서
//	* @PostMapping("/sign_in") Map<String, String> signIn
//	(@RequestParam("idForLogin") String idForLogin
//	, @RequestParam("passwordForLogin") String passwordForLogin
//	, HttpServletRequest request)
//	User user = userBO.signIn(idForLogin, passwordForLogin);
//	HttpSession session = request.getSession();
//	session.setAttribute("userId", user.getId());
//	session.setAttribute("userLoginId", user.getLoginId());
//	session.setAttribute("userNickName", user.getNickName());
//	session.setAttribute("userLocation", user.getLocation());