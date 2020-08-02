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
import com.rsp.controller.util.LogUtils;
import com.rsp.controller.util.SYS_GET;
import com.rsp.model.JosnModel;
import com.rsp.model.PageModel;
import com.rsp.model.Tab_coding;
import com.rsp.model.Tab_user_info;
import com.rsp.service.Isys_codingService;
import com.rsp.service.Isys_system_logService;
import com.rsp.service.IuserinfoService;

/**
 * 
  * 文件名：CodingController.java
  * 描述： 编码表数据访问表示类
  * 修改人： lingfe
  * 修改时间：2019年3月19日 下午4:25:55
  * 修改内容：
 */
@Controller
@RequestMapping("/coding")
public class CodingController {
	
	//系统日志
	@Autowired
	private Isys_system_logService isys_system_logService;
	
	//用户信息
	@Autowired
	private IuserinfoService iuserinfoService;
	
	//编码信息
	@Autowired
	private Isys_codingService isys_codingService;

	/**
	 * 是否有异常bug
	 */
	private int flag_bug=0;
	
	/**
	 * 
	 * 根据编码信息id标识查询信息
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
			
			//异常处理
			try {
				//设置目标id，系统日志需要
				session.setAttribute("target_id", id);
				
				//执行查询
				josn.data=isys_codingService.getWhereId(id);
				josn.msg="查询成功!";
				josn.state=200;
			} catch (Exception e) {
				flag_bug=1;
				josn.msg=e.getMessage();
				josn.state=500;
			}
			
			//系统日志
			LogUtils.jieLuSystemLog(request,LogUtils.SELECT,flag_bug,
					"根据编码信息id标识查询信息",
					josn.msg,session,isys_system_logService);
			//反馈
			return josn;
	}
	
	
	/**
	 * 
	 * 点击是否预排，如果是。为(all/所有)该医院下的设备新增预排。
	 * 点击是否预排，如果否。把(all/所有)该医院下的设备预排”隐藏”。
	 * @author lingfe     
	 * @created 2019年4月10日 上午10:12:22  
	 * @param id
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/flag_yp", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public JosnModel<Object> flag_yp(
	    		@RequestParam(value="id",required=false)String id,
	    		@RequestParam(value="flag_yp",required=false)Integer flag_yp,
	    		@RequestParam(value="yp_name",required=false)String yp_name,
	    		@RequestParam(value="yp_cycle",required=false)Integer yp_cycle,
	    		@RequestParam(value="next_time",required=false)Date next_time,
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
			}
			
			//异常处理
			try {
				//设置目标id，系统日志需要
				session.setAttribute("target_id", id);
				
				//根据id查询
				Tab_coding tab=isys_codingService.getWhereId(id);
				if(tab!=null){
					//更新值
					tab.setFlag_yp(flag_yp);
					tab.setYp_name(yp_name);
					tab.setYp_cycle(yp_cycle);
					tab.setNext_time(next_time);
					//执行修改
					isys_codingService.update(tab);
				}
				
				//执行查询
				josn.data=isys_codingService.getWhereId(id);
				josn.msg="查询成功!";
				josn.state=200;
			} catch (Exception e) {
				flag_bug=1;
				josn.msg=e.getMessage();
				josn.state=500;
			}
			
			//系统日志
			LogUtils.jieLuSystemLog(request,LogUtils.SELECT,flag_bug,
					"点击是否预排",
					josn.msg,session,isys_system_logService);
			//反馈
			return josn;
	}
	
	

	/**
	 * 
	 * 根据条件查询编码信息
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
	    		@RequestParam(value="hospital_id",required=false)String hospital_id,
	    		@RequestParam(value="coding_type",required=false)Integer coding_type,
	    		@RequestParam(value="flag_yp",required=false)Integer flag_yp,
	    		HttpServletRequest request,
	    		HttpSession session){
			//实例化对象
			JosnModel<Object> josn=new JosnModel<Object>();
			Map<String, Object>  map = new HashMap<>();
			
			try {
				//验证非空
				if(hospital_id!=null&&!"".equals(hospital_id)){
					map.put("hospital_id", hospital_id);
				}
				if(coding_type!=null){
					map.put("coding_type", coding_type);
				}
				if(flag_yp!=null){
					map.put("flag_yp", flag_yp);
				}
				
				//table表
				map.put("table", "sys_coding");
				
				josn.data=isys_codingService.getWhere(map);
				josn.msg="查询成功!";
				josn.state=200;
				
			} catch (Exception e) {
				flag_bug=1;
				josn.msg=e.getMessage();
				josn.state=500;
			}
			//系统日志
			LogUtils.jieLuSystemLog(request,LogUtils.SELECT,flag_bug,
					"根据条件查询编码信息",
					josn.msg,session,isys_system_logService);
			
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
	    		@RequestParam(value="coding_type",required=false)String coding_type,
	    		HttpServletRequest request,
	    		HttpSession session){
			//实例化对象
			JosnModel<Object> josn=new JosnModel<Object>();
			Map<String, Object> map=new HashMap<String, Object>();
			
			Object creator=session.getAttribute("userid");
			if(SYS_GET.IS_SESSION_VALIDATE){
				if(StringUtils.isEmpty(creator)){
					josn.msg="会话过期!请重新登录";
					return josn;
				}
			}else{
				creator="游客";
			}
			 			
			try {
				//执行查询
				Tab_coding tab=isys_codingService.getWhereId(id);
				if(tab!=null){
					//替换位置
					map.put("table", "sys_coding");
					map.put("coding_type", coding_type);
					for (int i = 1; i > 0;i++) {
						map.put("sort", tab.getSort()-i);
						List<Tab_coding> list=isys_codingService.getWhere(map);
						if(list!=null&&list.size()!=0){
							Tab_coding tab2=list.get(0);
							tab2.setSort(tab.getSort());
							tab2.setModify_date(new Date());
							tab2.setModify_code(creator.toString());
							tab2.setVersion(String.valueOf(Integer.parseInt(tab.getVersion())+1));
							isys_codingService.update(tab2);
							break;
						}else{
							map.put("sort", null);
							int count=isys_codingService.getCount(map);
							if(count<=1||tab.getSort()-i<=1){
								break;
							}
						}
					}
					
					//更新赋值
					tab.setModify_date(new Date());
					tab.setModify_code(creator.toString());
					tab.setVersion(String.valueOf(Integer.parseInt(tab.getVersion())+1));
					tab.setSort(tab.getSort()-1);
					
					//执行修改
					isys_codingService.update(tab);
					josn.state=200;
					josn.msg="操作成功!";
				}else{
					josn.msg="id无效!";
				}
			} catch (Exception e) {
				flag_bug=1;
				josn.msg=e.getMessage();
				josn.state=500;
			}
			//系统日志
			LogUtils.jieLuSystemLog(request,LogUtils.SELECT,flag_bug,
					"降",
					josn.msg,session,isys_system_logService);
			
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
			Map<String, Object> map=new HashMap<String, Object>();
			
			Object creator=session.getAttribute("userid");
			if(SYS_GET.IS_SESSION_VALIDATE){
				if(StringUtils.isEmpty(creator)){
					josn.msg="会话过期!请重新登录";
					return josn;
				}
			}else{
				creator="游客";
			}
			 
			try {
				//执行查询
				Tab_coding tab=isys_codingService.getWhereId(id);
				if(tab!=null){
					//替换位置
					map.put("table", "sys_coding");
					for (int i = 1; i > 0;i++) {
						map.put("sort", tab.getSort()+i);
						List<Tab_coding> list=isys_codingService.getWhere(map);
						if(list!=null&&list.size()!=0){
							Tab_coding tab2=list.get(0);
							tab2.setSort(tab.getSort());
							tab2.setModify_date(new Date());
							tab2.setModify_code(creator.toString());
							tab2.setVersion(String.valueOf(Integer.parseInt(tab.getVersion())+1));
							isys_codingService.update(tab2);
							break;
						}else{
							map.put("sort", null);
							int count=isys_codingService.getCount(map);
							if(count<=1||tab.getSort()+i>=count){
								break;
							}
						}
					}
					
					//更新赋值
					tab.setModify_date(new Date());
					tab.setModify_code(creator.toString());
					tab.setVersion(String.valueOf(Integer.parseInt(tab.getVersion())+1));
					tab.setSort(tab.getSort()+1);
					//执行修改
					isys_codingService.update(tab);
					josn.state=200;
					josn.msg="操作成功!";
				}else{
					josn.msg="id无效!";
				}
			} catch (Exception e) {
				flag_bug=1;
				josn.msg=e.getMessage();
				josn.state=500;
			}
			//系统日志
			LogUtils.jieLuSystemLog(request,LogUtils.ADD,flag_bug,
					"升",
					josn.msg,session,isys_system_logService);
			
			return josn;
	}
	

	/**
	 * 
	 * 修改编码信息
	 * @author lingfe     
	 * @created 2019年3月20日 上午11:03:23  
	 * @param coding
	 * @return
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public JosnModel<Object> update(Tab_coding coding,
    		HttpServletRequest request,HttpSession session){
		//实例化对象
		JosnModel<Object> josn=new JosnModel<Object>();
		Object creator=session.getAttribute("userid");
		if(SYS_GET.IS_SESSION_VALIDATE){
			if(StringUtils.isEmpty(creator)){
				josn.msg="会话过期!请重新登录";
				return josn;
			}
		}else{
			creator="游客";
		}
		
		//验证非空
		if(coding!=null){
			//设置目标id，系统日志需要
			session.setAttribute("target_id", coding.getId());
			if(coding.getId()!=null){
				//执行查询,得到数据
				Tab_coding tab_coding=isys_codingService.getWhereId(coding.getId());
				if(tab_coding!=null){
					//更新数据
					tab_coding.setModify_code(creator.toString());
					tab_coding.setModify_date(new Date());
					tab_coding.setVersion(String.valueOf(Integer.parseInt(tab_coding.getVersion())+1));
					tab_coding.setCoding_name(coding.getCoding_name());
					tab_coding.setCoding_type(coding.getCoding_type());
					tab_coding.setRemark(coding.getRemark());
					tab_coding.setState(coding.getState());
					
					//赋值是否预排信息
					tab_coding.setFlag_yp(coding.getFlag_yp());
					tab_coding.setYp_cycle(coding.getYp_cycle());
					tab_coding.setYp_name(coding.getYp_name());
					tab_coding.setNext_time(coding.getNext_time());
					
					//赋值是否停用信息
					tab_coding.setStop_flag(coding.getStop_flag());
					tab_coding.setStop_code(creator.toString());
					tab_coding.setStop_date(new Date());
					tab_coding.setStop_explain(coding.getStop_explain());
					
					
					try {
						//执行更新
						int tt=isys_codingService.update(tab_coding);
						if(tt>=1){
							josn.state=200;
							josn.msg="修改成功!";
							josn.data=tab_coding;
						}else{
							josn.msg="修改失败!";
						}
					} catch (Exception e) {
						flag_bug=1;
						josn.msg=e.getMessage();
						josn.state=500;
					}
					//系统日志
					LogUtils.jieLuSystemLog(request,LogUtils.ADD,flag_bug,
							"修改编码数据",
							josn.msg,session,isys_system_logService);
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
	 * 修改编码信息所需字段
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
			@RequestParam(value="flag_yp",required=false)Integer flag_yp,
			@RequestParam(value="yp_name",required=false)String yp_name,
			@RequestParam(value="yp_cycle",required=false)Integer yp_cycle,
			@RequestParam(value="next_time",required=false)Date next_time,
    		HttpServletRequest request,HttpSession session){
		//实例化对象
		JosnModel<Object> josn=new JosnModel<Object>();
		Object creator=session.getAttribute("userid");
		if(SYS_GET.IS_SESSION_VALIDATE){
			if(StringUtils.isEmpty(creator)){
				josn.msg="会话过期!请重新登录";
				return josn;
			}
		}else{
			creator="游客";
		}
		
		//验证非空
		if(id!=null){
			//设置目标id，系统日志需要
			session.setAttribute("target_id", id);
			if(flag_yp!=null){
				//执行查询,得到数据
				Tab_coding tab=isys_codingService.getWhereId(id);
				if(tab!=null){
					//更新数据
					tab.setModify_code(creator.toString());
					tab.setModify_date(new Date());
					tab.setFlag_yp(flag_yp);
					tab.setYp_name(yp_name);
					tab.setYp_cycle(yp_cycle);
					tab.setNext_time(next_time);
					
					try {
						//执行更新
						int tt=isys_codingService.update(tab);
						if(tt>=1){
							josn.state=200;
							josn.msg="修改成功!";
							josn.data=tab;
						}else{
							josn.msg="修改失败!";
						}
					} catch (Exception e) {
						flag_bug=1;
						josn.msg=e.getMessage();
						josn.state=500;
					}
					//系统日志
					LogUtils.jieLuSystemLog(request,LogUtils.ADD,flag_bug,
							"修改编码信息所需字段",
							josn.msg,session,isys_system_logService);
				}else{
					josn.msg="id无效!请检查!";
				}
			}else{
				josn.msg="参数有误!请检查";
			}
		}else{
			josn.msg="id不能为空!";
		}
		
		return josn;
	}
	
	
	/**
	 * 
	 * 分页请求编码信息
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
			@RequestParam(value="coding_type",required=false)Integer coding_type,
			@RequestParam(value="pageIndex",required=false,defaultValue="1")Integer pageIndex,
			@RequestParam(value="pageNum",required=false,defaultValue="10")Integer pageNum,
			@RequestParam(value="coding_name",required=false)String coding_name,
			@RequestParam(value="stop_flag",required=false)Integer stop_flag,
			HttpServletRequest request,HttpSession session){
		//实例化对象
		JosnModel<Object> josn=new JosnModel<Object>();
		Map<String, Object>  map = new HashMap<>();
		PageModel<Tab_coding> page=new PageModel<Tab_coding>();
		
		//验证非空
		if(!StringUtils.isEmpty(coding_type)){
			//赋值参数
			if(coding_type!=null){
				map.put("coding_type", coding_type);
			}
			if(coding_name!=null&&!"".equals(coding_name)){
				map.put("coding_name", coding_name);
			}
			if(stop_flag!=null){
				map.put("stop_flag", stop_flag);
			}
			map.put("pageIndex", pageIndex);
			map.put("pageNum",pageNum);
			page.setPageIndex(pageIndex);
			page.setPageNum(pageNum);
			
			
			//设置表:编码信息表
			map.put("table", "sys_coding");
			
			//得到总数据量
			int numCount=isys_codingService.getCount(map);
			page.setNumCount(numCount);
			
			try {
				//得到数据
				List<Tab_coding> list=isys_codingService.pageSelect(map);
				//验证非空
				if(list!=null){
					for (Tab_coding tab_coding : list) {
						//得到创建人名称
						Tab_user_info info=iuserinfoService.getWhereId(tab_coding.getCrt_code());
						if(info!=null){
							tab_coding.setCrt_name(info.getUsername());
						}
						//得到修改人名称
						info=iuserinfoService.getWhereId(tab_coding.getModify_code());
						if(info!=null){
							tab_coding.setModify_name(info.getUsername());
						}
						
						//验证是否停用
						if(tab_coding.getStop_flag()==0){
							//得到停用人
							info=iuserinfoService.getWhereId(tab_coding.getStop_code());
							if(info!=null){
								tab_coding.setStop_name(info.getUsername());
							}
						}else{
							tab_coding.setStop_name(null);
							tab_coding.setStop_date(null);
							tab_coding.setStop_explain(null);
							tab_coding.setStop_code(null);
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
				flag_bug=1;
				josn.msg=e.getMessage();
				josn.state=500;
			}
			//系统日志
			LogUtils.jieLuSystemLog(request,LogUtils.SELECT,flag_bug,
					"分页查询编码信息",
					josn.msg,session,isys_system_logService);
		}else{
			josn.msg="编码类型不能为空!";
		}
		
		return josn;
	}
	
	
	
	/**
	 * 
	 * 保存编码数据
	 * @author lingfe     
	 * @created 2019年3月19日 下午4:35:32  
	 * @param coding
	 * @return
	 */
	@RequestMapping(value = "/save", method = { RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public JosnModel<Tab_coding> save(Tab_coding coding,
    		HttpServletRequest request,HttpSession session){
		//实例化对象
		JosnModel<Tab_coding> josn=new JosnModel<Tab_coding>();
		//得到userId
		Object creator=session.getAttribute("userid");
		if(SYS_GET.IS_SESSION_VALIDATE){
			if(StringUtils.isEmpty(creator)){
				josn.msg="会话过期!请重新登录";
				return josn;
			}else{
				coding.setCrt_code(creator.toString());
			}
		}else{
			creator=GetIpUtil.getIpAddr(request);
		}
		
		//验证非空
		if(!StringUtils.isEmpty(coding.getCoding_name())){
			//赋值
			coding.setId(UUID.randomUUID().toString().replace("-", ""));
			coding.setCrt_date(new Date());
			coding.setStop_flag(1);
			
			try {
				
				//执行查询是否已经存在相同的编码名称
				Tab_coding tab=isys_codingService.getWhereCodingName(coding.getCoding_name());
				if(tab == null){
					//执行保存
					int tt=isys_codingService.save(coding);
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
				flag_bug=1;
				josn.state=500;
				josn.msg=e.getMessage();
			}
			//系统日志
			LogUtils.jieLuSystemLog(request,LogUtils.ADD,flag_bug,
					"保存编码数据",
					josn.msg,session,isys_system_logService);
		}else{
			josn.msg="编码名称不能为空!";
		}
		
		return josn;
	}
	
    
    /**
     * 
     * 根据编码表id标识删除数据
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

    	//得到userId
    	Object creator=session.getAttribute("userid");
		if(SYS_GET.IS_SESSION_VALIDATE){
			if(StringUtils.isEmpty(creator)){
				josn.msg="会话过期!请重新登录";
				return josn;
			}
		}
		
    	//验证非空
    	if(!StringUtils.isEmpty(id)){
    		try {
    			//执行删除
    			int tt=isys_codingService.deleteWhereId(id);
				if(tt>=1){
					//设置目标id，系统日志需要
					session.setAttribute("target_id", id);
					josn.msg="删除成功!";
					josn.state=200;
				}else{
					josn.msg="删除失败!不存在或者已经被删除!";
				}
			} catch (Exception e) {
				flag_bug=1;
				josn.msg=e.getMessage();
				josn.state=500;
			}
			//系统日志
			LogUtils.jieLuSystemLog(request,LogUtils.DELETE,flag_bug,
					"根据编码表id标识删除数据",
					josn.msg,session,isys_system_logService);
    	}else{
    		josn.msg="id不能为空!";
    	}
    	
    	return josn;
    }
    
}
