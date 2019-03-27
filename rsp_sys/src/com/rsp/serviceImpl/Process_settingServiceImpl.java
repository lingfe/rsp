package com.rsp.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsp.mapper.Iprocess_settingMapper;
import com.rsp.model.Tab_process_setting;
import com.rsp.service.Iprocess_settingService;

/**
 * 
  * 文件名：Process_settingServiceImpl.java
  * 描述： 流程设置数据访问业务逻辑层
  * 修改人： lingfe
  * 修改时间：2019年3月25日 上午10:29:09
  * 修改内容：
 */
@Service
public class Process_settingServiceImpl implements Iprocess_settingService {
	
	//自动装配
	@Autowired
	private Iprocess_settingMapper iprocess_settingMapper;

	@Override
	public int deleteWhereId(String id) {
		return iprocess_settingMapper.deleteWhereId(id);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return iprocess_settingMapper.getCount(map);
	}

	@Override
	public Tab_process_setting getWhereId(String id) {
		return iprocess_settingMapper.getWhereId(id);
	}

	@Override
	public List<Tab_process_setting> pageSelect(Map<String, Object> map) {
		return iprocess_settingMapper.pageSelect(map);
	}

	@Override
	public int save(Tab_process_setting pro) {
		return iprocess_settingMapper.save(pro);
	}

	@Override
	public int update(Tab_process_setting pro) {
		return iprocess_settingMapper.update(pro);
	}

}
