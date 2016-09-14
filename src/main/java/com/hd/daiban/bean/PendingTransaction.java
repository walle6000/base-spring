package com.hd.daiban.bean;

import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;

import com.hd.daiban.handler.ResourceMessage;

/*import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;*/

/*Pending Transaction Entity Bean*/

//@Entity
//@Table(name="HDPI_PENDING_TRANSACTION")
public class PendingTransaction implements Comparable<PendingTransaction> {
	
	private static ResourceMessage RM = new ResourceMessage("resource","zh_CN");
    /*@SequenceGenerator(name = "PENDING_SUQUENCE", sequenceName = "SEQ_HDPI_PENDING")
    @Id
    @GeneratedValue(generator = "PENDING_SUQUENCE")
    private Integer id;*/
	
	//@Id
	//@Column(name="TRANSACTION_ID")
	private String transactionId;
	
	//@Column(name="TRANSACTION_SUBJECT",nullable=false)
	private String transactionName;
	
	//@Transient
	private String transactionSubject = "<a target=\"blank\" href=\"{0}\">{1}</a>";
	
	//@Column(name="TRANSACTION_URL",nullable=false)
	private String url;

	//@Column(name="TRANSACTION_PRIORITY")
	private String priority;

	//@Column(name="TRANSACTION_DATE")
	private String transactionDate;

	//@Column(name="RECIPIENT",nullable=false)
	private String recipient;
	
	//@Column(name="STATUS")
	private String status;
	
	//@Column(name="TRANSACTION_TYPE",nullable=false)
	private String transactionType;
	
	//@Column(name="ISNOTIFIED")
	private String isNotified;
	
	//@Column(name="MOBILE")
	private String mobile;
	
	
	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionName() {
		return transactionName;
	}
	
    public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}
    

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		if(this.url !=null){
			this.url += url; 
		}else{
	 	    this.url = url;
		}
	}
	

	public String getTransactionSubject() {
		if(this.transactionSubject.indexOf("{0}")!=-1){
		     this.transactionSubject = MessageFormat.format(this.transactionSubject,this.url,this.transactionName);
		     if(StringUtils.isNotEmpty(this.priority)&&StringUtils.isNumeric(this.priority)&&Integer.parseInt(this.priority) > 50){
		    	  this.transactionSubject = RM.getText("urgentTransaction") + this.transactionSubject;
		     }
		}
		return this.transactionSubject;
	}

	public void setTransactionSubject(String transactionSubject) {
		this.transactionSubject = transactionSubject;
	}


	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	
	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getIsNotified() {
		return isNotified;
	}

	public void setIsNotified(String isNotified) {
		this.isNotified = isNotified;
	}
	
	

	/*public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}*/

	public void updateTransactionName(){
		if(this.transactionName!=null&&this.url!=null){
			this.transactionSubject = MessageFormat.format(this.transactionSubject,this.url,this.transactionName);
			if(StringUtils.isNotEmpty(this.priority)&&StringUtils.isNumeric(this.priority)&&Integer.parseInt(this.priority) > 50){
				this.transactionSubject = RM.getText("urgentTransaction") + this.transactionSubject;
		     }
		}
	}


	@Override
	public String toString() {
		return "PendingTransaction [transactionId="
				+ transactionId + ", transactionName=" + transactionName
				+ ", transactionSubject=" + transactionSubject + ", url=" + url
				+ ", level=" + priority + ", transactionDate=" + transactionDate
				+ ", recipient=" + recipient + ", status=" + status
				+ ", transactionType=" + transactionType + ", isNotified="
				+ isNotified + ", mobile=" + mobile + "]";
	}

	@Override
	public int compareTo(PendingTransaction o) {
		 if (this.transactionDate.compareTo(o.getTransactionDate()) > 0) {
	            return -1;
	        }
	        if (this.transactionDate.compareTo(o.getTransactionDate()) < 0) {
	            return 1;
	        }
	        return 0;
	}

	
	
}
