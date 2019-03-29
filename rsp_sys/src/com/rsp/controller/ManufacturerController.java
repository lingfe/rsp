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
import com.rsp.model.Tab_manufacturer;
import com.rsp.model.Tab_system_log;
import com.rsp.model.Tab_user_info;
import com.rsp.service.ImanufacturerService;
import com.rsp.service.Isystem_logService;
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
public class ManufacturerController {

	//自动装配
	@Autowired
	private ImanufacturerService imanufacturerService;
	
	//系统日志
	@Autowired
	private Isystem_logService isystem_logService;
	
	//用户信息
	@Autowired
	private IuserinfoService iuserinfoService;
	
	

	/**
	 * 
	 * 分页查询生产厂家信息
	 * @author lingfe     
	 * @created 2019年3月22日 下午3:29:53  
	 * @param pageIndex	当前页
	 * @param pageNum	页容量
	 * @param name	生产厂家名称
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
			@RequestParam(value="name",required=false)String name,
			@RequestParam(value="state",required=false)Integer state,
	    		HttpServletRequest request,
	    		HttpSession session){
			//实例化对象
			JosnModel<Object> josn=new JosnModel<Object>();
			Map<String, Object>  map = new HashMap<>();
			PageModel<Tab_manufacturer> page=new PageModel<Tab_manufacturer>();
			Tab_system_log sysLog=new Tab_system_log();
			
			//系统日志
			sysLog.setIp(GetIpUtil.getIpAddr(request));
			sysLog.setModel_name("分页查询生产厂家信息,"+request.getRequestURI());
			Object creator=session.getAttribute("userid");
			if(!StringUtils.isEmpty(creator)){
				sysLog.setCreator(creator.toString());
			}
			sysLog.setModify(sysLog.getCreator());
			sysLog.setOperation_type(1);
			
			try {
				//验证非空
				//赋值参数
				if(name!=null){
					map.put("name", name);
				}
				if(state!=null){
					map.put("state", state);
				}
				map.put("pageIndex", pageIndex);
				map.put("pageNum",pageNum);
				page.setPageIndex(pageIndex);
				page.setPageNum(pageNum);
				
				//设置表:编码信息表
				map.put("table", "manufacturer");
				
				//得到总数据量
				int numCount=imanufacturerService.getCount(map);
				page.setNumCount(numCount);
				
				//得到数据
				List<Tab_manufacturer> list=imanufacturerService.pageSelect(map);
				//验证非空
				if(list!=null){
					for (Tab_manufacturer manufacturer : list) {
						//得到创建人名称
						if(!StringUtils.isEmpty(manufacturer.getCreator())){
							if(!"游客".equals(manufacturer.getCreator())){
								Tab_user_info info=iuserinfoService.getWhereId(manufacturer.getCreator());
								if(info!=null){
									manufacturer.creator_name=info.getUsername();
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
				Tab_manufacturer man,
	    		HttpServletRequest request,
	    		HttpSession session){
			//实例化对象
			JosnModel<Object> josn=new JosnModel<Object>();
			Tab_system_log sysLog=new Tab_system_log();
			//系统日志
			sysLog.setIp(GetIpUtil.getIpAddr(request));
			sysLog.setModel_name("根据id标识修改生产厂家信息,"+request.getRequestURI());
			Object creator=session.getAttribute("userid");
			if(!StringUtils.isEmpty(creator)){
				sysLog.setCreator(creator.toString());
			}
			sysLog.setModify(sysLog.getCreator());
			sysLog.setOperation_type(2);
			
			try {
				//验证非空
				if(man!=null){
					//执行查询
					Tab_manufacturer manufacturer=imanufacturerService.getWhereId(man.getId());
					if(manufacturer!=null){
						sysLog.setTarget_id(man.getId());
						//赋值
						manufacturer.setModify(sysLog.getCreator());
						manufacturer.setName(man.getName());
						manufacturer.setRemark(man.getRemark());
						manufacturer.setMdate(new Date());
						manufacturer.setVersion(String.valueOf(Integer.parseInt(manufacturer.getVersion())+1));
						manufacturer.setState(man.getState());
						
						//执行更新
						int tt=imanufacturerService.update(manufacturer);
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
			Tab_system_log sysLog=new Tab_system_log();
			//系统日志
			sysLog.setIp(GetIpUtil.getIpAddr(request));
			sysLog.setModel_name("根据id标识删除生产厂家信息,"+request.getRequestURI());
			Object creator=session.getAttribute("userid");
			if(!StringUtils.isEmpty(creator)){
				sysLog.setCreator(creator.toString());
			}
			sysLog.setModify(sysLog.getCreator());
			sysLog.setOperation_type(3);
			
			try {
				//验证非空
				if(!StringUtils.isEmpty(id)){
					//执行删除
					int tt=imanufacturerService.deleteWhereId(id);
					if(tt>=1){
						josn.msg="删除成功!";
						josn.state=200;
						sysLog.setTarget_id(id);
					}else{
						josn.msg="删除失败!";
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
	    		@RequestParam(value="name",required=false)String name,
	    		@RequestParam(value="remark",required=false)String remark,
	    		HttpServletRequest request,
	    		HttpSession session){
			//实例化对象
			JosnModel<Object> josn=new JosnModel<Object>();
			Tab_system_log sysLog=new Tab_system_log();
			//系统日志
			sysLog.setIp(GetIpUtil.getIpAddr(request));
			sysLog.setModel_name("保存生产厂家信息,"+request.getRequestURI());
			Object creator=session.getAttribute("userid");
			if(!StringUtils.isEmpty(creator)){
				sysLog.setCreator(creator.toString());
			}
			sysLog.setModify(sysLog.getCreator());
			sysLog.setOperation_type(4);
			
			try {
				//验证非空
				if(!StringUtils.isEmpty(name)){
					//赋值
					Tab_manufacturer man=new Tab_manufacturer();
					man.setName(name);
					man.setCreator(sysLog.getCreator());
					man.setModify(man.getCreator());
					man.setRemark(remark);
					
					//执行保存
					int tt=imanufacturerService.save(man);
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
