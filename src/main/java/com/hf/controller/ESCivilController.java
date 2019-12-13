package com.hf.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hf.dao.IpsDao;
import com.hf.entity.IpsEntity;
import com.hf.service.CivilService;
import com.hf.util.ConstanceUtil;
import com.hf.util.LogUtil;
import com.hf.util.SpringContextUtil;
@RequestMapping("/civil")
@RestController
public class ESCivilController {
	@Autowired
	private CivilService civilService;
	@Autowired
	private IpsDao ipsDao;
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
		
		List<IpsEntity> ipsList = new ArrayList<IpsEntity>();
		for (int i = 0; i < 10; i++) {
			IpsEntity ip = new IpsEntity();
			ip.setCreatetime(new Date());
			ip.setIp("192.168.1."+i);
			ip.setUseport(i);
			ip.setDatafrom("test");
			ipsList.add(ip);
		}
		ipsDao.batchAddIps(ipsList);
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
