package com.rsp.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsp.mapper.Ilicence_walkthroughMapper;
import com.rsp.model.Tab_licence_walkthrough;
import com.rsp.service.Ilicence_walkthroughService;

/**
 * 
  * 文件名：Licence_walkthroughServiceImpl.java
  * 描述： 证照预排数据访问业务逻辑层
  * 修改人： lingfe
  * 修改时间：2019年3月28日 上午9:59:40
  * 修改内容：
 */
@Service
public class Licence_walkthroughServiceImpl implements Ilicence_walkthroughService {

	//自动装配
	@Autowired
	private Ilicence_walkthroughMapper ilicence_walkthroughMapper;

	@Override
	public int deleteWhereId(String id) {
		return ilicence_walkthroughMapper.deleteWhereId(id);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return ilicence_walkthroughMapper.getCount(map);
	}

	@Override
	public Tab_licence_walkthrough getWhereId(String id) {
		return ilicence_walkthroughMapper.getWhereId(id);
	}

	@Override
	public List<Tab_licence_walkthrough> pageSelect(Map<String, Object> map) {
		return ilicence_walkthroughMapper.pageSelect(map);
	}

	@Override
	public int save(Tab_licence_walkthrough tab) {
		return ilicence_walkthroughMapper.save(tab);
	}

	@Override
	public int update(Tab_licence_walkthrough tab) {
		return ilicence_walkthroughMapper.update(tab);
	}
	
}
