package com.rsp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.rsp.inter.Ichina;
import com.rsp.model.Tab_china;

public interface IchinaMapper extends Ichina{
	
	@Select("select * from china where name like '%${name}%'")
	List<Tab_china> selectLike(@Param("name")String name);
	
	@Insert("INSERT  INTO `china`"
			+ "(`id`,`name`,`pid`,`state`,`cdate`,`mdate`,`creator`,`modify`,`version`) "
			+ "VALUES "
			+ "(#{id},#{name},#{pid},"
			+ "#{state},#{cdate},#{mdate},#{creator},#{modify},#{version})")
	int save(Tab_china china);
	
	@Delete("delete from china where pid=#{pid}")
	int deleteWherePid(@Param("pid")String pid);
	
	@Delete("delete from china where id=#{id}")
	int deleteWhereId(@Param("id")String id);

	@Select("select * from china where pid=#{pid}")
	List<Tab_china> selectWhwerePid(String pid);
}
