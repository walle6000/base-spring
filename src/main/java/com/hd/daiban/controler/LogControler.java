package com.hd.daiban.controler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;




import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hd.daiban.bean.UserData;
import com.hd.daiban.service.LoginServiceInf;
import com.hd.daiban.service.PendingTransactionServiceInf;

@Controller
@Scope("prototype")
@RequestMapping("login.do")
public class LogControler {

	private static Logger Log = LoggerFactory.getLogger(LogControler.class);

	@Resource(name = "loginService")
	private LoginServiceInf logService;
	
	@Resource(name = "PendingTransactionService")
	private PendingTransactionServiceInf PendingTransactionService;

	@RequestMapping(params = "method=login")
	public ModelAndView login(UserData userLogin,HttpSession httpSession) throws Exception {

		Log.debug("The User {} is trying to login.", userLogin.getUserName());

		UserData userInfo = null;
		
		if(StringUtils.isNotEmpty(userLogin.getUserName())&&StringUtils.isNotEmpty(userLogin.getPassword())){
			 userInfo = logService.checkUserAuthurization(userLogin.getUserName(), userLogin.getPassword());
		}
		
		ModelAndView view;

		if (userInfo != null) {
			Log.debug("The User {} has been verified.", userInfo.getUserName());
			Log.debug("Loading pending transaction type");
			List<String> typeList = PendingTransactionService.getPendingTransactionType(userInfo.getUserName());
			Log.debug("pending transaction type size is {}",typeList.size());
			Map<String, Object> sucResponse = new HashMap<String, Object>();
			sucResponse.put("user", userLogin);
			sucResponse.put("typeList", typeList);
			view = new ModelAndView("businessList", sucResponse);
			httpSession.setAttribute("userInfo", userInfo);
		} else {
			Log.debug("The User fail to login.");
			Map<String, Object> failResponse = new HashMap<String, Object>();
			failResponse.put("user", userLogin);
			failResponse.put("errorMessage", "登录失败");
			view = new ModelAndView("login", failResponse);
		}

		return view;
	}

	@RequestMapping(params = "method=logout", method = RequestMethod.GET)
	public String logout(HttpSession httpSession) {
		UserData userInfo = (UserData) httpSession.getAttribute("userInfo");
		Log.debug("user {} is tyring to logout.", userInfo.getPassword());
		httpSession.invalidate();
		return "login";
	}

}
