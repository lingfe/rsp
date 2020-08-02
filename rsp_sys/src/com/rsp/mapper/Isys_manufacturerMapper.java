package com.rsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.rsp.inter.Isys_manufacturer;
import com.rsp.mapperProvider.PublicMapperProvider;
import com.rsp.model.Tab_sys_manufacturer;

public interface Isys_manufacturerMapper extends Isys_manufacturer {
	
	
	@Select("select * from sys_manufacturer where id=#{id}")
	Tab_sys_manufacturer getWhereId(@Param("id")String id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getCount")
	int getCount(Map<String, Object> map);
	
	@Update("update sys_manufacturer set "
			+ "hospital_id=#{hospital_id},manufacturer_name=#{manufacturer_name},"
			+ "flag_stop=#{flag_stop},stop_code=#{stop_code},stop_name=#{stop_name},stop_date=#{stop_date},stop_explain=#{stop_explain},"
			+ "remark=#{remark},"
			+ "state=#{state},modify_code=#{modify_code},modify_name=#{modify_name},modify_date=#{modify_name},version=#{version} "
			+ "where id=#{id}")
	int update(Tab_sys_manufacturer man);

	@Insert("INSERT  INTO `sys_manufacturer`"
			+ "(`id`,hospital_id,`manufacturer_name`,"
			+ "flag_stop,stop_code,stop_name,stop_date,stop_explain,"
			+ "`remark`,"
			+ "`state`,`crt_code`,crt_name,crt_date,"
			+ "`modify_code`,`modify_name`,`modify_date`,`version`) "
			+ "VALUES "
			+ "(#{id},#{hospital_id},#{manufacturer_name},"
			+ "#{flag_stop},#{stop_code},#{stop_name},#{stop_date},#{stop_explain},"
			+ "#{remark},"
			+ "#{state},#{crt_code},#{crt_name},#{crt_date},"
			+ "#{modify_code},#{modify_name},#{modify_date},#{version})")
	int save(Tab_sys_manufacturer man);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getPage")
	List<Tab_sys_manufacturer> pageSelect(Map<String, Object> map);
	
	@Delete("delete from sys_manufacturer where id=#{id}")
	int deleteWhereId(@Param("id")String id);
}
