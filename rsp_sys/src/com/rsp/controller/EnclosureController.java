package com.rsp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.rsp.controller.util.GetIpUtil;
import com.rsp.controller.util.LogUtils;
import com.rsp.controller.util.SYS_GET;
import com.rsp.controller.util.UploadUtils;
import com.rsp.model.JosnModel;
import com.rsp.model.PageModel;
import com.rsp.model.Tab_enclosure;
import com.rsp.model.Tab_user_info;
import com.rsp.service.IenclosuresService;
import com.rsp.service.Isys_system_logService;
import com.rsp.service.IuserinfoService;

/**
 * 
 * 文件名：EnclosureController.java 
 * 描述： 附件信息请求访问表层 
 * 修改人： lingfe 
 * 修改时间：2019年3月26日 上午9:52:41 
 * 修改内容：
 */
@Controller
@RequestMapping("/enclosure")
public class EnclosureController {

	// 自动装配
	@Autowired
	private IenclosuresService ienclosuresService;

	// 用户信息
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
	 * 附件下载
	 * @author lingfe     
	 * @created 2019年3月27日 下午2:38:43  
	 * @param fileName 文件名称
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/fileDownLoad", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseEntity<byte[]> fileDownLoad(
			@RequestParam("id") String id, 
			HttpServletRequest request, HttpSession session) {
		try {
			// 验证非空
			if (id != null) {
				//chax 
				Tab_enclosure fjxx= ienclosuresService.getWhereId(id);
				if(fjxx!=null){
					//下载文件
					return UploadUtils.downLoad(request,fjxx.getPath());
				}
			}
		} catch (Exception e) {
			flag_bug=1;
			session.setAttribute("czsm", e.getMessage());
		}
		//系统日志
		LogUtils.jieLuSystemLog(request,LogUtils.OTHER,flag_bug,
				"附件下载",
				session.getAttribute("czsm"),session,isys_system_logService);
		return null;
	}
	
	/**
	 * 
	 * 附件上传(单个)
	 * @author lingfe     
	 * @created 2019年3月27日 下午3:28:40  
	 * @param file
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/fileUpLoad", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public JosnModel<Object> fileUpLoad(
			@RequestParam("file") CommonsMultipartFile file,
			@RequestParam(value="yw_id",required=false) String yw_id,
			@RequestParam(value="coding_type",required=false)String coding_type,
			@RequestParam(value="remark",required=false) String remark,
			HttpServletRequest request, HttpSession session) {
		// 实例化对象
		JosnModel<Object> josn = new JosnModel<Object>();
		Tab_enclosure tab=new Tab_enclosure();
		
		//用户信息
		Object creator = session.getAttribute("userid");
		if(SYS_GET.IS_SESSION_VALIDATE){
			if(StringUtils.isEmpty(creator)){
				josn.msg="会话过期!请重新登录";
				return josn;
			}
		}else{
			creator=GetIpUtil.getIpAddr(request);
		}

		try {
			// 验证非空
			if (file != null) {
				if(yw_id!=null&&!"".equals(yw_id)){
					//附件上传
					Map<String, Object> map =UploadUtils.fileUpLoad(file, request);
					Object state=map.get("state");
					if("200".equals(state.toString())){
						//保存附件信息
						tab.setId(UUID.randomUUID().toString().replace("-", ""));
						tab.setYw_id(yw_id);
						tab.setCoding_type(coding_type);
						tab.setFj_remark(remark);
						tab.setFj_name_real(map.get("old_name").toString());
						tab.setFj_name_sys(map.get("prefix").toString());
						tab.setFj_size(Integer.parseInt(map.get("size").toString()));
						tab.setFlag_type(Integer.valueOf(map.get("type").toString()));
						tab.setPath(map.get("full_path").toString());
						Object hospital_id=session.getAttribute("hospital_id");
						if(hospital_id!=null){
							tab.setHospital_id(hospital_id.toString());
						}
						
						//创建人信息
						tab.setCrt_code(creator.toString());
						tab.setCrt_date(new Date());
						tab.setCrt_name(creator.toString());
						
						//执行保存
						ienclosuresService.save(tab);
						josn.state=200;
						josn.data=tab;
					}
					josn.msg=map.get("msg").toString();
				}else{
					josn.msg="yw_id不能为空!";
				}
			} else {
				josn.msg = "请选择要上传的附件!";
			}

		} catch (Exception e) {
			flag_bug=1;
			josn.msg = e.getMessage();
			josn.state = 500;
		}
		//系统日志
		LogUtils.jieLuSystemLog(request,LogUtils.OTHER,flag_bug,
						"附件上传(单个)",
						josn.msg,session,isys_system_logService);
				
		return josn;
	}


	/**
	 * 
	 * 附件上传(多个)
	 * @author lingfe
	 * @created 2019年3月27日 下午2:27:34
	 * @param desc
	 * @param file
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/fileUpLoadAll", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public JosnModel<Object> fileUpLoadAll(
			@RequestParam MultipartFile[] uploadFile,
			@RequestParam(value="setId",required=false) String setId,
			@RequestParam(value="coding_type",required=false)String coding_type,
			@RequestParam(value="remark",required=false) String remark,
			HttpServletRequest request, HttpSession session) {
		// 实例化对象
		JosnModel<Object> josn = new JosnModel<Object>();
		Tab_enclosure tab=new Tab_enclosure();

		Object creator = session.getAttribute("userid");
		if(SYS_GET.IS_SESSION_VALIDATE){
			if(StringUtils.isEmpty(creator)){
				josn.msg="会话过期!请重新登录";
				return josn;
			}
		}else{
			creator=GetIpUtil.getIpAddr(request);
		}

		try {
			// 验证非空
			if (uploadFile != null) {
				for (MultipartFile item : uploadFile) {
					if(item.getSize()>0){
						//上传
						Map<String, Object> map=UploadUtils.fileUpLoad(item, request, null);
						if(map.get("state")=="200"){
							//保存附件信息
							tab.setId(UUID.randomUUID().toString().replace("-", ""));
							tab.setYw_id(setId);
							tab.setCoding_type(coding_type);
							tab.setFj_remark(remark);
							tab.setFj_name_real(map.get("old_name").toString());
							tab.setFj_name_sys(map.get("prefix").toString());
							tab.setFj_size(Integer.parseInt(map.get("size").toString()));
							tab.setFlag_type(Integer.valueOf(map.get("type").toString()));
							tab.setHospital_id(session.getAttribute("hospital_id").toString());
							
							//创建人信息
							tab.setCrt_code(creator.toString());
							tab.setCrt_date(new Date());
							tab.setCrt_name(creator.toString());
							
							//执行保存
							int tt=ienclosuresService.save(tab);
							if(tt<1){
								break;
							}
						}else{
							josn.msg=map.get("msg").toString();
						}
					}
				}
				
				josn.msg = "上传成功!";
			} else {
				josn.msg = "请选择要上传的附件!";
			}

		} catch (Exception e) {
			flag_bug=1;
			josn.msg = e.getMessage();
			josn.state = 500;
		}
		//系统日志
		LogUtils.jieLuSystemLog(request,LogUtils.OTHER,flag_bug,
								"附件上传(多个)",
								josn.msg,session,isys_system_logService);
						
		return josn;
	}

	/**
	 * 
	 * 根据id标识删除附件信息
	 * @author lingfe
	 * @created 2019年3月26日 上午10:10:54
	 * @param id
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteWhereId", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public JosnModel<Object> deleteWhereId(@RequestParam(value = "id", required = false) String id,
			HttpServletRequest request, HttpSession session) {
		// 实例化对象
		JosnModel<Object> josn = new JosnModel<Object>();
		
		try {
			if(id!=null&&!"".equals(id)){
				String[] str=id.split(",");
				for (String s : str) {
					//查询
					Tab_enclosure tab=ienclosuresService.getWhereId(s);
					if (tab != null) {
						// 执行删除
						int tt = ienclosuresService.deleteWhereId(s);
						if (tt >= 1) {
							//执行删除文件
							UploadUtils.deleteFile(null);
							josn.msg = "删除成功!";
							josn.state = 200;
						} else {
							josn.msg = "删除失败!";
						}
					} else {
						josn.msg = "id无效!";
					}
				}
			}
		} catch (Exception e) {
			josn.msg = e.getMessage();
			josn.state = 500;
		}
		//系统日志
		LogUtils.jieLuSystemLog(request,LogUtils.DELETE,flag_bug,
										"删除附件",
										josn.msg,session,isys_system_logService);
					
		return josn;
	}

	/**
	 * 
	 * 分页查询附件信息
	 * 
	 * @author lingfe
	 * @created 2019年3月26日 上午10:08:15
	 * @param pageIndex
	 * @param pageNum
	 * @param state
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/pageSelect", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public JosnModel<Object> pageSelect(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(value = "pageNum", required = false, defaultValue = "10") Integer pageNum,
			@RequestParam(value = "yw_id", required=false)String yw_id,
			@RequestParam(value = "coding_type", required=false)String coding_type,
			@RequestParam(value = "state", required = false) Integer state, 
			HttpServletRequest request,
			HttpSession session) {
		// 实例化对象
		JosnModel<Object> josn = new JosnModel<Object>();
		Map<String, Object> map = new HashMap<>();
		PageModel<Tab_enclosure> page = new PageModel<Tab_enclosure>();

		try {
			// 验证非空
			if(!StringUtils.isEmpty(yw_id)){
				map.put("yw_id", yw_id);
			}
			if(!StringUtils.isEmpty(coding_type)){
				map.put("coding_type", coding_type);
			}
			if (state != null) {
				map.put("state", state);
			}
			
			// 赋值参数
			map.put("pageIndex", pageIndex);
			map.put("pageNum", pageNum);
			page.setPageIndex(pageIndex);
			page.setPageNum(pageNum);

			// 设置表:附件表
			map.put("table", "sys_fjxx");

			// 得到总数据量
			int numCount = ienclosuresService.getCount(map);
			page.setNumCount(numCount);

			// 得到数据
			List<Tab_enclosure> list = ienclosuresService.pageSelect(map);
			// 验证非空
			if (list != null) {
				for (Tab_enclosure tab : list) {
					// 得到创建人名称
					Tab_user_info info = iuserinfoService.getWhereId(tab.getCrt_code());
					if (info != null) {
						tab.setCrt_name(info.getUsername());
					}
				}

				josn.state = 200;
				josn.msg = "请求成功!";
				page.setList(list);
				josn.data = page;
			} else {
				josn.msg = "没有数据,添加一条吧!";
			}

		} catch (Exception e) {
			josn.msg = e.getMessage();
			josn.state = 500;
		}
		return josn;
	}

	/**
	 * 
	 * 根据id标识获取数据
	 * 
	 * @author lingfe
	 * @created 2019年3月26日 上午10:03:47
	 * @param id
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getWhereId", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public JosnModel<Object> getWhereId(@RequestParam(value = "id", required = false) String id,
			HttpServletRequest request, HttpSession session) {
		// 实例化对象
		JosnModel<Object> josn = new JosnModel<Object>();

		try {
			// 验证非空
			if (id != null && !"".equals(id)) {
				// 执行查询
				josn.data = ienclosuresService.getWhereId(id);
				josn.msg = "获取成功!";
				josn.state = 200;
			} else {
				josn.msg = "id不能为空!";
			}

		} catch (Exception e) {
			josn.msg = e.getMessage();
			josn.state = 500;
		}
		
		return josn;
	}

}
