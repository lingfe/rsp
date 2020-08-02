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
import com.rsp.model.Tab_equipment_walkthrough;
import com.rsp.model.Tab_system_log;
import com.rsp.model.Tab_user_info;
import com.rsp.service.Iequipment_walkthroughService;
import com.rsp.service.Isys_system_logService;
import com.rsp.service.IuserinfoService;

/**
 * 
  * 文件名：Equipment_walkthroughController.java
  * 描述： 设备预排表请求表示层
  * 修改人： lingfe
  * 修改时间：2019年3月26日 下午3:20:58
  * 修改内容：
 */
@Controller
@RequestMapping("/equipment_walkthrough")
public class Equipment_walkthroughController {

	
	//自动装配
	@Autowired
	private Iequipment_walkthroughService iequipment_walkthroughService;
	
	//系统日志
	@Autowired
	private Isys_system_logService isys_system_logService;
	
	//用户信息
	@Autowired
	private IuserinfoService iuserinfoService;
	
	/**
	 * 
	 * 修改多条设备预排信息(list多条数据保存)
	 * @author lingfe     
	 * @created 2019年3月26日 下午3:29:01  
	 * @param tab_list
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/update_list", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public JosnModel<Object> update_list(
				@RequestParam(value="tab_list",required=false) String tab_list,
	    		HttpServletRequest request,
	    		HttpSession session){
			//实例化对象
			JosnModel<Object> josn=new JosnModel<Object>();
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
				if(tab_list!=null&&!"".equals(tab_list)){
					//得到预排json数据
					JSONObject jsonObj=JSONObject.parseObject(tab_list);
					//取出eq_list对应的值,值为字符串
					String str = jsonObj.getString("tab_list"); 
			        //使用JSONArray.parseArray(String, Class<T>)将字符串转为指定对象集合
			        List<Tab_equipment_walkthrough> listPram = (List<Tab_equipment_walkthrough>) JSONArray.parseArray(str, Tab_equipment_walkthrough.class);
			        for (Tab_equipment_walkthrough tab : listPram) {
			        	//执行查询
						Tab_equipment_walkthrough gh=iequipment_walkthroughService.getWhereId(tab.getId());
						if(gh!=null){
							//更新值
							tab.setCrt_date(gh.getCrt_date());
							tab.setCrt_code(gh.getCrt_code());
							tab.setModify_date(new Date());
							tab.setModify_code(null);
							tab.setVersion(String.valueOf(Integer.parseInt(gh.getVersion())+1));
							
							//执行修改
							int tt=iequipment_walkthroughService.update(tab);
							if(tt>=1){
								josn.msg="修改成功!";
								josn.state=200;
								josn.data=tab;
							}else{
								josn.msg="修改失败!";
							}
						}else{
							josn.msg="请输入正确的id，该id无效!";
							josn.data=tab.getId();
							return josn;
						}
			        }
				}else{
					josn.msg="请填写设备预排信息!";
				}
			} catch (Exception e) {
				josn.msg=e.getMessage();
				josn.state=500;
			}
			
			return josn;
		}
	

	/**
	 * 
	 * 修改设备预排信息
	 * @author lingfe     
	 * @created 2019年3月26日 下午4:28:17  
	 * @param tab
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public JosnModel<Object> update(
	    		Tab_equipment_walkthrough tab,
	    		HttpServletRequest request,
	    		HttpSession session){
			//实例化对象
			JosnModel<Object> josn=new JosnModel<Object>();
			Tab_system_log sysLog=new Tab_system_log();
			
			//系统日志
			sysLog.setIp(GetIpUtil.getIpAddr(request));
			sysLog.setModel_name("修改设备预排信息,"+request.getRequestURI());
			Object creator=session.getAttribute("userid");
			if(SYS_GET.IS_SESSION_VALIDATE){
				if(!StringUtils.isEmpty(creator)){
					sysLog.setCreator(creator.toString());
				}else{
					josn.msg="会话过期!请重新登录";
					return josn;
				}
			}
			sysLog.setModify(sysLog.getCreator());
			sysLog.setOperation_type(2);
			
			try {
				//验证非空
				if(tab!=null){
					//执行查询
					Tab_equipment_walkthrough gh=iequipment_walkthroughService.getWhereId(tab.getId());
					if(gh!=null){
						//更新值
						tab.setCrt_date(gh.getCrt_date());
						tab.setCrt_code(gh.getCrt_code());
						tab.setModify_date(new Date());
						tab.setModify_code(sysLog.getCreator());
						tab.setVersion(String.valueOf(Integer.parseInt(gh.getVersion())+1));
						
						//执行修改
						int tt=iequipment_walkthroughService.update(tab);
						if(tt>=1){
							josn.msg="修改成功!";
							josn.state=200;
							josn.data=tab;
						}else{
							josn.msg="修改失败!";
						}
					}else{
						josn.msg="请输入正确的id，该id无效!";
					}
				}else{
					josn.msg="请填写设备预排信息!";
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
	 * 根据id标识删除信息
	 * @author lingfe     
	 * @created 2019年3月26日 下午4:08:55  
	 * @param id
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteWhereId", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public JosnModel<Object> deleteWhereId(
	    		@RequestParam(value="id",required=false)String id,
	    		HttpServletRequest request,
	    		HttpSession session){
			//实例化对象
			JosnModel<Object> josn=new JosnModel<Object>();
			Tab_system_log sysLog=new Tab_system_log();
			
			//系统日志
			sysLog.setIp(GetIpUtil.getIpAddr(request));
			sysLog.setModel_name("根据id标识删除信息,"+request.getRequestURI());
			Object creator=session.getAttribute("userid");
			if(SYS_GET.IS_SESSION_VALIDATE){
				if(!StringUtils.isEmpty(creator)){
					sysLog.setCreator(creator.toString());
				}else{
					josn.msg="会话过期!请重新登录";
					return josn;
				}
			}
			sysLog.setModify(sysLog.getCreator());
			sysLog.setOperation_type(3);
			
			try {
				//验证非空
				if(id!=null&&!"".equals(id)){
					//执行查询
					Tab_equipment_walkthrough tab=iequipment_walkthroughService.getWhereId(id);
					if(tab!=null){
						//执行删除
						int tt=iequipment_walkthroughService.deleteWhereId(id);
						if(tt>=1){
							josn.msg="删除成功!";
							josn.state=200;
						}else{
							josn.msg="删除失败!";
						}
					}else{
						josn.msg="id无效或者已经不存在了!";
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
	 * 分页查询设备预排信息
	 * @author lingfe     
	 * @created 2019年3月26日 下午3:48:59  
	 * @param pageIndex	当前页
	 * @param pageNum	页容量
	 * @param infirmary_name 医院名称
	 * @param department	科室
	 * @param is_obtain_rdtl	是否取得放射诊疗许可证: 0=否，1=是
	 * @param is_obtain_rsl	是否取得辐射安全许可证: 0=否，1=是
	 * @param is_obtain_lcl 是否取得大型配置许可证: 0=否，1=是
	 * @param is_scrap	是否报废
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/pageSelect", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public JosnModel<Object> pageSelect(
			@RequestParam(value="pageIndex",required=false,defaultValue="1")Integer pageIndex,
			@RequestParam(value="pageNum",required=false,defaultValue="10")Integer pageNum,
			HttpServletRequest request,
	    		HttpSession session){
			//实例化对象
			JosnModel<Object> josn=new JosnModel<Object>();
			Map<String, Object>  map = new HashMap<>();
			PageModel<Tab_equipment_walkthrough> page=new PageModel<Tab_equipment_walkthrough>();
			Tab_system_log sysLog=new Tab_system_log();
			
			//系统日志
			sysLog.setIp(GetIpUtil.getIpAddr(request));
			sysLog.setModel_name("分页查询设备预排信息,"+request.getRequestURI());
			Object creator=session.getAttribute("userid");
			if(!StringUtils.isEmpty(creator)){
				sysLog.setCreator(creator.toString());
			}
			sysLog.setModify(sysLog.getCreator());
			sysLog.setOperation_type(1);
			
			try {
				
				//验证非空
				map.put("pageIndex", pageIndex);
				map.put("pageNum",pageNum);
				page.setPageIndex(pageIndex);
				page.setPageNum(pageNum);
				
				//设置表:设备预排表
				map.put("table", "equipment_walkthrough");
				
				//得到总数据量
				int numCount=iequipment_walkthroughService.getCount(map);
				page.setNumCount(numCount);
				
				//得到数据
				List<Tab_equipment_walkthrough> list=iequipment_walkthroughService.pageSelect(map);
				//验证非空
				if(list!=null){
					for (Tab_equipment_walkthrough tab : list) {
						//得到创建人名称
						if(!StringUtils.isEmpty(tab.getCrt_code())){
							if(!"游客".equals(tab.getCrt_code())){
								Tab_user_info info=iuserinfoService.getWhereId(tab.getCrt_code());
								if(info!=null){
									tab.setCrt_name(info.getUsername());
								}
							}
						}
					}
					
					josn.state=200;
					josn.msg="请求成功!";
					page.setList(list);
					josn.data=page;
				}else{
					josn.msg="没有数据,添加一条吧!";
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
	 * 根据设备预排id标识查询设备预排信息
	 * @author lingfe     
	 * @created 2019年3月26日 下午3:33:49  
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
			
			//系统日志
			sysLog.setIp(GetIpUtil.getIpAddr(request));
			sysLog.setModel_name("根据设备预排id标识查询设备预排信息,"+request.getRequestURI());
			Object creator=session.getAttribute("userid");
			if(!StringUtils.isEmpty(creator)){
				sysLog.setCreator(creator.toString());
			}
			sysLog.setModify(sysLog.getCreator());
			sysLog.setOperation_type(2);
			
			try {
				//验证非空
				if(id!=null&&!"".equals(id)){
					//执行查询
					josn.data=iequipment_walkthroughService.getWhereId(id);
					josn.state=200;
					josn.msg="获取成功!";
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
	 * 保存多条设备预排信息(list多条数据保存)
	 * @author lingfe     
	 * @created 2019年3月26日 下午3:29:01  
	 * @param tab_list
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/save_list", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public JosnModel<Object> save_list(
				@RequestParam(value="tab_list",required=false) String tab_list,
	    		HttpServletRequest request,
	    		HttpSession session){
			//实例化对象
			JosnModel<Object> josn=new JosnModel<Object>();
			Tab_system_log sysLog=new Tab_system_log();
			
			//系统日志
			sysLog.setIp(GetIpUtil.getIpAddr(request));
			sysLog.setModel_name("保存设备预排信息,"+request.getRequestURI());
			Object creator=session.getAttribute("userid");
			if(SYS_GET.IS_SESSION_VALIDATE){
				if(!StringUtils.isEmpty(creator)){
					sysLog.setCreator(creator.toString());
				}else{
					josn.msg="会话过期!请重新登录";
					return josn;
				}
			}
			sysLog.setModify(sysLog.getCreator());
			sysLog.setOperation_type(4);
			
			try {
				//验证非空
				// 验证非空
				if(tab_list!=null&&!"".equals(tab_list)){
					//得到预排json数据
					JSONObject jsonObj=JSONObject.parseObject(tab_list);
					//取出eq_list对应的值,值为字符串
					String str = jsonObj.getString("tab_list"); 
			        //使用JSONArray.parseArray(String, Class<T>)将字符串转为指定对象集合
			        List<Tab_equipment_walkthrough> listPram = (List<Tab_equipment_walkthrough>) JSONArray.parseArray(str, Tab_equipment_walkthrough.class);
			        for (Tab_equipment_walkthrough tab2 : listPram) {
			        	tab2.setId(UUID.randomUUID().toString().replace("-", ""));
						tab2.setCrt_code(sysLog.getCreator());
						tab2.setCrt_date(new Date());
						//执行保存
						int tt=iequipment_walkthroughService.save(tab2);
						if(tt>=1){
							josn.msg="保存成功!";
							josn.data=tab2;
							josn.state=200;
						}else{
							josn.msg="保存失败!";
							break;
						}
			        }
				}else{
					josn.msg="请填写设备预排信息!";
				}
			} catch (Exception e) {
				sysLog.setIs_bug(1);
				josn.msg=e.getMessage();
				josn.state=500;
			}
			
			return josn;
		}
	
	
	/**
	 * 
	 * 保存设备预排信息
	 * @author lingfe     
	 * @created 2019年3月26日 下午3:29:01  
	 * @param tab
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/save", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public JosnModel<Object> save(
				Tab_equipment_walkthrough tab,
	    		HttpServletRequest request,
	    		HttpSession session){
			//实例化对象
			JosnModel<Object> josn=new JosnModel<Object>();
			Tab_system_log sysLog=new Tab_system_log();
			
			//系统日志
			sysLog.setIp(GetIpUtil.getIpAddr(request));
			sysLog.setModel_name("保存设备预排信息,"+request.getRequestURI());
			Object creator=session.getAttribute("userid");
			if(SYS_GET.IS_SESSION_VALIDATE){
				if(!StringUtils.isEmpty(creator)){
					sysLog.setCreator(creator.toString());
				}else{
					josn.msg="会话过期!请重新登录";
					return josn;
				}
			}
			sysLog.setModify(sysLog.getCreator());
			sysLog.setOperation_type(4);
			
			try {
				//验证非空
				if(tab!=null){
					tab.setId(UUID.randomUUID().toString().replace("-", ""));
					tab.setCrt_code(sysLog.getCreator());
					tab.setCrt_date(new Date());
					//执行保存
					int tt=iequipment_walkthroughService.save(tab);
					if(tt>=1){
						josn.msg="保存成功!";
						josn.data=tab;
						josn.state=200;
					}else{
						josn.msg="保存失败!";
					}
					
				}else{
					josn.msg="请填写设备预排信息!";
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
	
}
