package com.hd.daiban.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.hd.daiban.bean.UserData;
import com.hd.daiban.handler.SoapRequestService;

@Service("loginService")
public class LoginServiceImpl implements LoginServiceInf {
	
	private static Logger Log = LoggerFactory.getLogger(LoginServiceImpl.class);

	public UserData checkUserAuthurization(String userName, String password) throws Exception{
		Log.debug("check user for {}", userName);
		UserData userInfo = null;
		boolean isVerified = SoapRequestService.sendSoapRequestUserVerify(userName,password);
		if(isVerified){
			Log.debug("User {} is verified.",userName);
			userInfo = new UserData();
			userInfo.setUserName(userName);
			userInfo.setMobile("11111111111");
		}
		return userInfo;
	}

	public boolean logout() {
		// TODO Auto-generated method stub
		return false;
	}

	
}
