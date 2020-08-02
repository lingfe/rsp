package com.rsp.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsp.mapper.Iequipment_walkthroughMapper;
import com.rsp.model.Tab_equipment_walkthrough;
import com.rsp.service.Iequipment_walkthroughService;

/**
 * 
  * 文件名：Equipment_walkthroughServiceImpl.java
  * 描述： 设备预排表业务逻辑层
  * 修改人： lingfe
  * 修改时间：2019年3月26日 下午3:18:20
  * 修改内容：
 */
@Service
public class Equipment_walkthroughServiceImpl implements Iequipment_walkthroughService {

	//自动装配
	@Autowired
	private Iequipment_walkthroughMapper iequipment_walkthroughMapper;
	
	@Override
	public List<Tab_equipment_walkthrough> getWhere(Map<String, Object> map) {
		return iequipment_walkthroughMapper.getWhere(map);
	}
	
	@Override
	public int deleteWhereId(String id) {
		return iequipment_walkthroughMapper.deleteWhereId(id);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return iequipment_walkthroughMapper.getCount(map);
	}

	@Override
	public Tab_equipment_walkthrough getWhereId(String id) {
		return iequipment_walkthroughMapper.getWhereId(id);
	}

	@Override
	public List<Tab_equipment_walkthrough> pageSelect(Map<String, Object> map) {
		return iequipment_walkthroughMapper.pageSelect(map);
	}

	@Override
	public int save(Tab_equipment_walkthrough tab) {
		return iequipment_walkthroughMapper.save(tab);
	}

	@Override
	public int update(Tab_equipment_walkthrough tab) {
		return iequipment_walkthroughMapper.update(tab);
	}

}
