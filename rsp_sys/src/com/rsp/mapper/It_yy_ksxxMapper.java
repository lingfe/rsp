package com.rsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.rsp.inter.It_yy_ksxx;
import com.rsp.mapperProvider.PublicMapperProvider;
import com.rsp.model.Tab_t_yy_ksxx;

public interface It_yy_ksxxMapper extends It_yy_ksxx {

	@Delete("delete from t_yy_ksxx where id=#{id}")
	int deleteWhereId(@Param("id")String id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getCount")
	int getCount(Map<String, Object> map);
	
	@Select("select * from t_yy_ksxx where id=#{id}")
	Tab_t_yy_ksxx getWhereId(@Param("id")String id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getPage")
	List<Tab_t_yy_ksxx> pageSelect(Map<String, Object> map);
	
	@Insert("INSERT  INTO `t_yy_ksxx`"
			+ "(`id`,hospital_id,`parent_id`,kssx_id,`ks_name`,"
			+ "`ks_tel`,`ks_fzr`,"
			+ "`stop_flag`,`stop_code`,`stop_name`,`stop_date`,`stop_explain`,"
			+ "remark,"
			+ "`state`,`crt_code`,`crt_name`,`crt_date`,`modify_code`,`modify_name`,`modify_date`,`version`) "
			+ "VALUES "
			+ "(#{id},#{hospital_id},#{parent_id},#{kssx_id},#{ks_name},"
			+ "#{ks_tel},#{ks_fzr},"
			+ "#{stop_flag},#{stop_code},#{stop_name},#{stop_date},#{stop_explain},"
			+ "#{remark},"
			+ "#{state},#{crt_code},#{crt_name},#{crt_date},#{modify_code},#{modify_name},#{modify_date},#{version})")
	int save(Tab_t_yy_ksxx tab);
	
	@Update("update t_yy_ksxx set  "
			+ "hospital_id=#{hospital_id},parent_id=#{parent_id},kssx_id=#{kssx_id},ks_name=#{ks_name},"
			+ "ks_tel=#{ks_tel},ks_fzr=#{ks_fzr},"
			+ "sort=#{sort},stop_flag=#{stop_flag},stop_code=#{stop_code},stop_name=#{stop_name},stop_date=#{stop_date},stop_explain=#{stop_explain},"
			+ "remark=#{remark},"
			+ "state=#{state},modify_code=#{modify_code},modify_name=#{modify_name},modify_date=#{modify_name},version=#{version} "
			+ "where id=#{id}")
	int update(Tab_t_yy_ksxx tab);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getWhere")
	List<Tab_t_yy_ksxx> getWhere(Map<String, Object> map);
}
