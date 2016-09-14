package com.hd.daiban.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.hd.daiban.bean.PendingTransaction;
import com.hd.daiban.handler.SoapRequestService;

@Service("agentService")
public class AgentServiceImpl implements AgentServiceInf {
 
	private static Logger Log = LoggerFactory.getLogger(AgentServiceImpl.class);
	
	@Override
	public List<PendingTransaction> loadFamDataList(String userName) throws Exception{
		Log.debug("Loading FAM business list for {}", userName);
		
		List<PendingTransaction> ptList = SoapRequestService.sendSoapRequestForFAM(userName);
		
		if(ptList == null){
			ptList = new ArrayList<PendingTransaction>();
		}
		
		Collections.sort(ptList);
		
		Log.debug("Sorted Pending Transaction List Size is {}.", ptList.size());
		
		return ptList;
	}	
	
	/*this method is just used for testing*/
	public JSONObject loadFamList(String userName,String pageSize, String pageNum) {
		Log.debug("Loading FAM business list for {}", userName);
		JSONObject jsonObj = new JSONObject();
		
		JSONArray rows = new JSONArray();
		for (int i = 0; i < 14; i++) {
			JSONObject cell = new JSONObject();
			cell.put("id", i);
			if (i % 2 == 0) {
				cell.put("userName", "polaris");
				cell.put("gender", "FEMALE");
			} else {
				cell.put("userName", "NEIL");
				cell.put("gender", "MALE");
			}
			cell.put("email", "polaris@gmail.com");
			cell.put("QQ", "772" + i + "1837" + i);
			cell.put("mobilePhone", "132" + i + "1837" + i + "3" + i);
			cell.put("birthday", "198" + i + "-10-" + "1" + i);
			rows.add(cell);
		}
		jsonObj.put("rows", rows);
		
		jsonObj.put("page", pageNum);
		jsonObj.put("total", 2);
		jsonObj.put("records", 14);
		Log.debug("FAM business list:{}", jsonObj.toString());
		return jsonObj;
	}

}
