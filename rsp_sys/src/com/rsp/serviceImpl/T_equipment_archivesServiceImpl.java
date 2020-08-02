package com.rsp.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rsp.mapper.It_equipment_archivesMapper;
import com.rsp.mapper.It_markMapper;
import com.rsp.mapper.It_mark_infoMapper;
import com.rsp.model.Tab_equipment_walkthrough;
import com.rsp.model.Tab_t_equipment_archives;
import com.rsp.model.Tab_t_mark;
import com.rsp.model.Tab_t_mark_info;
import com.rsp.service.It_equipment_archivesService;

/**
 * 
  * 文件名：T_equipment_archivesServiceImpl.java
  * 描述： 设备档案业务逻辑层
  * 修改人： lingfe
  * 修改时间：2019年4月11日 上午9:54:09
  * 修改内容：
 */
@Service
public class T_equipment_archivesServiceImpl implements It_equipment_archivesService {

	@Autowired
	private It_equipment_archivesMapper it_equipment_archivesMapper;
	
	//痕迹
	@Autowired
	private It_markMapper it_markMapper;
	
	//痕迹信息
	@Autowired
	private It_mark_infoMapper it_mark_infoMapper;
	

	@Override
	public int deleteWhereId(String id) {
		return it_equipment_archivesMapper.deleteWhereId(id);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return it_equipment_archivesMapper.getCount(map);
	}

	@Override
	public Tab_t_equipment_archives getWhereId(String id) {
		return it_equipment_archivesMapper.getWhereId(id);
	}

	@Override
	public List<Tab_t_equipment_archives> pageSelect(Map<String, Object> map) {
		return it_equipment_archivesMapper.pageSelect(map);
	}

	@Override
	@Transactional
	public int save(Tab_t_equipment_archives tab) {
		//新增痕迹
		Tab_t_mark mark=new Tab_t_mark();
		mark.setId(UUID.randomUUID().toString().replace("-", ""));
		mark.setYw_id(tab.getId());
		mark.setCrt_code(tab.getCrt_code());
		mark.setCrt_date(new Date());
		it_markMapper.save(mark);
		
		//新增痕迹信息
		Tab_t_mark_info mark_info=new Tab_t_mark_info();
		mark_info.setId(UUID.randomUUID().toString().replace("-", ""));
		mark_info.setMark_id(mark.getId());
		mark_info.setMark_info("新增设备");
		mark_info.setCrt_code(tab.getCrt_code());
		mark_info.setCrt_date(new Date());
		it_mark_infoMapper.save(mark_info);
		
		//保存设备信息
		return it_equipment_archivesMapper.save(tab);
	}

	@Override
	public int update(Tab_t_equipment_archives tab) {
		return it_equipment_archivesMapper.update(tab);
	}
}
