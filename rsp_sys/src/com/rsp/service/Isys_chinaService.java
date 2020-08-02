package com.rsp.service;

import java.util.List;

import com.rsp.inter.Isys_china;
import com.rsp.model.Tab_sys_china;

public interface Isys_chinaService extends Isys_china {
	
	@Override
	int deleteWhereId(String id);
	
	@Override
	int deleteWherePid(String pid);
	
	@Override
	int save(Tab_sys_china china);
	
	@Override
	List<Tab_sys_china> selectLike(String name);

	@Override
	List<Tab_sys_china> selectWhwerePid(String pid);
}
