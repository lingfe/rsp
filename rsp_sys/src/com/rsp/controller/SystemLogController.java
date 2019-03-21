package com.rsp.controller;

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
import com.rsp.model.Tab_coding;
import com.rsp.model.Tab_system_log;
import com.rsp.model.Tab_user_info;
import com.rsp.service.Isystem_logService;
import com.rsp.service.IuserinfoService;

/**
 * 
  * 文件名：SystemLogController.java
  * 描述：  系统日志数据请求表示层
  * 修改人： lingfe
  * 修改时间：2019年3月21日 下午4:31:57
  * 修改内容：
 */
@Controller
@RequestMapping("/syslog")
public class SystemLogController {
	
	//自动装配
	
	//系统日志
	@Autowired
	private Isystem_logService isystem_logService;
	
	//用户信息
	@Autowired
	private IuserinfoService iuserinfoService;
	
	/**
	 * 
	 * 分页请求系统日志信息
	 * @author lingfe     
	 * @created 2019年3月21日 下午4:34:41  
	 * @param pageIndex 当前页
	 * @param pageNum 页容量
	 * @param state 状态
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getPage", method = { RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
	public JosnModel<Object> getPage(
			@RequestParam(value="pageIndex",required=false,defaultValue="1")Integer pageIndex,
			@RequestParam(value="pageNum",required=false,defaultValue="10")Integer pageNum,
			@RequestParam(value="state",required=false)Integer state,
			HttpServletRequest request,HttpSession session){
		//实例化对象
		JosnModel<Object> josn=new JosnModel<Object>();
		Map<String, Object>  map = new HashMap<>();
		PageModel<Tab_system_log> page=new PageModel<Tab_system_log>();
		Tab_system_log sysLog=new Tab_system_log();
		
		//系统日志
		sysLog.setIp(GetIpUtil.getIpAddr(request));
		sysLog.setModel_name("分页请求系统日志信息,"+request.getRequestURI());
		Object creator=session.getAttribute("userid");
		if(!StringUtils.isEmpty(creator)){
			sysLog.setCreator(creator.toString());
		}
		sysLog.setModify(sysLog.getCreator());
		sysLog.setOperation_type(1);
		
		//赋值参数
		if(state!=null){
			map.put("state", state);
		}
		map.put("pageIndex", pageIndex);
		map.put("pageNum",pageNum);
		page.setPageIndex(pageIndex);
		page.setPageNum(pageNum);
		
		
		//设置表:编码信息表
		map.put("table", "system_log");
		
		//得到总数据量
		int numCount=isystem_logService.getCount();
		page.setNumCount(numCount);
		
		try {
			//得到数据
			List<Tab_system_log> list=isystem_logService.pageSelect(map);
			//验证非空
			if(list!=null){
				for (Tab_system_log tab_system_log : list) {
					//得到日志创建人名称
					if(!StringUtils.isEmpty(tab_system_log.getCreator())){
						if(!"游客".equals(tab_system_log.getCreator())){
							Tab_user_info info=iuserinfoService.getWhereId(tab_system_log.getCreator());
							tab_system_log.creator_name=info.getUsername();
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
	
}
