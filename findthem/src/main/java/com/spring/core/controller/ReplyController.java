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
import com.spring.core.model.LikeModel;
import com.spring.core.model.PostModel;
import com.spring.core.model.ReplyModel;
import com.spring.core.model.ReportModel;
import com.spring.core.model.UserModel;
import com.spring.core.service.LikeService;
import com.spring.core.service.PostService;
import com.spring.core.service.ReplyService;
import com.spring.core.service.ReportService;
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
	
	@Autowired
	@Qualifier("reportServiceImpl")
	private ReportService reportService;
	
	private static final Log LOG=LogFactory.getLog(ReplyController.class);
	
	@GetMapping("/listAllReplyPost")
	public ModelAndView listAllReplyPost(@RequestParam(name="id") int id, Authentication auth) {
		
		PostModel postModel = new PostModel();
		
		ReplyModel replyModel = new ReplyModel();
		
		UserModel userModel = new UserModel();
		
		ModelAndView mav = new ModelAndView(Constantes.REPLY_VIEW);
		
		LOG.info("Buscando posts y replys con id_post: " + id);
		
		for(PostModel post:postService.listAllPosts()) {
			LOG.debug("Post entra: ");
			if(post.getPost_id()==id) {
                postModel=post;
                LOG.info("Post id: " + postModel.getPost_id());
            }
        }
		
		List<UserModel> userslist = userService.listAllUsers();
        
        // Usuario que le da like
        for(UserModel user:userslist) {
            if(user.getUsername().equals(auth.getName())) {
            	userModel = user;
            }
        }
		
		
		List<LikeModel> likeslist = likeService.listAllLikes();
		LikeModel likeModel = new LikeModel();
        
        for (LikeModel like:likeslist) {
			if (like.getPost_id().getPost_id()==postModel.getPost_id() && like.getUser_id().getId()==userModel.getId()) {
				likeModel = like;
			}
		}
        
        List<ReportModel> reportslist = reportService.listAllReports();
        ReportModel reportModel = new ReportModel();
        
        for (ReportModel report:reportslist) {
			if (report.getPost_id().getPost_id()==postModel.getPost_id() && report.getUser_id().getId()==userModel.getId()) {
				reportModel = report;
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
		mav.addObject("report", reportModel);
		
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PROTECTORA') or hasRole('ROLE_USER')")
	@PostMapping("/addReply")
    public String addReply(@RequestParam("imagen") MultipartFile img, @RequestParam(name="post_id") int post_id,
    		@Valid @ModelAttribute("reply") ReplyModel replyModel, BindingResult result,
            RedirectAttributes flash, Model model, Authentication auth) {
			
			UserModel userModel = new UserModel();
			
			ReplyModel rModel = new ReplyModel();
            
            // Fecha de creación del reply
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            
            // Usuario que ha hecho la respuesta
            LOG.info("Usuario: " + auth.getName() + "Fecha: " + date);
            List<UserModel> userslist = userService.listAllUsers();
            
            for(UserModel user:userslist) {
                if(user.getUsername().equals(auth.getName())) {
                	userModel = user;
                }
            }
            
            // Post  en el que se ha hecho la respuesta
            List<PostModel> postslist = postService.listAllPosts();
            PostModel postModel = new PostModel();
            
            for(PostModel pm:postslist) {
                if(pm.getPost_id()==post_id) {
                	LOG.info("Postdfhbdf");
                	postModel = pm;
                	rModel.setPost_id(postModel);
                }
            }
            
            rModel.setText(replyModel.getText());
            
			LOG.info("Result " + result);
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
                        rModel.setImage("/img/"+ replyService.listAllReplys().size() + userModel.getUsername() +".jpg");     
                    }catch(IOException e) {
                        e.printStackTrace();
                    }
                }
                
                
                rModel.setDate(formatter.format(date));
                rModel.setUser_id(userModel);
                replyService.addReply(rModel);
            }
            return "redirect:/listAllReplyPost?id=" + post_id;
        }
}
