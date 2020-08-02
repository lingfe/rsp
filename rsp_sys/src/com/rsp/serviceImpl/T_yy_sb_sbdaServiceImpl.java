package com.rsp.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rsp.mapper.It_markMapper;
import com.rsp.mapper.It_mark_infoMapper;
import com.rsp.mapper.It_yy_sb_sbdaMapper;
import com.rsp.model.Tab_t_mark;
import com.rsp.model.Tab_t_mark_info;
import com.rsp.model.Tab_t_yy_sb_sbda;
import com.rsp.service.It_yy_sb_sbdaService;

/**
 * 
  * 文件名：T_yy_sb_sbdaServiceImpl.java
  * 描述： 医院设备信息表访问业务逻辑层
  * 修改人： lingfe
  * 修改时间：2019年4月19日 下午3:36:58
  * 修改内容：
 */
@Service
public class T_yy_sb_sbdaServiceImpl implements It_yy_sb_sbdaService {

	@Autowired
	private It_yy_sb_sbdaMapper it_yy_sb_sbdaMapper;
	
	//痕迹
	@Autowired
	private It_markMapper it_markMapper;
	
	//痕迹信息
	@Autowired
	private It_mark_infoMapper it_mark_infoMapper;
	
	@Override
	public int deleteWhereId(String id) {
		return it_yy_sb_sbdaMapper.deleteWhereId(id);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return it_yy_sb_sbdaMapper.getCount(map);
	}

	@Override
	public Tab_t_yy_sb_sbda getWhereId(String id) {
		return it_yy_sb_sbdaMapper.getWhereId(id);
	}

	@Override
	public List<Tab_t_yy_sb_sbda> pageSelect(Map<String, Object> map) {
		return it_yy_sb_sbdaMapper.pageSelect(map);
	}

	@Override
	@Transactional
	public int save(Tab_t_yy_sb_sbda tab) {
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
		return it_yy_sb_sbdaMapper.save(tab);
	}

	@Override
	@Transactional
	public int update(Tab_t_yy_sb_sbda tab) {
		//新增痕迹
		Tab_t_mark mark=new Tab_t_mark();
		mark.setId(UUID.randomUUID().toString().replace("-", ""));
		mark.setYw_id(tab.getId());
		mark.setCrt_code(tab.getCrt_code());
		mark.setCrt_date(new Date());
		it_markMapper.save(mark);
		
		//新增痕迹信息
		Tab_t_mark_info mark_info=new Tab_t_mark_info();
		if(tab.str_list!=null&&tab.str_list.size()>=1){
			for (String str : tab.str_list) {
				mark_info.setId(UUID.randomUUID().toString().replace("-", ""));
				mark_info.setMark_id(mark.getId());
				mark_info.setYw_id(tab.getId());
				mark_info.setMark_info(str);
				mark_info.setCrt_code(tab.getCrt_code());
				mark_info.setCrt_date(new Date());
				it_mark_infoMapper.save(mark_info);
			}
		}
		
		//修改设备信息
		return it_yy_sb_sbdaMapper.update(tab);
	}

}
