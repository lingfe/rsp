package com.rsp.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsp.mapper.It_yy_ksxxMapper;
import com.rsp.model.Tab_t_yy_ksxx;
import com.rsp.service.It_yy_ksxxService;

/**
 * 
  * 文件名：T_department_managementServiceImpl.java
  * 描述： 科室管理数据访问业务逻辑层
  * 修改人： lingfe
  * 修改时间：2019年4月12日 上午11:53:09
  * 修改内容：
 */
@Service
public class T_yy_ksxxServiceImpl implements It_yy_ksxxService {

	@Autowired
	private It_yy_ksxxMapper it_yy_ksxxMapper;
	
	@Override
	public int deleteWhereId(String id) {
		return it_yy_ksxxMapper.deleteWhereId(id);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return it_yy_ksxxMapper.getCount(map);
	}

	@Override
	public List<Tab_t_yy_ksxx> getWhere(Map<String, Object> map) {
		return it_yy_ksxxMapper.getWhere(map);
	}

	@Override
	public Tab_t_yy_ksxx getWhereId(String id) {
		return it_yy_ksxxMapper.getWhereId(id);
	}

	@Override
	public List<Tab_t_yy_ksxx> pageSelect(Map<String, Object> map) {
		return it_yy_ksxxMapper.pageSelect(map);
	}

	@Override
	public int save(Tab_t_yy_ksxx tab) {
		return it_yy_ksxxMapper.save(tab);
	}

	@Override
	public int update(Tab_t_yy_ksxx tab) {
		return it_yy_ksxxMapper.update(tab);
	}

}
