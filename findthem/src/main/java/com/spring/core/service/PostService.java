package com.spring.core.service;

import java.util.List;

import com.spring.core.entity.Post;
import com.spring.core.model.PostModel;

public interface PostService {
	
	public abstract List<PostModel> listAllPosts();
	
	public abstract PostModel addPost(PostModel postModel);
	
	public abstract PostModel updatePost(PostModel postModel);
	
	public abstract int removePost(int id);
	
	PostModel findById(int id);

	//transformar entidad a modelo
    public abstract Post transform(PostModel postModel);

    //transformar modelo a entidad
    public abstract PostModel transform(Post post);

	
}
