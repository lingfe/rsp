package com.rsp.controller;

import java.util.ArrayList;
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

import com.rsp.controller.util.GetIpUtil;
import com.rsp.controller.util.LogUtils;
import com.rsp.controller.util.SYS_GET;
import com.rsp.model.JosnModel;
import com.rsp.model.PageModel;
import com.rsp.model.Tab_coding;
import com.rsp.model.Tab_equipment_walkthrough;
import com.rsp.model.Tab_sys_manufacturer;
import com.rsp.model.Tab_t_yy_hospital;
import com.rsp.model.Tab_t_yy_ksxx;
import com.rsp.model.Tab_t_yy_sb_sbda;
import com.rsp.model.Tab_user_info;
import com.rsp.service.Isys_manufacturerService;
import com.rsp.service.Iequipment_walkthroughService;
import com.rsp.service.Isys_codingService;
import com.rsp.service.Isys_system_logService;
import com.rsp.service.It_yy_hospitalService;
import com.rsp.service.It_yy_ksxxService;
import com.rsp.service.It_yy_sb_sbdaService;
import com.rsp.service.IuserinfoService;

/**
 * 
  * 文件名：T_yy_sb_sbdaController.java
  * 描述： 医院设备档案信息表表示层
  * 修改人： lingfe
  * 修改时间：2019年4月19日 下午3:39:52
  * 修改内容：
 */
@Controller
@RequestMapping("/t_yy_sb_sbda")
public class T_yy_sb_sbdaController {

	//医院设备档案信息
	@Autowired
	private It_yy_sb_sbdaService it_yy_sb_sbdaService;

	// 系统日志
	@Autowired
	private Isys_system_logService isys_system_logService;

	// 用户信息
	@Autowired
	private IuserinfoService iuserinfoService;
	
	//编码
	@Autowired
	private Isys_codingService isys_codingService;
	
	//生产厂家
	@Autowired
	private Isys_manufacturerService isys_manufacturerService;
	
	//科室管理
	@Autowired
	private It_yy_ksxxService it_yy_ksxxService;
	
	//医院信息
	@Autowired
	private It_yy_hospitalService it_yy_hospitalService;
	
	//设备预排
	@Autowired
	private Iequipment_walkthroughService iequipment_walkthroughService;

	/**
	 * 是否有bug
	 */
	private int flag_bug=0;
		
	
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
			
			try {
				//验证非空
				if(id!=null&&!"".equals(id)){
					//执行查询,得到设备档案信息
					Tab_t_yy_sb_sbda tab=it_yy_sb_sbdaService.getWhereId(id);
					if(tab!=null){
						//设定参数条件
						Map<String, Object> map=new HashMap<String, Object>();
						map.put("table", "equipment_walkthrough");
						map.put("equipment_id", id);
						//得到设备预排信息
						List<Tab_equipment_walkthrough> yp_list= iequipment_walkthroughService.getWhere(map);
						tab.yp_list=yp_list;
						josn.data=tab;
						josn.msg="获取成功!";
						josn.state=200;
					}else{
						josn.msg="id无效!";
					}
				}else{
					josn.msg="id不能为空!";
				}
			} catch (Exception e) {
				josn.msg=e.getMessage();
				josn.state=500;
			}
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
			@RequestParam(value = "sb_name", required = false) String sb_name,
			@RequestParam(value = "sb_syks_id", required = false) String sb_syks_id,
			@RequestParam(value="flag_fszlxkz",required=false)String flag_fszlxkz,
			@RequestParam(value="flag_fsaqxkz",required=false)Integer flag_fsaqxkz,
			@RequestParam(value="flag_dxpzxkz",required=false)String flag_dxpzxkz,
			@RequestParam(value="flag_sb_state",required=false)Integer flag_sb_state,
			HttpServletRequest request,
			HttpSession session) {
		// 实例化对象
		JosnModel<Object> josn = new JosnModel<Object>();
		Map<String, Object> map = new HashMap<>();
		PageModel<Tab_t_yy_sb_sbda> page = new PageModel<Tab_t_yy_sb_sbda>();

		try {
			// 验证非空
			// 赋值参数
			if (!StringUtils.isEmpty(sb_name)) {
				map.put("sb_name", sb_name);
			}
			if (!StringUtils.isEmpty(sb_syks_id)) {
				map.put("sb_syks_id", sb_syks_id);
			}
			if(!StringUtils.isEmpty(flag_fszlxkz)){
				map.put("flag_fszlxkz", flag_fszlxkz);
			}
			if(!StringUtils.isEmpty(flag_fsaqxkz)){
				map.put("flag_fsaqxkz", flag_fsaqxkz);
			}
			if(!StringUtils.isEmpty(flag_dxpzxkz)){
				map.put("flag_dxpzxkz", flag_dxpzxkz);
			}
			if(!StringUtils.isEmpty(flag_sb_state)){
				map.put("flag_sb_state", flag_sb_state);
			}

			map.put("pageIndex", pageIndex);
			map.put("pageNum", pageNum);
			page.setPageIndex(pageIndex);
			page.setPageNum(pageNum);

			// 设置表
			map.put("table", "t_yy_sb_sbda");

			// 得到总数据量
			int numCount = it_yy_sb_sbdaService.getCount(map);
			page.setNumCount(numCount);

			// 得到数据
			List<Tab_t_yy_sb_sbda> list = it_yy_sb_sbdaService.pageSelect(map);
			// 验证非空
			if (list != null) {
				for (Tab_t_yy_sb_sbda tab : list) {
					//得到医院名称
					Tab_t_yy_hospital yy=it_yy_hospitalService.getWhereId(tab.getHospital_id());
					if(yy!=null){
						yy.setHospital_name(yy.getHospital_name());
					}
					
					//得到设备装置类别名称
					Tab_coding coding=isys_codingService.getWhereId(tab.getSb_zzlb_id());
					if(coding!=null){
						tab.sb_zzlb_name=coding.getCoding_name();
					}
					
					//得到设备生产厂家名称
					Tab_sys_manufacturer manufacturer=isys_manufacturerService.getWhereId(tab.getSb_manufacturer_id());
					if(manufacturer!=null){
						tab.sb_manfacturer_name=manufacturer.getManufacturer_name();
					}
					
					//得到设备使用科室名称
					Tab_t_yy_ksxx dm=it_yy_ksxxService.getWhereId(tab.getSb_syks_id());
					if(dm!=null){
						tab.sb_syks_name=dm.getKs_name();
					}
					
					
					// 得到创建人名称
					if (!StringUtils.isEmpty(tab.getCrt_code())) {
						Tab_user_info info = iuserinfoService.getWhereId(tab.getCrt_code());
						if (info != null) {
							tab.setCrt_name(info.getUsername());
						}
					}else{
						tab.setCrt_name("游客");
					}
					
					// 得到修改人名称
					if (!StringUtils.isEmpty(tab.getModify_code())) {
						Tab_user_info info = iuserinfoService.getWhereId(tab.getModify_code());
						if (info != null) {
							tab.setModify_name(info.getUsername());
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
			josn.msg = e.getMessage();
			josn.state = 500;
		}
		return josn;
	}

	/**
	 * 
	 * 修改医院设备档案信息
	 * @author lingfe
	 * @created 2019年3月22日 下午3:22:14
	 * @param man
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public JosnModel<Object> update(
			Tab_t_yy_sb_sbda tab, 
			HttpServletRequest request, HttpSession session) {
		// 实例化对象
		JosnModel<Object> josn = new JosnModel<Object>();
		//得到userId
		Object creator=session.getAttribute("userid");
		if(SYS_GET.IS_SESSION_VALIDATE){
			if(StringUtils.isEmpty(creator)){
				josn.msg="会话过期!请重新登录";
				return josn;
			}
		}else{
			creator=GetIpUtil.getIpAddr(request);
		}
		
		//异常处理		
		try {
			// 验证非空
			if (tab != null) {
				// 执行查询
				Tab_t_yy_sb_sbda sb  = it_yy_sb_sbdaService.getWhereId(tab.getId());
				if (sb != null) {
					//修改内容
					List<String> str=new ArrayList<String>();
					if(sb.getSb_name() != tab.getSb_name()){
						str.add("修改了设备名称");
					}
					if(sb.getSb_manufacturer_id() != tab.getSb_manufacturer_id()){
						str.add("修改了设备生产厂家");
					}
					if(sb.getSb_syks_id() != tab.getSb_syks_id()){
						str.add("修改了设备使用科室");
					}
					tab.str_list=str;
					
					session.setAttribute("target_id", sb.getId());
					// 赋值
					tab.setCrt_code(tab.getCrt_code());
					tab.setCrt_date(tab.getCrt_date());
					tab.setVersion(sb.getVersion()+1);
					tab.setState(tab.getState());
					
					//修改人信息
					tab.setModify_code(creator.toString());
					tab.setModify_date(new Date());
					
					// 执行更新
					int tt = it_yy_sb_sbdaService.update(tab);
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
			flag_bug=1;
			josn.msg = e.getMessage();
			josn.state = 500;
		}
		//系统日志
		LogUtils.jieLuSystemLog(request,LogUtils.UPDATE,flag_bug,
						"修改医院设备档案信息",
						josn.msg,session,isys_system_logService);
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
		//得到userId
		Object creator=session.getAttribute("userid");
		if(SYS_GET.IS_SESSION_VALIDATE){
			if(StringUtils.isEmpty(creator)){
				josn.msg="会话过期!请重新登录";
				return josn;
			}
		}else{
			creator=GetIpUtil.getIpAddr(request);
		}		

		try {
			// 验证非空
			if (!StringUtils.isEmpty(id)) {
				// 执行删除
				int tt =it_yy_sb_sbdaService.deleteWhereId(id);
				if (tt >= 1) {
					josn.msg = "删除成功!";
					josn.state = 200;
					session.setAttribute("target_id", id);
				} else {
					josn.msg = "删除失败!";
				}
			} else {
				josn.msg = "id不能为空!";
			}

		} catch (Exception e) {
			flag_bug=1;
			josn.msg = e.getMessage();
			josn.state = 500;
		}
		//系统日志
		LogUtils.jieLuSystemLog(request,LogUtils.UPDATE,flag_bug,
								"修改医院设备档案信息",
								josn.msg,session,isys_system_logService);

		return josn;
	}

	/**
	 * 
	 * 保存医院设备档案信息
	 * @author lingfe
	 * @created 2019年3月22日 下午3:06:08
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/save", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public JosnModel<Object> save(
			Tab_t_yy_sb_sbda tab,
			HttpServletRequest request,
			HttpSession session) {
		// 实例化对象
		JosnModel<Object> josn = new JosnModel<Object>();
		//得到userId
		Object creator=session.getAttribute("userid");
		if(SYS_GET.IS_SESSION_VALIDATE){
			if(StringUtils.isEmpty(creator)){
				josn.msg="会话过期!请重新登录";
				return josn;
			}
		}else{
			creator=GetIpUtil.getIpAddr(request);
		}
		
		//异常处理
		try {
	        //验证非空
			if (!StringUtils.isEmpty(tab)) {
				//赋值
				tab.setId(UUID.randomUUID().toString().replace("-", ""));
				
				//创建人信息
				tab.setCrt_code(null);
				tab.setCrt_date(new Date());
				// 执行保存
				int tt = it_yy_sb_sbdaService.save(tab);
				if (tt >= 1) {
					josn.msg = "保存成功!";
					josn.data=tab;
					josn.state = 200;
				} else {
					josn.msg = "保存失败!";
				}
			} else {
				josn.msg = "请填写设备档案信息!";
			}

		} catch (Exception e) {
			flag_bug=1;
			josn.msg = e.getMessage();
			josn.state = 500;
		}
		//系统日志
		LogUtils.jieLuSystemLog(request,LogUtils.ADD,flag_bug,
				"保存医院设备档案信息",
				josn.msg,session,isys_system_logService);
		return josn;
	}
}
