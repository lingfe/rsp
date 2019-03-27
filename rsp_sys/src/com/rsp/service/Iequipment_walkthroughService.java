package com.rsp.service;

import java.util.List;
import java.util.Map;

import com.rsp.inter.Iequipment_walkthrough;
import com.rsp.model.Tab_equipment_walkthrough;

public interface Iequipment_walkthroughService extends Iequipment_walkthrough {

	@Override
	int deleteWhereId(String id);
	
	@Override
	int getCount(Map<String, Object> map);
	
	@Override
	Tab_equipment_walkthrough getWhereId(String id);
	
	@Override
	List<Tab_equipment_walkthrough> pageSelect(Map<String, Object> map);
	
	@Override
	int save(Tab_equipment_walkthrough tab);
	
	@Override
	int update(Tab_equipment_walkthrough tab);
}
