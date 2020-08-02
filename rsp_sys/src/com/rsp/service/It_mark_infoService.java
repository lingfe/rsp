package com.rsp.service;

import java.util.List;
import java.util.Map;

import com.rsp.inter.It_mark_info;
import com.rsp.model.Tab_t_mark_info;

public interface It_mark_infoService extends It_mark_info {

	@Override
	int deleteWhereId(String id);
	
	@Override
	List<Tab_t_mark_info> getWhere(Map<String, Object> map);
	
	@Override
	Tab_t_mark_info getWhereId(String id);
	
	@Override
	int save(Tab_t_mark_info tab);
}
