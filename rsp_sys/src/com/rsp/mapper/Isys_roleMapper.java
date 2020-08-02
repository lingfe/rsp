package com.rsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.rsp.inter.Isys_role;
import com.rsp.mapperProvider.PublicMapperProvider;
import com.rsp.model.Tab_sys_role;

public interface Isys_roleMapper extends Isys_role {

	@Delete("delete from sys_role where id=#{id}")
	int deleteWhereId(@Param("id")String id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getCount")
	int getCount(Map<String, Object> map);
	
	@Select("select * from sys_role where id=#{id}")
	Tab_sys_role getWhereId(@Param("id")String id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getPage")
	List<Tab_sys_role> pageSelect(Map<String, Object> map);
	
	@InsertProvider(type=PublicMapperProvider.class,method="save")
	int save(Tab_sys_role tab);
	
	@Update("update sys_role set "
			+ "role_name=#{role_name},"
			+ "state=#{state},modify_code=#{modify_code},modify_name=#{modify_name},modify_date=#{modify_name},version=#{version} "
			+ "where id=#{id}")
	int update(Tab_sys_role tab);
}
