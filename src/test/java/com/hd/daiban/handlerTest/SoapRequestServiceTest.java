package com.hd.daiban.handlerTest;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hd.daiban.base.BaseTest;
import com.hd.daiban.bean.PendingTransaction;
import com.hd.daiban.handler.SoapRequestService;

public class SoapRequestServiceTest extends BaseTest {
	
	private static Logger Log = LoggerFactory.getLogger(SoapRequestServiceTest.class);
	
	//@Test
	public void soapFAMRequestTest() throws Exception {
		// 服务的地址
		List<PendingTransaction> pts = SoapRequestService.sendSoapRequestForFAM("HDPI_EAM");
		
		for(PendingTransaction pt : pts){
			Log.debug(pt.toString());
		}
		
		Assert.assertEquals(8, pts.size());

	}
	
	//@Test
	public void soapVerifyRequestTest() throws Exception {
		
		boolean isVerified = SoapRequestService.sendSoapRequestUserVerify("ldap","12345");
		
		Assert.assertEquals(true, isVerified);
		
		isVerified = SoapRequestService.sendSoapRequestUserVerify("ldap_test","12345");
		
		Assert.assertEquals(false, isVerified);
	}

	
	@Test
	public void soapMessageRequestTest() throws Exception {
		
		boolean isVerified = SoapRequestService.sendSoapMessage("Message Testing","13981111111");
		
		Assert.assertEquals(true, isVerified);
		
		isVerified = SoapRequestService.sendSoapMessage("测试短信内容","13981111111");
		
		Assert.assertEquals(true, isVerified);
	}
}
