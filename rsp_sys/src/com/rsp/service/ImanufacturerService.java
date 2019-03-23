package com.rsp.service;

import java.util.List;
import java.util.Map;

import com.rsp.inter.Imanufacturer;
import com.rsp.model.Tab_manufacturer;

public interface ImanufacturerService extends Imanufacturer {
	
	@Override
	Tab_manufacturer getWhereId(String id);

	@Override
	int deleteWhereId(String id);
	
	@Override
	int getCount(Map<String, Object> map);
	
	@Override
	List<Tab_manufacturer> pageSelect(Map<String, Object> map);
	
	@Override
	int save(Tab_manufacturer man);
	
	@Override
	int update(Tab_manufacturer man);
}
