package com.hd.daiban.scheduleTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"file:src/main/resources/jpa/spring-jpa.xml","file:src/main/resources/schedule/spring-schedule.xml"})
@ContextConfiguration(locations={"file:src/main/resources/mybatis/spring-mybatis.xml","file:src/main/resources/schedule/spring-schedule.xml"})
public class MessageSendTaskTest extends AbstractJUnit4SpringContextTests {
	
	private static Logger Log = LoggerFactory.getLogger(MessageSendTaskTest.class);
	
	@Test
	public void deleteTest(){
		Log.debug("Sleep 60000");
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.debug("exist testing");
	}

}
