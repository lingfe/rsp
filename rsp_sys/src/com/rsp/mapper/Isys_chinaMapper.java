package com.rsp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.rsp.inter.Isys_china;
import com.rsp.model.Tab_sys_china;

public interface Isys_chinaMapper extends Isys_china{
	
	@Select("select * from sys_china where china_name like '%${china_name}%'")
	List<Tab_sys_china> selectLike(@Param("china_name")String china_name);
	
	@Insert("INSERT  INTO `sys_china`"
			+ "(`id`,`parent_id`,`china_name`) "
			+ "VALUES "
			+ "(#{id},#{parent_id},#{china_name})")
	int save(Tab_sys_china china);
	
	@Delete("delete from sys_china where parent_id=#{parent_id}")
	int deleteWherePid(@Param("parent_id")String parent_id);
	
	@Delete("delete from sys_china where id=#{id}")
	int deleteWhereId(@Param("id")String id);

	@Select("select * from sys_china where pid=#{pid}")
	List<Tab_sys_china> selectWhwerePid(String pid);
}
