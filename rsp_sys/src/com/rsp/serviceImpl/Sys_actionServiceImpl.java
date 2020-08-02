package com.rsp.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsp.mapper.Isys_actionMapper;
import com.rsp.model.Tab_sys_action;
import com.rsp.service.Isys_actionService;

/**
 * 
  * 文件名：Sys_actionServiceImpl.java
  * 描述： 权限表数据访问业务逻辑层
  * 修改人： lingfe
  * 修改时间：2019年4月12日 下午3:12:20
  * 修改内容：
 */
@Service
public class Sys_actionServiceImpl implements Isys_actionService {

	@Autowired
	private Isys_actionMapper isys_actionMapper;
	
	@Override
	public int deleteWhereId(Integer id) {
		return isys_actionMapper.deleteWhereId(id);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return isys_actionMapper.getCount(map);
	}

	@Override
	public Tab_sys_action getWhereId(Integer id) {
		return isys_actionMapper.getWhereId(id);
	}

	@Override
	public List<Tab_sys_action> pageSelect(Map<String, Object> map) {
		return isys_actionMapper.pageSelect(map);
	}

	@Override
	public int save(Tab_sys_action tab) {
		return isys_actionMapper.save(tab);
	}

	@Override
	public int update(Tab_sys_action tab) {
		return isys_actionMapper.update(tab);
	}

}
