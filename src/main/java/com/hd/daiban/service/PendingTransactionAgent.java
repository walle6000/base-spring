package com.hd.daiban.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.hd.daiban.bean.PendingTransaction;

@WebService
public interface PendingTransactionAgent {
	
	  @WebMethod
	  @WebResult
	  public String welcomeMessage(@WebParam String message);
	  

	  @WebMethod
	  @WebResult
	  public String submitPendingTransaction(@WebParam List<PendingTransaction> pt);
	  
	  @WebMethod
	  @WebResult
	  public List<PendingTransaction> generalLoadPendingTransaction(@WebParam String userName);
}
