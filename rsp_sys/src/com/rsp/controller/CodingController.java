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
import com.rsp.model.JosnModel;
import com.rsp.model.PageModel;
import com.rsp.model.Tab_coding;
import com.rsp.model.Tab_system_log;
import com.rsp.model.Tab_user_info;
import com.rsp.service.IcodingService;
import com.rsp.service.Isystem_logService;
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
	
	//自动装配
	
	//系统日志
	@Autowired
	private Isystem_logService isystem_logService;
	
	//用户信息
	@Autowired
	private IuserinfoService iuserinfoService;
	
	//编码信息
	@Autowired
	private IcodingService icodingService;
	

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
		Tab_system_log sysLog=new Tab_system_log();
		session.setAttribute("userid", "sdgdfdfgnbdffsdgfdf");
		
		//验证非空
		if(coding!=null){
			//系统日志
			sysLog.setId(UUID.randomUUID().toString().replace("-", ""));
			sysLog.setId(GetIpUtil.getIpAddr(request));
			sysLog.setModel_name(request.getRequestURI());
			String creator=session.getAttribute("userid").toString();
			sysLog.setCreator(creator);
			sysLog.setModify(sysLog.getCreator());
			sysLog.setOperation_type(2);
			sysLog.setTarget_id(coding.getId());
			
			if(coding.getId()!=null){
				//执行查询,得到数据
				Tab_coding tab_coding=icodingService.getWhereId(coding.getId());
				if(tab_coding!=null){
					//更新数据
					tab_coding.setMdate(new Date());
					tab_coding.setModify(coding.getModify());
					tab_coding.setVersion(String.valueOf(Integer.parseInt(tab_coding.getVersion())+1));
					tab_coding.setCoding_name(coding.getCoding_name());
					tab_coding.setCoding_type(coding.getCoding_type());
					tab_coding.setRemark(coding.getRemark());
					tab_coding.setState(coding.getState());
					try {
						//执行更新
						int tt=icodingService.update(tab_coding);
						if(tt>=1){
							josn.state=200;
							josn.msg="修改成功!";
							josn.data=tab_coding;
						}else{
							josn.msg="修改失败!";
						}
						sysLog.setIs_bug(0);
					} catch (Exception e) {
						sysLog.setIs_bug(0);
						sysLog.setExceptionally_detailed(e.getMessage());
						
						josn.msg=e.getMessage();
						josn.state=500;
					}
				}else{
					josn.msg="id无效!请检查!";
				}
			}else{
				josn.msg="参数有误!请检查";
			}
		}else{
			josn.msg="空对象!";
		}
		
		//添加系统日志
		isystem_logService.add(sysLog);
		
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
			@RequestParam(value="state",required=false)Integer state,
			HttpServletRequest request){
		//实例化对象
		JosnModel<Object> josn=new JosnModel<Object>();
		Map<String, Object>  map = new HashMap<>();
		PageModel<Tab_coding> page=new PageModel<Tab_coding>();
		
		//验证非空
		if(!StringUtils.isEmpty(coding_type)){
			//赋值参数
			if(coding_name!=null){
				map.put("coding_name", coding_name);
			}
			if(state!=null){
				map.put("state", state);
			}
			map.put("coding_type", coding_type);
			map.put("pageIndex", pageIndex);
			map.put("pageNum",pageNum);
			page.setPageIndex(pageIndex);
			page.setPageNum(pageNum);
			
			
			//设置表:编码信息表
			map.put("table", "coding");
			
			//得到总数据量
			int numCount=icodingService.getCount();
			page.setNumCount(numCount);
			
			//得到数据
			List<Tab_coding> list=icodingService.pageSelect(map);
			//验证非空
			if(list!=null){
				for (Tab_coding tab_coding : list) {
					//得到创建人名称
					Tab_user_info info=iuserinfoService.getWhereId(tab_coding.getCreator());
					tab_coding.creator_name=info.getUsername();
					
					//验证是否停用
					if(tab_coding.getState()==0){
						//得到停用人
						info=iuserinfoService.getWhereId(tab_coding.getModify());
						tab_coding.modify_name=info.getUsername();
					}else{
						tab_coding.modify_name="无";
					}
				}
				josn.state=200;
				josn.msg="请求成功!";
				page.setList(list);
				josn.data=page;
			}else{
				josn.msg="没有数据,添加一条吧!";
			}
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
    		HttpServletRequest request){
		//实例化对象
		JosnModel<Tab_coding> josn=new JosnModel<Tab_coding>();
		//验证非空
		if(!StringUtils.isEmpty(coding.getCoding_name())){
			//赋值
			coding.setId(UUID.randomUUID().toString().replace("-", ""));
			coding.setModify(coding.getCreator());
			
			try {
				//执行保存
				int tt=icodingService.save(coding);
				if(tt>=1){
					josn.msg="保存成功!";
					josn.state=200;
					josn.data=coding;
				}else{
					josn.msg="保存失败!";
				}
			} catch (Exception e) {
				josn.state=500;
				josn.msg=e.getMessage();
			}
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
    		HttpServletRequest request){
    	//实例化对象
    	JosnModel<Tab_coding> josn=new JosnModel<Tab_coding>();
    	//验证非空
    	if(!StringUtils.isEmpty(id)){
    		//执行删除
    		int tt=icodingService.deleteWhereId(id);
    		try {
				if(tt>=1){
					josn.msg="删除成功!";
					josn.state=200;
				}else{
					josn.msg="删除失败!不存在或者已经被删除!";
				}
			} catch (Exception e) {
				josn.msg=e.getMessage();
				josn.state=500;
			}
    	}else{
    		josn.msg="id不能为空!";
    	}
    	
    	return josn;
    }
    
}
