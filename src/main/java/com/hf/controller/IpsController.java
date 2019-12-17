package com.hf.controller;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.jsoup.internal.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hf.Thread.JiSuThread;
import com.hf.Thread.LiuLiuThread;
import com.hf.Thread.NimaThread;
import com.hf.Thread.XiLaThread;
import com.hf.util.ConstanceUtil;
import com.hf.util.LogUtil;
import com.hf.util.ThreadPoolSingleton;
@RequestMapping("/ips")
@RestController
public class IpsController {
	@RequestMapping("/start")
	public String start() {
		synchronized (this) {
			if (ConstanceUtil.SPIDER_STATE != 0) {
				return "already in use";
			}
			ConstanceUtil.SPIDER_STATE = 1;
		}
		ThreadPoolSingleton.getinstance().executeThread(new XiLaThread());
		ThreadPoolSingleton.getinstance().executeThread(new NimaThread());
		ThreadPoolSingleton.getinstance().executeThread(new JiSuThread());
		ThreadPoolSingleton.getinstance().executeThread(new LiuLiuThread());
		return "ok";
	}
	
	@RequestMapping("/level")
	public String level(HttpServletRequest request) {
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String string = (String) headerNames.nextElement();
			String header = request.getHeader(string);
			System.out.println(string+"---"+header);
		}
		String ip = request.getHeader("x-forwarded-for");
    	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
    		ip = request.getHeader("Proxy-Client-IP");
    		LogUtil.info(ip, this.getClass());
    	}
    	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
    		ip = request.getHeader("WL-Proxy-Client-IP");
    		LogUtil.info(ip, this.getClass());
    	}
    	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
    		ip = request.getRemoteAddr();
    		LogUtil.info(ip, this.getClass());
    	}
    	if (StringUtil.isBlank(ip)) {
			ip = "unknown";
		}
		return "ok";
	}
}
