package com.rsp.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsp.mapper.Isys_roleMapper;
import com.rsp.model.Tab_sys_role;
import com.rsp.service.Isys_roleService;

/**
 * 
  * 文件名：Sys_roleServiceImpl.java
  * 描述： 角色表访问业务逻辑层
  * 修改人： lingfe
  * 修改时间：2019年4月12日 下午3:54:10
  * 修改内容：
 */
@Service
public class Sys_roleServiceImpl implements Isys_roleService {

	@Autowired
	private Isys_roleMapper isys_roleMapper;

	@Override
	public int deleteWhereId(String id) {
		return isys_roleMapper.deleteWhereId(id);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return isys_roleMapper.getCount(map);
	}

	@Override
	public Tab_sys_role getWhereId(String id) {
		return isys_roleMapper.getWhereId(id);
	}

	@Override
	public List<Tab_sys_role> pageSelect(Map<String, Object> map) {
		return isys_roleMapper.pageSelect(map);
	}

	@Override
	public int save(Tab_sys_role tab) {
		return isys_roleMapper.save(tab);
	}

	@Override
	public int update(Tab_sys_role tab) {
		return isys_roleMapper.update(tab);
	}
}
