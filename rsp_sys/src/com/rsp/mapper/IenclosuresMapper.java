package com.rsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.rsp.inter.Ienclosures;
import com.rsp.mapperProvider.PublicMapperProvider;
import com.rsp.model.Tab_enclosure;

public interface IenclosuresMapper extends Ienclosures {
	
	@SelectProvider(type=PublicMapperProvider.class,method="getWhere")
	List<Tab_enclosure> getWhere(Map<String, Object> map);

	@Delete("delete from sys_fjxx where id=#{id}")
	int deleteWhereId(@Param("id")String id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getCount")
	int getCount(Map<String, Object> map);
	
	@Select("select * from sys_fjxx where id=#{id}")
	Tab_enclosure getWhereId(@Param("id")String id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getPage")
	List<Tab_enclosure> pageSelect(Map<String, Object> map);
	
	@Insert("INSERT  INTO `sys_fjxx`"
			+ "(`id`,`hospital_id`,`coding_type`,`yw_id`,"
			+ "`flag_type`,path,fj_name_real,fj_name_sys,fj_name_easy,fj_size,fj_remark,"
			+ "`sort`,`crt_code`,crt_name,crt_date) "
			+ "VALUES "
			+ "(#{id},#{hospital_id},#{coding_type},#{yw_id},"
			+ "#{flag_type},#{path},#{fj_name_real},#{fj_name_sys},#{fj_name_easy},#{fj_size},#{fj_remark},"
			+ "#{sort},#{crt_code},#{crt_name},#{crt_date})")
	int save(Tab_enclosure tab);
	
	@Update("update sys_fjxx set "
			+ "yw_id=#{yw_id},enclosure_type=#{enclosure_type},enclosure_name=#{enclosure_name},"
			+ "enclosure_remark=#{enclosure_remark},"
			+ "state=#{state},modify_code=#{modify_code},modify_name=#{modify_name},modify_date=#{modify_name},version=#{version} "
			+ "where id=#{id}")
	int update(Tab_enclosure tab);
	
}
