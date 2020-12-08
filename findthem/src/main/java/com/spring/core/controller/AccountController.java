package com.spring.core.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.core.constants.Constantes;
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
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PROTECTORA') or hasRole('ROLE_USER')")
	@GetMapping("/account")
	public ModelAndView listPosts(Authentication auth) {
		
		ModelAndView mav = new ModelAndView(Constantes.ACCOUNT_VIEW);
		
		UserModel userModel = new UserModel();
		
		//LOG.info("Usuario: " + auth.getName());
        List<UserModel> userslist = userService.listAllUsers();
        
        for(UserModel user:userslist) {
            if(user.getUsername().equals(auth.getName())) {
            	userModel = user;
            }
        }
		
		List<PostModel> postslist = postService.listAllPosts();
		
		List<PostModel> plist = new ArrayList<PostModel>();
		
		for(PostModel post:postslist) {
			//LOG.info("Post: " + post.getUser_id().getId());
			//LOG.info("Model: " + userModel.getId());
            if(post.getUser_id().getId()==userModel.getId()) {
                plist.add(post);
                LOG.info("Añadida: " + post);
            }
        }
		mav.addObject("user", userModel);
		mav.addObject("posts", plist);
		
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PROTECTORA') or hasRole('ROLE_USER')")
	@GetMapping("/deleteAccount")
	public String deleteAccount(Authentication auth) {
		
		UserModel userM = new UserModel();
		
        List<UserModel> userslist = userService.listAllUsers();
        
        for(UserModel user:userslist) {
            if(user.getUsername().equals(auth.getName())) {
            	userM = user;
            }
        }
		
        LOG.info("Modelo antes: " + userM.isEnabled() + "Usuario: " + userM.getUsername() + "id: " + userM.getId());
		userM.setEnabled(false);
		LOG.info("Modelo después: " + userM.isEnabled() + "Usuario: " + userM.getUsername() + "id: " + userM.getId());
		
		userService.editUser(userM);
		
		return "redirect:/login?logout";
	}
	
	@GetMapping("/editAccount")
	public ModelAndView editAccount(Authentication auth) {
		
		ModelAndView mav = new ModelAndView(Constantes.EDIT_ACCOUNT_VIEW);
		
		UserModel userM = new UserModel();
		
        List<UserModel> userslist = userService.listAllUsers();
        
        for(UserModel user:userslist) {
            if(user.getUsername().equals(auth.getName())) {
            	userM = user;
            }
        }
        
        mav.addObject("user", userM);
		
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PROTECTORA') or hasRole('ROLE_USER')")
	@PostMapping("/updateAccount")
	public String updateAccount(Authentication auth, @Valid @ModelAttribute("user") UserModel userModel) {
		
		UserModel userM = new UserModel();
		
        List<UserModel> userslist = userService.listAllUsers();
        
        for(UserModel user:userslist) {
            if(user.getUsername().equals(auth.getName())) {
            	userM = user;
            }
        }
		
        LOG.info("Modelo antes: " + userM.isEnabled() + "Usuario: " + userM.getUsername() + "id: " + userM.getId());
		userM.setEmail(userModel.getEmail());
		userM.setName(userModel.getName());
		userM.setSurname(userModel.getSurname());
		userM.setUsername(userModel.getUsername());
		userM.setPhone(userModel.getPhone());
		LOG.info("Modelo después: " + userM.isEnabled() + "Usuario: " + userM.getUsername() + "id: " + userM.getId());
		
		userService.editUser(userM);
		
		return "redirect:/login?logout";
	}
	
}
