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

	@Delete("delete from enclosure where id=#{id}")
	int deleteWhereId(@Param("id")String id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getCount")
	int getCount(Map<String, Object> map);
	
	@Select("select * from enclosure where id=#{id}")
	Tab_enclosure getWhereId(@Param("id")String id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getPage")
	List<Tab_enclosure> pageSelect(Map<String, Object> map);
	
	@Insert("INSERT  INTO `enclosure`"
			+ "(`id`,`set_id`,`enclosure_type`,`enclosure_name`,`enclosure_remark`,"
			+ "`state`,`cdate`,`mdate`,`creator`,`modify`,`version`) "
			+ "VALUES "
			+ "(#{id},#{set_id},#{enclosure_type},#{enclosure_name},#{enclosure_remark},"
			+ "#{state},#{cdate},#{mdate},#{creator},#{modify},#{version})")
	int save(Tab_enclosure tab);
	
	@Update("update enclosure set "
			+ "set_id=#{set_id},enclosure_type=#{enclosure_type},enclosure_name=#{enclosure_name},"
			+ "enclosure_remark=#{enclosure_remark},"
			+ "state=#{state},mdate=#{mdate},modify=#{modify},version=#{version} "
			+ "where id=#{id}")
	int update(Tab_enclosure tab);
	
}
