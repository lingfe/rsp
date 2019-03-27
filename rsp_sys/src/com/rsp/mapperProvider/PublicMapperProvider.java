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
        
        //流程设置表_流程名称
        if(map.get("process_name")!=null){
        	whereClause.append(" and process_name like '%").append(map.get("process_name")).append("%'");
        }
        
        //设备预排表
        //医院名称
        if(map.get("infirmary_name")!=null){
        	whereClause.append(" and infirmary_name='").append(map.get("infirmary_name")).append("'");
		}
        //科室
        if(map.get("department")!=null){
        	whereClause.append(" and department='").append(map.get("department")).append("'");
		}
		if(map.get("is_obtain_rdtl")!=null){
			whereClause.append(" and is_obtain_rdtl=").append(map.get("infirmary_name"));
		}
		if(map.get("is_obtain_rsl")!=null){
			whereClause.append(" and is_obtain_rsl=").append(map.get("infirmary_name"));
		}
		if(map.get("is_obtain_lcl")!=null){
			whereClause.append(" and is_obtain_lcl=").append(map.get("infirmary_name"));
		}
		if(map.get("is_scrap")!=null){
			whereClause.append(" and is_scrap='").append(map.get("is_scrap"));
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
        //编码表_编码名称
        if(map.get("coding_name")!=null){
        	whereClause.append(" and coding_name like '%").append(map.get("coding_name")).append("%'");
        }
        
        
        //流程设置表_省份
        if(map.get("province")!=null){
        	whereClause.append(" and province='").append(map.get("province")).append("'");
        }
        //流程设置表_流程类型
        if(map.get("process_type")!=null){
        	whereClause.append(" and process_type='").append(map.get("process_type")).append("'");
        }
        //流程设置表_流程分类
        if(map.get("process_classification")!=null){
        	whereClause.append(" and process_classification='").append(map.get("process_classification")).append("'");
        }
        
        //设备预排表
        //医院名称
        if(map.get("infirmary_name")!=null){
        	whereClause.append(" and infirmary_name='").append(map.get("infirmary_name")).append("'");
		}
        //科室
        if(map.get("department")!=null){
        	whereClause.append(" and department='").append(map.get("department")).append("'");
		}
		if(map.get("is_obtain_rdtl")!=null){
			whereClause.append(" and is_obtain_rdtl=").append(map.get("infirmary_name"));
		}
		if(map.get("is_obtain_rsl")!=null){
			whereClause.append(" and is_obtain_rsl=").append(map.get("infirmary_name"));
		}
		if(map.get("is_obtain_lcl")!=null){
			whereClause.append(" and is_obtain_lcl=").append(map.get("infirmary_name"));
		}
		if(map.get("is_scrap")!=null){
			whereClause.append(" and is_scrap='").append(map.get("is_scrap"));
		}
        
        //状态
        if(map.get("state")!=null){
        	whereClause.append(" and state=").append(map.get("state"));
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
