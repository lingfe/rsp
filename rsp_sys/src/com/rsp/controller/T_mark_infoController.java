package com.rsp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rsp.model.JosnModel;
import com.rsp.model.Tab_t_mark;
import com.rsp.model.Tab_t_mark_info;
import com.rsp.service.It_markService;
import com.rsp.service.It_mark_infoService;

/**
 * 
  * 文件名：T_mark_infoController.java
  * 描述： 痕迹信息表数据访问表示层
  * 修改人： lingfe
  * 修改时间：2019年4月20日 下午2:44:38
  * 修改内容：
 */
@Controller
@RequestMapping("/t_mark_info")
public class T_mark_infoController {

	@Autowired
	private It_markService it_markServce;
	
	//痕迹信息表
	@Autowired
	private It_mark_infoService it_mark_infoService;
	
	/**
	 * 
	 * 根据条件查询痕迹信息
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
	    		@RequestParam(value="sb_id",required=false)String sb_id,
	    		HttpServletRequest request,
	    		HttpSession session){
			//实例化对象
			JosnModel<Object> josn=new JosnModel<Object>();
			Map<String, Object> map=new HashMap<String, Object>();
			
			try {
				//table表
				map.put("table", "t_mark");
				map.put("yw_id", sb_id);
				List<Tab_t_mark> mark_list=it_markServce.getWhere(map);
				for (Tab_t_mark mark : mark_list) {
					//得到信息
					map.put("table", "t_mark_info");
					map.put("yw_id", null);
					map.put("mark_id", mark.getId());
					List<Tab_t_mark_info> tab_list=it_mark_infoService.getWhere(map);
					mark.mark_info=tab_list;
				}
				josn.data=mark_list;
				josn.msg="查询成功!";
				josn.state=200;
				
			} catch (Exception e) {
				josn.msg=e.getMessage();
				josn.state=500;
			}
			
			return josn;
	}
	
}
