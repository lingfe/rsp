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
import com.rsp.controller.util.SYS_GET;
import com.rsp.model.JosnModel;
import com.rsp.model.PageModel;
import com.rsp.model.Tab_sys_role_action;
import com.rsp.model.Tab_system_log;
import com.rsp.model.Tab_user_info;
import com.rsp.service.Isys_role_actionService;
import com.rsp.service.Isys_system_logService;
import com.rsp.service.IuserinfoService;

/**
 * 
  * 文件名：Sys_role_actionController.java
  * 描述： 角色权限访问表示层
  * 修改人： lingfe
  * 修改时间：2019年4月12日 下午5:07:04
  * 修改内容：
 */
@Controller
@RequestMapping("/sys_role_action")
public class Sys_role_actionController {

	//系统日志
	@Autowired
	private Isys_system_logService isys_system_logService;
		
	//用户信息
	@Autowired
	private IuserinfoService iuserinfoService;
	
	//角色权限
	@Autowired
	private Isys_role_actionService isys_role_actionService;
	
	

	/**
	 * 
	 * 根据角色权限id标识获取
	 * @author lingfe     
	 * @created 2019年3月25日 下午2:42:49  
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
			sysLog.setModel_name("根据角色权限id标识获取,"+request.getRequestURI());
			Object creator=session.getAttribute("userid");
			if(!StringUtils.isEmpty(creator)){
				sysLog.setCreator(creator.toString());
			}
			sysLog.setModify(sysLog.getCreator());
			sysLog.setOperation_type(1);
			
			try {
				//验证非空
				if(!StringUtils.isEmpty(id)){
					//执行查询
					Tab_sys_role_action tab=isys_role_actionService.getWhereId(id);
					if(tab!=null){
						josn.msg="请求成功!";
						josn.state=200;
						josn.data=tab;
					}else{
						josn.msg="id无效或者已经不存在!";
					}
				}else{
					josn.msg="id不能为空！";
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
	 * 分页查询角色权限信息
	 * @author lingfe     
	 * @created 2019年3月25日 上午11:17:28  
	 * @param pageIndex 当前页
	 * @param pageNum	页容量
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
			PageModel<Tab_sys_role_action> page=new PageModel<Tab_sys_role_action>();
			Tab_system_log sysLog=new Tab_system_log();
		
			//系统日志
			sysLog.setIp(GetIpUtil.getIpAddr(request));
			sysLog.setModel_name("分页查询角色权限信息,"+request.getRequestURI());
			Object creator=session.getAttribute("userid");
			if(!StringUtils.isEmpty(creator)){
				sysLog.setCreator(creator.toString());
			}
			sysLog.setModify(sysLog.getCreator());
			sysLog.setOperation_type(1);
			
			try {
				//赋值参数
				map.put("pageIndex", pageIndex);
				map.put("pageNum",pageNum);
				page.setPageIndex(pageIndex);
				page.setPageNum(pageNum);
				
				//设置表:编码信息表
				map.put("table", "process_setting");
				
				//得到总数据量
				int numCount=isys_role_actionService.getCount(map);
				page.setNumCount(numCount);
				
				//得到数据
				List<Tab_sys_role_action> list=isys_role_actionService.pageSelect(map);
				if(list==null||list.size()<=0){
					josn.msg="没有数据!添加一条吧!";
				}else{
					for (Tab_sys_role_action tab : list) {
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
					josn.msg="查询成功!";
					josn.state=200;
					page.setList(list);
					josn.data=page;
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
	 * 修改角色权限信息
	 * @author lingfe     
	 * @created 2019年3月25日 上午11:04:04  
	 * @param tabPro
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public JosnModel<Object> update(
				Tab_sys_role_action tab,
	    		HttpServletRequest request,
	    		HttpSession session){
			//实例化对象
			JosnModel<Object> josn=new JosnModel<Object>();
			Tab_system_log sysLog=new Tab_system_log();
			//系统日志
			sysLog.setIp(GetIpUtil.getIpAddr(request));
			sysLog.setModel_name("修改角色权限信息,"+request.getRequestURI());
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
				if(!StringUtils.isEmpty(tab)){
					if(!StringUtils.isEmpty(tab.getId())){
						//执行查询
						Tab_sys_role_action tab_to=isys_role_actionService.getWhereId(tab.getId());
						//赋值
						tab_to.setModify_date(new Date());
						tab_to.setModify_code(sysLog.getCreator());
						tab_to.setVersion(String.valueOf(Integer.parseInt(tab_to.getVersion())+1));
						tab_to.setAction_id(tab.getAction_id());
						tab_to.setRole_id(tab.getRole_id());
						
						//执行更新修改
						int tt=isys_role_actionService.update(tab_to);
						if(tt>=1){
							josn.msg="修改成功!";
							josn.state=200;
							josn.data=tab_to;
						}else{
							josn.msg="修改失败!";
						}
					}else{
						josn.msg="id不能为空!";
					}
				}else{
					josn.msg="请输入角色权限名称信息!";
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
	 * 根据角色权限id标识删除
	 * @author lingfe     
	 * @created 2019年3月25日 上午10:53:46  
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
			sysLog.setModel_name("根据角色权限id标识删除,"+request.getRequestURI());
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
				if(!StringUtils.isEmpty(id)){
					//执行查询
					Tab_sys_role_action tab=isys_role_actionService.getWhereId(id);
					if(tab!=null){
						//执行删除
						int tt=isys_role_actionService.deleteWhereId(id);
						if(tt>=0){
							josn.msg="删除成功!";
							josn.state=200;
						}else{
							josn.msg="删除失败!";
						}
					}else{
						josn.msg="该id无效或者已经被删除了!";
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
	 * 保存角色权限
	 * @author lingfe     
	 * @created 2019年3月25日 上午10:41:22  
	 * @param tabPro
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/save", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public JosnModel<Object> save(
				Tab_sys_role_action tab,
	    		HttpServletRequest request,
	    		HttpSession session){
			//实例化对象
			JosnModel<Object> josn=new JosnModel<Object>();
			Tab_system_log sysLog=new Tab_system_log();
			//系统日志
			sysLog.setIp(GetIpUtil.getIpAddr(request));
			sysLog.setModel_name("保存角色权限,"+request.getRequestURI());
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
					//赋值
					tab.setCrt_code(sysLog.getCreator());
					tab.setCrt_date(new Date());
					
					//执行保存
					int tt=isys_role_actionService.save(tab);
					if(tt>=0){
						josn.msg="保存成功!";
						josn.state=200;
						josn.data=tab;
					}else{
						josn.msg="保存失败!";
					}
				}else{
					josn.msg="请输入参数!";
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
