package com.rsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.rsp.inter.It_equipment_archives;
import com.rsp.mapperProvider.PublicMapperProvider;
import com.rsp.model.Tab_t_equipment_archives;

public interface It_equipment_archivesMapper extends It_equipment_archives  {

	@Delete("delete from t_equipment_archives where id=#{id}")
	int deleteWhereId(@Param("id")String id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getCount")
	int getCount(Map<String, Object> map);
	
	@Select("select * from t_equipment_archives where id=#{id}")
	Tab_t_equipment_archives getWhereId(@Param("id")String id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getPage")
	List<Tab_t_equipment_archives> pageSelect(Map<String, Object> map);
	
	@Insert("INSERT  INTO `t_equipment_archives`"
			+ "(`id`,"
			+ "hospital_id,hospital_name,"
			+ "`equipment_name`,`specification_type`,"
			+ "`device_class`,`equipment_number`,coding_id,`manfacturer_id`,"
			+ "`rated_voltage_kv`,`rated_current_ma`,`rated_power_w`,"
			+ "`equipment_nameplate_photo`,department_id,`department_name`,`purpose`,"
			+ "`location`,`is_obtain_rdtl`,`obtain_rdtl_date`,"
			+ "`is_obtain_rsl`,`obtain_rsl_date`,`is_obtain_lcl`,"
			+ "`obtain_lcl_date`,`source`,`installation_date`,"
			+ "`whereabouts`,is_scrap,`scrap_date`,"
			+ "stop_flag,stop_code,stop_name,stop_date,stop_explain,"
			+ "`state`,`crt_code`,crt_name,crt_date,"
			+ "`modify_code`,`modify_name`,`modify_date`,`version`) "
			+ "VALUES "
			+ "(#{id},"
			+ "#{hospital_id},#{hospital_name},"
			+ "#{equipment_name},#{specification_type},"
			+ "#{device_class},#{equipment_number},#{coding_id},#{manfacturer_id},"
			+ "#{rated_voltage_kv},#{rated_current_ma},#{rated_power_w},"
			+ "#{equipment_nameplate_photo},#{department_id},#{department_name},#{purpose},"
			+ "#{location},#{is_obtain_rdtl},#{obtain_rdtl_date},"
			+ "#{is_obtain_rsl},#{obtain_rsl_date},#{is_obtain_lcl},"
			+ "#{obtain_lcl_date},#{source},#{installation_date},"
			+ "#{whereabouts},#{is_scrap},#{scrap_date},"
			+ "#{stop_flag},#{stop_code},#{stop_name},#{stop_date},#{stop_explain},"
			+ "#{state},#{crt_code},#{crt_name},#{crt_date},"
			+ "#{modify_code},#{modify_name},#{modify_date},#{version})")
	int save(Tab_t_equipment_archives tab);
	
	@Update("update t_equipment_archives set "
			+ "hospital_id=#{hospital_id},hospital_name=#{hospital_name},"
			+ "equipment_name=#{equipment_name},specification_type=#{specification_type},"
			+ "device_class=#{device_class},equipment_number=#{equipment_number},coding_id=#{coding_id},manfacturer_id=#{manfacturer_id},"
			+ "rated_voltage_kv=#{rated_voltage_kv},rated_current_mA=#{rated_current_ma},rated_power_w=#{rated_power_w},"
			+ "equipment_nameplate_photo=#{equipment_nameplate_photo},department_id=#{department_id},department_name=#{department_name},purpose=#{purpose},"
			+ "location=#{location},is_obtain_rdtl=#{is_obtain_rdtl},obtain_rdtl_date=#{obtain_rdtl_date},"
			+ "is_obtain_rsl=#{is_obtain_rsl},obtain_rsl_date=#{obtain_rsl_date},is_obtain_lcl=#{is_obtain_lcl},"
			+ "obtain_lcl_date=#{obtain_lcl_date},source=#{source},installation_date=#{installation_date},"
			+ "whereabouts=#{whereabouts},is_scrap=#{is_scrap},scrap_date=#{scrap_date},"
			+ "stop_flag=#{stop_flag},stop_code=#{stop_code},stop_name=#{stop_name},stop_date=#{stop_date},stop_explain=#{stop_explain},"
			+ "state=#{state},modify_code=#{modify_code},modify_name=#{modify_name},modify_date=#{modify_date},version=#{version} "
			+ "where id=#{id}")
	int update(Tab_t_equipment_archives tab);

}
