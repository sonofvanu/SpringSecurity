package com.niit.SpringSecurityExample.dao;

import java.util.List;

import com.niit.SpringSecurityExample.model.UserInfo;

public interface LoginDAO {

	UserInfo findUserInfo(String username);

	List getUserRoles(String username);
}
