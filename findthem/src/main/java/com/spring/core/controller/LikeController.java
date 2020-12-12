package com.spring.core.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	private static final Log LOG=LogFactory.getLog(LikeController.class);
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PROTECTORA') or hasRole('ROLE_USER')")
	@GetMapping("/changeLike")
    public String changeLike(@RequestParam(name="post_id") int post_id, Model model, Authentication auth) {
			
			UserModel userModel = new UserModel();
			LOG.info("Usuario que da like: " + auth.getName() + " post: " + post_id);
			userModel = userService.findByUsername(auth.getName());
				
			LikeModel likeModel = new LikeModel();
			
            // Post que le dan like
            PostModel postModel = new PostModel();
            postModel = postService.findById(post_id);
            
            
            List<LikeModel> likeslist = likeService.listAllLikes();
            
            for (LikeModel like:likeslist) {
				if (like.getPost().getPost_id()==postModel.getPost_id() && like.getUser().getId()==userModel.getId()) {
					if (like.isEnabled()) {
						likeModel = like;
						likeModel.setEnabled(false);
						likeService.updateLike(likeModel);
						LOG.info("Like eliminado.");
						return "redirect:/listAllReplyPost?id=" + post_id;
					} else {
						likeModel = like;
						likeModel.setEnabled(true);
						likeService.updateLike(likeModel);
						LOG.info("Like establecido.");
						return "redirect:/listAllReplyPost?id=" + post_id;
					}
				}
			}
            
            LOG.info("Creando like...");
            
            likeModel.setPost(postModel);
            likeModel.setUser(userModel);
            likeModel.setEnabled(true);
            
            likeService.addLike(likeModel);
            
            return "redirect:/listAllReplyPost?id=" + post_id;
            
        //     LOG.info("papaya");
        //     likeModel = likeService.findByUserAndPost(userModel.getId(), post_id);
        //     LOG.info("papaya");
            
        //     if (likeModel!=null) {
        //     	if (likeModel.isEnabled()) {
    	// 			likeModel.setEnabled(false);
    	// 			likeService.updateLike(likeModel);
    	// 			LOG.info("Like eliminado.");
    	// 			return "redirect:/listAllReplyPost?id=" + post_id;
    	// 		} else {
    	// 			likeModel.setEnabled(true);
    	// 			likeService.updateLike(likeModel);
    	// 			LOG.info("Like establecido.");
    	// 			return "redirect:/listAllReplyPost?id=" + post_id;
    	// 		}
		// 	}
        }
}
