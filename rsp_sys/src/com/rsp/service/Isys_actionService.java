package com.rsp.service;

import java.util.List;
import java.util.Map;

import com.rsp.inter.Isys_action;
import com.rsp.model.Tab_sys_action;

public interface Isys_actionService extends Isys_action {

	@Override
	int deleteWhereId(Integer id);
	
	@Override
	int getCount(Map<String, Object> map);
	
	@Override
	Tab_sys_action getWhereId(Integer id);
	
	@Override
	List<Tab_sys_action> pageSelect(Map<String, Object> map);
	
	@Override
	int save(Tab_sys_action tab);
	
	@Override
	int update(Tab_sys_action tab);
	
}
