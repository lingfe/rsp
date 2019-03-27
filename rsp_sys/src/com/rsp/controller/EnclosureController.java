package com.rsp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import com.rsp.model.JosnModel;
import com.rsp.model.PageModel;
import com.rsp.model.Tab_enclosure;
import com.rsp.model.Tab_system_log;
import com.rsp.model.Tab_user_info;
import com.rsp.service.IenclosuresService;
import com.rsp.service.Isystem_logService;
import com.rsp.service.IuserinfoService;
import com.rsp.util.GetDateString;

/**
 * 
 * 文件名：EnclosureController.java 描述： 附件信息请求访问表层 修改人： lingfe 修改时间：2019年3月26日
 * 上午9:52:41 修改内容：
 */
@Controller
@RequestMapping("/enclosure")
public class EnclosureController {

	// 自动装配
	@Autowired
	private IenclosuresService ienclosuresService;

	// 系统日志
	@Autowired
	private Isystem_logService isystem_logService;

	// 用户信息
	@Autowired
	private IuserinfoService iuserinfoService;

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
			@RequestParam("fileName") String fileName, 
			HttpServletRequest request, HttpSession session) {
		// 实例化对象 
		Tab_system_log sysLog = new Tab_system_log();

		// 系统日志
		sysLog.setIp(GetIpUtil.getIpAddr(request));
		sysLog.setModel_name("附件下载," + request.getRequestURI());
		Object creator = session.getAttribute("userid");
		if (!StringUtils.isEmpty(creator)) {
			sysLog.setCreator(creator.toString());
		}
		sysLog.setModify(sysLog.getCreator());
		sysLog.setOperation_type(0);

		try {
			// 验证非空
			if (fileName != null) {
				// 获取ServletContext的对象 代表当前WEB应用
				ServletContext servletContext = request.getServletContext();
				//fileName = "童话镇.mp3";
				// 得到文件所在位置
				String realPath = servletContext.getRealPath("/fileUpload/" + fileName);
				// 将该文件加入到输入流之中
				InputStream in = new FileInputStream(new File(realPath));
				byte[] body = null;
				// 返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
				body = new byte[in.available()];
				// 读入到输入流里面
				in.read(body);
				
				
				HttpHeaders headers=new HttpHeaders();//设置响应头
			    headers.add("Content-Disposition", "attachment;filename="+fileName);
			    HttpStatus statusCode = HttpStatus.OK;//设置响应吗
			    ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(body, headers, statusCode);
			     
				return response;
			}
		} catch (Exception e) {
			sysLog.setIs_bug(1);
			// 操作说明
			sysLog.setExceptionally_detailed(e.getMessage());
		}
		
		// 添加系统日志
		isystem_logService.add(sysLog);

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
			HttpServletRequest request, HttpSession session) {
		// 实例化对象
		JosnModel<Object> josn = new JosnModel<Object>();
		Tab_system_log sysLog = new Tab_system_log();

		// 系统日志
		sysLog.setIp(GetIpUtil.getIpAddr(request));
		sysLog.setModel_name("附件上传(单个)," + request.getRequestURI());
		Object creator = session.getAttribute("userid");
		if (!StringUtils.isEmpty(creator)) {
			sysLog.setCreator(creator.toString());
		}
		sysLog.setModify(sysLog.getCreator());
		sysLog.setOperation_type(0);

		try {

			// 验证非空
			if (file != null) {
				// 获取ServletContext的对象 代表当前WEB应用
				ServletContext servletContext = request.getServletContext();
				//将当前日期作为目录
				String dateStr=GetDateString.getDate();
				// 得到文件上传目的位置的真实路径
				String realPath = servletContext.getRealPath("/fileUpload/"+dateStr);
				System.out.println("realPath :" + realPath);
				File file1 = new File(realPath);
				if (!file1.exists()) {
					// 如果该目录不存在，就创建此抽象路径名指定的目录。
					file1.mkdir();
				}
				String prefix = UUID.randomUUID().toString();
				prefix = prefix.replace("-", "");
				// 使用UUID加前缀命名文件，防止名字重复被覆盖
				String fileName = prefix + "_" + file.getOriginalFilename();

				// 声明输入输出流
				InputStream in = file.getInputStream();

				// 指定输出流的位置;
				OutputStream out = new FileOutputStream(new File(realPath + "\\" + fileName));

				// 这段代码也可以用IOUtils.copy(in, out)工具类的copy方法完成
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = in.read(buffer)) != -1) {
					out.write(buffer, 0, len);
					// 类似于文件复制，将文件存储到输入流，再通过输出流写入到上传位置
					out.flush();
				}
				// 关闭流
				out.close();
				in.close();

				josn.data=fileName;
				josn.msg = "上传成功!";
			} else {
				josn.msg = "请选择要上传的附件!";
			}

		} catch (Exception e) {
			sysLog.setIs_bug(1);
			josn.msg = e.getMessage();
			josn.state = 500;
		}
		// 操作说明
		sysLog.setExceptionally_detailed(josn.msg);
		// 添加系统日志
		isystem_logService.add(sysLog);

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
			HttpServletRequest request, HttpSession session) {
		// 实例化对象
		JosnModel<Object> josn = new JosnModel<Object>();
		Tab_system_log sysLog = new Tab_system_log();

		// 系统日志
		sysLog.setIp(GetIpUtil.getIpAddr(request));
		sysLog.setModel_name("附件上传(多个)," + request.getRequestURI());
		Object creator = session.getAttribute("userid");
		if (!StringUtils.isEmpty(creator)) {
			sysLog.setCreator(creator.toString());
		}
		sysLog.setModify(sysLog.getCreator());
		sysLog.setOperation_type(0);

		try {

			// 验证非空
			if (uploadFile != null) {
				for (MultipartFile item : uploadFile) {
					if(item.getSize()>0){
						//获取文件名
						String fileName=item.getOriginalFilename();
						//前半部分路径，目录，
						String leftPath=session.getServletContext().getRealPath("/fileUpload");
						//进行路径拼接
						File file=new File(leftPath,fileName);
						
						//执行
						item.transferTo(file);
						
						josn.data=fileName;
						
					}
				}
				josn.msg = "上传成功!";
			} else {
				josn.msg = "请选择要上传的附件!";
			}

		} catch (Exception e) {
			sysLog.setIs_bug(1);
			josn.msg = e.getMessage();
			josn.state = 500;
		}
		// 操作说明
		sysLog.setExceptionally_detailed(josn.msg);
		// 添加系统日志
		isystem_logService.add(sysLog);

		return josn;
	}

	/**
	 * 
	 * 修改附件信息
	 * 
	 * @author lingfe
	 * @created 2019年3月26日 上午10:18:30
	 * @param tab
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public JosnModel<Object> update(Tab_enclosure tab, HttpServletRequest request, HttpSession session) {
		// 实例化对象
		JosnModel<Object> josn = new JosnModel<Object>();
		Tab_system_log sysLog = new Tab_system_log();

		// 系统日志
		sysLog.setIp(GetIpUtil.getIpAddr(request));
		sysLog.setModel_name("修改附件信息," + request.getRequestURI());
		Object creator = session.getAttribute("userid");
		if (!StringUtils.isEmpty(creator)) {
			sysLog.setCreator(creator.toString());
		}
		sysLog.setModify(sysLog.getCreator());
		sysLog.setOperation_type(2);

		try {
			// 验证非空
			if (tab != null) {
				if (tab.getId() != null && !"".equals(tab.getId())) {
					// 执行查询
					Tab_enclosure en = ienclosuresService.getWhereId(tab.getId());
					// 更新赋值
					en.setSet_id(tab.getSet_id());
					en.setModify(sysLog.getCreator());
					en.setMdate(new Date());
					en.setVersion(String.valueOf(Integer.parseInt(en.getVersion()) + 1));
					en.setEnclosure_name(tab.getEnclosure_name());
					en.setEnclosure_remark(tab.getEnclosure_remark());
					en.setEnclosure_type(tab.getEnclosure_type());
					en.setState(tab.getState());

					// 执行修改更新
					int tt = ienclosuresService.update(tab);
					if (tt >= 0) {
						josn.msg = "修改成功!";
						josn.state = 200;
						josn.data = en;
					} else {
						josn.msg = "修改失败!";
					}
				} else {
					josn.msg = "id不能为空!";
				}
			} else {
				josn.msg = "请填写附件信息!";
			}

		} catch (Exception e) {
			sysLog.setIs_bug(1);
			josn.msg = e.getMessage();
			josn.state = 500;
		}
		// 操作说明
		sysLog.setExceptionally_detailed(josn.msg);
		// 添加系统日志
		isystem_logService.add(sysLog);

		return josn;
	}

	/**
	 * 
	 * 根据id标识删除附件信息
	 * 
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
		Tab_system_log sysLog = new Tab_system_log();

		// 系统日志
		sysLog.setIp(GetIpUtil.getIpAddr(request));
		sysLog.setModel_name("根据id标识删除附件信息," + request.getRequestURI());
		Object creator = session.getAttribute("userid");
		if (!StringUtils.isEmpty(creator)) {
			sysLog.setCreator(creator.toString());
		}
		sysLog.setModify(sysLog.getCreator());
		sysLog.setOperation_type(3);

		try {
			// 验证非空
			if (id != null && !"".equals(id)) {
				// 执行删除
				int tt = ienclosuresService.deleteWhereId(id);
				if (tt >= 1) {
					josn.msg = "删除成功!";
					josn.state = 200;
				} else {
					josn.msg = "删除失败!";
				}
			} else {
				josn.msg = "id不能为空！";
			}
		} catch (Exception e) {
			sysLog.setIs_bug(1);
			josn.msg = e.getMessage();
			josn.state = 500;
		}
		// 操作说明
		sysLog.setExceptionally_detailed(josn.msg);
		// 添加系统日志
		isystem_logService.add(sysLog);

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
			@RequestParam(value = "state", required = false) Integer state, HttpServletRequest request,
			HttpSession session) {
		// 实例化对象
		JosnModel<Object> josn = new JosnModel<Object>();
		Map<String, Object> map = new HashMap<>();
		PageModel<Tab_enclosure> page = new PageModel<Tab_enclosure>();
		Tab_system_log sysLog = new Tab_system_log();

		// 系统日志
		sysLog.setIp(GetIpUtil.getIpAddr(request));
		sysLog.setModel_name("分页查询附件信息," + request.getRequestURI());
		Object creator = session.getAttribute("userid");
		if (!StringUtils.isEmpty(creator)) {
			sysLog.setCreator(creator.toString());
		}
		sysLog.setModify(sysLog.getCreator());
		sysLog.setOperation_type(1);

		try {
			// 验证非空
			// 赋值参数
			if (state != null) {
				map.put("state", state);
			}
			map.put("pageIndex", pageIndex);
			map.put("pageNum", pageNum);
			page.setPageIndex(pageIndex);
			page.setPageNum(pageNum);

			// 设置表:附件表
			map.put("table", "enclosure");

			// 得到总数据量
			int numCount = ienclosuresService.getCount(map);
			page.setNumCount(numCount);

			// 得到数据
			List<Tab_enclosure> list = ienclosuresService.pageSelect(map);
			// 验证非空
			if (list != null) {
				for (Tab_enclosure tab : list) {
					// 得到创建人名称
					if (!StringUtils.isEmpty(tab.getCreator())) {
						if (!"游客".equals(tab.getCreator())) {
							Tab_user_info info = iuserinfoService.getWhereId(tab.getCreator());
							if (info != null) {
								tab.creator_name = info.getUsername();
							}
						}
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
			sysLog.setIs_bug(1);
			josn.msg = e.getMessage();
			josn.state = 500;
		}
		// 操作说明
		sysLog.setExceptionally_detailed(josn.msg);
		// 添加系统日志
		isystem_logService.add(sysLog);

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
		Tab_system_log sysLog = new Tab_system_log();

		// 系统日志
		sysLog.setIp(GetIpUtil.getIpAddr(request));
		sysLog.setModel_name("根据id标识获取数据," + request.getRequestURI());
		Object creator = session.getAttribute("userid");
		if (!StringUtils.isEmpty(creator)) {
			sysLog.setCreator(creator.toString());
		}
		sysLog.setModify(sysLog.getCreator());
		sysLog.setOperation_type(1);

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
			sysLog.setIs_bug(1);
			josn.msg = e.getMessage();
			josn.state = 500;
		}
		// 操作说明
		sysLog.setExceptionally_detailed(josn.msg);
		// 添加系统日志
		isystem_logService.add(sysLog);

		return josn;
	}

	/**
	 * 
	 * 保存附件信息
	 * 
	 * @author lingfe
	 * @created 2019年3月26日 上午10:00:42
	 * @param tab
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/save", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public JosnModel<Object> save(Tab_enclosure tab, HttpServletRequest request, HttpSession session) {
		// 实例化对象
		JosnModel<Object> josn = new JosnModel<Object>();
		Tab_system_log sysLog = new Tab_system_log();

		// 系统日志
		sysLog.setIp(GetIpUtil.getIpAddr(request));
		sysLog.setModel_name("保存附件信息," + request.getRequestURI());
		Object creator = session.getAttribute("userid");
		if (!StringUtils.isEmpty(creator)) {
			sysLog.setCreator(creator.toString());
		}
		sysLog.setModify(sysLog.getCreator());
		sysLog.setOperation_type(4);

		try {
			// 验证非空
			if (tab != null) {
				if (tab.getSet_id() != null && !"".equals(tab.getSet_id())) {

					if (tab.getEnclosure_name() != null && !"".equals(tab.getEnclosure_name())) {
						// 赋值
						tab.setId(UUID.randomUUID().toString().replace("-", ""));
						tab.setCreator(sysLog.getCreator());
						tab.setModify(tab.getCreator());

						// 执行保存
						int tt = ienclosuresService.save(tab);
						if (tt >= 1) {
							josn.msg = "保存成功!";
							josn.state = 200;
							josn.data = tab;
						} else {
							josn.msg = "保存失败!";
						}
					} else {
						josn.msg = "附件名称不能为空!";
					}
				} else {
					josn.msg = "setid不能为空!";
				}
			} else {
				josn.msg = "请填写附件信息!";
			}

		} catch (Exception e) {
			sysLog.setIs_bug(1);
			josn.msg = e.getMessage();
			josn.state = 500;
		}
		// 操作说明
		sysLog.setExceptionally_detailed(josn.msg);
		// 添加系统日志
		isystem_logService.add(sysLog);

		return josn;
	}

}
