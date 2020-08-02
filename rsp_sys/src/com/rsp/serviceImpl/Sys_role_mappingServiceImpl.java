package com.rsp.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsp.mapper.Isys_role_mappingMapper;
import com.rsp.model.Tab_sys_role_mapping;
import com.rsp.service.Isys_role_mappingService;

/**
 * 
  * 文件名：Sys_role_mappingServiceImpl.java
  * 描述： 角色映射表访问业务逻辑层
  * 修改人： lingfe
  * 修改时间：2019年4月12日 下午5:33:41
  * 修改内容：
 */
@Service
public class Sys_role_mappingServiceImpl implements Isys_role_mappingService {

	@Autowired
	private Isys_role_mappingMapper isys_role_mappingMapper;
	
	@Override
	public Tab_sys_role_mapping getWhereUserId(String user_id) {
		return isys_role_mappingMapper.getWhereUserId(user_id);
	}
	
	@Override
	public int deleteWhereId(String id) {
		return isys_role_mappingMapper.deleteWhereId(id);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return isys_role_mappingMapper.getCount(map);
	}

	@Override
	public Tab_sys_role_mapping getWhereId(String id) {
		return isys_role_mappingMapper.getWhereId(id);
	}

	@Override
	public List<Tab_sys_role_mapping> pageSelect(Map<String, Object> map) {
		return isys_role_mappingMapper.pageSelect(map);
	}

	@Override
	public int save(Tab_sys_role_mapping tab) {
		return isys_role_mappingMapper.save(tab);
	}

	@Override
	public int update(Tab_sys_role_mapping tab) {
		return isys_role_mappingMapper.update(tab);
	}

}
