package com.hd.daiban.dao.jpa;

import java.util.List;

//import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hd.daiban.bean.PendingTransaction;
import com.hd.daiban.dao.PendingTransactionDaoInf;
import com.hd.daiban.handler.ResourceMessage;
import com.hd.daiban.handler.SoapRequestService;

@Repository("pendingTransactionDao")
public class PendingTransactionDaoImpl extends BaseDaoImpl<PendingTransaction> implements PendingTransactionDaoInf {

	private static ResourceMessage RM = new ResourceMessage("resource", "en_US");
	
	private static Logger Log = LoggerFactory.getLogger(PendingTransactionDaoImpl.class);
	
	public PendingTransactionDaoImpl() {
		super(PendingTransaction.class);
	}

	@Override
	public int deletePendingTransactions(String transactionType) {
		/*Query query = em.createQuery("delete from " + PendingTransaction.class.getSimpleName() + " as PT where PT.transactionType = :type");
		
		query.setParameter("type", transactionType);
		
		int result = query.executeUpdate(); 
		return result;*/
		return 0;
	}

	@Override
	public List<PendingTransaction> getAllTransactionsByType(String transactionType) {
		/*Query query = em.createQuery("select t from "+ this.entityName + " t where t.transactionType = :type order by t.transactionDate desc");
		
		query.setParameter("type", transactionType);
		
		List<PendingTransaction> result = query.getResultList();
		return result;*/
		return null;
	}

	@Override
	public List<String> getAllTransactionType() {
        /*Query query = em.createQuery("select distinct t.transactionType from "+ this.entityName + " t");
		
		List<String> result = query.getResultList();
		return result;*/
		return null;
	}

	@Override
	public List<String> getTransactionTypeByUserName(String userName) {
		 /*Query query = em.createQuery("select distinct t.transactionType from "+ this.entityName + " t where t.recipient = :userName and t.transactionType <> 'FAM' order by t.transactionType asc");
		 query.setParameter("userName", userName);
		 List<String> result = query.getResultList();
		return result;*/
		return null;
	}

	@Override
	public List<PendingTransaction> getUserPendingTransactionByType(String userName, String transactionType) {
        /*Query query = em.createQuery("select t from "+ this.entityName + " t where t.transactionType = :type and t.recipient = :userName order by t.transactionDate desc");
		
		query.setParameter("type", transactionType);
		
		query.setParameter("userName", userName);
		
		List<PendingTransaction> result = query.getResultList();
		return result;*/
		return null;
	}

	@Override
	public List<PendingTransaction> getSendMessageForPendingTransactions() {
		/*Log.debug("Load pending transactions for sending message");
		String nativeSql = RM.getText("MessageNotifySQL");
		Log.debug("The native sql is {}",nativeSql);
		
		Query query = em.createNativeQuery(nativeSql, PendingTransaction.class);
		List<PendingTransaction> result = query.getResultList();
		
		return result;*/
		return null;
	}
	
	@Override
	public List<PendingTransaction> getUserPendingTransaction(String userName) {
        /*Query query = em.createQuery("select t from "+ this.entityName + " t where t.recipient = :userName order by t.transactionDate desc");
		
		query.setParameter("userName", userName);
		
		List<PendingTransaction> result = query.getResultList();
		return result;*/
		return null;
	}
	
}
