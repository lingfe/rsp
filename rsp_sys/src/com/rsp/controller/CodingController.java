package com.rsp.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rsp.model.JosnModel;
import com.rsp.model.Tab_coding;
import com.rsp.service.IcodingService;

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

	@Autowired
	private IcodingService icodingService;
	
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
    public JosnModel<Tab_coding> save(Tab_coding coding){
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
    public JosnModel<Tab_coding> deleteWhereId(@RequestParam(value="id",required=false)String id){
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
