package com.rsp.service;

import java.util.List;

import com.rsp.inter.Ichina;
import com.rsp.model.Tab_china;

public interface IchinaService extends Ichina {
	
	@Override
	int deleteWhereId(String id);
	
	@Override
	int deleteWherePid(String pid);
	
	@Override
	int save(Tab_china china);
	
	@Override
	List<Tab_china> selectLike(String name);

	@Override
	List<Tab_china> selectWhwerePid(String pid);
}
