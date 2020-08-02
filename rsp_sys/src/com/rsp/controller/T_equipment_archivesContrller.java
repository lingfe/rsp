package com.rsp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rsp.controller.util.GetIpUtil;
import com.rsp.controller.util.SYS_GET;
import com.rsp.model.JosnModel;
import com.rsp.model.PageModel;
import com.rsp.model.Tab_coding;
import com.rsp.model.Tab_equipment_walkthrough;
import com.rsp.model.Tab_sys_manufacturer;
import com.rsp.model.Tab_system_log;
import com.rsp.model.Tab_t_yy_ksxx;
import com.rsp.model.Tab_t_equipment_archives;
import com.rsp.model.Tab_t_mark;
import com.rsp.model.Tab_user_info;
import com.rsp.service.Iequipment_walkthroughService;
import com.rsp.service.Isys_manufacturerService;
import com.rsp.service.Isys_codingService;
import com.rsp.service.It_yy_ksxxService;
import com.rsp.service.It_equipment_archivesService;
import com.rsp.service.It_markService;
import com.rsp.service.It_mark_infoService;
import com.rsp.service.IuserinfoService;

/**
 * 
 * 文件名：T_equipment_archivesContrller.java 
 * 描述： 设备档案请求访问表示层 
 * 修改人： lingfe
 * 修改时间：2019年4月11日 上午9:57:05 
 * 修改内容：
 */
@Controller
@RequestMapping("/t_equipment_archives")
public class T_equipment_archivesContrller {

	// 自动装配
	@Autowired
	private It_equipment_archivesService it_equipment_archivesService;

	// 用户信息
	@Autowired
	private IuserinfoService iuserinfoService;
	
	//设备预排
	@Autowired
	private Iequipment_walkthroughService iequipment_walkthroughService;
	
	//痕迹
	@Autowired
	private It_markService it_markService;
	
	//痕迹信息
	@Autowired
	private It_mark_infoService it_mark_infoService;
	
	//编码
	@Autowired
	private Isys_codingService isys_codingService;
	
	//生产厂家
	@Autowired
	private Isys_manufacturerService isys_manufacturerService;
	
	//科室管理
	@Autowired
	private It_yy_ksxxService it_yy_ksxxService;
		
	
	/**
	 * 
	 * 根据id标识获取设备档案信息
	 * @author lingfe     
	 * @created 2019年3月28日 上午10:32:53  
	 * @param id
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getWhereId", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public JosnModel<Object> getWhereId(
	    		@RequestParam(value="id",required=false)String id,
	    		HttpServletRequest request,
	    		HttpSession session){
			//实例化对象
			JosnModel<Object> josn=new JosnModel<Object>();
			Tab_system_log sysLog=new Tab_system_log();
			Map<String, Object> map=new HashMap<String, Object>();
			
			//系统日志
			sysLog.setIp(GetIpUtil.getIpAddr(request));
			sysLog.setModel_name("根据id标识获取设备档案信息,"+request.getRequestURI());
			Object creator=session.getAttribute("userid");
			if(!StringUtils.isEmpty(creator)){
				sysLog.setCreator(creator.toString());
			}
			sysLog.setModify(sysLog.getCreator());
			sysLog.setOperation_type(1);
			
			try {
				//验证非空
				if(id!=null&&!"".equals(id)){
					//执行查询,得到设备档案信息
					Tab_t_equipment_archives tab=it_equipment_archivesService.getWhereId(id);
					if(tab!=null){
						//根据设备id得到设备预排信息
						map.put("tab", "equipment_walkthrough");
						map.put("equipment_id", id);
						List<Tab_equipment_walkthrough> ew_list=iequipment_walkthroughService.getWhere(map);
						if(ew_list!=null&&ew_list.size()!=0){
							map.put("ew_list", ew_list);
						}
						
						//根据设备id得到痕迹信息
						map.put("tab", "t_mark");
						map.put("equipment_id", id);
						List<Tab_t_mark> mark_list=it_markService.getWhere(map);
						for (Tab_t_mark mark : mark_list) {
							map.put("tab", "t_mark_info");
							map.put("equipment_id", null);
							map.put("mark_id", mark.getId());
							mark.mark_info=it_mark_infoService.getWhere(map);
						}
						
						//返回参数赋值
						map.put("tab", tab);
						map.put("ew_list", ew_list);
						map.put("mark_list", mark_list);
						
						josn.data=map;
						josn.msg="获取成功!";
						josn.state=200;
					}else{
						josn.msg="id无效!";
					}
				}else{
					josn.msg="id不能为空!";
				}
			} catch (Exception e) {
				sysLog.setIs_bug(1);
				josn.msg=e.getMessage();
				josn.state=500;
			}
			//操作说明
			sysLog.setExceptionally_detailed(josn.msg);
			//添加系统日志
			//isystem_logService.add(sysLog);
			
			return josn;
	}


	/**
	 * 
	 * 分页查询设备档案信息
	 * @author lingfe
	 * @created 2019年3月22日 下午3:29:53
	 * @param pageIndex	当前页
	 * @param pageNum	页容量
	 * @param hospital_name	医院名称
	 * @param equipment_name	设备名称
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/pageSelect", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public JosnModel<Object> pageSelect(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(value = "pageNum", required = false, defaultValue = "10") Integer pageNum,
			@RequestParam(value = "hospital_name", required = false) String hospital_name,
			@RequestParam(value = "equipment_name", required = false) String equipment_name,
			@RequestParam(value="department",required=false)String department,
			@RequestParam(value="is_obtain_rdtl",required=false)Integer is_obtain_rdtl,
			@RequestParam(value="is_obtain_rsl",required=false)String is_obtain_rsl,
			@RequestParam(value="is_obtain_lcl",required=false)Integer is_obtain_lcl,
			@RequestParam(value="is_scrap",required=false)Integer is_scrap,
			@RequestParam(value="state",required=false)Integer state,
			HttpServletRequest request,
			HttpSession session) {
		// 实例化对象
		JosnModel<Object> josn = new JosnModel<Object>();
		Map<String, Object> map = new HashMap<>();
		PageModel<Tab_t_equipment_archives> page = new PageModel<Tab_t_equipment_archives>();
		Tab_system_log sysLog = new Tab_system_log();

		// 系统日志
		sysLog.setIp(GetIpUtil.getIpAddr(request));
		sysLog.setModel_name("分页查询设备档案信息," + request.getRequestURI());
		Object creator = session.getAttribute("userid");
		if (!StringUtils.isEmpty(creator)) {
			sysLog.setCreator(creator.toString());
		}
		sysLog.setModify(sysLog.getCreator());
		sysLog.setOperation_type(1);

		try {
			// 验证非空
			// 赋值参数
			if (!StringUtils.isEmpty(hospital_name)) {
				map.put("hospital_name", hospital_name);
			}
			if (!StringUtils.isEmpty(equipment_name)) {
				map.put("equipment_name", equipment_name);
			}
			if(!StringUtils.isEmpty(department)){
				map.put("department", department);
			}
			if(!StringUtils.isEmpty(is_obtain_rdtl)){
				map.put("is_obtain_rdtl", is_obtain_rdtl);
			}
			if(!StringUtils.isEmpty(is_obtain_rsl)){
				map.put("is_obtain_rsl", is_obtain_rsl);
			}
			if(!StringUtils.isEmpty(is_obtain_lcl)){
				map.put("is_obtain_lcl", is_obtain_lcl);
			}
			if(!StringUtils.isEmpty(is_scrap));{
				map.put("is_scrap", is_scrap);
			}
			if(!StringUtils.isEmpty(state)){
				map.put("state", state);
			}
			map.put("pageIndex", pageIndex);
			map.put("pageNum", pageNum);
			page.setPageIndex(pageIndex);
			page.setPageNum(pageNum);

			// 设置表
			map.put("table", "t_equipment_archives");

			// 得到总数据量
			int numCount = it_equipment_archivesService.getCount(map);
			page.setNumCount(numCount);

			// 得到数据
			List<Tab_t_equipment_archives> list = it_equipment_archivesService.pageSelect(map);
			// 验证非空
			if (list != null) {
				for (Tab_t_equipment_archives tab : list) {
					//得到医院名称
					
					//得到编码名称
					Tab_coding coding=isys_codingService.getWhereId(tab.getCoding_id());
					if(coding!=null){
						tab.coding_name=coding.getCoding_name();
					}
					
					//得到生产厂家名称
					Tab_sys_manufacturer manufacturer=isys_manufacturerService.getWhereId(tab.getManfacturer_id());
					if(manufacturer!=null){
						tab.manfacturer_name=manufacturer.getManufacturer_name();
					}
					
					//得到科室名称
					Tab_t_yy_ksxx dm=it_yy_ksxxService.getWhereId(tab.getDepartment_id());
					if(dm!=null){
						tab.setDepartment_name(dm.getKs_name());
					}
					
					
					// 得到创建人名称
					if (!StringUtils.isEmpty(tab.getCrt_code())) {
						if (!"游客".equals(tab.getCrt_code())) {
							Tab_user_info info = iuserinfoService.getWhereId(tab.getCrt_code());
							if (info != null) {
								tab.setCrt_name(info.getUsername());
							}
						}
					}else{
						tab.setCrt_name("游客");
					}
					// 得到修改人名称
					if (!StringUtils.isEmpty(tab.getModify_code())) {
						if (!"游客".equals(tab.getModify_code())) {
							Tab_user_info info = iuserinfoService.getWhereId(tab.getModify_code());
							if (info != null) {
								tab.setModify_name(info.getUsername());
							}
						}
					}else{
						tab.setModify_name("游客");
					}
				}

				josn.state = 200;
				josn.msg = "请求成功!";
				page.setList(list);
				josn.data = page;
			} else {
				josn.msg = "没有数据,添加一条吧!";
			}

		} catch (Exception e) {
			sysLog.setIs_bug(1);
			josn.msg = e.getMessage();
			josn.state = 500;
		}
		// 操作说明
		sysLog.setExceptionally_detailed(josn.msg);
		// 添加系统日志
		//isystem_logService.add(sysLog);

		return josn;
	}

	/**
	 * 
	 * 修改设备档案信息
	 * @author lingfe
	 * @created 2019年3月22日 下午3:22:14
	 * @param man
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public JosnModel<Object> update(Tab_t_equipment_archives tab, 
			HttpServletRequest request, HttpSession session) {
		// 实例化对象
		JosnModel<Object> josn = new JosnModel<Object>();
		Tab_system_log sysLog = new Tab_system_log();
		// 系统日志
		sysLog.setIp(GetIpUtil.getIpAddr(request));
		sysLog.setModel_name("修改设备档案信息," + request.getRequestURI());
		Object creator = session.getAttribute("userid");
		if (SYS_GET.IS_SESSION_VALIDATE) {
			if (!StringUtils.isEmpty(creator)) {
				sysLog.setCreator(creator.toString());
			} else {
				josn.msg = "会话过期!请重新登录";
				return josn;
			}
		}
		sysLog.setModify(sysLog.getCreator());
		sysLog.setOperation_type(2);

		try {
			// 验证非空
			if (tab != null) {
				// 执行查询
				Tab_t_equipment_archives tea  = it_equipment_archivesService.getWhereId(tab.getId());
				if (tea != null) {
					sysLog.setTarget_id(tab.getId());
					// 赋值
					tab.setModify_code(sysLog.getCreator());
					tab.setModify_date(new Date());
					tab.setCrt_code(tea.getCrt_code());
					tab.setCrt_date(tea.getCrt_date());
					tab.setVersion(String.valueOf(Integer.parseInt(tea.getVersion())+1));
					tab.setState(tea.getState());

					// 执行更新
					int tt = it_equipment_archivesService.update(tab);
					if (tt >= 1) {
						josn.msg = "修改成功!";
						josn.state = 200;
						josn.data = tab;
					} else {
						josn.msg = "修改失败";
					}
				} else {
					josn.msg = "id无效或者已经不存在了!";
				}
			} else {
				josn.msg = "请填写信息修改!";
			}

		} catch (Exception e) {
			sysLog.setIs_bug(1);
			josn.msg = e.getMessage();
			josn.state = 500;
		}
		// 操作说明
		sysLog.setExceptionally_detailed(josn.msg);
		// 添加系统日志
		//isystem_logService.add(sysLog);

		return josn;
	}

	/**
	 * 
	 * 根据设备档案id标识删除信息
	 * @author lingfe
	 * @created 2019年3月22日 下午3:10:18
	 * @param id
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteWhereId", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public JosnModel<Object> deleteWhereId(
			@RequestParam(value = "id", required = false) String id,
			HttpServletRequest request, HttpSession session) {
		// 实例化对象
		JosnModel<Object> josn = new JosnModel<Object>();
		Tab_system_log sysLog = new Tab_system_log();
		// 系统日志
		sysLog.setIp(GetIpUtil.getIpAddr(request));
		sysLog.setModel_name("根据设备档案id标识删除信息," + request.getRequestURI());
		Object creator = session.getAttribute("userid");
		if (SYS_GET.IS_SESSION_VALIDATE) {
			if (!StringUtils.isEmpty(creator)) {
				sysLog.setCreator(creator.toString());
			} else {
				josn.msg = "会话过期!请重新登录";
				return josn;
			}
		}
		sysLog.setModify(sysLog.getCreator());
		sysLog.setOperation_type(3);

		try {
			// 验证非空
			if (!StringUtils.isEmpty(id)) {
				// 执行删除
				int tt =it_equipment_archivesService.deleteWhereId(id);
				if (tt >= 1) {
					josn.msg = "删除成功!";
					josn.state = 200;
					sysLog.setTarget_id(id);
				} else {
					josn.msg = "删除失败!";
				}
			} else {
				josn.msg = "id不能为空!";
			}

		} catch (Exception e) {
			sysLog.setIs_bug(1);
			josn.msg = e.getMessage();
			josn.state = 500;
		}
		// 操作说明
		sysLog.setExceptionally_detailed(josn.msg);
		// 添加系统日志
		//isystem_logService.add(sysLog);

		return josn;
	}

	/**
	 * 
	 * 保存设备档案信息
	 * @author lingfe
	 * @created 2019年3月22日 下午3:06:08
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/save", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public JosnModel<Object> save(
			Tab_t_equipment_archives tab,
			@RequestParam(value="eq_list",required=false) String  eq_list,
			HttpServletRequest request,
			HttpSession session) {
		// 实例化对象
		JosnModel<Object> josn = new JosnModel<Object>();
		Tab_system_log sysLog = new Tab_system_log();
		
		// 系统日志
		sysLog.setIp(GetIpUtil.getIpAddr(request));
		sysLog.setModel_name("保存设备档案信息," + request.getRequestURI());
		Object creator = session.getAttribute("userid");
		if (SYS_GET.IS_SESSION_VALIDATE) {
			if (!StringUtils.isEmpty(creator)) {
				sysLog.setCreator(creator.toString());
			} else {
				josn.msg = "会话过期!请重新登录";
				return josn;
			}
		}
		sysLog.setModify(sysLog.getCreator());
		sysLog.setOperation_type(4);

		try {
			
			// 验证非空
			if(eq_list!=null&&!"".equals(eq_list)){
				//得到预排json数据
				JSONObject jsonObj=JSONObject.parseObject(eq_list);
				//取出eq_list对应的值,值为字符串
				String str = jsonObj.getString("eq_list"); 
		        //使用JSONArray.parseArray(String, Class<T>)将字符串转为指定对象集合
		        List<Tab_equipment_walkthrough> listPram = (List<Tab_equipment_walkthrough>) JSONArray.parseArray(str, Tab_equipment_walkthrough.class);
		        for (Tab_equipment_walkthrough requestPram : listPram) {
		            System.out.println(requestPram.toString());
		        }
		        //赋值
		        tab.eq_list=listPram;
			}
	        
			if (!StringUtils.isEmpty(tab)) {
				//赋值
				tab.setId(UUID.randomUUID().toString().replace("-", ""));
				tab.setCrt_code(sysLog.getCreator());
				tab.setCrt_date(new Date());
				// 执行保存
				int tt = it_equipment_archivesService.save(tab);
				if (tt >= 1) {
					josn.msg = "保存成功!";
					josn.state = 200;
				} else {
					josn.msg = "保存失败!";
				}
			} else {
				josn.msg = "请填写设备档案信息!";
			}

		} catch (Exception e) {
			sysLog.setIs_bug(1);
			josn.msg = e.getMessage();
			josn.state = 500;
		}
		// 操作说明
		sysLog.setExceptionally_detailed(josn.msg);
		// 添加系统日志
		//isystem_logService.add(sysLog);

		return josn;
	}
}
