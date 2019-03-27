package com.rsp.service;

import java.util.List;
import java.util.Map;

import com.rsp.inter.Iprocess_setting;
import com.rsp.model.Tab_process_setting;

public interface Iprocess_settingService extends Iprocess_setting {

	@Override
	int deleteWhereId(String id);
	
	@Override
	int getCount(Map<String, Object> map);
	
	@Override
	Tab_process_setting getWhereId(String id);
	
	@Override
	List<Tab_process_setting> pageSelect(Map<String, Object> map);
	
	@Override
	int save(Tab_process_setting pro);
	
	@Override
	int update(Tab_process_setting pro);
}
