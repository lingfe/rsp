package com.rsp.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsp.mapper.Isys_role_actionMapper;
import com.rsp.model.Tab_sys_role_action;
import com.rsp.service.Isys_role_actionService;

/**
 * 
  * 文件名：Sys_role_actionServiceImpl.java
  * 描述： 角色权限表访问业务逻辑层
  * 修改人： lingfe
  * 修改时间：2019年4月12日 下午5:03:25
  * 修改内容：
 */
@Service
public class Sys_role_actionServiceImpl implements Isys_role_actionService {

	@Autowired
	private Isys_role_actionMapper isys_role_actionMapper;
	
	@Override
	public Tab_sys_role_action getWhereRoleIdAndActionIdList(String role_id, Integer action_id) {
		return isys_role_actionMapper.getWhereRoleIdAndActionIdList(role_id, action_id);
	}
	
	@Override
	public int deleteWhereId(String id) {
		return isys_role_actionMapper.deleteWhereId(id);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return isys_role_actionMapper.getCount(map);
	}

	@Override
	public Tab_sys_role_action getWhereId(String id) {
		return isys_role_actionMapper.getWhereId(id);
	}

	@Override
	public List<Tab_sys_role_action> pageSelect(Map<String, Object> map) {
		return isys_role_actionMapper.pageSelect(map);
	}

	@Override
	public int save(Tab_sys_role_action tab) {
		return isys_role_actionMapper.save(tab);
	}

	@Override
	public int update(Tab_sys_role_action tab) {
		return isys_role_actionMapper.update(tab);
	}

}
