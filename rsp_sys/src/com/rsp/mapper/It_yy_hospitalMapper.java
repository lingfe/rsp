package com.rsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.rsp.inter.It_yy_hospital;
import com.rsp.mapperProvider.PublicMapperProvider;
import com.rsp.model.Tab_t_mark;
import com.rsp.model.Tab_t_yy_hospital;

public interface It_yy_hospitalMapper extends It_yy_hospital {

	@Delete("delete from t_yy_hospital where id=#{id}")
	int deleteWhereId(@Param("id")String id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getCount")
	int getCount(Map<String, Object> map);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getWhere")
	List<Tab_t_mark> getWhere(Map<String, Object> map);
	
	@Select("select * from t_yy_hospital where id=#{id}")
	Tab_t_yy_hospital getWhereId(@Param("id")String id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getPage")
	List<Tab_t_yy_hospital> pageSelect(Map<String, Object> map);
	
	@Insert("INSERT  INTO `t_yy_hospital`"
			+ "(`id`,"
			+ "pid,"
			+ "`hospital_name`,`sort`,"
			+ "stop_flag,stop_code,stop_name,stop_date,stop_explain,"
			+ "`remark`,"
			+ "`state`,`crt_code`,crt_name,crt_date,"
			+ "`modify_code`,`modify_name`,`modify_date`,`version`) "
			+ "VALUES "
			+ "(#{id},"
			+ "#{pid},"
			+ "#{hospital_name},#{sort},"
			+ "#{stop_flag},#{stop_code},#{stop_name},#{stop_date},#{stop_explain},"
			+ "#{remark},"
			+ "#{state},#{crt_code},#{crt_name},#{crt_date},"
			+ "#{modify_code},#{modify_name},#{modify_date},#{version})")
	int save(Tab_t_yy_hospital tab);
	
	@Update("update t_yy_hospital set "
			+ "hospital_name=#{hospital_name},"
			+ "sort=#{sort},"
			+ "stop_flag=#{stop_flag},stop_code=#{stop_code},stop_name=#{stop_name},stop_date=#{stop_date},stop_explain=#{stop_explain},"
			+ "remark=#{remark},"
			+ "state=#{state},modify_code=#{modify_code},modify_name=#{modify_name},modify_date=#{modify_name},version=#{version} "
			+ "where id=#{id}")
	int update(Tab_t_yy_hospital tab);
}
