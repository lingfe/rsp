package com.rsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.rsp.inter.Iequipment_walkthrough;
import com.rsp.mapperProvider.PublicMapperProvider;
import com.rsp.model.Tab_equipment_walkthrough;

public interface Iequipment_walkthroughMapper extends Iequipment_walkthrough {

	@SelectProvider(type=PublicMapperProvider.class,method="getWhere")
	List<Tab_equipment_walkthrough> getWhere(Map<String, Object> map);
	
	@Delete("delete from equipment_walkthrough where id=#{id}")
	int deleteWhereId(@Param("id")String id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getCount")
	int getCount(Map<String, Object> map);
	
	@Select("select * from equipment_walkthrough where id=#{id}")
	Tab_equipment_walkthrough getWhereId(@Param("id")String id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getPage")
	List<Tab_equipment_walkthrough> pageSelect(Map<String, Object> map);
	
	@Insert("INSERT  INTO `equipment_walkthrough`"
			+ "(`id`,"
			+ "equipment_id,"
			+ "is_walkthrough,walkthrough_name,walkthrough_cycle,next_time,"
			+ "`state`,`crt_code`,crt_name,crt_date,"
			+ "`modify_code`,`modify_name`,`modify_date`,`version`) "
			+ "VALUES "
			+ "(#{id},"
			+ "#{equipment_id},"
			+ "#{is_walkthrough},#{walkthrough_name},#{walkthrough_cycle},#{next_time},"
			+ "#{state},#{crt_code},#{crt_name},#{crt_date},"
			+ "#{modify_code},#{modify_name},#{modify_date},#{version})")
	int save(Tab_equipment_walkthrough tab);
	
	@Update("update equipment_walkthrough set "
			+ "equipment_id=#{equipment_id},"
			+ "is_walkthrough=#{is_walkthrough},walkthrough_name=#{walkthrough_name},walkthrough_cycle=#{walkthrough_cycle},next_time=#{next_time},"
			+ "state=#{state},modify_code=#{modify_code},modify_name=#{modify_name},modify_date=#{modify_name},version=#{version} "
			+ "where id=#{id}")
	int update(Tab_equipment_walkthrough tab);
}
