package com.spring.core.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.core.constants.Constantes;
import com.spring.core.model.PostModel;
import com.spring.core.model.UserModel;
import com.spring.core.service.LikeService;
import com.spring.core.service.PostService;
import com.spring.core.service.UserService;

@Controller
@RequestMapping("/")
public class PostsController {
	
	@Autowired
	@Qualifier("postServiceImpl")
	private PostService postService;
	
	@Autowired
	@Qualifier("likeServiceImpl")
	private LikeService likeService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	private static final Log LOG=LogFactory.getLog(ReplyController.class);
	
	@GetMapping("/")
	public ModelAndView listAllPosts() {
		
		ModelAndView mav = new ModelAndView(Constantes.POST_VIEW);
		
		mav.addObject("posts", postService.listAllPosts());
		
		mav.addObject("likes", likeService.listAllLikes());
		
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PROTECTORA') or hasRole('ROLE_USER')")
	@PostMapping("/addPost")
    public String addPost(@RequestParam("imagen") MultipartFile img, @Valid @ModelAttribute("post") PostModel postModel, BindingResult result,
            RedirectAttributes flash, Model model, Authentication auth) {
			
			UserModel userModel = new UserModel();
			
			// Usuario que ha creado el post
            LOG.info("Usuario: " + auth.getName());
            userModel = userService.findByUsername(auth.getName());
            
			
			LOG.info("Prueba: " + postModel.getPost_id());
			LOG.info("Prueba2: " + userModel.getUsername());
			
            if (result.hasErrors()) {
            	//LOG.info("PostModel User id: " + postModel.getUser_id().getName());
            	LOG.info("Error al crear un post");
            	return "createPost";
            }else {
                if(!img.isEmpty()) {
                    Path directory=Paths.get(".\\src\\main\\resources\\static\\img");
                    String ruta=directory.toFile().getAbsolutePath();
                    LOG.info("ruta"+ruta);
                    try {
                        byte[] bytes=img.getBytes();
                        Path rutaCompleta=Paths.get(ruta + "\\" + postService.listAllPosts().size() + userModel.getUsername() + ".jpg");
                        LOG.info("Ruta de la imagen: "+rutaCompleta);
                        Files.write(rutaCompleta,bytes);
                        postModel.setImage("/img/"+ postService.listAllPosts().size() + userModel.getUsername() +".jpg");
                        
                        // Fecha de creación del post
                        Date date = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        LOG.info("Fecha de creacion del post: " + date);
                        postModel.setDate(formatter.format(date));
                        
                    }catch(IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    
                    postModel.setImage("/img/michi.jpg");
                }
                postModel.setUser(userModel);
                postService.addPost(postModel);
            }
            return "redirect:/";
        }
	

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PROTECTORA') or hasRole('ROLE_USER')")
	@GetMapping("/formpost")
    public ModelAndView formPost(@RequestParam(name="id") int id, Model Model) {
        
		ModelAndView mav=new ModelAndView(Constantes.POST_CREATE_VIEW);
		
		PostModel postModel= new PostModel();
        
        if (id!=-1) {
			postModel = postService.findById(id);
		}
        
        return mav.addObject("post", postModel);
        
    }
	
	@GetMapping("/deletepost")
    public String deletePost(@RequestParam(name="id") int id, Authentication auth) {
		
        postService.removePost(id);
        return "redirect:/account";
    }
}
