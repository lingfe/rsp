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
import com.rsp.model.Tab_coding;
import com.rsp.model.Tab_sys_department_coding;
import com.rsp.model.Tab_system_log;
import com.rsp.model.Tab_user_info;
import com.rsp.service.Isys_department_codingService;
import com.rsp.service.Isys_system_logService;
import com.rsp.service.IuserinfoService;

/**
 * 
  * 文件名：Sys_department_codingController.java
  * 描述： 科室编码数据请求访问表示层
  * 修改人： lingfe
  * 修改时间：2019年4月8日 下午5:01:39
  * 修改内容：
 */
@RequestMapping("/sys_department_coding")
@Controller
public class Sys_department_codingController {


	
	//自动装配
	
	//系统日志
	@Autowired
	private Isys_system_logService isys_system_logService;
	
	//用户信息
	@Autowired
	private IuserinfoService iuserinfoService;
	
	//科室编码信息
	@Autowired
	private Isys_department_codingService isys_department_codingService;
	
	
	/**
	 * 
	 * 根据id标识查询科室编码信息
	 * @author lingfe     
	 * @created 2019年4月10日 下午3:15:49  
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
			sysLog.setModel_name("根据编码信息id标识查询信息,"+request.getRequestURI());
			Object creator=session.getAttribute("userid");
			if(!StringUtils.isEmpty(creator)){
				sysLog.setCreator(creator.toString());
			}
			sysLog.setModify(sysLog.getCreator());
			sysLog.setOperation_type(2);
			
			try {
				josn.data=isys_department_codingService.getWhereId(id);
				josn.msg="查询成功!";
				josn.state=200;
				
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
	 * 降
	 * @author lingfe     
	 * @created 2019年4月2日 下午2:49:46  
	 * @param id
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/desc", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public JosnModel<Object> desc(
	    		@RequestParam(value="id",required=false)String id,
	    		HttpServletRequest request,
	    		HttpSession session){
			//实例化对象
			JosnModel<Object> josn=new JosnModel<Object>();
			Tab_system_log sysLog=new Tab_system_log();
			Map<String, Object> map=new HashMap<String, Object>();
			
			//系统日志
			sysLog.setIp(GetIpUtil.getIpAddr(request));
			sysLog.setModel_name("降,"+request.getRequestURI());
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
				//执行查询
				Tab_sys_department_coding tab=isys_department_codingService.getWhereId(id);
				if(tab!=null){
					//替换位置
					map.put("table", "sys_department_coding");
					map.put("coding_sort", tab.getCoding_sort()-1);
					List<Tab_sys_department_coding> list=isys_department_codingService.getWhere(map);
					if(list!=null&&list.size()!=0){
						Tab_sys_department_coding tab2=list.get(0);
						tab2.setCoding_sort(tab.getCoding_sort());
						tab2.setModify_date(new Date());
						tab2.setModify_code(sysLog.getCreator());
						tab2.setVersion(String.valueOf(Integer.parseInt(tab.getVersion())+1));
						isys_department_codingService.update(tab2);
					}
					
					//更新赋值
					tab.setModify_date(new Date());
					tab.setModify_code(sysLog.getCreator());
					tab.setVersion(String.valueOf(Integer.parseInt(tab.getVersion())+1));
					tab.setCoding_sort(tab.getCoding_sort()-1);
					//执行修改
					isys_department_codingService.update(tab);
					josn.state=200;
					josn.msg="操作成功!";
				}else{
					josn.msg="id无效!";
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
	 * 升
	 * @author lingfe     
	 * @created 2019年4月2日 下午2:30:20  
	 * @param id
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/asc", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public JosnModel<Object> asc(
	    		@RequestParam(value="id",required=false)String id,
	    		HttpServletRequest request,
	    		HttpSession session){
			//实例化对象
			JosnModel<Object> josn=new JosnModel<Object>();
			Tab_system_log sysLog=new Tab_system_log();
			Map<String, Object> map=new HashMap<String, Object>();
			
			//系统日志
			sysLog.setIp(GetIpUtil.getIpAddr(request));
			sysLog.setModel_name("升,"+request.getRequestURI());
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
				//执行查询
				Tab_sys_department_coding tab=isys_department_codingService.getWhereId(id);
				if(tab!=null){
					//替换位置
					map.put("table", "sys_department_coding");
					for (int i = 1; i > 0;i++) {
						map.put("coding_sort", tab.getCoding_sort()+i);
						List<Tab_sys_department_coding> list=isys_department_codingService.getWhere(map);
						if(list!=null&&list.size()!=0){
							Tab_sys_department_coding tab2=list.get(0);
							tab2.setCoding_sort(tab.getCoding_sort());
							tab2.setModify_date(new Date());
							tab2.setModify_code(sysLog.getCreator());
							tab2.setVersion(String.valueOf(Integer.parseInt(tab.getVersion())+1));
							isys_department_codingService.update(tab2);
							break;
						}else{
							map.put("coding_sort", null);
							int count=isys_department_codingService.getCount(map);
							if(count<=1){
								break;
							}
						}
					}
					
					//更新赋值
					tab.setModify_date(new Date());
					tab.setModify_code(sysLog.getCreator());
					tab.setVersion(String.valueOf(Integer.parseInt(tab.getVersion())+1));
					tab.setCoding_sort(tab.getCoding_sort()+1);
					//执行修改
					isys_department_codingService.update(tab);
					josn.state=200;
					josn.msg="操作成功!";
				}else{
					josn.msg="id无效!";
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
	 * 修改科室编码信息
	 * @author lingfe     
	 * @created 2019年3月20日 上午11:03:23  
	 * @param coding
	 * @return
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public JosnModel<Object> update(Tab_sys_department_coding coding,
    		HttpServletRequest request,HttpSession session){
		//实例化对象
		JosnModel<Object> josn=new JosnModel<Object>();
		Tab_system_log sysLog=new Tab_system_log();
		//系统日志
		sysLog.setIp(GetIpUtil.getIpAddr(request));
		sysLog.setModel_name("修改科室编码信息,"+request.getRequestURI());
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
		
		//验证非空
		if(coding!=null){
			sysLog.setTarget_id(coding.getId());
			if(coding.getId()!=null){
				//执行查询,得到数据
				Tab_sys_department_coding tab_coding=isys_department_codingService.getWhereId(coding.getId());
				if(tab_coding!=null){
					//更新数据
					tab_coding.setModify_code(sysLog.getCreator());
					tab_coding.setModify_date(new Date());
					tab_coding.setVersion(String.valueOf(Integer.parseInt(tab_coding.getVersion())+1));
					tab_coding.setCoding_name(coding.getCoding_name());
					tab_coding.setRemark(coding.getRemark());
					tab_coding.setState(coding.getState());
					tab_coding.setHospital_id(coding.getHospital_id());
					
					//验证是否停用
					if(tab_coding.getStop_flag()!=coding.getStop_flag()&&tab_coding.getStop_flag()==1){
						//赋值停用信息
						tab_coding.setStop_flag(coding.getStop_flag());
						tab_coding.setStop_code(sysLog.getCreator());
						tab_coding.setStop_date(new Date());
						tab_coding.setStop_explain(coding.getStop_explain());
					}
					
					try {
						//执行更新
						int tt=isys_department_codingService.update(tab_coding);
						if(tt>=1){
							josn.state=200;
							josn.msg="修改成功!";
							josn.data=tab_coding;
						}else{
							josn.msg="修改失败!";
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
				}else{
					josn.msg="id无效!请检查!";
				}
			}else{
				josn.msg="参数有误!请检查";
			}
		}else{
			josn.msg="空对象!";
		}
		
		return josn;
	}
	
	/**
	 * 
	 * 分页请求科室编码信息
	 * @author lingfe     
	 * @created 2019年3月20日 上午9:12:35  
	 * @param coding_type	编码类型
	 * @param pageIndex 当前页
	 * @param pageNum 页容量
	 * @return
	 */
	@RequestMapping(value = "/getPage", method = { RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
	public JosnModel<Object> getPage(
			@RequestParam(value="pageIndex",required=false,defaultValue="1")Integer pageIndex,
			@RequestParam(value="pageNum",required=false,defaultValue="10")Integer pageNum,
			@RequestParam(value="coding_name",required=false)String coding_name,
			@RequestParam(value="stop_flag",required=false)Integer stop_flag,
			HttpServletRequest request,HttpSession session){
		//实例化对象
		JosnModel<Object> josn=new JosnModel<Object>();
		Map<String, Object>  map = new HashMap<>();
		PageModel<Tab_sys_department_coding> page=new PageModel<Tab_sys_department_coding>();
		Tab_system_log sysLog=new Tab_system_log();
		//系统日志
		sysLog.setIp(GetIpUtil.getIpAddr(request));
		sysLog.setModel_name("分页请求科室编码信息,"+request.getRequestURI());
		Object creator=session.getAttribute("userid");
		if(!StringUtils.isEmpty(creator)){
			sysLog.setCreator(creator.toString());
		}
		sysLog.setModify(sysLog.getCreator());
		sysLog.setOperation_type(1);
		
		//验证非空
		if(true){
			if(coding_name!=null){
				map.put("coding_name", coding_name);
			}
			if(stop_flag!=null){
				map.put("stop_flag", stop_flag);
			}
			map.put("pageIndex", pageIndex);
			map.put("pageNum",pageNum);
			page.setPageIndex(pageIndex);
			page.setPageNum(pageNum);
			
			
			//设置表:科室编码信息表
			map.put("table", "sys_department_coding");
			
			//得到总数据量
			int numCount=isys_department_codingService.getCount(map);
			page.setNumCount(numCount);
			
			try {
				//得到数据
				List<Tab_sys_department_coding> list=isys_department_codingService.pageSelect(map);
				//验证非空
				if(list!=null){
					for (Tab_sys_department_coding tab_coding : list) {
						//得到创建人名称
						Tab_user_info info=iuserinfoService.getWhereId(tab_coding.getCrt_code());
						if(info!=null){
							tab_coding.setCrt_name(info.getUsername());
						}
						
						//验证是否停用
						if(tab_coding.getState()!=null&&tab_coding.getState()==0){
							//得到停用人
							info=iuserinfoService.getWhereId(tab_coding.getStop_code());
							if(info!=null){
								tab_coding.setStop_name(info.getUsername());
							}
						}else{
							tab_coding.setStop_name(null);
							tab_coding.setStop_date(null);
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
		}
		
		return josn;
	}
	
	/**
	 * 
	 * 保存科室编码数据
	 * @author lingfe     
	 * @created 2019年3月19日 下午4:35:32  
	 * @param coding
	 * @return
	 */
	@RequestMapping(value = "/save", method = { RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public JosnModel<Tab_sys_department_coding> save(Tab_sys_department_coding coding,
    		HttpServletRequest request,HttpSession session){
		//实例化对象
		JosnModel<Tab_sys_department_coding> josn=new JosnModel<Tab_sys_department_coding>();
		Tab_system_log sysLog=new Tab_system_log();
		//系统日志
		sysLog.setIp(GetIpUtil.getIpAddr(request));
		sysLog.setModel_name("保存科室编码数据,"+request.getRequestURI());
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
		
		//验证非空
		if(!StringUtils.isEmpty(coding.getCoding_name())){
			//赋值
			coding.setId(UUID.randomUUID().toString().replace("-", ""));
			coding.setCrt_code(sysLog.getCreator());
			coding.setCrt_date(new Date());
			coding.setStop_flag(1);
			
			try {
				
				//执行查询是否已经存在相同的编码名称
				Tab_sys_department_coding tab=isys_department_codingService.getWhereCodingName(coding.getCoding_name());
				if(tab == null){
					//执行保存
					int tt=isys_department_codingService.save(coding);
					if(tt>=1){
						josn.msg="保存成功!";
						josn.state=200;
						josn.data=coding;
					}else{
						josn.msg="保存失败!";
					}
				}else{
					josn.msg="该编码名称系统已经存在了!请换一个";
				}
			} catch (Exception e) {
				sysLog.setIs_bug(1);
				josn.state=500;
				josn.msg=e.getMessage();
			}
			//操作说明
			sysLog.setExceptionally_detailed(josn.msg);
			//添加系统日志
			//isystem_logService.add(sysLog);
		}else{
			josn.msg="编码名称不能为空!";
		}
		
		return josn;
	}
	
    
    /**
     * 
     * 根据科室编码表id标识删除数据
     * @author lingfe     
     * @created 2019年3月19日 下午5:35:01  
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteWhereId", method = { RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public JosnModel<Tab_coding> deleteWhereId(@RequestParam(value="id",required=false)String id,
    		HttpServletRequest request,HttpSession session){
    	//实例化对象
    	JosnModel<Tab_coding> josn=new JosnModel<Tab_coding>();
    	Tab_system_log sysLog=new Tab_system_log();
		//系统日志
		sysLog.setIp(GetIpUtil.getIpAddr(request));
		sysLog.setModel_name("根据科室编码表id标识删除数据,"+request.getRequestURI());
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
		
    	//验证非空
    	if(!StringUtils.isEmpty(id)){
    		//执行删除
    		int tt=isys_department_codingService.deleteWhereId(id);
    		try {
				if(tt>=1){
					sysLog.setTarget_id(id);
					josn.msg="删除成功!";
					josn.state=200;
				}else{
					josn.msg="删除失败!不存在或者已经被删除!";
				}
			} catch (Exception e) {
				josn.msg=e.getMessage();
				josn.state=500;
			}
    		//操作说明
			sysLog.setExceptionally_detailed(josn.msg);
			//添加系统日志
			//isystem_logService.add(sysLog);
    	}else{
    		josn.msg="id不能为空!";
    	}
    	
    	return josn;
    }
    

}
