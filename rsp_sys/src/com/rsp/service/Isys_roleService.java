package com.rsp.service;

import java.util.List;
import java.util.Map;

import com.rsp.inter.Isys_role;
import com.rsp.model.Tab_sys_role;

public interface Isys_roleService extends Isys_role {

	@Override
	int deleteWhereId(String id);
	
	@Override
	int getCount(Map<String, Object> map);
	
	@Override
	Tab_sys_role getWhereId(String id);
	
	@Override
	List<Tab_sys_role> pageSelect(Map<String, Object> map);
	
	@Override
	int save(Tab_sys_role tab);
	
	@Override
	int update(Tab_sys_role tab);
}
