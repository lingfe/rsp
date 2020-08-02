package com.rsp.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsp.mapper.Isys_system_logMapper;
import com.rsp.model.Tab_sys_system_log;
import com.rsp.service.Isys_system_logService;

/**
 * 
  * 文件名：System_logServiceImpl.java
  * 描述： 系统日志表业务逻辑层
  * 修改人： lingfe
  * 修改时间：2019年3月20日 下午5:28:27
  * 修改内容：
 */
@Service
public class Sys_system_logServiceImpl implements Isys_system_logService {

	//自动装配
	@Autowired
	private Isys_system_logMapper isys_system_logMapper;
	
	@Override
	public int getCount() {
		return isys_system_logMapper.getCount();
	}

	@Override
	public List<Tab_sys_system_log> pageSelect(Map<String, Object> map) {
		return isys_system_logMapper.pageSelect(map);
	}

	@Override
	public int add(Tab_sys_system_log syslog) {
		return isys_system_logMapper.add(syslog);
	}
	
}
