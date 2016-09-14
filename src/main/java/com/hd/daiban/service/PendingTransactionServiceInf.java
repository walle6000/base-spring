package com.hd.daiban.service;

import java.util.List;
import com.hd.daiban.bean.PendingTransaction;

public interface PendingTransactionServiceInf {

    public List<PendingTransaction> getAllTransactions();
    
    public void updateTransactions(List<PendingTransaction> entities);
    
    public List<String> getPendingTransactionType(String userName);
    
    public List<PendingTransaction> getUserPendingTransactionByType(String userName, String transactionType);
	
}
