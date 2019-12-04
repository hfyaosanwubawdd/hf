package com.hf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hf.dao.IpsDao;
import com.hf.entity.IpsEntity;
@RequestMapping("/ips")
@RestController
public class IpsController {
	@Autowired
	private IpsDao ipsDao;
	@RequestMapping("/save")
	public String ipsSave() {
		System.out.println("阿斯蒂芬斯蒂芬");
		IpsEntity ipsEntity = new IpsEntity();
		ipsEntity.setIp("asdfsadf");
		ipsEntity.setDatafrom("test");
		ipsEntity.setUseport(8888);
		ipsDao.save(ipsEntity);
		return "ok";
	}
	@RequestMapping("/find")
	@ResponseBody
	public List<IpsEntity> find() {
		return ipsDao.findPage(1, 2);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable Integer id) {
		ipsDao.delete(id);
		return "ok";
	}
}
