package com.rsp.service;

import java.util.List;
import java.util.Map;

import com.rsp.inter.Isys_department_coding;
import com.rsp.model.Tab_sys_department_coding;

public interface Isys_department_codingService extends Isys_department_coding {

	@Override
	List<Tab_sys_department_coding> getWhere(Map<String, Object> map);
	
	@Override
	int deleteWhereId(String id);
	
	@Override
	int getCount(Map<String, Object> map);
	
	@Override
	Tab_sys_department_coding getWhereId(String id);
	
	@Override
	List<Tab_sys_department_coding> pageSelect(Map<String, Object> map);
	
	@Override
	int save(Tab_sys_department_coding tab);
	
	@Override
	int update(Tab_sys_department_coding tab);
	
	@Override
	Tab_sys_department_coding getWhereCodingName(String coding_name);
}
