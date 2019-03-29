package com.rsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.rsp.inter.Ilicence_walkthrough;
import com.rsp.mapperProvider.PublicMapperProvider;
import com.rsp.model.Tab_licence_walkthrough;

public interface Ilicence_walkthroughMapper extends Ilicence_walkthrough {
	
	@Delete("delete from licence_walkthrough where id=#{id}")
	int deleteWhereId(@Param("id")String id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getCount")
	int getCount(Map<String, Object> map);
	
	@Select("select * from licence_walkthrough where id=#{id}")
	Tab_licence_walkthrough getWhereId(@Param("id")String id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getPage")
	List<Tab_licence_walkthrough> pageSelect(Map<String, Object> map);
	
	@Insert("INSERT  INTO `licence_walkthrough`"
			+ "(`id`,`hospital_name`,`prearranged_type`,"
			+ "`licence_type`,`certificates_number`,`organization_name`,"
			+ "`legal_person_charge`,`address`,`remark`,"
			+ "`is_annual_inspection`,`is_annual_assessment`,`is_licence_continue`,"
			+ "`inspection_date`,`inspection_cycle`,`assessment_date`,"
			+ "`assessment_cycle`,`valid_certificate`,`certification_authority`,"
			+ "`issuing_date`,"
			+ "`state`,`cdate`,`mdate`,`creator`,`modify`,`version`) "
			+ "VALUES "
			+ "(#{id},#{hospital_name},#{prearranged_type},"
			+ "#{licence_type},#{certificates_number},#{organization_name},"
			+ "#{legal_person_charge},#{address},#{remark},"
			+ "#{is_annual_inspection},#{is_annual_assessment},#{is_licence_continue},"
			+ "#{inspection_date},#{inspection_cycle},#{assessment_date},"
			+ "#{assessment_cycle},#{valid_certificate},#{certification_authority},"
			+ "#{issuing_date},"
			+ "#{state},#{cdate},#{mdate},#{creator},#{modify},#{version})")
	int save(Tab_licence_walkthrough tab);
	
	@Update("update licence_walkthrough set "
			+ "hospital_name=#{hospital_name},prearranged_type=#{prearranged_type},"
			+ "licence_type=#{licence_type},certificates_number=#{certificates_number},organization_name=#{organization_name},"
			+ "legal_person_charge=#{legal_person_charge},address=#{address},remark=#{remark},"
			+ "is_annual_inspection=#{is_annual_inspection},is_annual_assessment=#{is_annual_assessment},is_licence_continue=#{is_licence_continue},"
			+ "inspection_date=#{inspection_date},inspection_cycle=#{inspection_cycle},assessment_date=#{assessment_date},"
			+ "assessment_cycle=#{assessment_cycle},valid_certificate=#{valid_certificate},certification_authority=#{certification_authority},"
			+ "issuing_date=#{issuing_date},"
			+ "state=#{state},mdate=#{mdate},modify=#{modify},version=#{version} "
			+ "where id=#{id}")
	int update(Tab_licence_walkthrough tab);

}
