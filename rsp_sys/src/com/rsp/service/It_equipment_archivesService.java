package com.rsp.service;

import java.util.List;
import java.util.Map;

import com.rsp.inter.It_equipment_archives;
import com.rsp.model.Tab_t_equipment_archives;

public interface It_equipment_archivesService extends It_equipment_archives {

	
	
	@Override
	int deleteWhereId(String id);
	
	@Override
	int getCount(Map<String, Object> map);
	
	@Override
	Tab_t_equipment_archives getWhereId(String id);
	
	@Override
	List<Tab_t_equipment_archives> pageSelect(Map<String, Object> map);
	
	@Override
	int save(Tab_t_equipment_archives tab);
	
	@Override
	int update(Tab_t_equipment_archives tab);
}
