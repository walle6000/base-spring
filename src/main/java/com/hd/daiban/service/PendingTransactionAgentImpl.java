package com.hd.daiban.service;

import java.text.MessageFormat;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.daiban.bean.PendingTransaction;
import com.hd.daiban.dao.PendingTransactionDaoInf;


@Service("PendingTransactionAgentService")
public class PendingTransactionAgentImpl implements PendingTransactionAgent {
	
	private static Logger Log = LoggerFactory.getLogger(PendingTransactionAgentImpl.class);
	
	private static String SUC = "{0} Pending Transactipon have been submitted successfully";
	
	private static String ONSUC = "None of the Pending Transactipon have been submitted";

	@Autowired
	private PendingTransactionDaoInf PendingTransactionDao;

	@Override
	@WebMethod
	@WebResult
	public String welcomeMessage(@WebParam String message) {
		Log.debug("Web Service Testing on welcome message interface.");
		return "server success , accpet message values is :" + message;
	}

	@Override
	@WebMethod
	@WebResult
	public String submitPendingTransaction(@WebParam List<PendingTransaction> pt) {
		Log.debug("Web Service - submit pending transactions:");
		Log.debug("Web Service - Pending transaction submitted size is {}", pt.size());
		if(pt != null && pt.size() != 0){
			Log.debug("Web Service - Pending transaction submitted size is {}", pt.size());
			String PendingTransactionType = pt.get(0).getTransactionType();
			Log.debug("Web Service - Pending transaction submitted type is {}", PendingTransactionType);
			int deleted = PendingTransactionDao.deletePendingTransactions(PendingTransactionType);
			Log.debug("Web Service - Cleard previous Pending transactions and number is {}", deleted);
			PendingTransactionDao.saveAll(pt);
			Log.debug("Web Service - New Pending transactions have been submitted");
			return MessageFormat.format(SUC,pt.size());
		}else{
		    return ONSUC;
		}
	}

	@Override
	@WebMethod
	@WebResult
	public List<PendingTransaction> generalLoadPendingTransaction(@WebParam String userName) {
		
		return PendingTransactionDao.getUserPendingTransaction(userName);
	}

}
