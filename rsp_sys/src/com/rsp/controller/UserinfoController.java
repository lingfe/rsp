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
import com.rsp.controller.util.LogUtils;
import com.rsp.controller.util.SYS_GET;
import com.rsp.model.JosnModel;
import com.rsp.model.Tab_user_info;
import com.rsp.service.Isys_system_logService;
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
@SessionAttributes(value={"userid","hospital_id"})
public class UserinfoController {
	
	//自动装配
	
	//系统日志
	@Autowired
	private Isys_system_logService isys_system_logService;
		
	//用户信息
	@Autowired
	private IuserinfoService iuserinfoService;
	
	/**
	 * 是否有bug
	 */
	private int flag_bug=0;
	
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
		//系统日志
		LogUtils.jieLuSystemLog(request,LogUtils.OTHER,flag_bug,
										"退出登录",
										josn.msg,session,isys_system_logService);
		try {
			//销毁session
			session.invalidate();
			josn.state=200;
			josn.msg="退出成功";
		} catch (Exception e) {
			flag_bug=1;
			josn.msg=e.getMessage();
			josn.state=500;
		}
		
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
		
		//验证非空
		if(!StringUtils.isEmpty(pwd)){
			if(!StringUtils.isEmpty(newPwd)){
				
				try {
					//匹配原始密码
					Tab_user_info info=iuserinfoService.getWhereNamePwd(username, pwd);
					if(info!=null){
						session.setAttribute("target_id", info.getId());
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
					flag_bug=1;
					josn.msg=e.getMessage();
					josn.state=500;
				}
				//系统日志
				LogUtils.jieLuSystemLog(request,LogUtils.OTHER,flag_bug,
										"修改密码",
										josn.msg,session,isys_system_logService);
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
		
		//验证非空
		if(!StringUtils.isEmpty(username)){
			if(!StringUtils.isEmpty(pwd)){
				try {
					//执行查询
					Tab_user_info info=iuserinfoService.getWhereNamePwd(username, pwd);
					if(info!=null){
						//存储到session
						session.setAttribute("userid", info.getId());
						session.setAttribute("hospital_id", info.getHospital_id());
						
						josn.data=info;
						josn.state=200;
						josn.msg="登录成功!";
					}else{
						josn.msg="该用户不存在或者密码错误!";
					}
				} catch (Exception e) {
					flag_bug=1;
					josn.msg=e.getMessage();
					josn.state=500;
				}
				//系统日志
				LogUtils.jieLuSystemLog(request,LogUtils.OTHER,flag_bug,
										"登录",
										josn.msg,session,isys_system_logService);
				
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
		
		Object creator=session.getAttribute("userid");
		if(SYS_GET.IS_SESSION_VALIDATE){
			if(StringUtils.isEmpty(creator)){
				json.msg="会话过期!请重新登录";
				return json;
			}
		}else{
			creator=GetIpUtil.getIpAddr(request);
		}
		
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
				flag_bug=1;
			}
			//系统日志
			LogUtils.jieLuSystemLog(request,LogUtils.OTHER,flag_bug,
									"保存用户信息",
									json.msg,session,isys_system_logService);
			
		}
		
		return json;
	}
}
