package com.spring.core.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.core.entity.UserRole;
import com.spring.core.model.UserModel;
import com.spring.core.repository.UserRepository;
import com.spring.core.service.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserDetailsService, UserService{
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Autowired
    private DozerBeanMapper dozer;
	
	private User buildUser(com.spring.core.entity.User user, List<GrantedAuthority> authorities) {
    	return new User(user.getUsername(),user.getPassword(),user.isEnabled(),true,true,true,authorities);
    	
    }
    	
    private List<GrantedAuthority> buildAuthorities(Set<UserRole> userRoles){
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		
		for(UserRole userRole: userRoles) {
			auths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		
		return new ArrayList<GrantedAuthority>(auths);
	}
    
    @Override
    public UserModel updateUser(UserModel userModel) {
    	com.spring.core.entity.User user=transform(userModel);
        return transform(userRepository.save(user));
    } 
	
	@Override
    public int removeUser(int id) {
        userRepository.deleteById(id);
		return 0;
	}
       
	
	@Override
    public com.spring.core.entity.User transform(UserModel userModel) {
        return dozer.map(userModel, com.spring.core.entity.User.class);
    }

    @Override
    public UserModel transform(com.spring.core.entity.User user) {
        return dozer.map(user, UserModel.class);
    }
    
    @Override
    public List<UserModel> listAllUsers() {
        List<com.spring.core.entity.User> listUser=userRepository.findAll();
        List<UserModel> listaCM=new ArrayList<>();
        listUser.forEach(a->{
        	UserModel userModel=transform(a);
            listaCM.add(userModel);
        });
        
        return listaCM;
    }

    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.spring.core.entity.User user = userRepository.findByUsername(username);
		List<GrantedAuthority> authorities = buildAuthorities(user.getUser_Role());	
		return buildUser(user, authorities);
	}
    
    @Override
    public UserModel findByUsername(String username) {
		com.spring.core.entity.User userResponse = userRepository.findByUsername(username);
		return transform(userResponse);
    }

    @Override
	public com.spring.core.entity.User addUser(com.spring.core.entity.User user) {
		user.setEnabled(true);
        return userRepository.save(user);
	}

	@Override
	public com.spring.core.entity.User editUser(UserModel userM) {
		com.spring.core.entity.User user=transform(userM);
        return userRepository.save(user);
	}

	
   
}