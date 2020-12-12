package com.spring.core.service;

import java.util.List;

import com.spring.core.entity.User;
import com.spring.core.model.UserModel;

public interface UserService {

		public abstract List<UserModel> listAllUsers();
		
		public abstract User addUser(User userM);
		
		public abstract User editUser(UserModel userM);
		
		public abstract UserModel updateUser(UserModel userModel);
		
		public abstract int removeUser(int id);
		
		//transformar entidad a modelo
	    public abstract User transform(UserModel userModel);

	    //transformar modelo a entidad
	    public abstract UserModel transform(User user);

		UserModel findByUsername(String username);

		
}
