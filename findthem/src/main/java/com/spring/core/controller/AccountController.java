package com.spring.core.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.core.model.PostModel;
import com.spring.core.model.UserModel;
import com.spring.core.service.PostService;
import com.spring.core.service.UserService;

@Controller
@RequestMapping("/")
public class AccountController {
	
	@Autowired
	@Qualifier("postServiceImpl")
	private PostService postService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	private static final Log LOG=LogFactory.getLog(ReplyController.class);
	
	@GetMapping("/account")
	public ModelAndView listTypePosts(Authentication auth) {
		
		ModelAndView mav = new ModelAndView("account");
		
		UserModel userModel = new UserModel();
		
		LOG.info("Usuario: " + auth.getName());
        List<UserModel> userslist = userService.listAllUsers();
        
        for(UserModel user:userslist) {
            if(user.getUsername().equals(auth.getName())) {
            	userModel = user;
            }
        }
		
		List<PostModel> postslist = postService.listAllPosts();
		
		List<PostModel> plist = new ArrayList<PostModel>();
		
		for(PostModel post:postslist) {
			LOG.info("Post: " + post.getUser_id().getId());
			LOG.info("Model: " + userModel.getId());
            if(post.getUser_id().getId()==userModel.getId()) {
                plist.add(post);
                LOG.info("Añadida: " + post);
            }
        }
		mav.addObject("user", userModel);
		mav.addObject("posts", plist);
		
		return mav;
	}
	
	
}
