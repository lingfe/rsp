package com.rsp.service;

import java.util.List;
import java.util.Map;

import com.rsp.inter.It_mark;
import com.rsp.model.Tab_t_mark;

public interface It_markService extends It_mark {

	@Override
	int deleteWhereId(String id);
	
	@Override
	List<Tab_t_mark> getWhere(Map<String, Object> map);
	
	@Override
	Tab_t_mark getWhereId(String id);
	
	@Override
	int save(Tab_t_mark tab);
}
