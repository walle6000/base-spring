package com.hd.daiban.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.hd.daiban.bean.PendingTransaction;

public interface AgentServiceInf {

	public List<PendingTransaction> loadFamDataList(String userName) throws Exception;
	
	public JSONObject loadFamList(String userName,String pageSize, String pageNum);
	
}
