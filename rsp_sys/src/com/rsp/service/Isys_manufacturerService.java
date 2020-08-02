package com.rsp.service;

import java.util.List;
import java.util.Map;

import com.rsp.inter.Isys_manufacturer;
import com.rsp.model.Tab_sys_manufacturer;

public interface Isys_manufacturerService extends Isys_manufacturer {
	
	@Override
	Tab_sys_manufacturer getWhereId(String id);

	@Override
	int deleteWhereId(String id);
	
	@Override
	int getCount(Map<String, Object> map);
	
	@Override
	List<Tab_sys_manufacturer> pageSelect(Map<String, Object> map);
	
	@Override
	int save(Tab_sys_manufacturer man);
	
	@Override
	int update(Tab_sys_manufacturer man);
}
