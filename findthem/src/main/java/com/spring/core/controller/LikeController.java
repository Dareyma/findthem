package com.spring.core.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.core.model.LikeModel;
import com.spring.core.model.PostModel;
import com.spring.core.model.UserModel;
import com.spring.core.service.PostService;
import com.spring.core.service.UserService;

public class LikeController {
	
	@Autowired
	@Qualifier("postServiceImpl")
	private PostService postService;

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	private static final Log LOG=LogFactory.getLog(ReplyController.class);
	
	@PostMapping("/changeLike")
    public String changeLike(@RequestParam("enable") LikeModel likeModel, @RequestParam("post_id")  PostModel postModel, Model model, Authentication auth) {
			
			UserModel userModel = new UserModel();
			
            List<UserModel> userslist = userService.listAllUsers();
            
            for(UserModel user:userslist) {
    			LOG.info("Usuario que da like: " + auth.getName());
                if(user.getUsername().equals(auth.getName())) {
                	userModel = user;
                }
            }
            
			
            likeModel.setUser_id(userModel);
            
            return "redirect:/";
        }
	
}
