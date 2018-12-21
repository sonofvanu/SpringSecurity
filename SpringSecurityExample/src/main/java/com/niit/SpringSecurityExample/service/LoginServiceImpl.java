package com.niit.SpringSecurityExample.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.niit.SpringSecurityExample.dao.LoginDAO;
import com.niit.SpringSecurityExample.model.UserInfo;

@Repository
public class LoginServiceImpl implements UserDetailsService {

	LoginDAO loginDao;

	@Autowired
	public void setLoginDao(LoginDAO loginDao) {
		this.loginDao = loginDao;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo = loginDao.findUserInfo(username);

		if (userInfo == null) {
			throw new UsernameNotFoundException("username was not found in the database");
		}

		List roles = loginDao.getUserRoles(username);

		List grantList = new ArrayList();

		if (roles != null) {
			for (Object role : roles) {
				GrantedAuthority authority = new SimpleGrantedAuthority((String) role);
				grantList.add(authority);
			}
		}

		UserDetails userDetails = new User(userInfo.getUsername(), userInfo.getPassword(), grantList);

		return userDetails;
	}

}
