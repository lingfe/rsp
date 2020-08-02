package com.rsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.rsp.inter.It_mark_info;
import com.rsp.mapperProvider.PublicMapperProvider;
import com.rsp.model.Tab_t_mark_info;

public interface It_mark_infoMapper extends It_mark_info {

	@Delete("delete from t_mark_info where id=#{id}")
	int deleteWhereId(@Param("id")String id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getWhere")
	List<Tab_t_mark_info> getWhere(Map<String, Object> map);
	
	@Select("select * from t_mark_info where id=#{id}")
	Tab_t_mark_info getWhereId(@Param("id")String id);
	
	@Insert("INSERT  INTO `t_mark_info`"
			+ "(id,mark_id,mark_info,"
			+ "`state`,`crt_code`,crt_name,crt_date,"
			+ "`modify_code`,`modify_name`,`modify_date`,`version`) "
			+ "VALUES "
			+ "(#{id},#{mark_id},#{mark_info},"
			+ "#{state},#{crt_code},#{crt_name},#{crt_date},"
			+ "#{modify_code},#{modify_name},#{modify_date},#{version})")
	int save(Tab_t_mark_info tab);
}
