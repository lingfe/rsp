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
			+ "infirmary_name,"
			+ "`equipment_name`,`specification_type`,"
			+ "`device_class`,`equipment_number`,`manfacturer_id`,"
			+ "`rated_voltage_kv`,`rated_current_ma`,`rated_power_w`,"
			+ "`equipment_nameplate_photo`,`department`,`purpose`,"
			+ "`location`,`is_obtain_rdtl`,`obtain_rdtl_date`,"
			+ "`is_obtain_rsl`,`obtain_rsl_date`,`is_obtain_lcl`,"
			+ "`obtain_lcl_date`,`source`,`installation_date`,"
			+ "`whereabouts`,is_scrap,`scrap_date`,"
			+ "`state`,`cdate`,`mdate`,`creator`,`modify`,`version`) "
			+ "VALUES "
			+ "(#{id},"
			+ "#{infirmary_name},"
			+ "#{equipment_name},#{specification_type},"
			+ "#{device_class},#{equipment_number},#{manfacturer_id},"
			+ "#{rated_voltage_kv},#{rated_current_ma},#{rated_power_w},"
			+ "#{equipment_nameplate_photo},#{department},#{purpose},"
			+ "#{location},#{is_obtain_rdtl},#{obtain_rdtl_date},"
			+ "#{is_obtain_rsl},#{obtain_rsl_date},#{is_obtain_lcl},"
			+ "#{obtain_lcl_date},#{source},#{installation_date},"
			+ "#{whereabouts},#{is_scrap},#{scrap_date},"
			+ "#{state},#{cdate},#{mdate},#{creator},#{modify},#{version})")
	int save(Tab_equipment_walkthrough tab);
	
	@Update("update equipment_walkthrough set "
			+ "infirmary_name=#{infirmary_name},"
			+ "equipment_name=#{equipment_name},specification_type=#{specification_type},"
			+ "device_class=#{device_class},equipment_number=#{equipment_number},manfacturer_id=#{manfacturer_id},"
			+ "rated_voltage_kv=#{rated_voltage_kv},rated_current_mA=#{rated_current_ma},rated_power_w=#{rated_power_w},"
			+ "equipment_nameplate_photo=#{equipment_nameplate_photo},department=#{department},purpose=#{purpose},"
			+ "location=#{location},is_obtain_rdtl=#{is_obtain_rdtl},obtain_rdtl_date=#{obtain_rdtl_date},"
			+ "is_obtain_rsl=#{is_obtain_rsl},obtain_rsl_date=#{obtain_rsl_date},is_obtain_lcl=#{is_obtain_lcl},"
			+ "obtain_lcl_date=#{obtain_lcl_date},source=#{source},installation_date=#{installation_date},"
			+ "whereabouts=#{whereabouts},is_scrap=#{is_scrap},scrap_date=#{scrap_date},"
			+ "state=#{state},mdate=#{mdate},modify=#{modify},version=#{version} "
			+ "where id=#{id}")
	int update(Tab_equipment_walkthrough tab);
}
