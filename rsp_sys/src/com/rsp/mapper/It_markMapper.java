package com.rsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.rsp.inter.It_mark;
import com.rsp.mapperProvider.PublicMapperProvider;
import com.rsp.model.Tab_t_mark;

public interface It_markMapper extends It_mark {

	@Delete("delete from t_mark where id=#{id}")
	int deleteWhereId(@Param("id")String id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getWhere")
	List<Tab_t_mark> getWhere(Map<String, Object> map);
	
	@Select("select * from t_mark where id=#{id}")
	Tab_t_mark getWhereId(@Param("id")String id);
	
	@Insert("INSERT  INTO `t_mark`"
			+ "(id,yw_id,"
			+ "`state`,`crt_code`,crt_name,crt_date,"
			+ "`modify_code`,`modify_name`,`modify_date`,`version`) "
			+ "VALUES "
			+ "(#{id},#{yw_id},"
			+ "#{state},#{crt_code},#{crt_name},#{crt_date},"
			+ "#{modify_code},#{modify_name},#{modify_date},#{version})")
	int save(Tab_t_mark tab);
}
