package com.rsp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.rsp.model.JosnModel;
import com.rsp.model.PageModel;
import com.rsp.model.Tab_equipment_walkthrough;
import com.rsp.model.Tab_manufacturer;
import com.rsp.model.Tab_system_log;
import com.rsp.model.Tab_user_info;
import com.rsp.service.Iequipment_walkthroughService;
import com.rsp.service.Isystem_logService;
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
	private Isystem_logService isystem_logService;
	
	//用户信息
	@Autowired
	private IuserinfoService iuserinfoService;
	
	

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
			sysLog.setModel_name("修改编码信息,"+request.getRequestURI());
			Object creator=session.getAttribute("userid");
			if(!StringUtils.isEmpty(creator)){
				sysLog.setCreator(creator.toString());
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
						tab.setCdate(gh.getCdate());
						tab.setMdate(new Date());
						tab.setCreator(gh.getCreator());
						tab.setModify(sysLog.getCreator());
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
			isystem_logService.add(sysLog);
			
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
			if(!StringUtils.isEmpty(creator)){
				sysLog.setCreator(creator.toString());
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
			isystem_logService.add(sysLog);
			
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
			@RequestParam(value="infirmary_name",required=false)String infirmary_name,
			@RequestParam(value="department",required=false)String department,
			@RequestParam(value="is_obtain_rdtl",required=false)Integer is_obtain_rdtl,
			@RequestParam(value="is_obtain_rsl",required=false)String is_obtain_rsl,
			@RequestParam(value="is_obtain_lcl",required=false)Integer is_obtain_lcl,
			@RequestParam(value="is_scrap",required=false)Integer is_scrap,
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
				if(infirmary_name!=null&&!"".equals(infirmary_name)){
					map.put("infirmary_name", infirmary_name);
				}
				if(department!=null&&!"".equals(department)){
					map.put("department", department);
				}
				if(is_obtain_rdtl!=null&&!"".equals(is_obtain_rdtl));{
					map.put("is_obtain_rdtl", is_obtain_rdtl);
				}
				if(is_obtain_rsl!=null&&!"".equals(is_obtain_rsl)){
					map.put("is_obtain_rsl", is_obtain_rsl);
				}
				if(is_obtain_lcl!=null&&!"".equals(is_obtain_lcl)){
					map.put("is_obtain_lcl", is_obtain_lcl);
				}
				if(is_scrap!=null&&!"".equals(is_scrap));{
					map.put("is_scrap", is_scrap);
				}
				
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
						if(!StringUtils.isEmpty(tab.getCreator())){
							if(!"游客".equals(tab.getCreator())){
								Tab_user_info info=iuserinfoService.getWhereId(tab.getCreator());
								if(info!=null){
									tab.creator_name=info.getUsername();
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
			isystem_logService.add(sysLog);
			
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
			isystem_logService.add(sysLog);
			
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
			if(!StringUtils.isEmpty(creator)){
				sysLog.setCreator(creator.toString());
			}
			sysLog.setModify(sysLog.getCreator());
			sysLog.setOperation_type(4);
			
			try {
				//验证非空
				if(tab!=null){
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
			isystem_logService.add(sysLog);
			
			return josn;
		}
	
}
