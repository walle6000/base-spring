package com.hd.daiban.controler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.daiban.bean.Page;
import com.hd.daiban.bean.PendingTransaction;
import com.hd.daiban.bean.UserData;
import com.hd.daiban.service.AgentServiceInf;
import com.hd.daiban.service.PendingTransactionServiceInf;


@Controller
@Scope("prototype")
@RequestMapping("agent.do")
public class FamControler {

	private static Logger Log = LoggerFactory.getLogger(FamControler.class);
	
	@Autowired
	private AgentServiceInf agentService;
	
	@Autowired
    private PendingTransactionServiceInf pendingTransactionService;	
	/*@Resource(name = "loginService")
	private LoginServiceInf logService;*/
	
	/*
	 * Request Parameters
	 * rows=10&page=1&sidx=transactionDate&sord=desc
	 * */
	@RequestMapping(params = "method=famload")
	@ResponseBody
	public Page<PendingTransaction> loadFamList(HttpServletRequest request,HttpSession httpSession){
		
		UserData userInfo = (UserData) httpSession.getAttribute("userInfo");
		
		Log.debug("The User {} is trying to loading FAM business list.",userInfo.getUserName());
		
        int pageSize = Integer.parseInt(request.getParameter("rows"));
		
		int pageNum = Integer.parseInt(request.getParameter("page"));
		
		String sord = request.getParameter("sord");
		
		Log.debug("The pageSize is {} and page number is {} and sort order is {}",pageSize,pageNum,sord);
		
		Page<PendingTransaction> pt_page = null;
		List<PendingTransaction> ptList = null;
		try {
			 ptList= agentService.loadFamDataList(userInfo.getUserName());
			 
			 pt_page = new Page<PendingTransaction>(pageSize,pageNum,sord,ptList);
			
			 if(ptList != null && ptList.size() != 0){
			     pendingTransactionService.updateTransactions(ptList);
			 }    
			 ObjectMapper mapper = new ObjectMapper();
			 Log.debug("Pending Transaction List size is:{}", pt_page.getRows().size());
			Log.debug("Page Info of FAM business list:{}", mapper.writeValueAsString(pt_page));
		} catch (Exception e) {
			Log.error("Exception:\n"+e.getStackTrace().toString());
			e.printStackTrace();
		}
		
		return pt_page;
	}
	
	/*
	 * This method is just used for testing
	 * rows=10&page=1&sidx=id&sord=asc
	 * */
	@RequestMapping(params = "method=famjson")
	@ResponseBody
	public JSONObject loadFamJson(HttpServletRequest request,HttpSession httpSession){
		
		UserData userInfo = (UserData) httpSession.getAttribute("userInfo");
		
		Log.debug("The User {} is trying to loading FAM business json.",userInfo.getUserName());
		
		String pageSize = request.getParameter("rows");
		
		String pageNum = request.getParameter("page");
		
		Log.debug("The pageSize is {}.",pageSize);
		
		Log.debug("The pageNum is {}.",pageNum);
		
		JSONObject page = agentService.loadFamList(userInfo.getUserName(),pageSize,pageNum);
		
		return page;
	}

}
