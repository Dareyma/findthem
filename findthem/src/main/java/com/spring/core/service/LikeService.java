package com.spring.core.service;

import java.util.List;

import com.spring.core.entity.Like;
import com.spring.core.entity.Post;
import com.spring.core.entity.User;
import com.spring.core.model.LikeModel;
import com.spring.core.model.PostModel;
import com.spring.core.model.UserModel;

public interface LikeService {
	
	public abstract List<LikeModel> listAllLikes();
	
	public abstract LikeModel addLike(LikeModel likeModel);
	
	public abstract LikeModel updateLike(LikeModel repolikeModelrtModel);
	
	public abstract int removeLike(int id);
	
	//LikeModel findByUser_idAndPost_id(int id, int id2);
	
	//transformar entidad a modelo
    public abstract Like transform(LikeModel likeModel);

    //transformar modelo a entidad
    public abstract LikeModel transform(Like like);

	//LikeModel findByUserAndPost(UserModel id, PostModel id2);

	LikeModel findByUserAndPost(PostModel postModel, UserModel userModel);

	Post transform(PostModel postModel);

	PostModel transform(Post post);

	UserModel transform(User user);

	User transform(UserModel userModel);

	

	

	
}
