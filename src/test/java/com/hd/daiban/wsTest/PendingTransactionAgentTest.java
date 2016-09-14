package com.hd.daiban.wsTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hd.daiban.bean.PendingTransaction;
import com.hd.daiban.service.PendingTransactionAgent;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/resources/ws/ws-beans-client.xml"})
public class PendingTransactionAgentTest extends AbstractJUnit4SpringContextTests {
	
	private static Logger Log = LoggerFactory.getLogger(PendingTransactionAgentTest.class);
	
	@Resource(name="agentClient")
	private PendingTransactionAgent pendingTransactionAgentClient;
	
	@Test
	public void welcomeMessageTest() {
		Log.debug("Soap Service welcome Message Test");
		String result = pendingTransactionAgentClient.welcomeMessage("Hello World");
		Log.debug("Soap Service welcome Message Test, Result is {}", result);
	}
	
	@Test
	public void submitPendingTransactionTest(){
		Log.debug("Soap Service submit pedning transaction Test");
		List<PendingTransaction> pt = new ArrayList<PendingTransaction>();
		
		for(int i = 0; i<20; i++){
		PendingTransaction entity_a = new PendingTransaction();
		entity_a.setIsNotified("Y");
		entity_a.setRecipient("ldaptest");
		entity_a.setStatus("OPEN");
		entity_a.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		entity_a.setTransactionId("20000"+i);
		entity_a.setTransactionName("Subject Testing");
		entity_a.setTransactionType("Test C");
		entity_a.setPriority("80");
		entity_a.setUrl("http://www.baidu.com");
		pt.add(entity_a);
		}
		
		String result = pendingTransactionAgentClient.submitPendingTransaction(pt);
		Log.debug("Soap Service welcome Message Test, Result is {}", result);
		
		Assert.assertTrue(result.indexOf("20")>-1);
	}

}
