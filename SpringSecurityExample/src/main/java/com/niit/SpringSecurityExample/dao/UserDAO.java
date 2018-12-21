package com.niit.SpringSecurityExample.dao;

import java.util.List;

import com.niit.SpringSecurityExample.model.UserInfo;

public interface UserDAO {

	public List list();

	public UserInfo findUserByUsername(String username);

	public void update(String username, String password);

	public void add(String username, String password);

	public boolean userExists(String username);
}
