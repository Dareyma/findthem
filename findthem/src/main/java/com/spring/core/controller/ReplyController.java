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
		
		postModel = postService.findById(id);
        LOG.info("Post id: " + postModel.getPost_id());
        
        // Usuario que le da like
		userModel = userService.findByUsername(auth.getName());
		
		
		List<LikeModel> likeslist = likeService.listAllLikes();
		LikeModel likeModel = new LikeModel();
        
        for (LikeModel like:likeslist) {
			if (like.getPost().getPost_id()==postModel.getPost_id() && like.getUser().getId()==userModel.getId()) {
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
    public ModelAndView addReply(@RequestParam("imagen") MultipartFile img,
    		@Valid @ModelAttribute("reply") ReplyModel replyModel, BindingResult result,
            RedirectAttributes flash, Model model, Authentication auth) {
			
			ModelAndView mav = new ModelAndView();
		
			UserModel userModel = new UserModel();
			
			ReplyModel rModel = new ReplyModel();
            
            // Fecha de creación del reply
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            
            // Usuario que ha hecho la respuesta
            LOG.info("Usuario: " + auth.getName() + "Fecha: " + date);
            userModel = userService.findByUsername(auth.getName());
            
            if (result.hasErrors()) {
            	LOG.info("Error al crear una reply");
            	LOG.info(result.getFieldError());
            	//for (int i = 0; i < replyModel.; i++) {
            		LOG.info("post: " + replyModel.getPost_id().getPost_id());
				//}
            	
            	mav.setViewName("redirect:/listAllReplyPost?id=" + replyModel.getPost_id().getPost_id());
            	//return "redirect:/listAllReplyPost?id=" + replyModel.getPost_id().getPost_id(); //PostModel post_id
            	
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
                rModel.setPost_id(replyModel.getPost_id());
                rModel.setText(replyModel.getText());
                rModel.setUser_id(userModel);
                
                replyService.addReply(rModel);
                mav.setViewName("redirect:/listAllReplyPost?id=" + replyModel.getPost_id().getPost_id());
            }
            
            return mav;
        }
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PROTECTORA') or hasRole('ROLE_USER')")
	@GetMapping("/newreply")
    public String formPost(@RequestParam(name="id") int id, Model model) {
        
		ReplyModel replyModel = new ReplyModel();
		PostModel postModel = new PostModel();
		
		postModel = postService.findById(id);
		replyModel.setPost_id(postModel);
		
		model.addAttribute("reply", replyModel);
		
		return Constantes.REPLY_CREATE;
		
		
		/*ModelAndView mav=new ModelAndView(Constantes.REPLY_CREATE);
		
		ReplyModel replyModel= new ReplyModel();
		PostModel postModel= new PostModel();
		
		postModel = postService.findById(id);
		replyModel.setPost_id(postModel);
		mav.addObject("reply", replyModel);
		
		LOG.info("Modelo: " + replyModel.getPost_id().getPost_id());
		LOG.info("Texto: " + mav);
		
		return mav;*/
        
    }
}
