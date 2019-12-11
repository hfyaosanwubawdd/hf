package com.hf.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hf.dao.DictDao;
import com.hf.entity.DictEntity;
import com.hf.service.CivilService;
@Service
public class CivilServiceImpl implements CivilService {

	@Autowired
	private DictDao dictDao;
	@Override
	public Map<Integer, String> getDictList() {
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		List<DictEntity> dictlist = dictDao.findAll();
		for (DictEntity dictEntity : dictlist) {
			resultMap.put(dictEntity.getId(), dictEntity.getDict_name());
		}
		return resultMap;
	}
}
