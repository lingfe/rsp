package com.rsp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.rsp.controller.util.GetIpUtil;
import com.rsp.model.JosnModel;
import com.rsp.model.Tab_system_log;
import com.rsp.model.Tab_user_info;
import com.rsp.service.Isystem_logService;
import com.rsp.service.IuserinfoService;

/**
 * 
  * 文件名：UserinfoController.java
  * 描述： 用户信息数据请求， 表示层
  * 修改人： lingfe
  * 修改时间：2017年12月18日 下午2:18:45
  * 修改内容：
 */
@Controller
@RequestMapping("/userinfo")
@SessionAttributes(value={"userid"})
public class UserinfoController {
	
	//自动装配
	
	//系统日志
	@Autowired
	private Isystem_logService isystem_logService;
		
	//用户信息
	@Autowired
	private IuserinfoService iuserinfoService;
	
	
	/**
	 * 
	 * 用户退出登录
	 * @author lingfe     
	 * @created 2019年3月22日 上午9:06:50  
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/quitSystem", method = { RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public JosnModel<Object> quitSystem(
		HttpServletRequest request,HttpSession session){
		//实例化对象
		JosnModel<Object> josn=new JosnModel<Object>();
		Tab_system_log sysLog=new Tab_system_log();
		//系统日志
		sysLog.setIp(GetIpUtil.getIpAddr(request));
		sysLog.setModel_name("用户退出登录,"+request.getRequestURI());
		Object creator=session.getAttribute("userid");
		if(!StringUtils.isEmpty(creator)){
			sysLog.setCreator(creator.toString());
		}
		sysLog.setModify(sysLog.getCreator());
		sysLog.setOperation_type(0);
		
		try {
			//销毁session
			session.invalidate();
			josn.state=200;
			josn.msg="退出成功";
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
	 * 修改密码
	 * @author lingfe     
	 * @created 2019年3月20日 下午2:36:38  
	 * @param username 用户名
	 * @param pwd	密码
	 * @param newPwd	新密码
	 * @return
	 */
	@RequestMapping(value = "/updatePwd", method = { RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public JosnModel<Object> updatePwd(
    		@RequestParam(value="username",required=false)String username,
    		@RequestParam(value="pwd",required=false)String pwd,
			@RequestParam(value="newPwd",required=false)String newPwd,
			HttpServletRequest request,HttpSession session){
		//实例化对象
		JosnModel<Object> josn=new JosnModel<Object>();
		Tab_system_log sysLog=new Tab_system_log();
		
		//系统日志
		sysLog.setIp(GetIpUtil.getIpAddr(request));
		sysLog.setModel_name(request.getRequestURI());
		Object creator=session.getAttribute("userid");
		if(!StringUtils.isEmpty(creator)){
			sysLog.setCreator(creator.toString());
		}
		sysLog.setModify(sysLog.getCreator());
		sysLog.setOperation_type(2);
		
		//验证非空
		if(!StringUtils.isEmpty(pwd)){
			if(!StringUtils.isEmpty(newPwd)){
				
				try {
					//匹配原始密码
					Tab_user_info info=iuserinfoService.getWhereNamePwd(username, pwd);
					if(info!=null){
						sysLog.setTarget_id(info.getId());
						//执行修改
						int tt=iuserinfoService.updatePwd(info.getId(), newPwd);
						if(tt>=1){
							josn.state=200;
							josn.msg="修改成功!";
						}
					}else{
						josn.msg="修改失败!原始密码错误!";
					}
				} catch (Exception e) {
					sysLog.setIs_bug(1);
					
					josn.msg=e.getMessage();
					josn.state=500;
				}
				//操作说明
				sysLog.setExceptionally_detailed(josn.msg);
				//保存系统日志
				isystem_logService.add(sysLog);
			}else{
				josn.msg="请输入新密码!";
			}
		}else{
			josn.msg="请输入原始密码!";
		}
		
		return josn;
	}
	
	
	/**
	 * 
	 * 用户密码登录
	 * @author lingfe     
	 * @created 2019年3月21日 下午3:47:06  
	 * @param username
	 * @param pwd
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getLogin", method = { RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
	public JosnModel<Object> getLogin(
			@RequestParam(value="username",required=false)String username,
			@RequestParam(value="pwd",required=false)String pwd,
			HttpServletRequest request,
			HttpSession session){
		//实例化对象
		JosnModel<Object> josn=new JosnModel<Object>();
		Tab_system_log sysLog=new Tab_system_log();
		
		//系统日志
		sysLog.setIp(GetIpUtil.getIpAddr(request));
		sysLog.setModel_name(request.getRequestURI());				
		sysLog.setModify(sysLog.getCreator());
		sysLog.setOperation_type(1);
		
		//验证非空
		if(!StringUtils.isEmpty(username)){
			if(!StringUtils.isEmpty(pwd)){
				try {
					//执行查询
					Tab_user_info info=iuserinfoService.getWhereNamePwd(username, pwd);
					if(info!=null){
						//将userid存储到session
						session.setAttribute("userid", info.getId());
						sysLog.setCreator(info.getId());
						
						josn.data=info;
						josn.state=200;
						josn.msg="登录成功!";
					}else{
						josn.msg="该用户不存在或者密码错误!";
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
			}else{
				josn.msg="密码不能为空!";
			}
		}else{
			josn.msg="用户名不能为空!";
		}
		
		return josn;
	}
	
	
	/**
	 * 
	 * 保存用户信息，并返回info
	 * @author lingfe     
	 * @created 2019年3月21日 下午3:48:27  
	 * @param username
	 * @param avatar
	 * @return
	 */
	@RequestMapping(value = "/save", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody 
	public JosnModel<Tab_user_info> save(
			@RequestParam(value="username",required=false) String username,
			@RequestParam(value="pwd",required=false) String pwd,
			HttpServletRequest request,
			HttpSession session){
		
		//实例化对象
		Tab_user_info tab_user_info=new Tab_user_info();
		JosnModel<Tab_user_info> json= new JosnModel<Tab_user_info>(); 
		Tab_system_log sysLog=new Tab_system_log();
		
		//系统日志
		sysLog.setIp(GetIpUtil.getIpAddr(request));
		sysLog.setModel_name(request.getRequestURI());	
		Object creator=session.getAttribute("userid");
		if(!StringUtils.isEmpty(creator)){
			sysLog.setCreator(creator.toString());
		}
		sysLog.setModify(sysLog.getCreator());
		sysLog.setOperation_type(1);
		
		if(StringUtils.isEmpty(username)){
			json.msg ="用户名不能为空!";
			return json;
		}else if(StringUtils.isEmpty(pwd)){
			json.msg ="密码不能为空!";
			return json;
		}else{
			try {
				//根据用户名查询用户
				Tab_user_info info=iuserinfoService.selectWhereName(username);
				if(StringUtils.isEmpty(info)){
					//设置参数,保存用户
					tab_user_info.setUsername(username);
					tab_user_info.setPwd(pwd);
					
					//执行保存
					int tt=iuserinfoService.save(tab_user_info);
					if(tt >= 1){
						json.state=200;
						json.msg="登陆成功!";
						json.data=tab_user_info;
					}else{
						json.msg="登陆失败!系统错误!";
					}
				}else{
					json.msg="该用户名已经被使用!";
				}
			} catch (Exception e) {
				json.state=500;
				json.msg=e.getMessage();
				sysLog.setIs_bug(1);
			}
			
			//操作说明
			sysLog.setExceptionally_detailed(json.msg);
			//添加系统日志
			isystem_logService.add(sysLog);
		}
		
		return json;
	}
}
