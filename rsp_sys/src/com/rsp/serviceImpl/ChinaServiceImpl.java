package com.rsp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsp.mapper.IchinaMapper;
import com.rsp.model.Tab_china;
import com.rsp.service.IchinaService;

/**
 * 
  * 文件名：ChinaServiceImpl.java
  * 描述： 区域信息表数据访问业务逻辑层
  * 修改人： lingfe
  * 修改时间：2019年3月22日 上午9:34:26
  * 修改内容：
 */
@Service
public class ChinaServiceImpl implements IchinaService {

	//自动装配
	@Autowired
	private IchinaMapper ichinaMapper;

	@Override
	public List<Tab_china> selectWhwerePid(String pid) {
		return ichinaMapper.selectWhwerePid(pid);
	}

	@Override
	public int deleteWhereId(String id) {
		return ichinaMapper.deleteWhereId(id);
	}

	@Override
	public int deleteWherePid(String pid) {
		return ichinaMapper.deleteWherePid(pid);
	}

	@Override
	public int save(Tab_china china) {
		return ichinaMapper.save(china);
	}

	@Override
	public List<Tab_china> selectLike(String name) {
		return ichinaMapper.selectLike(name);
	}
	
	
}
