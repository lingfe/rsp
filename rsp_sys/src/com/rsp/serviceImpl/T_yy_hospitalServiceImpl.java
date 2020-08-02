package com.rsp.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsp.mapper.It_yy_hospitalMapper;
import com.rsp.model.Tab_t_mark;
import com.rsp.model.Tab_t_yy_hospital;
import com.rsp.service.It_yy_hospitalService;

/**
 * 
  * 文件名：T_yy_hospitalServiceImpl.java
  * 描述： 医院信息表业务逻辑层
  * 修改人： lingfe
  * 修改时间：2019年4月20日 上午9:49:53
  * 修改内容：
 */
@Service
public class T_yy_hospitalServiceImpl implements It_yy_hospitalService {

	@Autowired
	private It_yy_hospitalMapper it_yy_hospitalMapper;
	
	@Override
	public int deleteWhereId(String id) {
		return it_yy_hospitalMapper.deleteWhereId(id);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return it_yy_hospitalMapper.getCount(map);
	}

	@Override
	public List<Tab_t_mark> getWhere(Map<String, Object> map) {
		return it_yy_hospitalMapper.getWhere(map);
	}

	@Override
	public Tab_t_yy_hospital getWhereId(String id) {
		return it_yy_hospitalMapper.getWhereId(id);
	}

	@Override
	public List<Tab_t_yy_hospital> pageSelect(Map<String, Object> map) {
		return it_yy_hospitalMapper.pageSelect(map);
	}

	@Override
	public int save(Tab_t_yy_hospital tab) {
		return it_yy_hospitalMapper.save(tab);
	}

	@Override
	public int update(Tab_t_yy_hospital tab) {
		return it_yy_hospitalMapper.update(tab);
	}

}
