package com.rsp.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsp.mapper.Isys_manufacturerMapper;
import com.rsp.model.Tab_sys_manufacturer;
import com.rsp.service.Isys_manufacturerService;

/**
 * 
  * 文件名：ManufacturerServiceImpl.java
  * 描述： 生产厂家数据访问业务逻辑层
  * 修改人： lingfe
  * 修改时间：2019年3月22日 下午2:55:51
  * 修改内容：
 */
@Service
public class Sys_manufacturerServiceImpl implements Isys_manufacturerService {

	//自动装配
	@Autowired
	private Isys_manufacturerMapper isys_manufacturerMapper;

	@Override
	public int deleteWhereId(String id) {
		return isys_manufacturerMapper.deleteWhereId(id);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return isys_manufacturerMapper.getCount(map);
	}

	@Override
	public List<Tab_sys_manufacturer> pageSelect(Map<String, Object> map) {
		return isys_manufacturerMapper.pageSelect(map);
	}

	@Override
	public int save(Tab_sys_manufacturer man) {
		return isys_manufacturerMapper.save(man);
	}

	@Override
	public int update(Tab_sys_manufacturer man) {
		return isys_manufacturerMapper.update(man);
	}

	@Override
	public Tab_sys_manufacturer getWhereId(String id) {
		return isys_manufacturerMapper.getWhereId(id);
	}
	
}
