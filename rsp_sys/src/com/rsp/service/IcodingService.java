package com.rsp.service;

import java.util.List;
import java.util.Map;

import com.rsp.inter.Icoding;
import com.rsp.model.Tab_coding;

public interface IcodingService extends Icoding {
	
	@Override
	Tab_coding getWhereId(String id);
	
	@Override
	int getCount();

	@Override
	int deleteWhereId(String id);
	
	@Override
	public List<Tab_coding> pageSelect(Map<String,Object> map);
	
	@Override
	int save(Tab_coding coding);
	
	@Override
	int update(Tab_coding coding);
}
