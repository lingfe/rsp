package com.rsp.service;

import java.util.List;
import java.util.Map;

import com.rsp.inter.It_yy_hospital;
import com.rsp.model.Tab_t_mark;
import com.rsp.model.Tab_t_yy_hospital;

public interface It_yy_hospitalService extends It_yy_hospital {

	@Override
	int deleteWhereId(String id);
	
	@Override
	int getCount(Map<String, Object> map);
	
	@Override
	List<Tab_t_mark> getWhere(Map<String, Object> map);
	
	@Override
	Tab_t_yy_hospital getWhereId(String id);
	
	@Override
	List<Tab_t_yy_hospital> pageSelect(Map<String, Object> map);
	
	@Override
	int save(Tab_t_yy_hospital tab);
	
	@Override
	int update(Tab_t_yy_hospital tab);
	
}
