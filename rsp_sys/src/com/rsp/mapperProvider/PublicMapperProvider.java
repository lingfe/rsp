package com.rsp.mapperProvider;

import java.util.Map;

/**
 * 
  * 文件名：PublicMapperProvider.java
  * 描述： 公共查询映射类
  * 修改人： lingfe
  * 修改时间：2019年3月19日 下午4:17:21
  * 修改内容：
 */
public class PublicMapperProvider  {
	
	//获取总数据量
	public String getCount(Map<String, Object> map){
		//实例化对象
		StringBuilder whereClause = new StringBuilder();
        whereClause.append("select count(*) from ");
        whereClause.append(map.get("table"));
        whereClause.append(" where 1=1  ");
        
        //验证条件
        //状态
        if(map.get("state")!=null){
        	whereClause.append(" and state=").append(map.get("state"));
        }
        //生产厂家表_名称、区域信息表_名称
        if(map.get("name")!=null){
        	whereClause.append(" and name like '%").append(map.get("name")).append("%'");
        }
        //编码表_编码名称
        if(map.get("coding_name")!=null){
        	whereClause.append(" and coding_name like '%").append(map.get("coding_name")).append("%'");
        }
        
        return whereClause.toString();
	}

	
	//分页
	public String getPage(Map<String, Object> map){
		StringBuilder whereClause = new StringBuilder();
        whereClause.append("select * from ");
        whereClause.append(map.get("table"));
        whereClause.append(" where 1=1  ");
        //验证条件
        //编码表_编码类型
        if(map.get("coding_type")!=null){
        	whereClause.append(" and coding_type=").append(map.get("coding_type"));
        }
        //状态
        if(map.get("state")!=null){
        	whereClause.append(" and state=").append(map.get("state"));
        }
        //编码表_编码名称
        if(map.get("coding_name")!=null){
        	whereClause.append(" and coding_name like '%").append(map.get("coding_name")).append("%'");
        }
        whereClause.append(" order by  mdate desc ");

		//条件追加,分页查询
		if(map != null){
			if(map.get("pageIndex")!=null && map.get("pageNum")!=null){
				int pageIndex=Integer.parseInt(map.get("pageIndex").toString());
				int pageNum=Integer.parseInt(map.get("pageNum").toString());
				whereClause.append(" limit ").append((pageIndex-1)*pageNum).append(",").append(pageNum);
			}
		}
		
		return whereClause.toString();
	}


}
