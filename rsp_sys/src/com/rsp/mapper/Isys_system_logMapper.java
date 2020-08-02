package com.rsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.rsp.inter.Isys_system_log;
import com.rsp.mapperProvider.PublicMapperProvider;
import com.rsp.model.Tab_sys_system_log;

public interface Isys_system_logMapper  extends Isys_system_log{
	
	@Select("select count(*) from sys_system_log")
	int getCount();

	@Insert("INSERT  INTO `sys_system_log`"
			+ "(`id`,hospital_id,`ip`,`model_name`,`cz_type`,`flag_bug`,`yc_detailed`,`target_id`,target_name,"
			+ "`crt_code`,`crt_date`,`crt_name`)"
			+ "VALUES "
			+ "(#{id},#{hospital_id},#{ip},#{model_name},#{cz_type},#{flag_bug},#{yc_detailed},#{target_id},#{target_name},"
			+ "#{crt_code},#{crt_date},#{crt_name})")
	int add(Tab_sys_system_log syslog);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getPage")
	List<Tab_sys_system_log> pageSelect(Map<String, Object> map);
}
