package com.rsp.service;

import java.util.List;
import java.util.Map;

import com.rsp.inter.It_yy_ksxx;
import com.rsp.model.Tab_t_yy_ksxx;

public interface It_yy_ksxxService extends It_yy_ksxx {

	@Override
	int deleteWhereId(String id);
	
	@Override
	int getCount(Map<String, Object> map);
	
	@Override
	List<Tab_t_yy_ksxx> getWhere(Map<String, Object> map);
	
	@Override
	Tab_t_yy_ksxx getWhereId(String id);
	
	@Override
	List<Tab_t_yy_ksxx> pageSelect(Map<String, Object> map);
	
	@Override
	int save(Tab_t_yy_ksxx tab);
	
	@Override
	int update(Tab_t_yy_ksxx tab);
}
