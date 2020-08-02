package com.rsp.controller.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.rsp.util.GetDateString;

public class UploadUtils {
	
	
	/**
	 * 
	 * 文件上传(单个)
	 * 通过CommonsMultipartFile 上传
	 * 使用文件输入输出流方式
	 * @author lingfe     
	 * @created 2019年4月19日 上午10:26:01  
	 * @param file	文件
	 * @param request 请求
	 * @return map 反馈
	 * @throws Exception
	 */
	public static Map<String, Object> fileUpLoad(CommonsMultipartFile file, 
			HttpServletRequest request) throws Exception {
		//实例化对象
		Map<String, Object> map=new HashMap<String, Object>();
				
		// 文件原名称
		String old_name=file.getOriginalFilename();
		map.put("old_name", old_name);
		
		// 截取文件后缀,文件类型
        String type=old_name.indexOf(".")!=-1?old_name.substring(old_name.lastIndexOf(".")+1, old_name.length()):null;
        // 判断文件类型是否为空
        if (type!=null) {
        	//存放与访问目录,默认其他
        	String set_get="other\\";
        	// 验证后缀
            if ("GIF".equals(type.toUpperCase())
            		||"PNG".equals(type.toUpperCase())
            		||"JPG".equals(type.toUpperCase())
            		||"ICO".equals(type.toUpperCase())) {
            	//1=图片文件,0=其他文件,默认0
            	map.put("type", 1);
            	//更改为图片目录
            	set_get="images\\";
            }else{
            	//1=图片文件,0=其他文件,默认0
            	map.put("type", 1);
            }
            
            // 得到文件上传目的位置的真实路径\项目在容器中实际发布运行的根路径,储存路径
            //String realPath=request.getSession().getServletContext().getRealPath("/");
            String realPath=SYS_GET.SET_IMG_PATH_URL;
            map.put("storage_path", realPath);
            
            // 重新定义文件名称防止重复
            String prefix = UUID.randomUUID().toString().replace("-", "");
    		map.put("prefix", prefix);
            //将当前日期作为目录
    		String dateStr=GetDateString.getDate();
    		//路径拼接
    		String trueFileName=set_get+dateStr+"\\"+prefix+"."+type;
        
    		// 设置存放图片文件的路径,图片完整路径，全路径
            String full_path=realPath+trueFileName;
            //是否创建文件夹
            File file1 = new File(full_path);
    		if (!file1.exists()) {
    			// 如果该目录不存在，就创建此抽象路径名指定的目录。
    			//file1.mkdir();
    			file1.mkdirs();
    		}
    		// 全路径
            map.put("full_path", full_path);
            System.out.println("存放文件的全路径:"+full_path);
            
            // 转存文件到指定的路径
            file.transferTo(new File(full_path));
            System.out.println("文件成功上传到指定目录下");
            
            //文件大小
            map.put("size", file.getSize());
            
            // 可加上域名访问的可浏览路径
            map.put("url", trueFileName.replace("\\", "/"));
            map.put("state", 200);
            map.put("msg", "上传成功!");
        }
		
		return map;
	}


	/**
	 * 
	 *	文件上传(单个)
	 *  通过MultipartFile 上传，
	 *  使用转存,可设定文件夹方式
	 * @author lingfe     
	 * @created 2019年4月19日 上午10:06:37  
	 * @param file	文件
	 * @param request	请求
	 * @param folder	文件夹
	 * @return map 反馈
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static Map<String, Object> fileUpLoad(MultipartFile file,
    		HttpServletRequest request,
    		String folder) throws IllegalStateException, IOException{
		//实例化对象
		Map<String, Object> map=new HashMap<String, Object>();
		
		//定义变量
        String path=null;// 文件路径
        String type=null;// 文件类型
        String fileName=file.getOriginalFilename();// 文件原名称
        map.put("old_name", fileName);
        System.out.println("上传的文件原名称:"+fileName);
        
        // 截取文件后缀
        type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
        // 判断文件类型是否为空
        if (type!=null) {
        	//存放与访问目录,默认其他
        	String set_get="other\\";
        	
        	// 验证后缀
            if ("GIF".equals(type.toUpperCase())
            		||"PNG".equals(type.toUpperCase())
            		||"JPG".equals(type.toUpperCase())
            		||"ICO".equals(type.toUpperCase())) {
            	//1=图片文件,0=其他文件,默认0
            	map.put("type", 1);
            	//更改为图片目录
            	set_get="images\\";
            }else{
            	//1=图片文件,0=其他文件,默认0
            	map.put("type", 1);
            }
            
            //后缀
        	map.put("suffix", type);
        	
            // 项目在容器中实际发布运行的根路径,储存路径
            //String realPath=request.getSession().getServletContext().getRealPath("/");
            String realPath=SYS_GET.SET_IMG_PATH_URL;
            map.put("storage_path", realPath);
            
            // 自定义的文件名称,系统重新设定的名称
            String uuid=UUID.randomUUID().toString().replace("-", "");//uuid
            map.put("custom_name", uuid);
            String trueFileName=uuid+"."+type;
            
            //将当前日期作为目录
        	String dateStr=getYiJieDate();
        	
        	//验证文件夹是否为空
            if(!StringUtils.isEmpty(folder)){
            	//路径拼接
            	trueFileName=set_get+folder+"\\"+dateStr+"\\"+trueFileName;
            }else{
            	trueFileName=set_get+dateStr+"\\"+trueFileName;
            }
            
            // 设置存放图片文件的路径,图片完整路径，全路径
            path=realPath+trueFileName;
            //是否创建文件夹
            File file1 = new File(path);
    		if (!file1.exists()) {
    			// 如果该目录不存在，就创建此抽象路径名指定的目录。
    			//file1.mkdir();
    			file1.mkdirs();
    		}
    		// 全路径
            map.put("full_path", path);
            System.out.println("存放图片文件的路径:"+path);
            // 转存文件到指定的路径
            file.transferTo(new File(path));
            System.out.println("文件成功上传到指定目录下");
            
            //文件大小
            map.put("size", file.getSize());
            
            // 可加上域名访问的可浏览路径
            map.put("url", trueFileName.replace("\\", "/"));
            map.put("state", 200);
            map.put("msg", "上传成功!");
            
            return map;
        }else {
            System.out.println("文件类型为空");
            map.put("msg", "文件类型为空");
            return null;
        }
	}
	
	
	/**
	 * 
	 * 删除文件
	 * @author lingfe     
	 * @created 2019年4月19日 上午11:02:11  
	 * @param full_path 全路径\根路径
	 */
	public static void deleteFile(String full_path){
		//验证图片路径
		if(full_path!=null&&!"".equals(full_path)){
			//删除文件,带路径的文件名
	        File file = new File(full_path);
	        if(file.delete()){
	            System.out.println("deleted path="+full_path);
	        }
		}
	}
	
    /** 
     * 得到真实文件名 
     * @param fileName 
     * @return 
     */  
    public static String subFileName(String fileName){  
        //查找最后一个 \ (文件分隔符)位置  
        int index = fileName.lastIndexOf(File.separator);  
        if(index == -1){  
            //没有分隔符，说明是真实名称  
            return fileName;  
        }else {  
            return fileName.substring(index+1);  
        }  
    }  
  
    /** 
     * 获得hashcode 生成二级目录 
     * @param uuidFileName 
     * @return 
     */  
    public static String generateRandomDir(String uuidFileName){  
        int hashCode = uuidFileName.hashCode();//得到它的hashcode编码  
        //一级目录  
        int d1 = hashCode & 0xf;  
        //二级目录  
        int d2 = (hashCode >> 4) & 0xf;  
        return "/"+d1+"/"+d2;  
    } 
    
    /**
     * 
     * 获取当前的年月日
     * @author lingfe     
     * @created 2017年12月20日 下午1:59:35  
     * @return
     */
    public static String getYiJieDate(){
        Date d = new Date();  
        System.out.println(d);  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
        String dateNowStr = sdf.format(d);   
        
        return dateNowStr;
    }



    
    /**
     * 
     * 文件下载(单个)
     * @author lingfe     
     * @created 2019年4月19日 上午9:37:02  
     * @param request
     * @param fileName
     * @return 
     * @throws IOException 
     */
	public static ResponseEntity<byte[]> downLoad(HttpServletRequest request, String realPath) throws IOException {
		// 获取ServletContext的对象 代表当前WEB应用
		//ServletContext servletContext = request.getServletContext();
		//fileName = "童话镇.mp3";
		// 得到文件所在位置
		//String realPath = servletContext.getRealPath("/fileUpload/" + realPath);
		// 将该文件加入到输入流之中
		InputStream in = new FileInputStream(new File(realPath));
		byte[] body = null;
		// 返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
		body = new byte[in.available()];
		// 读入到输入流里面
		in.read(body);
		
		
		HttpHeaders headers=new HttpHeaders();//设置响应头
	    headers.add("Content-Disposition", "attachment;filename="+realPath);
	    HttpStatus statusCode = HttpStatus.OK;//设置响应吗
	    ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(body, headers, statusCode);
	    return response;
	}
}
