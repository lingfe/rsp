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
import com.rsp.model.Tab_licence_walkthrough;
import com.rsp.model.Tab_system_log;
import com.rsp.model.Tab_user_info;
import com.rsp.service.Ilicence_walkthroughService;
import com.rsp.service.Isys_system_logService;
import com.rsp.service.IuserinfoService;

/**
 * 
  * 文件名：Licence_walkthroughController.java
  * 描述： 证照预排请求访问表示层
  * 修改人： lingfe
  * 修改时间：2019年3月28日 上午10:02:34
  * 修改内容：
 */
@Controller
@RequestMapping("/licence_walkthrough")
public class Licence_walkthroughController {

	//自动装配
	@Autowired
	private Ilicence_walkthroughService ilicence_walkthroughService;
	
	//系统日志
	@Autowired
	private Isys_system_logService isys_system_logService;
		
	//用户信息
	@Autowired
	private IuserinfoService iuserinfoService;
	

	/**
	 * 
	 * 修改证照预排信息
	 * @author lingfe     
	 * @created 2019年3月28日 上午10:34:20  
	 * @param tab
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public JosnModel<Object> update(
			Tab_licence_walkthrough tab,
	    		HttpServletRequest request,
	    		HttpSession session){
			//实例化对象
			JosnModel<Object> josn=new JosnModel<Object>();
			Tab_system_log sysLog=new Tab_system_log();
			
			//系统日志
			sysLog.setIp(GetIpUtil.getIpAddr(request));
			sysLog.setModel_name("修改证照预排信息,"+request.getRequestURI());
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
					if(tab.getId()!=null&&!"".equals(tab.getId())){
						//查询
						Tab_licence_walkthrough gh=ilicence_walkthroughService.getWhereId(tab.getId());
						
						//更新值
						tab.setModify_code(sysLog.getCreator());
						tab.setModify_date(new Date());
						tab.setVersion(String.valueOf(Integer.parseInt(gh.getVersion())+1));
						
						
						//执行更新
						int tt=ilicence_walkthroughService.update(tab);
						if(tt>=1){
							josn.msg="修改成功!";
							josn.state=200;
							josn.data=tab;
						}else{
							josn.msg="修改失败!";
						}
					}else{
						josn.msg="id不为空！";
					}
				}else{
					josn.msg="请填写要修改的信息！";
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
	 * 根据id标识获取证照预排信息
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
			
			//系统日志
			sysLog.setIp(GetIpUtil.getIpAddr(request));
			sysLog.setModel_name("根据id标识获取证照预排信息,"+request.getRequestURI());
			Object creator=session.getAttribute("userid");
			if(!StringUtils.isEmpty(creator)){
				sysLog.setCreator(creator.toString());
			}
			sysLog.setModify(sysLog.getCreator());
			sysLog.setOperation_type(1);
			
			try {
				//验证非空
				if(id!=null&&!"".equals(id)){
					//执行查询
					josn.data=ilicence_walkthroughService.getWhereId(id);
					josn.msg="获取成功!";
					josn.state=200;
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
	 * 根据id标识删除证照预排信息
	 * @author lingfe     
	 * @created 2019年3月28日 上午10:30:34  
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
			sysLog.setModel_name("根据id标识删除证照预排信息,"+request.getRequestURI());
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
					Tab_licence_walkthrough tab=ilicence_walkthroughService.getWhereId(id);
					if(tab!=null){
						//执行删除
						int tt=ilicence_walkthroughService.deleteWhereId(id);
						if(tt>=1){
							josn.msg="删除成功!";
							josn.state=200;
						}else{
							josn.msg="删除失败!";
						}
					}else{
						josn.msg="id无效或者已经被删除了！";
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
	 * 分页查询证照预排信息
	 * @author lingfe     
	 * @created 2019年3月28日 上午10:24:50  
	 * @param pageIndex
	 * @param pageNum
	 * @param hospital_name	医院名称
	 * @param prearranged_type 预排类型
	 * @param licence_type 证照类型
	 * @param certificates_number 证件编号
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/pageSelect", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public JosnModel<Object> pageSelect(
			@RequestParam(value="pageIndex",required=false,defaultValue="1")Integer pageIndex,
			@RequestParam(value="pageNum",required=false,defaultValue="10")Integer pageNum,
			@RequestParam(value="hospital_name",required=false)String hospital_name,
			@RequestParam(value="prearranged_type",required=false)String prearranged_type,
			@RequestParam(value="licence_type",required=false)String licence_type,
			@RequestParam(value="certificates_number",required=false)String certificates_number,
	    		HttpServletRequest request,
	    		HttpSession session){
			//实例化对象
			JosnModel<Object> josn=new JosnModel<Object>();
			Map<String, Object>  map = new HashMap<>();
			PageModel<Tab_licence_walkthrough> page=new PageModel<Tab_licence_walkthrough>();
			Tab_system_log sysLog=new Tab_system_log();
			
			//系统日志
			sysLog.setIp(GetIpUtil.getIpAddr(request));
			sysLog.setModel_name("分页查询证照预排信息,"+request.getRequestURI());
			Object creator=session.getAttribute("userid");
			if(!StringUtils.isEmpty(creator)){
				sysLog.setCreator(creator.toString());
			}
			sysLog.setModify(sysLog.getCreator());
			sysLog.setOperation_type(1);
			
			try {
				//验证非空
				//赋值参数
				if(hospital_name!=null&&!"".equals(hospital_name)){
					map.put("hospital_name", hospital_name);
				}
				if(prearranged_type!=null&&!"".equals(prearranged_type)){
					map.put("prearranged_type", prearranged_type);
				}
				if(licence_type!=null&&!"".equals(licence_type)){
					map.put("licence_type", licence_type);
				}
				if(certificates_number!=null&&!"".equals(certificates_number)){
					map.put("certificates_number", certificates_number);
				}
				
				map.put("pageIndex", pageIndex);
				map.put("pageNum",pageNum);
				page.setPageIndex(pageIndex);
				page.setPageNum(pageNum);
				
				//设置表:证照预排信息表
				map.put("table", "licence_walkthrough");
				
				//得到总数据量
				int numCount=ilicence_walkthroughService.getCount(map);
				page.setNumCount(numCount);
				
				//得到数据
				List<Tab_licence_walkthrough> list=ilicence_walkthroughService.pageSelect(map);
				//验证非空
				if(list!=null){
					for (Tab_licence_walkthrough tab : list) {
						//得到创建人名称
						if(!StringUtils.isEmpty(tab.getCrt_code())){
							if(!"游客".equals(tab.getCrt_code())){
								Tab_user_info info=iuserinfoService.getWhereId(tab.getCrt_code());
								if(info!=null){
									tab.setCrt_code(info.getUsername());
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
	 * 保存证照预排信息
	 * @author lingfe     
	 * @created 2019年3月28日 上午10:10:52  
	 * @param tab
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/save", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public JosnModel<Object> save(
				Tab_licence_walkthrough tab,
	    		HttpServletRequest request,
	    		HttpSession session){
			//实例化对象
			JosnModel<Object> josn=new JosnModel<Object>();
			Tab_system_log sysLog=new Tab_system_log();
			
			//系统日志
			sysLog.setIp(GetIpUtil.getIpAddr(request));
			sysLog.setModel_name("保存证照预排信息,"+request.getRequestURI());
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
					//赋值
					tab.setCrt_code(sysLog.getCreator());
					
					//执行保存
					int tt=ilicence_walkthroughService.save(tab);
					if(tt>=1){
						josn.msg="保存成功!";
						josn.data=tab;
						josn.state=200;
					}else{
						josn.msg="save失败!";
					}
				}else{
					josn.msg="请填写证照预排信息!";
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
