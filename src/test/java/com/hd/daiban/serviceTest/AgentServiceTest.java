package com.hd.daiban.serviceTest;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hd.daiban.base.BaseTest;
import com.hd.daiban.bean.PendingTransaction;
import com.hd.daiban.service.AgentServiceInf;

public class AgentServiceTest extends BaseTest{
	
	private static Logger Log = LoggerFactory.getLogger(AgentServiceTest.class);

	@Autowired
	private AgentServiceInf agentService;
	
	@Test
	public void loadFamListTest() throws Exception{
		Assert.assertNotNull(agentService);
		List<PendingTransaction> ptList = null;
		try {
			ptList = agentService.loadFamDataList("admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(ptList);
		Assert.assertEquals(8,ptList.size());
		Log.debug("Pending Transaction List Size is {}.", ptList.size());
		for(PendingTransaction pt : ptList){
			Log.debug("Pending Transaction  is {}.", pt.toString());
		}
		Collections.sort(ptList);
		Log.debug("Sorted Pending Transaction List Size is {}.", ptList.size());
		for(PendingTransaction pt : ptList){
			Log.debug("Pending Transaction  is {}", pt.getUrl());
			//Log.debug(pt.getTransactionDate().replaceAll("T", " ").substring(0, 19));
		}
	}
	
}
