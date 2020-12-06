package com.spring.core.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.core.model.LikeModel;
import com.spring.core.model.PostModel;
import com.spring.core.model.UserModel;
import com.spring.core.service.LikeService;
import com.spring.core.service.PostService;
import com.spring.core.service.UserService;


@Controller
@RequestMapping("")
public class LikeController {
	
	@Autowired
	@Qualifier("postServiceImpl")
	private PostService postService;

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	@Qualifier("likeServiceImpl")
	private LikeService likeService;
	
	private static final Log LOG=LogFactory.getLog(ReplyController.class);
	
	@GetMapping("/changeLike")
    public String changeLike(@RequestParam(name="post_id") int post_id, Model model, Authentication auth,
    		@ModelAttribute("like") LikeModel likeModel) {
			
			UserModel userModel = new UserModel();
			
            List<UserModel> userslist = userService.listAllUsers();
            
            LOG.info("Entra10");
            
            // Usuario que le da like
            for(UserModel user:userslist) {
    			LOG.info("Usuario que da like: " + auth.getName());
                if(user.getUsername().equals(auth.getName())) {
                	userModel = user;
                }
            }
            
			
            // Post que le dan like
            List<PostModel> postslist = postService.listAllPosts();
            PostModel postModel = new PostModel();
            
            for(PostModel post:postslist) {
                if(post.getPost_id()==post_id) {
                	LOG.info("Entra");
                	postModel = post;
                }
            }
            
            List<LikeModel> likeslist = likeService.listAllLikes();
            
            for (LikeModel like:likeslist) {
				if (like.getPost_id().getPost_id()==postModel.getPost_id() && like.getUser_id().getId()==userModel.getId()) {
					LOG.info("Entra4");
					if (like.isEnabled()) {
						likeModel.setEnabled(false);
						LOG.info("Entra2");
						return "redirect:/listAllReplyPost?id=" + post_id;
					} else {
						likeModel.setEnabled(true);
						LOG.info("Entra3");
						return "redirect:/listAllReplyPost?id=" + post_id;
					}
				}
			}
            
            LOG.info("Entra5");
            
            likeModel.setPost_id(postModel);
            likeModel.setUser_id(userModel);
            likeModel.setEnabled(true);
            
            likeService.addLike(likeModel);
            
            return "redirect:/listAllReplyPost?id=" + post_id;
        }
	
}
