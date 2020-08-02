package com.rsp.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsp.mapper.It_markMapper;
import com.rsp.model.Tab_t_mark;
import com.rsp.service.It_markService;

/**
 * 
  * 文件名：T_markServiceImpl.java
  * 描述： 痕迹表数据访问业务逻辑层
  * 修改人： lingfe
  * 修改时间：2019年4月16日 上午11:27:33
  * 修改内容：
 */
@Service
public class T_markServiceImpl implements It_markService {

	@Autowired
	private It_markMapper it_markMapper;
	
	@Override
	public int deleteWhereId(String id) {
		return it_markMapper.deleteWhereId(id);
	}

	@Override
	public List<Tab_t_mark> getWhere(Map<String, Object> map) {
		return it_markMapper.getWhere(map);
	}

	@Override
	public Tab_t_mark getWhereId(String id) {
		return it_markMapper.getWhereId(id);
	}

	@Override
	public int save(Tab_t_mark tab) {
		return it_markMapper.save(tab);
	}

}
