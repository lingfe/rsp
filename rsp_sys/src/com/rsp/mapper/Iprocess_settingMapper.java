package com.rsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.rsp.inter.Iprocess_setting;
import com.rsp.mapperProvider.PublicMapperProvider;
import com.rsp.model.Tab_process_setting;

public interface Iprocess_settingMapper extends Iprocess_setting  {
	
	@Update("update process_setting set "
			+ "hospital_id=#{hospital_id},province=#{province},process_type=#{process_type},"
			+ "process_classification=#{process_classification},"
			+ "process_name=#{process_name},process_link=#{process_link},remark=#{remark},"
			+ "stop_flag=#{stop_flag},stop_code=#{stop_code},stop_name=#{stop_name},stop_date=#{stop_date},stop_explain=#{stop_explain},"
			+ "state=#{state},modify_code=#{modify_code},modify_name=#{modify_name},modify_date=#{modify_name},version=#{version} "
			+ "where id=#{id}")
	int update(Tab_process_setting pro);
	
	@Select("select * from process_setting where id=#{id}")
	Tab_process_setting getWhereId(@Param("id")String id);

	@Delete("delete from process_setting where id=#{id}")
	int deleteWhereId(@Param("id")String id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getCount")
	int getCount(Map<String, Object> map);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getPage")
	List<Tab_process_setting> pageSelect(Map<String, Object> map);
	
	@Insert("INSERT  INTO `process_setting`"
			+ "(`id`,hospital_id,province,`process_type`,`process_classification`,`process_name`,`process_link`,`remark`,"
			+ "stop_flag,stop_code,stop_name,stop_date,stop_explain,"
			+ "`state`,`crt_code`,crt_name,crt_date,"
			+ "`modify_code`,`modify_name`,`modify_date`,`version`) "
			+ "VALUES "
			+ "(#{id},#{hospital_id},#{province},#{process_type},#{process_classification},#{process_name},#{process_link},#{remark},"
			+ "#{stop_flag},#{stop_code},#{stop_name},#{stop_date},#{stop_explain},"
			+ "#{state},#{crt_code},#{crt_name},#{crt_date},"
			+ "#{modify_code},#{modify_name},#{modify_date},#{version})")
	int save(Tab_process_setting pro);
	
}
