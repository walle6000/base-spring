package com.hd.daiban.base;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/mvc-config.xml","file:src/main/resources/spring/application-config.xml"})
public class BaseTest extends AbstractJUnit4SpringContextTests {
	
	@Before
	public void beforeTest(){
		
	}
	
	@After
	public void afterTest(){
		
	}
	
	@Test
	public void BasementTest(){
		
	}

}
