package com.rsp.service;

import java.util.List;
import java.util.Map;

import com.rsp.inter.Isys_role_mapping;
import com.rsp.model.Tab_sys_role_mapping;

public interface Isys_role_mappingService extends Isys_role_mapping {

	@Override
	Tab_sys_role_mapping getWhereUserId(String user_id);
	
	@Override
	int deleteWhereId(String id);
	
	@Override
	int getCount(Map<String, Object> map);
	
	@Override
	Tab_sys_role_mapping getWhereId(String id);
	
	@Override
	List<Tab_sys_role_mapping> pageSelect(Map<String, Object> map);
	
	@Override
	int save(Tab_sys_role_mapping tab);
	
	@Override
	int update(Tab_sys_role_mapping tab);
}
