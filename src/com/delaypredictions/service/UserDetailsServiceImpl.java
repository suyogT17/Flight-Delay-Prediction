package com.delaypredictions.service;

import java.util.ArrayList;
import java.util.Collection;





import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.delaypredictions.dao.RefMasterMaintainDAOImpl;
import com.delaypredictions.model.User;
import com.delaypredictions.util.ApplicationConstantsUtil;
import com.delaypredictions.util.SearchParameter;




@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private RefMasterMaintainDAOImpl refMasterMaintainDAOImpl;

	@Override
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException, DataAccessException {
		// TODO Auto-generated method stub
		UserDetails userDetails  = null;
		User user = null;
		//Criterion crt = Restrictions.eq("userName", arg0);
		List<SearchParameter> searchparameters = new ArrayList<SearchParameter>();
		searchparameters.add(new SearchParameter(ApplicationConstantsUtil.MC_EQUAL, "userName", arg0));
		System.out.println("username: "+arg0);
		
		try {
			List<User> userList = refMasterMaintainDAOImpl.findEntityList(User.class, searchparameters, null);
			System.out.println(userList.size()+"  "+userList);
			if(userList != null && userList.size()>0)
			{
				user = userList.get(0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user==null)
		{
			System.out.println("usernull");
			throw new UsernameNotFoundException("user not found");
		}
		
		userDetails = this.buildUserFromUserEntity(user);
		return userDetails;
	}

	private UserDetails buildUserFromUserEntity(User userEntity) {
		// TODO Auto-generated method stub
		final String username = userEntity.getUserName();
		final String password = userEntity.getPassword();
		final boolean enabled = userEntity.getIsEnabled();
		final Collection<GrantedAuthority> authority = new ArrayList<GrantedAuthority>();
		authority.add(new GrantedAuthorityImpl(userEntity.getRole().getRole_name()));
		org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(username, password, true, true, true, true, authority);
		return user;
	}

}
 