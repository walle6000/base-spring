package com.hd.daiban.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.daiban.bean.PendingTransaction;
import com.hd.daiban.dao.PendingTransactionDaoInf;

@Service("PendingTransactionService")
public class PendingTransactionServiceImpl implements
		PendingTransactionServiceInf {
	
	private static Logger Log = LoggerFactory.getLogger(PendingTransactionServiceImpl.class);

	@Autowired
	private PendingTransactionDaoInf pendingTransactionDao;
	
	@Override
	public List<PendingTransaction> getAllTransactions() {
		List<PendingTransaction> ptList = pendingTransactionDao.getAll();
		
		if(ptList == null){
			ptList = new ArrayList<PendingTransaction>();
		}
		
		Log.debug("Sorted Pending Transaction List Size is {}.", ptList.size());
		return ptList;
	}

	@Override
	public void updateTransactions(List<PendingTransaction> entities) {
		Log.debug("Updating Pending transaction size is {}", entities.size());
		String PendingTransactionType = entities.get(0).getTransactionType();
		Log.debug("Updating Pending transaction type is {}", PendingTransactionType);
		int deleted = pendingTransactionDao.deletePendingTransactions(PendingTransactionType);
		Log.debug("Cleared previous Pending transactions and number is {}", deleted);
		pendingTransactionDao.saveAll(entities);	
	}

	@Override
	public List<String> getPendingTransactionType(String userName) {
		
		return pendingTransactionDao.getTransactionTypeByUserName(userName);
	}

	@Override
	public List<PendingTransaction> getUserPendingTransactionByType(String userName, String transactionType) {
        List<PendingTransaction> ptList = pendingTransactionDao.getUserPendingTransactionByType(userName, transactionType);
		
		if(ptList == null){
			ptList = new ArrayList<PendingTransaction>();
		}
		
		Log.debug("Sorted Pending Transaction List Size is {}.", ptList.size());
		return ptList;
	}

}
