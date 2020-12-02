package com.spring.core.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.core.model.PostModel;
import com.spring.core.service.PostService;

@Controller
@RequestMapping("/")
public class PostTypesController {
	
	@Autowired
	@Qualifier("postServiceImpl")
	private PostService postService;
	private static final Log LOG=LogFactory.getLog(ReplyController.class);
	
	@GetMapping("/listPostType")
	public ModelAndView listTypePosts(@RequestParam(name="type") int type) {
		
		ModelAndView mav = new ModelAndView("index");
		
		List<PostModel> postslist = postService.listAllPosts();
		
		List<PostModel> plist = new ArrayList<PostModel>();
		
		for(PostModel post:postslist) {
			LOG.info("Entra en el for" + postslist);
            if(post.getType()==type) {
                plist.add(post);
                LOG.info("Añadida: " + post);
            }
        }
		
		mav.addObject("posts", plist);
		
		return mav;
	}
}
