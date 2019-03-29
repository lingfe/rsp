package com.rsp.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import com.rsp.inter.Iproject_manage;

public interface Iproject_manageMapper extends Iproject_manage {

	
	@Delete("delete from project_manage where id=#{id}")
	int deleteWhereId(@Param("id")String id);
	
	
	
}
