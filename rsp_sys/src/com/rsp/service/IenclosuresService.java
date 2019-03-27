package com.rsp.service;

import java.util.List;
import java.util.Map;

import com.rsp.inter.Ienclosures;
import com.rsp.model.Tab_enclosure;

public interface IenclosuresService extends Ienclosures {

	@Override
	int deleteWhereId(String id);
	
	@Override
	int getCount(Map<String, Object> map);
	
	@Override
	Tab_enclosure getWhereId(String id);
	
	@Override
	List<Tab_enclosure> pageSelect(Map<String, Object> map);
	
	@Override
	int save(Tab_enclosure tab);
	
	@Override
	int update(Tab_enclosure tab);
}
