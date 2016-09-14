package com.hd.daiban.handler;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import com.hd.daiban.bean.PendingTransaction;

public class SaxParseService extends DefaultHandler {
	
	private List<PendingTransaction> pendingTransactions = null;  
    private PendingTransaction pendingTransaction = null;  
    private String preTag = null;//作用是记录解析时的上一个节点名称  
    private String transactionType = "FAM";
    private String itemTag = "soaNotifinfos";
    
      
    public static List<PendingTransaction> getPendingTransactions(InputStream xmlStream) throws Exception{  
        SAXParserFactory factory = SAXParserFactory.newInstance();  
        SAXParser parser = factory.newSAXParser();  
        SaxParseService handler = new SaxParseService();  
        parser.parse(xmlStream, handler);  
        return handler.getPendingTransactions();  
    }
      
    public List<PendingTransaction> getPendingTransactions(){  
        return pendingTransactions;  
    }  
      
    @Override  
    public void startDocument() throws SAXException {  
    	pendingTransactions = new ArrayList<PendingTransaction>();  
    }  
  
    @Override  
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {  
        if(itemTag.equals(qName)){  
        	pendingTransaction = new PendingTransaction();  
        }  
        preTag = qName;//将正在解析的节点名称赋给preTag  
    }  
  
    @Override  
    public void endElement(String uri, String localName, String qName)  
            throws SAXException {  
        if(itemTag.equals(qName)){
        	pendingTransaction.setTransactionType(transactionType);
        	pendingTransaction.updateTransactionName();
        	pendingTransactions.add(pendingTransaction);  
        	pendingTransaction = null;  
        }  
        preTag = null;
    }  
      
    @Override  
    public void characters(char[] ch, int start, int length) throws SAXException {  
        if(preTag!=null){  
            String content = new String(ch,start,length);  
            if("NOTIFICATION_ID".equalsIgnoreCase(preTag)){  
            	pendingTransaction.setTransactionId(content);  
            }else if("SUBJECT".equalsIgnoreCase(preTag)){
            	pendingTransaction.setTransactionName(content);  
            }else if("MORE_INFO_ROLE".equalsIgnoreCase(preTag)){//for getting the URL
            	pendingTransaction.setUrl(content);  
            }else if("BEGIN_DATE".equalsIgnoreCase(preTag)){  
            	pendingTransaction.setTransactionDate(content.replaceAll("T", " ").substring(0, 19));  
            }else if("STATUS".equalsIgnoreCase(preTag)){  
            	pendingTransaction.setStatus(content);  
            }else if("PRIORITY".equalsIgnoreCase(preTag)){  
            	pendingTransaction.setPriority(content);  
            }else if("ORIGINAL_RECIPIENT".equalsIgnoreCase(preTag)){  
            	pendingTransaction.setRecipient(content);  
            }
        }  
    }  

}
