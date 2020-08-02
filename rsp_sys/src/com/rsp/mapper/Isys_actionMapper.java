package com.rsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.rsp.inter.Isys_action;
import com.rsp.mapperProvider.PublicMapperProvider;
import com.rsp.model.Tab_sys_action;

public interface Isys_actionMapper extends Isys_action {

	@Delete("delete from sys_action where id=#{id}")
	int deleteWhereId(@Param("id")Integer id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getCount")
	int getCount(Map<String, Object> map);
	
	@Select("select * from sys_action where id=#{id}")
	Tab_sys_action getWhereId(@Param("id")Integer id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getPage")
	List<Tab_sys_action> pageSelect(Map<String, Object> map);
	
	@Insert("INSERT  INTO `sys_action`"
			+ "(action_name,"
			+ "`state`,`crt_code`,crt_name,crt_date,"
			+ "`modify_code`,`modify_name`,`modify_date`,`version`) "
			+ "VALUES "
			+ "(#{action_name},"
			+ "#{state},#{crt_code},#{crt_name},#{crt_date},"
			+ "#{modify_code},#{modify_name},#{modify_date},#{version})")
	int save(Tab_sys_action tab);
	
	@Update("update sys_action set "
			+ "action_name=#{action_name},"
			+ "state=#{state},modify_code=#{modify_code},modify_name=#{modify_name},modify_date=#{modify_name},version=#{version} "
			+ "where id=#{id}")
	int update(Tab_sys_action tab);
}
