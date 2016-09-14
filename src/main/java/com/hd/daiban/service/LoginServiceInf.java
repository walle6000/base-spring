package com.hd.daiban.service;

import com.hd.daiban.bean.UserData;

public interface LoginServiceInf {
	
	public UserData checkUserAuthurization(String userName,String password) throws Exception;

	public boolean logout();

}
