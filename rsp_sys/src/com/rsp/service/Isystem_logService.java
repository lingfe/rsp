package com.rsp.service;

import java.util.List;
import java.util.Map;

import com.rsp.inter.Isystem_log;
import com.rsp.model.Tab_system_log;

public interface Isystem_logService extends Isystem_log{

	@Override
	int add(Tab_system_log syslog);
	
	@Override
	List<Tab_system_log> pageSelect(Map<String, Object> map);
}
