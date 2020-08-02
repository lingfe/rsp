package com.rsp.service;

import java.util.List;
import java.util.Map;

import com.rsp.inter.Isys_system_log;
import com.rsp.model.Tab_sys_system_log;

public interface Isys_system_logService extends Isys_system_log{
	
	@Override
	int getCount();

	@Override
	int add(Tab_sys_system_log syslog);
	
	@Override
	List<Tab_sys_system_log> pageSelect(Map<String, Object> map);
}
