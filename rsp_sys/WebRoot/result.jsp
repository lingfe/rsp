<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'result.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
		<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	 <form action="<%=basePath%>enclosure/fileUpLoad" method="post" enctype="multipart/form-data">
	   <h2>文件上传</h2>
	                文件:<input type="file" name="file"/><br/><br/><!-- 
	           <input type="file" name="uploadFile"/><br/><br/> -->
	      <input type="submit" value="上传"/>
	  </form>
  	
  	
  	<a href="<%=basePath%>enclosure/fileDownLoad?fileName=bec2b15a3e6d40bd8057170210e1e36e_简历-重庆1.0.doc">
        用户头像：bec2b15a3e6d40bd8057170210e1e36e_简历-重庆1.0.doc
    </a>   
  </body>
</html>
