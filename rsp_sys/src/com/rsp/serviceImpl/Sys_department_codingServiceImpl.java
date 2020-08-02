package com.rsp.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsp.mapper.Isys_department_codingMapper;
import com.rsp.model.Tab_sys_department_coding;
import com.rsp.service.Isys_department_codingService;

/**
 * 
  * 文件名：Sys_department_codingServiceImpl.java
  * 描述： 科室编码数据访问业务逻辑层
  * 修改人： lingfe
  * 修改时间：2019年4月8日 下午4:57:35
  * 修改内容：
 */
@Service
public class Sys_department_codingServiceImpl implements Isys_department_codingService{

	
	@Autowired
	private Isys_department_codingMapper isys_department_codingMapper;
	
	@Override
	public int deleteWhereId(String id) {
		return isys_department_codingMapper.deleteWhereId(id);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return isys_department_codingMapper.getCount(map);
	}

	@Override
	public Tab_sys_department_coding getWhereId(String id) {
		return isys_department_codingMapper.getWhereId(id);
	}

	@Override
	public List<Tab_sys_department_coding> pageSelect(Map<String, Object> map) {
		return isys_department_codingMapper.pageSelect(map);
	}

	@Override
	public int save(Tab_sys_department_coding tab) {
		return isys_department_codingMapper.save(tab);
	}

	@Override
	public int update(Tab_sys_department_coding tab) {
		return isys_department_codingMapper.update(tab);
	}

	@Override
	public Tab_sys_department_coding getWhereCodingName(String coding_name) {
		return isys_department_codingMapper.getWhereCodingName(coding_name);
	}

	@Override
	public List<Tab_sys_department_coding> getWhere(Map<String, Object> map) {
		return isys_department_codingMapper.getWhere(map);
	}

}
