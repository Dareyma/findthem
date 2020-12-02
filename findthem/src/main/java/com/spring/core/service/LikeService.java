package com.spring.core.service;

import java.util.List;

import com.spring.core.entity.Like;
import com.spring.core.model.LikeModel;

public interface LikeService {
	
	public abstract List<LikeModel> listAllLikes();
	
	public abstract LikeModel addLike(LikeModel likeModel);
	
	public abstract LikeModel updateLike(LikeModel repolikeModelrtModel);
	
	public abstract int removeLike(int id);
	
	//transformar entidad a modelo
    public abstract Like transform(LikeModel likeModel);

    //transformar modelo a entidad
    public abstract LikeModel transform(Like like);
}
