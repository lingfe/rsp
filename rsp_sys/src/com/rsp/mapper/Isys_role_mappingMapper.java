package com.rsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.rsp.inter.Isys_role_mapping;
import com.rsp.mapperProvider.PublicMapperProvider;
import com.rsp.model.Tab_sys_role_mapping;

public interface Isys_role_mappingMapper extends Isys_role_mapping {
	
	@Select("select * from sys_role_mapping where user_id=#{user_id} limit 0,1")
	Tab_sys_role_mapping getWhereUserId(@Param("user_id")String user_id);

	@Delete("delete from sys_role_mapping where id=#{id}")
	int deleteWhereId(@Param("id")String id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getCount")
	int getCount(Map<String, Object> map);
	
	@Select("select * from sys_role_mapping where id=#{id}")
	Tab_sys_role_mapping getWhereId(@Param("id")String id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getPage")
	List<Tab_sys_role_mapping> pageSelect(Map<String, Object> map);
	
	@Insert("INSERT  INTO `sys_role_mapping`"
			+ "(id,user_id,role_id,"
			+ "`state`,`crt_code`,crt_name,crt_date,"
			+ "`modify_code`,`modify_name`,`modify_date`,`version`) "
			+ "VALUES "
			+ "(#{id},#{user_id},#{role_id},"
			+ "#{state},#{crt_code},#{crt_name},#{crt_date},"
			+ "#{modify_code},#{modify_name},#{modify_date},#{version})")
	int save(Tab_sys_role_mapping tab);
	
	@Update("update sys_role_mapping set "
			+ "user_id=#{user_id},role_id=#{role_id},"
			+ "state=#{state},modify_code=#{modify_code},modify_name=#{modify_name},modify_date=#{modify_name},version=#{version} "
			+ "where id=#{id}")
	int update(Tab_sys_role_mapping tab);
}
