package com.delaypredictions.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delaypredictions.dao.RefMasterMaintainDAOImpl;
import com.delaypredictions.model.Role;
import com.delaypredictions.model.User;

@Service
public class UserService {

	@Autowired
	RefMasterMaintainDAOImpl refMasterMaintainDAOImpl;
	
	public void registerUser(User user) {
		// TODO Auto-generated method stub
		
		user.setIsEnabled(true);
		user.setIsLocked(false);
		user.setCreationDate(new Date());
		Role role=new Role();
		role.setRole_ID(2);
		user.setRole(role);
		refMasterMaintainDAOImpl.saveEntity(user);
		
	}

	
	
	
}
