package com.rsp.service;

import java.util.List;
import java.util.Map;

import com.rsp.inter.Isys_role_action;
import com.rsp.model.Tab_sys_role_action;

public interface Isys_role_actionService extends Isys_role_action {

	@Override
	Tab_sys_role_action getWhereRoleIdAndActionIdList(String role_id, Integer action_id);
	
	@Override
	int deleteWhereId(String id);
	
	@Override
	int getCount(Map<String, Object> map);
	
	@Override
	Tab_sys_role_action getWhereId(String id);
	
	@Override
	List<Tab_sys_role_action> pageSelect(Map<String, Object> map);
	
	@Override
	int save(Tab_sys_role_action tab);
	
	@Override
	int update(Tab_sys_role_action tab);
}
