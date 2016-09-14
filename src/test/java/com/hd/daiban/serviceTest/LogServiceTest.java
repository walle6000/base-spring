package com.hd.daiban.serviceTest;


import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hd.daiban.base.BaseTest;
import com.hd.daiban.bean.UserData;
import com.hd.daiban.controler.FamControler;
import com.hd.daiban.service.LoginServiceInf;




public class LogServiceTest extends BaseTest{


	private static Logger Log = LoggerFactory.getLogger(LogServiceTest.class);
	
	@Resource(name = "loginService")
	LoginServiceInf loginService;
	
	@Autowired
	private FamControler FAMControler;
	
	@Test
	public void checkUserAuthurizationTest() throws Exception{
		Log.debug("Test for user login.");
		Assert.assertNotNull(loginService);
		UserData userinfo = loginService.checkUserAuthurization("ldaptest", "12345");
		Assert.assertNotNull(userinfo);
		userinfo = loginService.checkUserAuthurization("admin", "admin123");
		Assert.assertNull(userinfo);
	}

}
