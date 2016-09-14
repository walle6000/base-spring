package com.hd.daiban.handler;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hd.daiban.bean.PendingTransaction;

public class SoapRequestService {
	
    private static Logger Log = LoggerFactory.getLogger(SoapRequestService.class);
    
    private static ResourceMessage RM = new ResourceMessage("resource", "en_US");
    
    //private static String FAMURL = "http://famtest.hdpi.cn:8000/webservices/SOAProvider/plsql/cux_fnd_mobile_notifi_pkg/?wsdl";
    //private static String FAMURL = "http://famtest.hdpi.cn:8000/webservices/SOAProvider/plsql/cux_soa_notifi_pkg/?wsdl";
    private static String FAMURL = RM.getText("FAMURL");
    
    /*private static String LOGINURL = "http://10.100.227.111:9012/webmvc/services/userAuthent?wsdl";*/
    private static String LOGINURL = RM.getText("LOGINURL");
    
    /*private static String MESSAGEURL = "http://10.158.176.46:7001/smsService/service/MessageService?wsdl";*/
    private static String MESSAGEURL = RM.getText("MESSAGEURL");
	
	private static String loginName = RM.getText("FamLoginName");
	
	private static String loginPassword = RM.getText("FamLoginPassword");
	
    private static String messageUser = RM.getText("MessageUser");
	
	private static String messagePassword = RM.getText("MessagePassword");
    
    public static List<PendingTransaction> sendSoapRequestForFAM(String userName) throws Exception {
		Log.debug("{} is trying to send soap request.",userName);
		// 服务的地址
		URL wsUrl = new URL(FAMURL);

		HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");

		OutputStream os = conn.getOutputStream();
		
		/*// 请求体
		String soap = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">"
				+ "<soap:Header><wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\" soap:mustUnderstand=\"1\"><wsse:UsernameToken xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\"><wsse:Username>{0}</wsse:Username><wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">{1}</wsse:Password></wsse:UsernameToken></wsse:Security></soap:Header>"
				+ "<soap:Body xmlns:ns1=\"http://xmlns.oracle.com/apps/fnd_mo/soaprovider/plsql/cux_soa_notifi_pkg/query_notification_info/\">"
				+ "<ns1:InputParameters>"
				+ "<ns1:P_USER_NAME>{2}</ns1:P_USER_NAME>"
				+ "<ns1:P_ATTRIBUTE1/>"
				+ "<ns1:P_ATTRIBUTE2/>"
				+ "<ns1:P_ATTRIBUTE3/>"
				+ "<ns1:P_ATTRIBUTE4/>"
				+ "<ns1:P_ATTRIBUTE5/>"
				+ "</ns1:InputParameters>"
				+ "</soap:Body>" + "</soap:Envelope>";*/
		
		String soap = RM.getText("FamSoap");
		
		if("true".equalsIgnoreCase(RM.getText("debug"))){//for debug
			//soap = MessageFormat.format(soap,loginName,loginPassword,"HDPI_EAM");
			soap = MessageFormat.format(soap,"HDPI_EAM");
		}else{
		    soap = MessageFormat.format(soap, userName);
		}
		
		Log.debug("Request soap content is {}",soap);

		os.write(soap.getBytes());

		InputStream is = conn.getInputStream();
		
		List<PendingTransaction> pendingTransactions = SaxParseService.getPendingTransactions(is);

		is.close();
		os.close();
		conn.disconnect();
		
		Log.debug("Return request result,the size is {}",pendingTransactions.size());
		return pendingTransactions;
	}
    
    public static boolean sendSoapRequestUserVerify(String userName,String password) throws Exception {
		Log.debug("{} is trying to send soap request for verifying",userName);
		// 服务的地址
		URL wsUrl = new URL(LOGINURL);

		HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");

		OutputStream os = conn.getOutputStream();
		
		// 请求体
		/*String soap = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webService.ibm.com/\">"+
				      "<soapenv:Header/><soapenv:Body><web:isUser><arg0>{0}</arg0><arg1>{1}</arg1></web:isUser></soapenv:Body></soapenv:Envelope>";*/
		
		String soap = RM.getText("LoginSoap");
		
		soap = MessageFormat.format(soap,userName,password);
		
		Log.debug("Request soap content is {}",soap);

		os.write(soap.getBytes());

		InputStream is = conn.getInputStream();
		
		byte[] b = new byte[1024];
		int len = 0;
		String s = "";
		while ((len = is.read(b)) != -1) {
			String ss = new String(b, 0, len, "UTF-8");
			s += ss;
		}
		
		is.close();
		os.close();
		conn.disconnect();
		
		Log.debug("Return request result is {}",s);
		return s.indexOf("true") != -1;
	}
    
    public static boolean sendSoapMessage(String message,String phoneNumber) throws Exception {
		Log.debug("trying to send message for for the phone number:{}", phoneNumber);
		Log.debug("trying to send message:{}", message);
		// 服务的地址
		URL wsUrl = new URL(MESSAGEURL);

		HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");

		OutputStream os = conn.getOutputStream();
		
		// 请求体
		String soap = RM.getText("SendMessageSoap");
		
		soap = MessageFormat.format(soap,messageUser,messagePassword,message,phoneNumber);
		
		Log.debug("Request message soap content is {}",soap);

		os.write(soap.getBytes());

		InputStream is = conn.getInputStream();
		
		byte[] b = new byte[1024];
		int len = 0;
		String s = "";
		while ((len = is.read(b)) != -1) {
			String ss = new String(b, 0, len, "UTF-8");
			s += ss;
		}
		
		is.close();
		os.close();
		conn.disconnect();
		
		Log.debug("Return message request result is {}",s);
		return s.indexOf("Y") != -1;
	}
}
