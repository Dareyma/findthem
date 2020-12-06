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

import com.spring.core.constants.Constantes;
import com.spring.core.model.LikeModel;
import com.spring.core.model.PostModel;
import com.spring.core.model.ReplyModel;
import com.spring.core.model.UserModel;
import com.spring.core.service.LikeService;
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
	@Qualifier("likeServiceImpl")
	private LikeService likeService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	private static final Log LOG=LogFactory.getLog(ReplyController.class);
	
	@GetMapping("/listAllReplyPost")
	public ModelAndView listAllReplyPost(@RequestParam(name="id") int id) {
		
		PostModel postModel = new PostModel();
		
		ReplyModel replyModel = new ReplyModel();
		
		ModelAndView mav = new ModelAndView(Constantes.REPLY_VIEW);
		
		LOG.info("Buscando posts y replys con id_post: " + id);
		
		for(PostModel post:postService.listAllPosts()) {
			LOG.debug("Post entra: ");
			if(post.getPost_id()==id) {
                postModel=post;
                LOG.info("Post id: " + postModel.getPost_id());
            }
        }
		
		
		List<LikeModel> likeslist = likeService.listAllLikes();
		LikeModel likeModel = new LikeModel();
        
        for (LikeModel like:likeslist) {
			if (like.getPost_id().getPost_id()==postModel.getPost_id()) {
				likeModel = like;
			}
		}
		
		
		List<ReplyModel> replyslist = replyService.listAllReplys();
		
		List<ReplyModel> rlist = new ArrayList<ReplyModel>();
		
		for(ReplyModel reply:replyslist) {
            if(reply.getPost_id().getPost_id()==id) {
                rlist.add(reply);
                //LOG.info("Añadida: " + reply);
            }
        }
		
		mav.addObject("reply", replyModel);
		mav.addObject("like", likeModel);
		mav.addObject("respuestas", rlist);
		mav.addObject("post", postModel);
		
		return mav;
	}
	
	@PostMapping("/addReply")
    public String addReply(@RequestParam("imagen") MultipartFile img, @RequestParam(name="post_id") int post_id,
    		@Valid @ModelAttribute("reply") ReplyModel replyModel, BindingResult result,
            RedirectAttributes flash, Model model, Authentication auth) {
			
			UserModel userModel = new UserModel();
            
            // Fecha de creación del reply
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            LOG.info("Fecha de creacion del reply: " + date);
            
            // Usuario que ha hecho la respuesta
            LOG.info("Usuario: " + auth.getName());
            List<UserModel> userslist = userService.listAllUsers();
            
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
                if(post.getPost_id()==post_id) {
                	LOG.info("Postdfhbdf");
                	postModel = post;
                	replyModel.setPost_id(postModel);
                }
            }
			
            if (result.hasErrors()) {
            	LOG.info("Error al crear una reply");
            	LOG.info(result.getFieldError());
            	return "redirect:/listAllReplyPost?id=" + post_id;
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
                }
                
                
                replyModel.setDate(formatter.format(date));
                replyModel.setUser_id(userModel);
                replyService.addReply(replyModel);
            }
            return "redirect:/listAllReplyPost?id=" + post_id;
        }
}
