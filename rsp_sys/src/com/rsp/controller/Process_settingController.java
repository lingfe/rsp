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

import com.rsp.controller.util.GetIpUtil;
import com.rsp.controller.util.SYS_GET;
import com.rsp.model.JosnModel;
import com.rsp.model.PageModel;
import com.rsp.model.Tab_process_setting;
import com.rsp.model.Tab_system_log;
import com.rsp.model.Tab_user_info;
import com.rsp.service.Iprocess_settingService;
import com.rsp.service.Isys_system_logService;
import com.rsp.service.IuserinfoService;

/**
 * 
  * 文件名：Process_settingController.java
  * 描述： 流程设置表示层
  * 修改人： lingfe
  * 修改时间：2019年3月25日 上午10:33:07
  * 修改内容：
 */
@Controller
@RequestMapping("/process_setting")
public class Process_settingController {
	
	//自动装配
	
	//系统日志
	@Autowired
	private Isys_system_logService isys_system_logService;
		
	//用户信息
	@Autowired
	private IuserinfoService iuserinfoService;
	
	//流程设置
	@Autowired
	private Iprocess_settingService iprocess_settingService;
	
	

	/**
	 * 
	 * 根据id标识获取流程设置信息
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
			sysLog.setModel_name("根据id标识获取流程设置信息,"+request.getRequestURI());
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
					Tab_process_setting pro=iprocess_settingService.getWhereId(id);
					if(pro!=null){
						josn.msg="请求成功!";
						josn.state=200;
						josn.data=pro;
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
	 * 分页查询流程设置信息
	 * @author lingfe     
	 * @created 2019年3月25日 上午11:17:28  
	 * @param pageIndex 当前页
	 * @param pageNum	页容量
	 * @param province	省份
	 * @param process_name	流程名称
	 * @param state	状态
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/pageSelect", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public JosnModel<Object> pageSelect(
			@RequestParam(value="pageIndex",required=false,defaultValue="1")Integer pageIndex,
			@RequestParam(value="pageNum",required=false,defaultValue="10")Integer pageNum,
			@RequestParam(value="province",required=false)String province,
			@RequestParam(value="process_type",required=false)String process_type,
			@RequestParam(value="process_classification",required=false)String process_classification,
			@RequestParam(value="state",required=false)Integer state,
	    		HttpServletRequest request,
	    		HttpSession session){
			//实例化对象
			JosnModel<Object> josn=new JosnModel<Object>();
			Map<String, Object>  map = new HashMap<>();
			PageModel<Tab_process_setting> page=new PageModel<Tab_process_setting>();
			Tab_system_log sysLog=new Tab_system_log();
		
			//系统日志
			sysLog.setIp(GetIpUtil.getIpAddr(request));
			sysLog.setModel_name("分页查询流程设置信息,"+request.getRequestURI());
			Object creator=session.getAttribute("userid");
			if(!StringUtils.isEmpty(creator)){
				sysLog.setCreator(creator.toString());
			}
			sysLog.setModify(sysLog.getCreator());
			sysLog.setOperation_type(1);
			
			try {
				//验证非空
				//赋值参数
				if(province!=null&&!"".equals(province)){
					map.put("province", province);
				}else{
					josn.msg="省份不能为空!";
				}
				if(process_type!=null&&!"".equals(process_type)){
					map.put("process_type", process_type);
				}
				if(process_classification!=null&&!"".equals(process_classification)){
					map.put("process_classification", process_classification);
				}
				if(state!=null){
					map.put("state", state);
				}
				map.put("pageIndex", pageIndex);
				map.put("pageNum",pageNum);
				page.setPageIndex(pageIndex);
				page.setPageNum(pageNum);
				
				//设置表:编码信息表
				map.put("table", "process_setting");
				
				//得到总数据量
				int numCount=iprocess_settingService.getCount(map);
				page.setNumCount(numCount);
				
				//得到数据
				List<Tab_process_setting> list=iprocess_settingService.pageSelect(map);
				if(list==null||list.size()<=0){
					josn.msg="没有数据!添加一条吧!";
				}else{
					for (Tab_process_setting pro : list) {
						//得到创建人名称
						if(!StringUtils.isEmpty(pro.getCrt_code())){
							if(!"游客".equals(pro.getCrt_code())){
								Tab_user_info info=iuserinfoService.getWhereId(pro.getCrt_code());
								if(info!=null){
									pro.setCrt_name(info.getUsername());
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
	 * 修改流程设置信息
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
				Tab_process_setting tabPro,
	    		HttpServletRequest request,
	    		HttpSession session){
			//实例化对象
			JosnModel<Object> josn=new JosnModel<Object>();
			Tab_system_log sysLog=new Tab_system_log();
			//系统日志
			sysLog.setIp(GetIpUtil.getIpAddr(request));
			sysLog.setModel_name("修改流程设置信息,"+request.getRequestURI());
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
				if(!StringUtils.isEmpty(tabPro)){
					if(!StringUtils.isEmpty(tabPro.getId())){
						//执行查询
						Tab_process_setting pro=iprocess_settingService.getWhereId(tabPro.getId());
						//赋值
						pro.setProcess_classification(tabPro.getProcess_classification());
						pro.setProcess_link(tabPro.getProcess_link());
						pro.setProcess_name(tabPro.getProcess_name());
						pro.setProcess_type(tabPro.getProcess_type());
						pro.setModify_date(new Date());
						pro.setModify_code(sysLog.getCreator());
						pro.setVersion(String.valueOf(Integer.parseInt(pro.getVersion())+1));
						
						
						//验证是否停用
						if(tabPro.getStop_flag()!=pro.getStop_flag()&&pro.getStop_flag()==1){
							//赋值停用信息
							pro.setStop_code(sysLog.getCreator());
							pro.setStop_date(new Date());
							pro.setStop_explain(pro.getStop_explain());
						}
						
						//执行更新修改
						int tt=iprocess_settingService.update(pro);
						if(tt>=1){
							josn.msg="修改成功!";
							josn.state=200;
							josn.data=pro;
						}else{
							josn.msg="修改失败!";
						}
					}else{
						josn.msg="id不能为空!";
					}
				}else{
					josn.msg="请输入信息!";
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
	 * 根据id标识删除流程设置信息
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
			sysLog.setModel_name("根据id标识删除流程设置信息,"+request.getRequestURI());
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
					Tab_process_setting tabPro=iprocess_settingService.getWhereId(id);
					if(tabPro!=null){
						//执行删除
						int tt=iprocess_settingService.deleteWhereId(id);
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
	 * 保存流程设置信息
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
				Tab_process_setting tabPro,
	    		HttpServletRequest request,
	    		HttpSession session){
			//实例化对象
			JosnModel<Object> josn=new JosnModel<Object>();
			Tab_system_log sysLog=new Tab_system_log();
			//系统日志
			sysLog.setIp(GetIpUtil.getIpAddr(request));
			sysLog.setModel_name("保存流程设置信息,"+request.getRequestURI());
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
				if(tabPro!=null){
					if(!StringUtils.isEmpty(tabPro.getProcess_type())){
						if(!StringUtils.isEmpty(tabPro.getProcess_name())){
							//赋值
							tabPro.setId(UUID.randomUUID().toString().replace("-", ""));
							tabPro.setCrt_code(sysLog.getCreator());
							tabPro.setCrt_date(new Date());
							
							
							//执行保存
							int tt=iprocess_settingService.save(tabPro);
							if(tt>=0){
								josn.msg="保存成功!";
								josn.state=200;
								josn.data=tabPro;
							}else{
								josn.msg="保存失败!";
							}
						}else{
							josn.msg="请输入流程名称!";
						}
					}else{
						josn.msg="请输入流程分类!";
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
