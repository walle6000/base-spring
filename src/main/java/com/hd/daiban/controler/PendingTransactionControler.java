package com.hd.daiban.controler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.hd.daiban.service.PendingTransactionServiceInf;


@Controller
@Scope("prototype")
@RequestMapping("transactions.do")
public class PendingTransactionControler {

	private static Logger Log = LoggerFactory.getLogger(PendingTransactionControler.class);
	
	@Autowired
	private PendingTransactionServiceInf pendingTransactionService;
	
	/*
	 * Request Parameters
	 * rows=10&page=1&sidx=transactionDate&sord=desc
	 * type: Pending Transaction Type
	 * */
	@RequestMapping(params = "method=transactionLoad")
	@ResponseBody
	public Page<PendingTransaction> loadPendingTransactiopnList(HttpServletRequest request,HttpSession httpSession){
		
		UserData userInfo = (UserData) httpSession.getAttribute("userInfo");
		
		Log.debug("The User {} is trying to loading FAM business list.",userInfo.getUserName());
		
        int pageSize = Integer.parseInt(request.getParameter("rows"));
		
		int pageNum = Integer.parseInt(request.getParameter("page"));
		
		String sord = request.getParameter("sord");
		
		String transactionType = request.getParameter("type");
		
		Log.debug("The pending transaction type is {}", transactionType);
		
		Log.debug("The pageSize is {} and page number is {} and sort order is {}",pageSize,pageNum,sord);
		
		Page<PendingTransaction> pt_page = null;
		List<PendingTransaction> ptList = null;
		try {
			 ptList= pendingTransactionService.getUserPendingTransactionByType(userInfo.getUserName(), transactionType);
			 
			 pt_page = new Page<PendingTransaction>(pageSize,pageNum,sord,ptList);
				
			 ObjectMapper mapper = new ObjectMapper();
			 Log.debug("Pending Transaction List size is:{}", pt_page.getRows().size());
			 Log.debug("Page Info of transaction business list:{}", mapper.writeValueAsString(pt_page));
		} catch (Exception e) {
			Log.error("Exception:\n"+e.getStackTrace().toString());
			e.printStackTrace();
		}
		
		return pt_page;
	}
	
	

}
