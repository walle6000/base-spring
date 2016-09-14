package com.hd.daiban.daoTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hd.daiban.bean.PendingTransaction;
import com.hd.daiban.dao.PendingTransactionDaoInf;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/resources/mybatis/spring-mybatis.xml"})
//@ContextConfiguration(locations={"file:src/main/resources/jpa/spring-jpa.xml"})
public class PendingTransactionDaoTest extends AbstractJUnit4SpringContextTests {
	
	private static Logger Log = LoggerFactory.getLogger(PendingTransactionDaoTest.class);
	
	@Autowired
	private PendingTransactionDaoInf pendingTransactionDaoInf;
	
	@Test
	public void saveTest() throws Exception {
		Assert.assertNotNull(pendingTransactionDaoInf);
		PendingTransaction entity = new PendingTransaction();
		entity.setIsNotified("Y");
		entity.setRecipient("ldaptest");
		entity.setStatus("OPEN");
		entity.setTransactionDate("2016-05-12");
		entity.setTransactionId("100000");
		entity.setTransactionName("Subject Testing");
		entity.setTransactionType("Test A");
		entity.setUrl("http://www.163.com");
		pendingTransactionDaoInf.save(entity);
		
		PendingTransaction entity_1 = new PendingTransaction();
		entity_1.setIsNotified("N");
		entity_1.setRecipient("ldaptest");
		entity_1.setStatus("OPEN");
		entity_1.setTransactionDate("2016-05-13");
		entity_1.setTransactionId("100001");
		entity_1.setTransactionName("Subject Testing");
		entity_1.setTransactionType("Test A");
		entity_1.setUrl("http://www.163.com");
		
		PendingTransaction entity_2 = new PendingTransaction();
		entity_2.setIsNotified("N");
		entity_2.setRecipient("ldaptest");
		entity_2.setStatus("OPEN");
		entity_2.setTransactionDate("2016-05-14");
		entity_2.setTransactionId("100002");
		entity_2.setTransactionName("Subject Testing");
		entity_2.setTransactionType("Test B");
		entity_2.setUrl("http://www.163.com");
		
		List<PendingTransaction> pt = new ArrayList<PendingTransaction>();
		pt.add(entity_1);
		pt.add(entity_2);
		pendingTransactionDaoInf.saveAll(pt);
	}
	
	@Test
	public void updateTest(){
		PendingTransaction entity = new PendingTransaction();
		entity.setIsNotified("Y");
		entity.setRecipient("ldaptest");
		entity.setStatus("OPEN");
		entity.setTransactionDate("2016-05-12");
		entity.setTransactionId("100000");
		entity.setTransactionName("Subject Testing 1");
		entity.setTransactionType("Test A");
		entity.setUrl("http://www.163.com");
		pendingTransactionDaoInf.update(entity);
		
		PendingTransaction entity_1 = new PendingTransaction();
		entity_1.setIsNotified("Y");
		entity_1.setRecipient("ldaptest");
		entity_1.setStatus("OPEN");
		entity_1.setTransactionDate("2016-05-13");
		entity_1.setTransactionId("100001");
		entity_1.setTransactionName("Subject Testing 2");
		entity_1.setTransactionType("Test A");
		entity_1.setUrl("http://www.163.com");
		
		PendingTransaction entity_2 = new PendingTransaction();
		entity_2.setIsNotified("Y");
		entity_2.setRecipient("ldaptest");
		entity_2.setStatus("OPEN");
		entity_2.setTransactionDate("2016-05-14");
		entity_2.setTransactionId("100002");
		entity_2.setTransactionName("Subject Testing 3");
		entity_2.setTransactionType("Test B");
		entity_2.setUrl("http://www.163.com");
		
		List<PendingTransaction> pt = new ArrayList<PendingTransaction>();
		pt.add(entity_1);
		pt.add(entity_2);
		pendingTransactionDaoInf.updateAll(pt);
	}
	
	@Test
	public void getTest() throws Exception {
		Assert.assertNotNull(pendingTransactionDaoInf);
		List<PendingTransaction> pt = pendingTransactionDaoInf.getAll();
		Assert.assertNotNull(pt);
		Log.debug("The Select Result Size is {}", pt.size());
		
		PendingTransaction onePt = pendingTransactionDaoInf.getById("100002");
		Log.debug("get one pt is {}", onePt.toString());
		
		List<String> typeList = pendingTransactionDaoInf.getTransactionTypeByUserName("ldaptest");
		Log.debug("The Select Result Type Size is {}", typeList.size());
		Log.debug("The Select Result Type is {}", typeList.get(0));
		
		pt = pendingTransactionDaoInf.getUserPendingTransactionByType("ldaptest", "Test A");
		Log.debug("The Select Result Size is for ldaptest {}", pt.size());
		
		
		pt = pendingTransactionDaoInf.getSendMessageForPendingTransactions();
		for(PendingTransaction t : pt){
			Log.debug(t.toString());
		}
		Log.debug("The Select Result Size is for message {}", pt.size());
		
		pt = pendingTransactionDaoInf.getUserPendingTransaction("ldaptest");
		Log.debug("The Select pending transactions for user ldaptest {}", pt.size());
	}
	
	//@Test
	public void deleteTest(){
		int result_testB =pendingTransactionDaoInf.deletePendingTransactions("Test B");
		Log.debug("The Delete Result is {}", result_testB);
		int result = pendingTransactionDaoInf.delete();
		Log.debug("The Delete Result is {}", result);
	}

}
