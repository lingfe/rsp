package com.rsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.rsp.inter.Imanufacturer;
import com.rsp.mapperProvider.PublicMapperProvider;
import com.rsp.model.Tab_manufacturer;

public interface ImanufacturerMapper extends Imanufacturer {
	
	
	@Select("select * from manufacturer where id=#{id}")
	Tab_manufacturer getWhereId(@Param("id")String id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getCount")
	int getCount(Map<String, Object> map);
	
	@Update("update manufacturer set "
			+ "name=#{name},remark=#{remark},"
			+ "state=#{state},mdate=#{mdate},modify=#{modify},version=#{version} "
			+ "where id=#{id}")
	int update(Tab_manufacturer man);

	@Insert("INSERT  INTO `manufacturer`"
			+ "(`id`,`name`,`remark`,"
			+ "`state`,`cdate`,`mdate`,`creator`,`modify`,`version`) "
			+ "VALUES "
			+ "(#{id},#{name},#{remark},"
			+ "#{state},#{cdate},#{mdate},#{creator},#{modify},#{version})")
	int save(Tab_manufacturer man);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getPage")
	List<Tab_manufacturer> pageSelect(Map<String, Object> map);
	
	@Delete("delete from manufacturer where id=#{id}")
	int deleteWhereId(@Param("id")String id);
}
