package com.rsp.service;

import java.util.List;
import java.util.Map;

import com.rsp.inter.It_yy_sb_sbda;
import com.rsp.model.Tab_t_yy_sb_sbda;

public interface It_yy_sb_sbdaService extends It_yy_sb_sbda {

	@Override
	int deleteWhereId(String id);
	
	@Override
	int getCount(Map<String, Object> map);
	
	@Override
	Tab_t_yy_sb_sbda getWhereId(String id);
	
	@Override
	List<Tab_t_yy_sb_sbda> pageSelect(Map<String, Object> map);
	
	@Override
	int save(Tab_t_yy_sb_sbda tab);
	
	@Override
	int update(Tab_t_yy_sb_sbda tab);
}
