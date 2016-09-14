package com.hd.daiban.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.hd.daiban.bean.PendingTransaction;
import com.hd.daiban.dao.mybatis.MyBatisRepository;

@Component
@MyBatisRepository
public interface PendingTransactionDaoInf extends BaseDaoInf<PendingTransaction>{

	public int deletePendingTransactions(String transactionType);
		
	public List<PendingTransaction>	getAllTransactionsByType(String transactionType);
	
	public List<String> getAllTransactionType();
	
	public List<String> getTransactionTypeByUserName(String userName);
	
	public List<PendingTransaction> getUserPendingTransactionByType(@Param("userName") String userName,@Param("transactionType") String transactionType);
	
	public List<PendingTransaction> getSendMessageForPendingTransactions();
	
	public List<PendingTransaction> getUserPendingTransaction(String userName);
	
}
