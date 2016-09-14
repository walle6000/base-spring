package com.hd.daiban.schedule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hd.daiban.bean.PendingTransaction;
import com.hd.daiban.dao.PendingTransactionDaoInf;
import com.hd.daiban.handler.SoapRequestService;

public class MessageSendTask {

	private static Logger Log = LoggerFactory.getLogger(SoapRequestService.class);
	
	@Autowired
	private PendingTransactionDaoInf PendingTransactionDao; 
	
	public void execute(){
		Log.debug("Begin to execute schedule task");
		List<PendingTransaction> messageList = PendingTransactionDao.getSendMessageForPendingTransactions();
		Log.debug("The Select Result Size for message is {}", messageList.size());
		for(PendingTransaction t : messageList){
			Log.debug("Trying to send one message to {} and The number is {}", t.getRecipient(),t.getTransactionId());
			new Sender(t).start();
		}
		Log.debug("Complete one schedule task");
	}
	
	class Sender extends Thread{
		
		private PendingTransaction messageTransaction;
		
		public Sender(PendingTransaction pt){
			this.messageTransaction = pt;
		}
		
		@Override
		public void run() {
			String message = this.messageTransaction.getTransactionType() + /*"\n" + this.messageTransaction.getTransactionId() +*/ "\n" + this.messageTransaction.getTransactionName();
			try {
				Boolean isSent = SoapRequestService.sendSoapMessage(message, this.messageTransaction.getMobile());
				if(isSent){
					messageTransaction.setIsNotified("Y");
					PendingTransactionDao.update(this.messageTransaction);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
	} 
	
}
