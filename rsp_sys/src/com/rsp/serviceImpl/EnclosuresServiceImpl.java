package com.rsp.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsp.mapper.IenclosuresMapper;
import com.rsp.model.Tab_enclosure;
import com.rsp.service.IenclosuresService;

/**
 * 
  * 文件名：IenclosuresServiceImpl.java
  * 描述： 
  * 修改人： lingfe
  * 修改时间：2019年3月26日 上午9:50:06
  * 修改内容：
 */
@Service
public class EnclosuresServiceImpl implements IenclosuresService {

	//自动装配
	@Autowired
	private IenclosuresMapper ienclosuresMapper;
	
	@Override
	public List<Tab_enclosure> getWhere(Map<String, Object> map) {
		return ienclosuresMapper.getWhere(map);
	}

	@Override
	public int deleteWhereId(String id) {
		return ienclosuresMapper.deleteWhereId(id);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return ienclosuresMapper.getCount(map);
	}

	@Override
	public Tab_enclosure getWhereId(String id) {
		return ienclosuresMapper.getWhereId(id);
	}

	@Override
	public List<Tab_enclosure> pageSelect(Map<String, Object> map) {
		return ienclosuresMapper.pageSelect(map);
	}

	@Override
	public int save(Tab_enclosure tab) {
		return ienclosuresMapper.save(tab);
	}

	@Override
	public int update(Tab_enclosure tab) {
		return ienclosuresMapper.update(tab);
	}
}
