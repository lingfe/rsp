package com.rsp.service;

import java.util.List;
import java.util.Map;

import com.rsp.inter.Ilicence_walkthrough;
import com.rsp.model.Tab_licence_walkthrough;


public interface Ilicence_walkthroughService extends Ilicence_walkthrough {

	@Override
	int deleteWhereId(String id);
	
	@Override
	int getCount(Map<String, Object> map);
	
	@Override
	Tab_licence_walkthrough getWhereId(String id);
	
	@Override
	List<Tab_licence_walkthrough> pageSelect(Map<String, Object> map);
	
	@Override
	int save(Tab_licence_walkthrough tab);
	
	@Override
	int update(Tab_licence_walkthrough tab);
}
