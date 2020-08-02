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
import com.rsp.model.Tab_sys_department_coding;
import com.rsp.model.Tab_system_log;
import com.rsp.model.Tab_t_yy_ksxx;
import com.rsp.model.Tab_user_info;
import com.rsp.service.Isys_department_codingService;
import com.rsp.service.It_yy_ksxxService;
import com.rsp.service.IuserinfoService;

/**
 * 
  * 文件名：T_department_managementController.java
  * 描述： 科室管理数据访问表示层
  * 修改人： lingfe
  * 修改时间：2019年4月12日 上午11:55:39
  * 修改内容：
 */
@Controller
@RequestMapping("/t_department_management")
public class T_yy_ksxxController {
	
	//用户信息
	@Autowired
	private IuserinfoService iuserinfoService;
	
	//科室管理
	@Autowired
	private It_yy_ksxxService it_yy_ksxxService;
	
	//科室编码
	@Autowired
	private Isys_department_codingService isys_department_codingService;
	
	
	/**
	 * 
	 * 根据科室管理id标识获取数据
	 * @author lingfe     
	 * @created 2019年4月10日 上午10:12:22  
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
			sysLog.setModel_name("根据科室管理id标识获取数据,"+request.getRequestURI());
			Object creator=session.getAttribute("userid");
			if(!StringUtils.isEmpty(creator)){
				sysLog.setCreator(creator.toString());
			}
			sysLog.setModify(sysLog.getCreator());
			sysLog.setOperation_type(2);
			
			try {
				josn.data=it_yy_ksxxService.getWhereId(id);
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
	 * 根据条件查询科室管理信息
	 * @author lingfe     
	 * @created 2019年4月10日 上午10:12:22  
	 * @param id
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getWhere", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public JosnModel<Object> getWhere(
	    		HttpServletRequest request,
	    		HttpSession session){
			//实例化对象
			JosnModel<Object> josn=new JosnModel<Object>();
			Map<String, Object>  map = new HashMap<>();
			Tab_system_log sysLog=new Tab_system_log();
			
			//系统日志
			sysLog.setIp(GetIpUtil.getIpAddr(request));
			sysLog.setModel_name("根据条件查询科室管理信息,"+request.getRequestURI());
			Object creator=session.getAttribute("userid");
			if(!StringUtils.isEmpty(creator)){
				sysLog.setCreator(creator.toString());
			}
			sysLog.setModify(sysLog.getCreator());
			sysLog.setOperation_type(2);
			
			try {
				//验证非空
				
				
				//table表
				map.put("table", "t_department_management");
				
				josn.data=it_yy_ksxxService.getWhere(map);
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
				Tab_t_yy_ksxx tab=it_yy_ksxxService.getWhereId(id);
				if(tab!=null){
					//替换位置
					map.put("table", "t_department_management");
					map.put("sort", tab.getSort()-1);
					List<Tab_t_yy_ksxx> list=it_yy_ksxxService.getWhere(map);
					if(list!=null&&list.size()!=0){
						Tab_t_yy_ksxx tab2=list.get(0);
						tab2.setSort(tab.getSort());
						tab2.setModify_date(new Date());
						tab2.setModify_code(sysLog.getCreator());
						tab2.setVersion(String.valueOf(Integer.parseInt(tab.getVersion())+1));
						it_yy_ksxxService.update(tab2);
					}
					
					//更新赋值
					tab.setModify_date(new Date());
					tab.setModify_code(sysLog.getCreator());
					tab.setVersion(String.valueOf(Integer.parseInt(tab.getVersion())+1));
					tab.setSort(tab.getSort()-1);
					
					//执行修改
					it_yy_ksxxService.update(tab);
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
				Tab_t_yy_ksxx tab=it_yy_ksxxService.getWhereId(id);
				if(tab!=null){
					//替换位置
					map.put("table", "t_department_management");
					for (int i = 1; i > 0;i++) {
						map.put("sort", tab.getSort()+i);
						List<Tab_t_yy_ksxx> list=it_yy_ksxxService.getWhere(map);
						if(list!=null&&list.size()!=0){
							Tab_t_yy_ksxx tab2=list.get(0);
							tab2.setSort(tab.getSort());
							tab2.setModify_date(new Date());
							tab2.setModify_code(sysLog.getCreator());
							tab2.setVersion(String.valueOf(Integer.parseInt(tab.getVersion())+1));
							it_yy_ksxxService.update(tab2);
							break;
						}else{
							map.put("sort", null);
							int count=it_yy_ksxxService.getCount(map);
							if(count<=1){
								break;
							}
						}
					}
					
					//更新赋值
					tab.setModify_date(new Date());
					tab.setModify_code(sysLog.getCreator());
					tab.setVersion(String.valueOf(Integer.parseInt(tab.getVersion())+1));
					tab.setSort(tab.getSort()+1);
					//执行修改
					it_yy_ksxxService.update(tab);
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
	 * 修改科室管理信息
	 * @author lingfe     
	 * @created 2019年3月20日 上午11:03:23  
	 * @param coding
	 * @return
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public JosnModel<Object> update(Tab_t_yy_ksxx tab,
    		HttpServletRequest request,HttpSession session){
		//实例化对象
		JosnModel<Object> josn=new JosnModel<Object>();
		Tab_system_log sysLog=new Tab_system_log();
		//系统日志
		sysLog.setIp(GetIpUtil.getIpAddr(request));
		sysLog.setModel_name("修改科室管理信息,"+request.getRequestURI());
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
		if(tab!=null){
			sysLog.setTarget_id(tab.getId());
			if(tab.getId()!=null){
				//执行查询,得到数据
				Tab_t_yy_ksxx tab_coding=it_yy_ksxxService.getWhereId(tab.getId());
				if(tab_coding!=null){
					//更新数据
					tab_coding.setModify_code(sysLog.getCreator());
					tab_coding.setModify_date(new Date());
					tab_coding.setVersion(String.valueOf(Integer.parseInt(tab_coding.getVersion())+1));
					tab_coding.setRemark(tab.getRemark());
					tab_coding.setState(tab.getState());
					tab_coding.setKssx_id(tab.getKssx_id());
					tab_coding.setKs_name(tab.getKs_name());
					tab_coding.setKs_tel(tab.getKs_tel());
					tab_coding.setParent_id(tab.getParent_id());
					
					//验证是否停用
					if(tab_coding.getStop_flag()!=tab.getStop_flag()&&tab_coding.getStop_flag()==1){
						//赋值停用信息
						tab_coding.setStop_flag(tab.getStop_flag());
						tab_coding.setStop_code(sysLog.getCreator());
						tab_coding.setStop_date(new Date());
						tab_coding.setStop_explain(tab.getStop_explain());
					}
					
					try {
						//执行更新
						int tt=it_yy_ksxxService.update(tab_coding);
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
	 * 修改科室管理信息所需字段
	 * @author lingfe     
	 * @created 2019年4月11日 上午11:39:10  
	 * @param id	编码id
	 * @param is_walkthrough	是否预排
	 * @param walkthrough_name	预排名称
	 * @param walkthrough_cycle	预排周期
	 * @param next_time	下次时间
	 * @param request
	 * @param session
	 * @return 
	 */
	@RequestMapping(value = "/update_field", method = { RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public JosnModel<Object> update_field(
    		@RequestParam(value="id",required=false)String id,
			HttpServletRequest request,HttpSession session){
		//实例化对象
		JosnModel<Object> josn=new JosnModel<Object>();
		Tab_system_log sysLog=new Tab_system_log();
		//系统日志
		sysLog.setIp(GetIpUtil.getIpAddr(request));
		sysLog.setModel_name("修改科室管理信息所需字段,"+request.getRequestURI());
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
		if(id!=null){
			sysLog.setTarget_id(id);
			//执行查询,得到数据
			Tab_t_yy_ksxx tab=it_yy_ksxxService.getWhereId(id);
			if(tab!=null){
				//更新数据
				tab.setModify_code(sysLog.getCreator());
				tab.setModify_date(new Date());
				
				try {
					//执行更新
					int tt=it_yy_ksxxService.update(tab);
					if(tt>=1){
						josn.state=200;
						josn.msg="修改成功!";
						josn.data=tab;
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
			josn.msg="id不能为空!";
		}
		
		return josn;
	}
	
	
	/**
	 * 
	 * 分页请求科室管理信息
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
			@RequestParam(value="kssx_id",required=false)String department_coding_id,
			@RequestParam(value="ks_name",required=false)String department_name,
			@RequestParam(value="pid",required=false)String pid,
			@RequestParam(value="stop_flag",required=false)Integer stop_flag,
			HttpServletRequest request,HttpSession session){
		//实例化对象
		JosnModel<Object> josn=new JosnModel<Object>();
		Map<String, Object>  map = new HashMap<>();
		PageModel<Tab_t_yy_ksxx> page=new PageModel<Tab_t_yy_ksxx>();

		
		try {  
			
			//赋值参数
			if(department_coding_id!=null&&!"".equals(department_coding_id)){
				map.put("kssx_id", department_coding_id);
			}
			if(department_name!=null&&!"".equals(department_name)){
				map.put("ks_name", department_name);
			}
			if(pid!=null&&!"".equals(pid)){
				map.put("parent_id", pid);
			}
			if(stop_flag!=null){
				map.put("stop_flag", stop_flag);
			}
			
			map.put("pageIndex", pageIndex);
			map.put("pageNum",pageNum);
			page.setPageIndex(pageIndex);
			page.setPageNum(pageNum);
			
			//设置表
			map.put("table", "t_yy_ksxx");
			
			//得到总数据量
			int numCount=it_yy_ksxxService.getCount(map);
			page.setNumCount(numCount);
			
			//得到数据
			List<Tab_t_yy_ksxx> list=it_yy_ksxxService.pageSelect(map);
			//验证非空
			if(list!=null){
				for (Tab_t_yy_ksxx tab_coding : list) {
					//得到科室属性
					Tab_sys_department_coding de=isys_department_codingService.getWhereId(tab_coding.getKssx_id());
					if(de!=null){
						tab_coding.kssx_name=de.getCoding_name();
					}
					
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
			josn.msg=e.getMessage();
			josn.state=500;
		}
		return josn;
	}
	
	/**
	 * 
	 * 保存科室管理信息数据
	 * @author lingfe     
	 * @created 2019年3月19日 下午4:35:32  
	 * @param coding
	 * @return
	 */
	@RequestMapping(value = "/save", method = { RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public JosnModel<Object> save(Tab_t_yy_ksxx tab,
    		HttpServletRequest request,HttpSession session){
		//实例化对象
		JosnModel<Object> josn=new JosnModel<Object>();
		Tab_system_log sysLog=new Tab_system_log();
		//系统日志
		sysLog.setIp(GetIpUtil.getIpAddr(request));
		sysLog.setModel_name("保存科室管理信息数据,"+request.getRequestURI());
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
		if(!StringUtils.isEmpty(tab.getKs_name())){
			//赋值
			tab.setId(UUID.randomUUID().toString().replace("-", ""));
			tab.setCrt_code(sysLog.getCreator());
			tab.setCrt_date(new Date());
			tab.setStop_flag(1);
			
			try {
				//执行查询是否重复
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("hospital_id", tab.getHospital_id());
				map.put("ks_name", tab.getKs_name());
				map.put("table","t_yy_ksxx");
				List<Tab_t_yy_ksxx> tab_list=it_yy_ksxxService.getWhere(map);
				if(tab_list==null||tab_list.size()<=0){
					//执行保存
					int tt=it_yy_ksxxService.save(tab);
					if(tt>=1){
						josn.msg="保存成功!";
						josn.state=200;
						josn.data=tab;
					}else{
						josn.msg="保存失败!";
					}
				}else{
					josn.msg="科室名称重复了!";
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
			josn.msg="科室名称不能为空!";
		}
		
		return josn;
	}
	
    
    /**
     * 
     * 根据科室管理id标识删除
     * @author lingfe     
     * @created 2019年3月19日 下午5:35:01  
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteWhereId", method = { RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public JosnModel<Object> deleteWhereId(@RequestParam(value="id",required=false)String id,
    		HttpServletRequest request,HttpSession session){
    	//实例化对象
    	JosnModel<Object> josn=new JosnModel<Object>();
    	Tab_system_log sysLog=new Tab_system_log();
		//系统日志
		sysLog.setIp(GetIpUtil.getIpAddr(request));
		sysLog.setModel_name("根据科室管理id标识删除,"+request.getRequestURI());
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
    		int tt=it_yy_ksxxService.deleteWhereId(id);
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
