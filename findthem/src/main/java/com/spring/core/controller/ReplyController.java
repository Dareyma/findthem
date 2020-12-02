package com.spring.core.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.spring.core.model.PostModel;
import com.spring.core.model.ReplyModel;
import com.spring.core.model.UserModel;
import com.spring.core.service.PostService;
import com.spring.core.service.ReplyService;
import com.spring.core.service.UserService;


@Controller
@RequestMapping("")
public class ReplyController {
	
	@Autowired
	@Qualifier("replyServiceImpl")
	private ReplyService replyService;
	
	@Autowired
	@Qualifier("postServiceImpl")
	private PostService postService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	private static final Log LOG=LogFactory.getLog(ReplyController.class);
	
	@GetMapping("/listAllReplyPost")
	public ModelAndView listAllReplyPost(@RequestParam(name="id") int id) {
		
		PostModel postModel = new PostModel();
		
		ModelAndView mav = new ModelAndView("reply");
		
		LOG.info("Buscando posts y replys con id_post: " + id);
		
		for(PostModel post:postService.listAllPosts()) {
			LOG.debug("Post entra: ");
			if(post.getPost_id()==id) {
				LOG.info("IF: ");
                postModel=post;
                LOG.info("Post id: " + postModel.getPost_id());
            }
        }
		
		mav.addObject("post", postModel);
		
		List<ReplyModel> replyslist = replyService.listAllReplys();
		
		List<ReplyModel> rlist = new ArrayList<ReplyModel>();
		
		for(ReplyModel reply:replyslist) {
            if(reply.getPost_id().getPost_id()==id) {
                rlist.add(reply);
                LOG.info("Añadida: " + reply);
            }
        }
		
		mav.addObject("replys", rlist);
		
		return mav;
	}
	
	@PostMapping("/addReply")
    public String addReply(@RequestParam("imagen") MultipartFile img, @Valid @ModelAttribute("replys") ReplyModel replyModel, BindingResult result,
            RedirectAttributes flash, Model model, Authentication auth) {
			
			UserModel userModel = new UserModel();
			
			// Usuario que ha creado el reply
            LOG.info("Usuario: " + auth.getName());
            List<UserModel> userslist = userService.listAllUsers();
            
            // Fecha de creación del reply
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            LOG.info("Fecha de creacion del reply: " + date);
            
            // Usuario que ha hecho la respuesta
            for(UserModel user:userslist) {
    			LOG.info("Usuario que crea el reply: " + auth.getName());
                if(user.getUsername().equals(auth.getName())) {
                	userModel = user;
                }
            }
            
            // Post  en el que se ha hecho la respuesta
            List<PostModel> postslist = postService.listAllPosts();
            PostModel postModel = new PostModel();
            
            for(PostModel post:postslist) {
    			LOG.info("Usuario que crea el reply: " + auth.getName());
                if(post.getPost_id()==replyModel.getPost_id().getPost_id()) {
                	postModel = post;
                }
            }
			
            if (result.hasErrors()) {
            	//LOG.info("replyModel User id: " + replyModel.getUser_id().getName());
            	LOG.info("Error al crear una reply");
            	return "createreply";
            }else {
                if(!img.isEmpty()) {
                    Path directory=Paths.get(".\\src\\main\\resources\\static\\img");
                    String ruta=directory.toFile().getAbsolutePath();
                    LOG.info("ruta"+ruta);
                    try {
                        byte[] bytes=img.getBytes();
                        Path rutaCompleta=Paths.get(ruta + "\\" + replyService.listAllReplys().size() + userModel.getUsername() + ".jpg");
                        LOG.info("Ruta de la imagen: "+rutaCompleta);
                        Files.write(rutaCompleta,bytes);
                        replyModel.setImage("/img/"+ replyService.listAllReplys().size() + userModel.getUsername() +".jpg");     
                    }catch(IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    
                    replyModel.setImage("/img/michi.jpg");
                }
                replyModel.setPost_id(postModel);
                replyModel.setDate(formatter.format(date));
                replyModel.setUser_id(userModel);
                replyService.addReply(replyModel);
            }
            return "redirect:/listAllReplyPost?id=" + postModel.getPost_id();
        }
}
