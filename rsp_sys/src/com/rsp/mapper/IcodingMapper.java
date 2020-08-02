package com.rsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.rsp.inter.Icoding;
import com.rsp.mapperProvider.PublicMapperProvider;
import com.rsp.model.Tab_coding;

public interface IcodingMapper extends Icoding {
	
	@SelectProvider(type=PublicMapperProvider.class,method="getWhere")
	List<Tab_coding> getWhere(Map<String, Object> map);
	
	@Select("select * from sys_coding ORDER BY sort asc LIMIT 1 ")
	Tab_coding getOne_asc();
	
	@Select("select * from sys_coding ORDER BY sort DESC LIMIT 1")
	Tab_coding getOne_desc();
	
	@Select("select * from sys_coding where coding_name=#{coding_name}")
	Tab_coding getWhereCodingName(@Param("coding_name")String coding_name);
	
	@Select("select * from sys_coding where id=#{id}")
	Tab_coding getWhereId(@Param("id")String id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getCount")
	int getCount(Map<String, Object> map);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getPage")
	List<Tab_coding> pageSelect(Map<String, Object> map);

	@Insert("INSERT  INTO `sys_coding`"
			+ "(`id`,"
			+ "hospital_id,"
			+ "`coding_type`,`coding_name`,"
			+ "flag_yp,yp_name,yp_cycle,next_time,"
			+ "stop_flag,stop_code,stop_name,stop_date,stop_explain,"
			+ "`remark`,"
			+ "`state`,`crt_code`,crt_name,crt_date,"
			+ "`modify_code`,`modify_name`,`modify_date`,`version`) "
			+ "VALUES "
			+ "(#{id},"
			+ "#{hospital_id},"
			+ "#{coding_type},#{coding_name},"
			+ "#{flag_yp},#{yp_name},#{yp_cycle},#{next_time},"
			+ "#{stop_flag},#{stop_code},#{stop_name},#{stop_date},#{stop_explain},"
			+ "#{remark},"
			+ "#{state},#{crt_code},#{crt_name},#{crt_date},"
			+ "#{modify_code},#{modify_name},#{modify_date},#{version})")
	int save(Tab_coding coding);
	
	@Delete("delete from sys_coding where id=#{id}")
	int deleteWhereId(@Param("id")String id);
	
	@Update("update sys_coding set "
			+ "hospital_id=#{hospital_id},"
			+ "coding_type=#{coding_type},coding_name=#{coding_name},"
			+ "flag_yp=#{flag_yp},yp_name=#{yp_name},yp_cycle=#{yp_cycle},next_time=#{next_time},"
			+ "sort=#{sort},"
			+ "stop_flag=#{stop_flag},stop_code=#{stop_code},stop_name=#{stop_name},stop_date=#{stop_date},stop_explain=#{stop_explain},"
			+ "remark=#{remark},"
			+ "state=#{state},modify_code=#{modify_code},modify_name=#{modify_name},modify_date=#{modify_name},version=#{version} "
			+ "where id=#{id}")
	int update(Tab_coding coding);
}
