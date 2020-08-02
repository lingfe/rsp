package com.rsp.mapperProvider;

import java.lang.reflect.Field;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.rsp.util.GetDateString;

/**
 * 
  * 文件名：PublicMapperProvider.java
  * 描述： 公共查询映射类
  * 修改人： lingfe
  * 修改时间：2019年3月19日 下午4:17:21
  * 修改内容：
 */
public class PublicMapperProvider<T>  {
	
	//验证值
	protected String obj(Object obj,Field fd){
		StringBuilder sql_value=new StringBuilder();
		//验证
		if(obj == null ){
			sql_value.append("NULL");
		}else if(fd.getType().getName().equals("java.lang.String")){
			sql_value.append("'").append(obj).append("'");
		}else if(fd.getType().getName().equals("java.util.Date")){
			sql_value.append(GetDateString.getDateTime());
		}else{
			sql_value.append(obj);
		}
		
		return sql_value.toString();
	}
	
	//插入-insert
	public String insert(final T tab){
		return new SQL(){
			{
				//表
				//INSERT_INTO(tab.getClass().getSimpleName().toLowerCase());
				INSERT_INTO("sys_role");
				//得到字段数组
				Field[] field = tab.getClass().getDeclaredFields();
				//得到字段
				for (Field fd : field) {
					VALUES(fd.getName(),"#{"+fd.getName()+"}");
				}
			}
		}.toString();
	}

	
	//添加-add
	public String add(T tab){
		//实例化对象
		SQL sql=new SQL();
		//表
		//sql.INSERT_INTO(tab.getClass().getSimpleName().toLowerCase());
		sql.INSERT_INTO("sys_role");
		//得到字段数组
		Field[] field = tab.getClass().getDeclaredFields();
		//得到字段
		for (int i = 0; i < field.length; i++) {
			Field fd = field[i];
			try {
				//设置可访问
	            fd.setAccessible(true);
	            //赋值
	            Object obj=fd.get(tab);
	            sql.VALUES(fd.getName(),this.obj(obj, fd));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return sql.toString();
	}
	
	//保存-preservation
	public String preservation(T tab){
		//实例化对象
		StringBuilder sql = new StringBuilder();
		StringBuilder sql_field=new StringBuilder();
		StringBuilder sql_value=new StringBuilder();
		//得到字段数组
		Field[] field = tab.getClass().getDeclaredFields();
		//得到字段
		for (int i = 0; i < field.length; i++) {
			Field fd = field[i];
			try {
				//设置可访问
	            fd.setAccessible(true);
	            //赋值
	            Object obj=fd.get(tab);
	            sql_value.append(this.obj(obj, fd));
				//尾
				if(i<field.length-1){
					sql_field.append(field[i].getName()).append(",");
					sql_value.append(",");
				}else{
					sql_field.append(field[i].getName()).append(")");
					sql_value.append(")");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//field
		//sql.append("INSERT INTO ").append(tab.getClass().getSimpleName()).append(" (").append(sql_field);
		sql.append("INSERT INTO ").append("sys_role").append(" (").append(sql_field);
		//values
		sql.append(" VALUES (").append(sql_value);
		//返回sql
		return sql.toString();
	}
	
	//可选择方式:保存、添加、插入
	public String save(T tab){
		//实例化对象
		StringBuffer sql=new StringBuffer();
		
		//sql.append(this.insert(tab));
		//sql.append(this.add(tab));
		sql.append(this.preservation(tab));
		
		return sql.toString();
	}
	
	//根据条件查询
	public String getWhere(Map<String, Object> map){
		//实例化对象
		StringBuilder whereClause = new StringBuilder();
        whereClause.append("select * from ");
        whereClause.append(map.get("table"));
        whereClause.append(" where 1=1  ");
        
        //业务id(痕迹表，)
        if(map.get("yw_id")!=null){
        	whereClause.append(" and yw_id='").append(map.get("yw_id")).append("'");
		}
        
        //医院id
        if(map.get("hospital_id")!=null){
        	whereClause.append(" and hospital_id='").append(map.get("hospital_id")).append("'");
		}
        //设备id(设备预排表,痕迹表)
        if(map.get("equipment_id")!=null){
        	whereClause.append(" and equipment_id='").append(map.get("equipment_id")).append("'");
		}
        
        //痕迹信息表_痕迹id
        if(map.get("mark_id")!=null){
        	whereClause.append(" and mark_id='").append(map.get("mark_id")).append("'");
		}
        
        //编码表_是否预排
        if(map.get("flag_yp")!=null){
        	whereClause.append(" and flag_yp=").append(map.get("flag_yp"));
		}
        //编码表_编码类型
        if(map.get("coding_type")!=null){
        	whereClause.append(" and stop_flag=1 and coding_type=").append(map.get("coding_type"));
		}
        
        //排序(编码信息,科室管理)
        if(map.get("sort")!=null){
        	whereClause.append(" and sort=").append(map.get("sort"));
		}
        //科室管理表_科室名称
        if(map.get("ks_name")!=null){
        	whereClause.append(" and ks_name='").append(map.get("ks_name")).append("'");
		}
        
        //按照修改时间排序
        if(!"sys_fjxx".equals(map.get("table"))){
        	whereClause.append(" order by crt_date desc ");
        }
        
        
        return whereClause.toString();
	}

	
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
        //停用标记(编码表,科室管理表)
        if(map.get("stop_flag")!=null){
        	whereClause.append(" and stop_flag=").append(map.get("stop_flag"));
        }
        //业务id(附件信息表)
        if(map.get("yw_id")!=null){
        	whereClause.append(" and yw_id='").append(map.get("yw_id")).append("'");
		}
        
        
        //证照预排表	start
        if(map.get("hospital_name")!=null){
        	whereClause.append(" and hospital_name='").append(map.get("hospital_name")).append("'");
		}
		if(map.get("prearranged_type")!=null){
			whereClause.append(" and prearranged_type='").append(map.get("prearranged_type")).append("'");
		}
		if(map.get("licence_type")!=null){
			whereClause.append(" and licence_type='").append(map.get("licence_type")).append("'");
		}
		if(map.get("certificates_number")!=null){
			whereClause.append(" and certificates_number='").append(map.get("certificates_number")).append("'");
		}
		//end
        
        //生产厂家表_名称
        if(map.get("manufacturer_name")!=null){
        	whereClause.append(" and manufacturer_name like '%").append(map.get("manufacturer_name")).append("%'");
        }
        
        //区域信息表_名称
        if(map.get("china_name")!=null){
        	whereClause.append(" and china_name like '%").append(map.get("china_name")).append("%'");
        }
        
        //编码表_编码类型\附件信息表_编码类型
        if(map.get("coding_type")!=null){
        	whereClause.append(" and coding_type=").append(map.get("coding_type"));
        }
        //编码表_编码名称
        if(map.get("coding_name")!=null){
        	whereClause.append(" and coding_name like '%").append(map.get("coding_name")).append("%'");
        }
        
        //流程设置表_流程名称
        if(map.get("process_name")!=null){
        	whereClause.append(" and process_name like '%").append(map.get("process_name")).append("%'");
        }
        
        //设备档案表
        //医院名称
        if(map.get("hospital_name")!=null){
        	whereClause.append(" and hospital_name='").append(map.get("hospital_name")).append("'");
		}
        if(map.get("equipment_name")!=null){
        	whereClause.append(" and equipment_name='").append(map.get("equipment_name")).append("'");
		}

		
		//科室管理_科室编码id
		if(map.get("kssx_id")!=null){
			whereClause.append(" and kssx_id='").append(map.get("kssx_id")).append("'");
		}
		//科室管理_科室名称
		if(map.get("ks_name")!=null){
			whereClause.append(" and ks_name='").append(map.get("ks_name")).append("'");
		}
		//科室管理表_pid
		if(map.get("parent_id")!=null){
			whereClause.append(" and parent_id='").append(map.get("parent_id")).append("'");
		}
   
        return whereClause.toString();
	}

	
	//分页
	public String getPage(Map<String, Object> map){
		StringBuilder whereClause = new StringBuilder();
        whereClause.append("select * from ");
        whereClause.append(map.get("table"));
        whereClause.append(" where 1=1  ");
        
        //状态
        if(map.get("state")!=null){
        	whereClause.append(" and state=").append(map.get("state"));
        }
        //停用标记(编码表,科室管理表)
        if(map.get("stop_flag")!=null){
        	whereClause.append(" and stop_flag=").append(map.get("stop_flag"));
        }
        //业务id(附件信息表)
        if(map.get("yw_id")!=null){
        	whereClause.append(" and yw_id='").append(map.get("yw_id")).append("'");
		}
        
        
		//科室管理_科室属性
		if(map.get("kssx_id")!=null){
			whereClause.append(" and kssx_id='").append(map.get("kssx_id")).append("'");
		}
		//科室管理_科室名称
		if(map.get("ks_name")!=null){
			whereClause.append(" and ks_name='").append(map.get("ks_name")).append("'");
		}
		//科室管理表_pid
		if(map.get("parent_id")!=null){
			whereClause.append(" and parent_id='").append(map.get("parent_id")).append("'");
		}
        
		
		//生产厂家表_名称
        if(map.get("manufacturer_name")!=null){
        	whereClause.append(" and manufacturer_name like '%").append(map.get("manufacturer_name")).append("%'");
        }
        
        //区域信息表_名称
        if(map.get("china_name")!=null){
        	whereClause.append(" and china_name like '%").append(map.get("china_name")).append("%'");
        }
		
		
		//编码表_编码类型\附件信息表_编码类型
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
        
        //设备档案表
        //医院名称
        if(map.get("hospital_name")!=null){
        	whereClause.append(" and hospital_name='").append(map.get("hospital_name")).append("'");
		}
        if(map.get("equipment_name")!=null){
        	whereClause.append(" and equipment_name='").append(map.get("equipment_name")).append("'");
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
		
		
        //编码表
        if(map.get("table")=="sys_coding"){
        	whereClause.append(" order by  sort desc ");
        }
        //科室管理表
        else if(map.get("table")=="t_yy_ksxx"){
        	whereClause.append(" order by  sort desc ");
        }
        else if(!"sys_fjxx".equals(map.get("table"))){
        	whereClause.append(" order by  modify_date desc ");
        }

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
