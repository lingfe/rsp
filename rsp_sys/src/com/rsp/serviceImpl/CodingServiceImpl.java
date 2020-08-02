package com.rsp.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsp.mapper.IcodingMapper;
import com.rsp.model.Tab_coding;
import com.rsp.service.Isys_codingService;

/**
 * 
  * 文件名：CodingServiceImpl.java
  * 描述： 编码表数据访问业务逻辑类
  * 修改人： lingfe
  * 修改时间：2019年3月19日 下午4:21:57
  * 修改内容：
 */
@Service
public class CodingServiceImpl implements Isys_codingService {

	//自动装配
	@Autowired
	private IcodingMapper icodingMapper;
	
	@Override
	public Tab_coding getWhereId(String id) {
		return icodingMapper.getWhereId(id);
	}
	
	@Override
	public int getCount(Map<String, Object> map) {
		return icodingMapper.getCount(map);
	}
	
	@Override
	public int deleteWhereId(String id) {
		return icodingMapper.deleteWhereId(id);
	}	
	
	@Override
	public List<Tab_coding> pageSelect(Map<String, Object> map) {
		return icodingMapper.pageSelect(map);
	}
	
	@Override
	public int save(Tab_coding coding) {
		return icodingMapper.save(coding);
	}
	
	@Override
	public int update(Tab_coding coding) {
		return icodingMapper.update(coding);
	}

	@Override
	public Tab_coding getWhereCodingName(String coding_name) {
		return icodingMapper.getWhereCodingName(coding_name);
	}
	
	@Override
	public Tab_coding getOne_asc() {
		return icodingMapper.getOne_asc();
	}
	
	@Override
	public Tab_coding getOne_desc() {
		return icodingMapper.getOne_desc();
	}

	@Override
	public List<Tab_coding> getWhere(Map<String, Object> map) {
		return icodingMapper.getWhere(map);
	}
}
