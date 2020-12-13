package com.spring.core.service;

import java.util.List;

import com.spring.core.entity.Post;
import com.spring.core.entity.Reply;
import com.spring.core.model.PostModel;
import com.spring.core.model.ReplyModel;

public interface ReplyService {
	
	public abstract List<ReplyModel> listAllReplys();
	
	public abstract ReplyModel addReply(ReplyModel replyModel);
	
	//transformar entidad a modelo
    public abstract Reply transform(ReplyModel replyModel);

    //transformar modelo a entidad
    public abstract ReplyModel transform(Reply reply);

	List<ReplyModel> findAllByPost(PostModel postModel);

	Post transform(PostModel postModel);

	PostModel transform(Post post);

}
