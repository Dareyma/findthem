package com.spring.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.core.entity.Post;
import com.spring.core.entity.Reply;
import com.spring.core.model.PostModel;
import com.spring.core.model.ReplyModel;
import com.spring.core.repository.ReplyRepository;
import com.spring.core.service.ReplyService;

@Service("replyServiceImpl")
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	@Qualifier("replyRepository")
	private ReplyRepository replyRepository;
	
	@Autowired
    private DozerBeanMapper dozer;
	
	@Override
    public List<ReplyModel> listAllReplys() {
        List<Reply> listReply=replyRepository.findAll();
        List<ReplyModel> listaCM=new ArrayList<>();
        listReply.forEach(a->{
        	ReplyModel replyModel=transform(a);
            listaCM.add(replyModel);
        });

        // Ordenar por nombre
        // Collections.sort(listaCM, (ReplyModel r1, ReplyModel r2) -> c1.getNombre().compareTo(c2.getNombre()));

        return listaCM;
    }
	
	@Override
    public List<ReplyModel> findAllByPost(PostModel postModel) {
		Post post = transform(postModel);
		List<Reply> replyResponse = replyRepository.findAllByPost(post);
		
		if(replyResponse.isEmpty()) {
			return null;
			
		} else {
			List<ReplyModel> rlist = new ArrayList<ReplyModel>();
			
			for(Reply reply:replyResponse) {
	            rlist.add(transform(reply));
	        }
			
			return rlist;
		}
    }

	@Override
    public ReplyModel addReply(ReplyModel replyModel) {
		Reply reply=transform(replyModel);
        return transform(replyRepository.save(reply));
    }
	
	@Override
    public Reply transform(ReplyModel replyModel) {
        return dozer.map(replyModel, Reply.class);
    }

    @Override
    public ReplyModel transform(Reply reply) {
        return dozer.map(reply, ReplyModel.class);
    }
    
    @Override
    public Post transform(PostModel postModel) {
        return dozer.map(postModel, Post.class);
    }
	
	@Override
    public PostModel transform(Post post) {
        return dozer.map(post, PostModel.class);
    }
}
