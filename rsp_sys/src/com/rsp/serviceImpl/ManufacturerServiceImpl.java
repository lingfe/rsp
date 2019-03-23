package com.rsp.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsp.mapper.ImanufacturerMapper;
import com.rsp.model.Tab_manufacturer;
import com.rsp.service.ImanufacturerService;

/**
 * 
  * 文件名：ManufacturerServiceImpl.java
  * 描述： 生产厂家数据访问业务逻辑层
  * 修改人： lingfe
  * 修改时间：2019年3月22日 下午2:55:51
  * 修改内容：
 */
@Service
public class ManufacturerServiceImpl implements ImanufacturerService {

	//自动装配
	@Autowired
	private ImanufacturerMapper imanufacturerMapper;

	@Override
	public int deleteWhereId(String id) {
		return imanufacturerMapper.deleteWhereId(id);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return imanufacturerMapper.getCount(map);
	}

	@Override
	public List<Tab_manufacturer> pageSelect(Map<String, Object> map) {
		return imanufacturerMapper.pageSelect(map);
	}

	@Override
	public int save(Tab_manufacturer man) {
		return imanufacturerMapper.save(man);
	}

	@Override
	public int update(Tab_manufacturer man) {
		return imanufacturerMapper.update(man);
	}

	@Override
	public Tab_manufacturer getWhereId(String id) {
		return imanufacturerMapper.getWhereId(id);
	}
	
}
