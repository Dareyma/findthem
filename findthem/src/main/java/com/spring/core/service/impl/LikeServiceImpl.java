package com.spring.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.core.entity.Like;
import com.spring.core.entity.Post;
import com.spring.core.entity.User;
import com.spring.core.model.LikeModel;
import com.spring.core.model.PostModel;
import com.spring.core.model.UserModel;
import com.spring.core.repository.LikeRepository;
import com.spring.core.service.LikeService;

@Service("likeServiceImpl")
public class LikeServiceImpl implements LikeService{
	
	@Autowired
	@Qualifier("likeRepository")
	private LikeRepository likeRepository;
	
	@Autowired
    private DozerBeanMapper dozer;
	
	@Override
    public List<LikeModel> listAllLikes() {
        List<Like> listLike=likeRepository.findAll();
        List<LikeModel> listaCM=new ArrayList<>();
        listLike.forEach(a->{
        	LikeModel likeModel=transform(a);
            listaCM.add(likeModel);
        });

        // Ordenar por nombre
        // Collections.sort(listaCM, (ReplyModel r1, ReplyModel r2) -> c1.getNombre().compareTo(c2.getNombre()));

        return listaCM;
    }

	@Override
    public LikeModel addLike(LikeModel likeModel) {
		Like like=transform(likeModel);
        return transform(likeRepository.save(like));
    }
	
	@Override
    public LikeModel updateLike(LikeModel likeModel) {
		Like like=transform(likeModel);
        return transform(likeRepository.save(like));
    } 
	
	@Override
    public int removeLike(int id) {
		likeRepository.deleteById(id);
        return 0;
    }
	
	@Override
    public LikeModel findByUserAndPost(PostModel postModel, UserModel userModel) {
		User user = transform(userModel);
		Post post = transform(postModel);
		Optional<Like> likeResponse = likeRepository.findByUserAndPost(user, post);
		
		if(likeResponse.isPresent()) {
			return transform(likeResponse.get());
		} else {
			return null;
		}
		
    }
	
	@Override
    public Like transform(LikeModel likeModel) {
        return dozer.map(likeModel, Like.class);
    }
	
	@Override
    public LikeModel transform(Like like) {
        return dozer.map(like, LikeModel.class);
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
