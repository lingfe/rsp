package com.rsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.rsp.inter.Icoding;
import com.rsp.mapperProvider.PublicMapperProvider;
import com.rsp.model.Tab_coding;

public interface IcodingMapper extends Icoding {
	
	@SelectProvider(type=PublicMapperProvider.class,method="getPage")
	List<Tab_coding> pageSelect(Map<String, Object> map);

	@Insert("INSERT  INTO `coding`"
			+ "(`id`,`coding_type`,`coding_name`,`remark`,"
			+ "`state`,`cdate`,`mdate`,`creator`,`modify`,`version`) "
			+ "VALUES "
			+ "(#{id},#{coding_type},#{coding_name},#{remark},"
			+ "#{state},#{cdate},#{mdate},#{creator},#{modify},#{version})")
	int save(Tab_coding coding);
	
	@Delete("delete from coding where id=#{id}")
	int deleteWhereId(@Param("id")String id);
	
	@Update("update coding set "
			+ "coding_type=#{coding_type},coding_name=#{coding_name},"
			+ "remark=#{remark},state=#{state},mdate=#{mdate},modify=#{modify},version=#{version}")
	int update(Tab_coding coding);
}
