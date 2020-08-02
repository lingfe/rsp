package com.rsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.rsp.inter.It_yy_sb_sbda;
import com.rsp.mapperProvider.PublicMapperProvider;
import com.rsp.model.Tab_t_yy_sb_sbda;

public interface It_yy_sb_sbdaMapper extends It_yy_sb_sbda {

	@Delete("delete from t_yy_sb_sbda where id=#{id}")
	int deleteWhereId(@Param("id")String id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getCount")
	int getCount(Map<String, Object> map);
	
	@Select("select * from t_yy_sb_sbda where id=#{id}")
	Tab_t_yy_sb_sbda getWhereId(@Param("id")String id);
	
	@SelectProvider(type=PublicMapperProvider.class,method="getPage")
	List<Tab_t_yy_sb_sbda> pageSelect(Map<String, Object> map);
	
	@Insert("INSERT  INTO `t_yy_sb_sbda`(`id`,`hospital_id`,"
			+ "`sb_name`,`sb_ggxh`,`sb_zzlb_id`,`sb_code`,"
			+ "`sb_manufacturer_id`,`sb_eddy`,`sb_eddl`,`sb_edgl`,"
			+ "`sb_glks_id`,`sb_syks_id`,`sb_yt_id`,`sb_szcs`,"
			+ ""
			+ "`flag_fszlxkz`,`fszlxkz_state_name`,`fszlxkz_xm_id`,`fszlxkz_date`,"
			+ "`flag_fsaqxkz`,`fsaqxkz_state_name`,`fsaqxkz_xm_id`,`fsaqxkz_date`,"
			+ "`flag_dxpzxkz`,`dxpzxkz_state_name`,`dxpzxkz_xm_id`,`dxpzxkz_date`,"
			+ ""
			+ "`sb_ly`,`sb_azsj`,`sb_qx`,"
			+ ""
			+ "`flag_sb_state`,`sb_state_date`,`sb_state_remark`,`sb_state_name`,`remark`,"
			+ ""
			+ "`stop_flag`,`stop_code`,`stop_name`,`stop_date`,`stop_explain`,"
			+ ""
			+ "`crt_code`,`crt_name`,`crt_date`,`modify_code`,`modify_name`,`modify_date`,`version`) "
			+ "VALUES "
			+ "(#{id},#{hospital_id},"
			+ "#{sb_name},#{sb_ggxh},#{sb_zzlb_id},#{sb_code},"
			+ "#{sb_manufacturer_id},#{sb_eddy},#{sb_eddl},#{sb_edgl},"
			+ "#{sb_glks_id},#{sb_syks_id},#{sb_yt_id},#{sb_szcs},"
			+ ""
			+ "#{flag_fszlxkz},#{fszlxkz_state_name},#{fszlxkz_xm_id},#{fszlxkz_date},"
			+ "#{flag_fsaqxkz},#{fsaqxkz_state_name},#{fsaqxkz_xm_id},#{fsaqxkz_date},"
			+ "#{flag_dxpzxkz},#{dxpzxkz_state_name},#{dxpzxkz_xm_id},#{dxpzxkz_date},"
			+ ""
			+ "#{sb_ly},#{sb_azsj},#{sb_qx},"
			+ ""
			+ "#{flag_sb_state},#{sb_state_date},#{sb_state_remark},#{sb_state_name},#{remark},"
			+ ""
			+ "#{stop_flag},#{stop_code},#{stop_name},#{stop_date},#{stop_explain},"
			+ ""
			+ "#{crt_code},#{crt_name},#{crt_date},#{modify_code},#{modify_name},#{modify_date},#{version})")
	int save(Tab_t_yy_sb_sbda tab);
	
	@Update("update t_yy_sb_sbda set "
			+ "sb_name=#{sb_name},sb_ggxh=#{sb_ggxh},sb_zzlb_id=#{sb_zzlb_id},sb_code=#{sb_code},"
			+ "sb_manufacturer_id=#{sb_manufacturer_id},sb_eddy=#{sb_eddy},sb_eddl=#{sb_eddl},sb_edgl=#{sb_edgl},"
			+ "sb_glks_id=#{sb_glks_id},sb_syks_id=#{sb_syks_id},sb_yt_id=#{sb_yt_id},sb_szcs=#{sb_szcs},"
			+ ""
			+ "flag_fszlxkz=#{flag_fszlxkz},flag_fsaqxkz=#{flag_fsaqxkz},flag_dxpzxkz=#{flag_dxpzxkz},"
			+ ""
			+ "sb_ly=#{sb_ly},sb_azsj=#{sb_azsj},sb_qx=#{sb_qx},"
			+ ""
			+ "flag_sb_state=#{flag_sb_state},sb_state_date=#{sb_state_date},sb_state_remark=#{sb_state_remark},sb_state_name=#{sb_state_name},remark=#{remark},"
			+ ""
			+ "stop_flag=#{stop_flag},stop_code=#{stop_code},stop_name=#{stop_name},stop_date=#{stop_date},stop_explain=#{stop_explain},"
			+ ""
			+ "modify_code=#{modify_code},modify_name=#{modify_name},modify_date=#{modify_date},version=#{version} "
			+ "where id=#{id}")
	int update(Tab_t_yy_sb_sbda tab);
}
