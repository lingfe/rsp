package com.rsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.rsp.inter.Isys_department_coding;
import com.rsp.mapperProvider.PublicMapperProvider;
import com.rsp.model.Tab_sys_department_coding;

public interface Isys_department_codingMapper extends Isys_department_coding {

	@SelectProvider(type=PublicMapperProvider.class,method="getWhere")
	List<Tab_sys_department_coding> getWhere(Map<String, Object> map);
	
	@Delete("delete from sys_department_coding where id=#{id}")
	int deleteWhereId(@Param("id")String id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getCount")
	int getCount(Map<String, Object> map);
	
	@Select("select * from sys_department_coding where id=#{id}")
	Tab_sys_department_coding getWhereId(@Param("id")String id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getPage")
	List<Tab_sys_department_coding> pageSelect(Map<String, Object> map);
	
	@Insert("INSERT  INTO `sys_department_coding`"
			+ "(`id`,hospital_id,`coding_name`,"
			+ "stop_flag,stop_code,stop_name,stop_date,stop_explain,"
			+ "`remark`,"
			+ "`state`,`crt_code`,crt_name,crt_date,"
			+ "`modify_code`,`modify_name`,`modify_date`,`version`) "
			+ "VALUES "
			+ "(#{id},#{hospital_id},#{coding_name},"
			+ "#{stop_flag},#{stop_code},#{stop_name},#{stop_date},#{stop_explain},"
			+ "#{remark},"
			+ "#{state},#{crt_code},#{crt_name},#{crt_date},"
			+ "#{modify_code},#{modify_name},#{modify_date},#{version})")
	int save(Tab_sys_department_coding tab);
	
	@Update("update sys_department_coding set "
			+ "hospital_id=#{hospital_id},"
			+ "coding_name=#{coding_name},"
			+ "coding_sort=#{coding_sort},"
			+ "stop_flag=#{stop_flag},stop_code=#{stop_code},stop_name=#{stop_name},stop_date=#{stop_date},stop_explain=#{stop_explain},"
			+ "remark=#{remark},"
			+ "state=#{state},modify_code=#{modify_code},modify_name=#{modify_name},modify_date=#{modify_name},version=#{version} "
			+ "where id=#{id}")
	int update(Tab_sys_department_coding tab);
	
	@Select("select * from sys_department_coding where coding_name=#{coding_name}")
	Tab_sys_department_coding getWhereCodingName(@Param("coding_name")String coding_name);
	
}
