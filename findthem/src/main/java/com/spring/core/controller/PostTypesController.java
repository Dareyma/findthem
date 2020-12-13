package com.spring.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.core.constants.Constantes;
import com.spring.core.model.PostModel;
import com.spring.core.service.LikeService;
import com.spring.core.service.PostService;

@Controller
@RequestMapping("/")
public class PostTypesController {
	
	@Autowired
	@Qualifier("postServiceImpl")
	private PostService postService;
	
	@Autowired
	@Qualifier("likeServiceImpl")
	private LikeService likeService;
	
	@GetMapping("/listPostType")
	public ModelAndView listTypePosts(@RequestParam(name="type") int type) {
		
		ModelAndView mav = new ModelAndView(Constantes.POST_VIEW);
		
		List<PostModel> plist = postService.findAllByType(type);
		
		mav.addObject("posts", plist);
		
		mav.addObject("likes", likeService.listAllLikes());
		
		return mav;
	}
}
