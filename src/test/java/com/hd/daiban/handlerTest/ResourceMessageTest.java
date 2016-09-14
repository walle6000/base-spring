package com.hd.daiban.handlerTest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hd.daiban.base.BaseTest;
import com.hd.daiban.handler.ResourceMessage;

public class ResourceMessageTest  extends BaseTest{
	
	private String resources = "resource";
	
	private String localeStr = "en_US";
	
	private static Logger Log = LoggerFactory.getLogger(ResourceMessageTest.class);
	
	@Test
	public void resourceReadTest(){
		ResourceMessage rm = new ResourceMessage(resources, localeStr);
		Log.debug(rm.getText("FamLoginName"));
	}

}
