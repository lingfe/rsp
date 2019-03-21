package com.rsp.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsp.inter.Isystem_log;
import com.rsp.mapper.Isystem_logMapper;
import com.rsp.model.Tab_system_log;
import com.rsp.service.Isystem_logService;

/**
 * 
  * 文件名：System_logServiceImpl.java
  * 描述： 系统日志表业务逻辑层
  * 修改人： lingfe
  * 修改时间：2019年3月20日 下午5:28:27
  * 修改内容：
 */
@Service
public class System_logServiceImpl implements Isystem_logService {

	//自动装配
	@Autowired
	private Isystem_logMapper isystem_logMapper;
	
	@Override
	public int getCount() {
		return isystem_logMapper.getCount();
	}

	@Override
	public List<Tab_system_log> pageSelect(Map<String, Object> map) {
		return isystem_logMapper.pageSelect(map);
	}

	@Override
	public int add(Tab_system_log syslog) {
		return isystem_logMapper.add(syslog);
	}
	
}
