package com.hf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hf.service.CivilService;
import com.hf.util.ConstanceUtil;
import com.hf.util.LogUtil;
@RequestMapping("/civil")
@RestController
public class ESCivilController {
	@Autowired
	private CivilService civilService;
    @Value("${param}")
    private String param;
	@RequestMapping("/start")
	public String start() {
//		synchronized(this) {
//			if (ConstanceUtil.CIVIL_STATE != 0) {
//				return "正在运行";
//			}
//			ConstanceUtil.CIVIL_STATE = 1;
//		}
//		Map<Integer, String> idMap = civilService.getDictList();
		LogUtil.info(param, this.getClass());
		return "ok";
	}
	
	@RequestMapping("/stop")
	@ResponseBody
	public String stop() {
		if (ConstanceUtil.CIVIL_STATE == 0) {
			return "已经停止";
		}
		if (ConstanceUtil.CIVIL_STATE == 1) {
			return "即将停止";
		}
		ConstanceUtil.CIVIL_STATE = 1;
		return "即将停止";
	}
}
