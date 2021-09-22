package com.sns.invest.post.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.invest.common.FileManagerService;
import com.sns.invest.post.dao.PostDAO;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	public int addPost(int userId, String userNickName, String content, MultipartFile file
			, String investStyle, String stockItemName, String investmentOpinion, String investmentProcess) {
		
		FileManagerService fileManager = new FileManagerService();
		
		String filePath = fileManager.saveFile(userId, file);
		
		if(filePath == null) {
			return -1;
		}
		
		return postDAO.insertInvestPost(userId, userNickName, content, filePath, investStyle, stockItemName, investmentOpinion, investmentProcess);
	}
}
