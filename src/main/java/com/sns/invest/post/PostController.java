package com.sns.invest.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@GetMapping("/guest_view")
	public String firstMeet() {
		return "post/firstMeet";
	}
	
	@GetMapping("/invest_view")
	public String investTimeline() {
		return "post/investTimeline";
	}
	@GetMapping("/my_home_view")
	public String myHome() {
		return "post/myHome";
	}
	@GetMapping("/local_view")
	public String localTimeline() {
		return "post/localTimeline";
	}
	@GetMapping("/gossip_view")
	public String gossipTimeline() {
		return "post/gossipTimeline";
	}
}
