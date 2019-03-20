package com.rsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rsp.model.JosnModel;
import com.rsp.model.Tab_user_info;
import com.rsp.service.IuserinfoService;
import com.rsp.util.OpenIdUtil;

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
public class UserinfoController {
	
	//自动装配
	@Autowired
	private IuserinfoService iuserinfoService;
	
	
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
			@RequestParam(value="newPwd",required=false)String newPwd){
		//实例化对象
		JosnModel<Object> josn=new JosnModel<Object>();
		//验证非空
		if(!StringUtils.isEmpty(pwd)){
			if(!StringUtils.isEmpty(newPwd)){
				//匹配原始密码
				Tab_user_info info=iuserinfoService.getWhereNamePwd(username, pwd);
				if(info!=null){
					//执行修改
					int tt=iuserinfoService.updatePwd(info.getId(), newPwd);
					if(tt>=1){
						josn.state=200;
						josn.msg="修改成功!";
					}
				}else{
					josn.msg="修改失败!原始密码错误!";
				}
			}else{
				josn.msg="请输入新密码!";
			}
		}else{
			josn.msg="请输入原始密码!";
		}
		
		return josn;
	}
	
	
	/**
	 * 用户密码登录
	 */
	@RequestMapping(value = "/getLogin", method = { RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
	public JosnModel<Object> getLogin(@RequestParam(value="username",required=false)String username,
			@RequestParam(value="pwd",required=false)String pwd){
		//实例化对象
		JosnModel<Object> josn=new JosnModel<Object>();
		//验证非空
		if(!StringUtils.isEmpty(username)){
			if(!StringUtils.isEmpty(pwd)){
				//执行查询
				Tab_user_info info=iuserinfoService.getWhereNamePwd(username, pwd);
				if(info!=null){
					josn.data=info;
					josn.state=200;
					josn.msg="登录成功!";
				}else{
					josn.msg="该用户不存在或者密码错误!";
				}
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
	 * （微信用户登陆）保存用户信息，并返回info
	 * @author lingfe     
	 * @created 2017年12月18日 下午2:28:22  
	 * @param 用户id
	 * @return
	 */
	@RequestMapping(value = "/save", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody JosnModel<Tab_user_info> save(@RequestParam(value="code",required=false) String code,
			@RequestParam(value="username",required=false) String username,
			@RequestParam(value="avatar",required=false) String avatar){
		
		//实例化对象
		Tab_user_info tab_user_info=new Tab_user_info();
		JosnModel<Tab_user_info> json= new JosnModel<Tab_user_info>(); 
		//验证非空
		if(StringUtils.isEmpty(code)){
			json.msg="code为空!";
			return json;
		}else if(StringUtils.isEmpty(username)){
			json.msg ="用户信息不能为空!";
			return json;
		}
		
		//验证openid
		String openid=OpenIdUtil.oauth2GetOpenid(code, "2");
		if(StringUtils.isEmpty(openid)){
			json.msg="code无效!请检查";
			return json;
		}else{
			//根据openid查询用户
			Tab_user_info	getWhereOpenid = iuserinfoService.getWhereOpenid(openid);
			if(StringUtils.isEmpty(getWhereOpenid)){
				//设置参数,保存用户
				tab_user_info.setUsername(username);
				tab_user_info.setAvatar(avatar);
				tab_user_info.setOpenid(openid);
				tab_user_info.setCreator(openid);
				
				json.data=tab_user_info;
				
				//执行保存
				int tt=iuserinfoService.save(tab_user_info);
				if(tt >= 1){
					json.state=200;
					json.msg="登陆成功!";
				}else{
					json.msg="登陆失败!系统错误!";
				}
				
				//返回结果
				return json;
			}else{
				json.data=getWhereOpenid;
				json.msg="登陆成功!";
				//返回用户信息
				return json;
			}
		}
	}
}
