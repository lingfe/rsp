package com.rsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;

import com.rsp.inter.Isystem_log;
import com.rsp.mapperProvider.PublicMapperProvider;
import com.rsp.model.Tab_system_log;

public interface Isystem_logMapper  extends Isystem_log{

	@Insert("INSERT  INTO `system_log`"
			+ "(`id`,`ip`,`model_name`,`operation_type`,`is_bug`,`exceptionally_detailed`,`target_id`,"
			+ "`state`,`cdate`,`mdate`,`creator`,`modify`,`version`) "
			+ "VALUES "
			+ "(#{id},#{ip},#{model_name},#{operation_type},#{is_bug},#{exceptionally_detailed},#{target_id},"
			+ "#{state},#{cdate},#{mdate},#{creator},#{modify},#{version})")
	int add(Tab_system_log syslog);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getPage")
	List<Tab_system_log> pageSelect(Map<String, Object> map);
}
