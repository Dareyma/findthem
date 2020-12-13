package com.spring.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.core.entity.Post;
import com.spring.core.entity.User;
import com.spring.core.model.PostModel;
import com.spring.core.model.UserModel;
import com.spring.core.repository.PostRepository;
import com.spring.core.service.PostService;

@Service("postServiceImpl")
public class PostServiceImpl implements PostService{
	
	@Autowired
	@Qualifier("postRepository")
	private PostRepository postRepository;
	
	@Autowired
    private DozerBeanMapper dozer;
	
	@Override
    public List<PostModel> listAllPosts() {
        List<Post> listPost=postRepository.findAll();
        List<PostModel> listaCM=new ArrayList<>();
        listPost.forEach(a->{
        	PostModel postModel=transform(a);
            listaCM.add(postModel);
        });

        // Ordenar por nombre
        // Collections.sort(listaCM, (ReplyModel r1, ReplyModel r2) -> c1.getNombre().compareTo(c2.getNombre()));

        return listaCM;
    }

	@Override
    public PostModel addPost(PostModel postModel) {
		Post post=transform(postModel);
        return transform(postRepository.save(post));
    }
	
	@Override
    public PostModel updatePost(PostModel postModel) {
    	Post post=transform(postModel);
        return transform(postRepository.save(post));
    } 
	
	@Override
    public int removePost(int id) {
        postRepository.deleteById(id);
        return 0;
    }
	
	@Override
    public PostModel findById(int id) {
		Optional<Post> postResponse = postRepository.findById(id);
		return transform(postResponse.get());
    }
	
	@Override
    public List<PostModel> findAllByUser(UserModel userModel) {
		User user = transform(userModel);
		List<Post> postResponse = postRepository.findAllByUser(user);
		
		if(postResponse.isEmpty()) {
			return null;
		} else {
			List<PostModel> plist = new ArrayList<PostModel>();
			for(Post post:postResponse) {
	            plist.add(transform(post));
	        }
			return plist;
		}
    }
	
	@Override
    public Post transform(PostModel postModel) {
        return dozer.map(postModel, Post.class);
    }
	
	@Override
    public PostModel transform(Post post) {
        return dozer.map(post, PostModel.class);
    }
	
	@Override
    public User transform(UserModel userModel) {
        return dozer.map(userModel, User.class);
    }
	
	@Override
    public UserModel transform(User user) {
        return dozer.map(user, UserModel.class);
    }
        
}
