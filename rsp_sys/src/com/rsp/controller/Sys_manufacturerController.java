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
import com.rsp.model.Tab_sys_manufacturer;
import com.rsp.model.Tab_system_log;
import com.rsp.model.Tab_user_info;
import com.rsp.service.Isys_manufacturerService;
import com.rsp.service.Isys_system_logService;
import com.rsp.service.IuserinfoService;

/**
 * 
  * 文件名：ManufacturerController.java
  * 描述： 生产厂家数据请求表示层
  * 修改人： lingfe
  * 修改时间：2019年3月22日 下午2:58:27
  * 修改内容：
 */
@Controller
@RequestMapping("/manufacturer")
public class Sys_manufacturerController {

	//自动装配
	@Autowired
	private Isys_manufacturerService isys_manufacturerService;
	
	//用户信息
	@Autowired
	private IuserinfoService iuserinfoService;
	
	//系统日志
	@Autowired
	private Isys_system_logService isys_system_logService;
		
	/**
	 * 是否有异常bug
	 */
	private int flag_bug=0;

	/**
	 * 
	 * 分页查询生产厂家信息
	 * @author lingfe     
	 * @created 2019年3月22日 下午3:29:53  
	 * @param pageIndex	当前页
	 * @param pageNum	页容量
	 * @param manufacturer_name	生产厂家名称
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
			@RequestParam(value="manufacturer_name",required=false)String manufacturer_name,
			@RequestParam(value="state",required=false)Integer state,
	    		HttpServletRequest request,
	    		HttpSession session){
			//实例化对象
			JosnModel<Object> josn=new JosnModel<Object>();
			Map<String, Object>  map = new HashMap<>();
			PageModel<Tab_sys_manufacturer> page=new PageModel<Tab_sys_manufacturer>();
			
			try {
				//验证非空
				if(manufacturer_name!=null){
					map.put("manufacturer_name", manufacturer_name);
				}
				if(state!=null){
					map.put("state", state);
				}
				
				//赋值参数
				map.put("pageIndex", pageIndex);
				map.put("pageNum",pageNum);
				page.setPageIndex(pageIndex);
				page.setPageNum(pageNum);
				
				//设置表:编码信息表
				map.put("table", "sys_manufacturer");
				
				//得到总数据量
				int numCount=isys_manufacturerService.getCount(map);
				page.setNumCount(numCount);
				
				//得到数据
				List<Tab_sys_manufacturer> list=isys_manufacturerService.pageSelect(map);
				//验证非空
				if(list!=null){
					for (Tab_sys_manufacturer manufacturer : list) {
						//得到创建人名称
						if(!StringUtils.isEmpty(manufacturer.getCrt_code())){
							Tab_user_info info=iuserinfoService.getWhereId(manufacturer.getCrt_code());
							if(info!=null){
								manufacturer.setCrt_name(info.getUsername());
							}
						}
						
						//得到修改人名称
						if(!StringUtils.isEmpty(manufacturer.getModify_code())){
							Tab_user_info info=iuserinfoService.getWhereId(manufacturer.getModify_code());
							if(info!=null){
								manufacturer.setCrt_name(info.getUsername());
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
				flag_bug=1;
				josn.msg=e.getMessage();
				josn.state=500;
			}
			//系统日志
			LogUtils.jieLuSystemLog(request,LogUtils.UPDATE,flag_bug,
									"分页获取生产厂家信息",
									josn.msg,session,isys_system_logService);
			
			return josn;
		}
	

	/**
	 * 
	 * 根据id标识修改生产厂家信息
	 * @author lingfe     
	 * @created 2019年3月22日 下午3:22:14  
	 * @param man
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public JosnModel<Object> update(
				Tab_sys_manufacturer man,
	    		HttpServletRequest request,
	    		HttpSession session){
			//实例化对象
			JosnModel<Object> josn=new JosnModel<Object>();
			Tab_system_log sysLog=new Tab_system_log();
			//系统日志
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
				//验证非空
				if(man!=null){
					//执行查询
					Tab_sys_manufacturer manufacturer=isys_manufacturerService.getWhereId(man.getId());
					if(manufacturer!=null){
						session.setAttribute("target_id", man.getId());
						//赋值
						manufacturer.setManufacturer_name(man.getManufacturer_name());
						manufacturer.setRemark(man.getRemark());
						manufacturer.setVersion(String.valueOf(Integer.parseInt(manufacturer.getVersion())+1));
						manufacturer.setState(man.getState());
						
						//停用信息
						if(man.getFlag_stop()==0){
							manufacturer.setFlag_stop(man.getFlag_stop());
							manufacturer.setStop_explain(man.getStop_explain());
							manufacturer.setStop_code(creator.toString());
							manufacturer.setCrt_date(new Date());
						}
						
						//修改人信息
						manufacturer.setModify_code(sysLog.getCreator());
						manufacturer.setModify_date(new Date());
						
						//执行更新
						int tt=isys_manufacturerService.update(manufacturer);
						if(tt>=1){
							josn.msg="修改成功!";
							josn.state=200;
							josn.data=manufacturer;
						}else{
							josn.msg="修改失败";
						}
					}else{
						josn.msg="id无效或者已经不存在了!";
					}
				}else{
					josn.msg="请填写信息修改!";
				}
				
			} catch (Exception e) {
				flag_bug=1;
				josn.msg=e.getMessage();
				josn.state=500;
			}
			//系统日志
			LogUtils.jieLuSystemLog(request,LogUtils.UPDATE,flag_bug,
									"根据id标识修改生产厂家信息",
									josn.msg,session,isys_system_logService);
			
			return josn;
		}
	
	
	/**
	 * 
	 * 根据id标识删除生产厂家信息
	 * @author lingfe     
	 * @created 2019年3月22日 下午3:10:18  
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
			
			try {
				//验证非空
				if(!StringUtils.isEmpty(id)){
					//执行删除
					int tt=isys_manufacturerService.deleteWhereId(id);
					if(tt>=1){
						josn.msg="删除成功!";
						josn.state=200;
						session.setAttribute("target_id", id);
					}else{
						josn.msg="删除失败!";
					}
				}else{
					josn.msg="id不能为空!";
				}
				
			} catch (Exception e) {
				flag_bug=1;
				josn.msg=e.getMessage();
				josn.state=500;
			}
			//系统日志
			LogUtils.jieLuSystemLog(request,LogUtils.ADD,flag_bug,
									"根据id删除生产厂家信息",
									josn.msg,session,isys_system_logService);
			
			return josn;
		}

	
	
	/**
	 * 
	 * 保存生产厂家信息
	 * @author lingfe     
	 * @created 2019年3月22日 下午3:06:08  
	 * @param name 名称
	 * @param remark	备注
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/save", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public JosnModel<Object> save(
	    		@RequestParam(value="manufacturer_name",required=false)String manufacturer_name,
	    		@RequestParam(value="remark",required=false)String remark,
	    		HttpServletRequest request,
	    		HttpSession session){
			//实例化对象
			JosnModel<Object> josn=new JosnModel<Object>();
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
				if(!StringUtils.isEmpty(manufacturer_name)){
					//赋值
					Tab_sys_manufacturer man=new Tab_sys_manufacturer();
					man.setId(UUID.randomUUID().toString().replace("-", ""));
					man.setManufacturer_name(manufacturer_name);
					man.setRemark(remark);
					
					//创建人信息
					man.setCrt_code(creator.toString());
					man.setCrt_date(new Date());
					
					//执行保存
					int tt=isys_manufacturerService.save(man);
					if(tt>=1){
						josn.msg="保存成功!";
						josn.state=200;
					}else{
						josn.msg="保存失败!";
					}
				}else{
					josn.msg="生产厂家名称不能为空!";
				}
				
			} catch (Exception e) {
				flag_bug=1;
				josn.msg=e.getMessage();
				josn.state=500;
			}
			//系统日志
			LogUtils.jieLuSystemLog(request,LogUtils.ADD,flag_bug,
									"保存生产厂家信息",
									josn.msg,session,isys_system_logService);
			
			return josn;
		}

}
