package com.rsp.controller;

import java.util.List;
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
import com.rsp.model.JosnModel;
import com.rsp.model.Tab_china;
import com.rsp.model.Tab_system_log;
import com.rsp.service.IchinaService;
import com.rsp.service.Isystem_logService;

/**
 * 
  * 文件名：ChinaController.java
  * 描述：区域信息表数据请求访问表示层 
  * 修改人： lingfe
  * 修改时间：2019年3月22日 上午9:36:14
  * 修改内容：
 */
@Controller
@RequestMapping("/china")
public class ChinaController {

	//自动装配
	@Autowired
	private IchinaService ichinaService;
	
	//系统日志
	@Autowired
	private Isystem_logService isystem_logService;
	
	/**
	 * 
	 * 根据区域信息id标识删除区域信息
	 * @author lingfe     
	 * @created 2019年3月22日 上午10:28:29  
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
			sysLog.setModel_name("根据区域信息id标识删除区域信息,"+request.getRequestURI());
			Object creator=session.getAttribute("userid");
			if(!StringUtils.isEmpty(creator)){
				sysLog.setCreator(creator.toString());
			}
			sysLog.setModify(sysLog.getCreator());
			sysLog.setOperation_type(2);
			
			try {
				//验证非空
				if(!StringUtils.isEmpty(id)){
					//执行查询
					List<Tab_china> list=ichinaService.selectWhwerePid(id);
					if(null == list ||list.size()<=0){
						//执行删除
						int tt=ichinaService.deleteWhereId(id);
						if(tt>=1){
							josn.state=200;
							josn.msg="删除成功!";
						}else{
							josn.msg="删除失败!该id不存在或者已经被删除!";
						}
					}else{
						josn.msg="该数据下还有其他子级,不能删除!";
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
	 * 模糊查询区域信息
	 * @author lingfe     
	 * @created 2019年3月22日 上午10:22:24  
	 * @param name	区域名称
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/selectLike", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public JosnModel<Object> selectLike(
	    		@RequestParam(value="name",required=false)String name,
	    		HttpServletRequest request,
	    		HttpSession session){
			//实例化对象
			JosnModel<Object> josn=new JosnModel<Object>();
			Tab_system_log sysLog=new Tab_system_log();
			//系统日志
			sysLog.setIp(GetIpUtil.getIpAddr(request));
			sysLog.setModel_name("模糊查询区域信息,"+request.getRequestURI());
			Object creator=session.getAttribute("userid");
			if(!StringUtils.isEmpty(creator)){
				sysLog.setCreator(creator.toString());
			}
			sysLog.setModify(sysLog.getCreator());
			sysLog.setOperation_type(2);
			
			try {
				//验证非空
				if(!StringUtils.isEmpty(name)){
					//执行查询
					List<Tab_china> list=ichinaService.selectLike(name);
					josn.data=list;
					josn.state=200;
					josn.msg="查询成功!";
				}else{
					josn.msg="区域名称不能为空!";
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
	 * 保存区域信息
	 * @author lingfe     
	 * @created 2019年3月22日 上午10:19:15  
	 * @param pid
	 * @param name 区域名称
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/save", method = { RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
	public JosnModel<Object> save(
    		@RequestParam(value="pid",required=false,defaultValue="0")String pid,
    		@RequestParam(value="name",required=false)String name,
    		HttpServletRequest request,
    		HttpSession session){
		//实例化对象
		JosnModel<Object> josn=new JosnModel<Object>();
		Tab_china china=new Tab_china();
		Tab_system_log sysLog=new Tab_system_log();
		//系统日志
		sysLog.setIp(GetIpUtil.getIpAddr(request));
		sysLog.setModel_name("保存区域信息,"+request.getRequestURI());
		Object creator=session.getAttribute("userid");
		if(!StringUtils.isEmpty(creator)){
			sysLog.setCreator(creator.toString());
		}
		sysLog.setModify(sysLog.getCreator());
		sysLog.setOperation_type(4);
		
		try {
			//验证非空
			if(!StringUtils.isEmpty(pid)){
				if(!StringUtils.isEmpty(name)){
					//执行搜索
					List<Tab_china> list=ichinaService.selectWhwerePid(pid);
					if(list!=null){
						//赋值
						china.setId(UUID.randomUUID().toString().replace("-", ""));
						china.setPid(pid);
						china.setName(name);
						china.setCreator(sysLog.getCreator());
						china.setModify(sysLog.getCreator());
						
						//执行保存
						int tt=ichinaService.save(china);
						if(tt>=1){
							josn.state=200;
							josn.msg="保存成功!";
							josn.data=china;
						}else{
							josn.msg="保存失败!";
						}
					}else{
						josn.msg="pid无效或者已经不存在!";
					}
				}else{
					josn.msg="区域名称不能为空!";
				}
			}else{
				josn.msg="pid不能为空!";
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
	 * 根据pid查询区域信息
	 * @author lingfe     
	 * @created 2019年3月22日 上午9:39:53  
	 * @param pid	
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/selectWherePid", method = { RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public JosnModel<Object> selectWherePid(
    		@RequestParam(value="pid",required=false,defaultValue="0")String pid,
    		HttpServletRequest request,
    		HttpSession session){
		//实例化对象
		JosnModel<Object> josn=new JosnModel<Object>();
		Tab_system_log sysLog=new Tab_system_log();
		//系统日志
		sysLog.setIp(GetIpUtil.getIpAddr(request));
		sysLog.setModel_name("根据pid查询区域信息,"+request.getRequestURI());
		Object creator=session.getAttribute("userid");
		if(!StringUtils.isEmpty(creator)){
			sysLog.setCreator(creator.toString());
		}
		sysLog.setModify(sysLog.getCreator());
		sysLog.setOperation_type(2);
		
		try {
			//验证非空
			if(!StringUtils.isEmpty(pid)){
				//执行查询
				List<Tab_china> list=ichinaService.selectWhwerePid(pid);
				if(list!=null){
					josn.state=200;
					josn.msg="请求成功!";
					josn.data=list;
				}else{
					josn.msg="pid无效或者已经不存在!请检查";
				}
			}else{
				josn.msg="pid不能为空!";
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
