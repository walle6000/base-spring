/*
################################################################################
#                                                                               
# Name : ResourceMessage.java       
# Author: Zhou Tao                                             
# Desc : ResourceMessage is used to decorate class java.util.ResourceBundle .
#                                                                                                                                                             
# (C) COPYRIGHT IBM Corporation 2013                                      
# All Rights Reserved.                                                          
#                                                                               
# Licensed Materials-Property of IBM                                            
#                                                                               
################################################################################ 
*/
package com.hd.daiban.handler;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ResourceMessage {
	private  static Logger logger = LoggerFactory.getLogger(ResourceMessage.class);
	private  Map<String,String> resourceMap = new Hashtable<String,String>();
	private  static final String SPLIT_REX = ",";
	public ResourceMessage(){}
	ResourceBundle resourceBundle;
	
	public ResourceMessage(String resources,String localeStr){
		logger.info("Starting load resource ...");
		bundleResource(resources,localeStr);
	}
	
	public ResourceMessage(String resources)
	{
		if(resources!=null){
			Enumeration<String> enumKey = null;
			String[] resourceArray = resources.split(SPLIT_REX);
			for(String resourceStr : resourceArray){
				
				logger.info("Starting load resource file [ " + resourceStr + " ] with Locale [  defaultLocale ]...");
				resourceBundle = ResourceBundle.getBundle(resourceStr, Locale.getDefault());
				
				enumKey= resourceBundle.getKeys();
				while(enumKey.hasMoreElements()){
					String keyTmp = enumKey.nextElement();
					resourceMap.put(keyTmp, resourceBundle.getString(keyTmp));
				}
			}
		}
	}
	/**
	 * <P><P>
	 * @param resources  e.g "resource1,resource2"
	 * @param localeStr  e.g "zh_CN"
	 */
	public void bundleResource(String resources,String localeStr){
		Locale locale  =new Locale(localeStr.split("_")[0], localeStr.split("_")[1]);
		Enumeration<String> enumKey = null;
		
		if(resources!=null){
			String[] resourceArray = resources.split(SPLIT_REX);
			for(String resourceStr : resourceArray){
				
				logger.info("Starting load resource file [ " + resourceStr + " ] with Locale [ " + localeStr + " ]...");
				resourceBundle = ResourceBundle.getBundle(resourceStr, locale);
				
				enumKey= resourceBundle.getKeys();
				while(enumKey.hasMoreElements()){
					String keyTmp = enumKey.nextElement();
					resourceMap.put(keyTmp, resourceBundle.getString(keyTmp));
				}
			}
		}
	}
	
	/**
	 * <P>Get resource string with key string</p>
	 * @param str
	 * @return 
	 */
	public String getText(String str){
		String result  = resourceMap.get(str);
		if(result != null){
			return result;
		}
		return str;
	}
	
}
