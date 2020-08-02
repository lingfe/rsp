package com.rsp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsp.mapper.Isys_chinaMapper;
import com.rsp.model.Tab_sys_china;
import com.rsp.service.Isys_chinaService;

/**
 * 
  * 文件名：ChinaServiceImpl.java
  * 描述： 区域信息表数据访问业务逻辑层
  * 修改人： lingfe
  * 修改时间：2019年3月22日 上午9:34:26
  * 修改内容：
 */
@Service
public class Sys_chinaServiceImpl implements Isys_chinaService {

	//自动装配
	@Autowired
	private Isys_chinaMapper isys_chinaMapper;

	@Override
	public List<Tab_sys_china> selectWhwerePid(String pid) {
		return isys_chinaMapper.selectWhwerePid(pid);
	}

	@Override
	public int deleteWhereId(String id) {
		return isys_chinaMapper.deleteWhereId(id);
	}

	@Override
	public int deleteWherePid(String pid) {
		return isys_chinaMapper.deleteWherePid(pid);
	}

	@Override
	public int save(Tab_sys_china china) {
		return isys_chinaMapper.save(china);
	}

	@Override
	public List<Tab_sys_china> selectLike(String name) {
		return isys_chinaMapper.selectLike(name);
	}
	
	
}
