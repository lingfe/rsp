package com.rsp.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsp.mapper.It_mark_infoMapper;
import com.rsp.model.Tab_t_mark_info;
import com.rsp.service.It_mark_infoService;

/**
 * 
  * 文件名：T_markServiceImpl.java
  * 描述： 痕迹表数据访问业务逻辑层
  * 修改人： lingfe
  * 修改时间：2019年4月16日 上午11:27:33
  * 修改内容：
 */
@Service
public class T_mark_infoServiceImpl implements It_mark_infoService {

	@Autowired
	private It_mark_infoMapper it_mark_infoMapper;
	
	@Override
	public int deleteWhereId(String id) {
		return it_mark_infoMapper.deleteWhereId(id);
	}

	@Override
	public List<Tab_t_mark_info> getWhere(Map<String, Object> map) {
		return it_mark_infoMapper.getWhere(map);
	}

	@Override
	public Tab_t_mark_info getWhereId(String id) {
		return it_mark_infoMapper.getWhereId(id);
	}

	@Override
	public int save(Tab_t_mark_info tab) {
		return it_mark_infoMapper.save(tab);
	}

}
