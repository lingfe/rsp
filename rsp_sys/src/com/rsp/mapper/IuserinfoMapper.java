package com.rsp.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.junit.runners.Parameterized.Parameters;

import com.rsp.inter.Iuserinfo;
import com.rsp.mapperProvider.IuserinfoMapperProvider;
import com.rsp.model.Tab_user_info;
		
/**
 * 
  * 文件名：Mapper_user_info.java
  * 描述：用户信息数据访问接口 
  * 修改人： lingfe
  * 修改时间：2018年10月1日 上午8:32:31
  * 修改内容：
 */	
public interface IuserinfoMapper extends Iuserinfo {
	
	@Select("select * from user_info where username=#{username}")
	Tab_user_info selectWhereName(@Param("username")String username);
	
	@Update("update user_info set pwd=#{newPwd} where id=#{id}")
	int updatePwd(@Param("id")String id, @Param("newPwd")String newPwd);
	
	@Select("select * from user_info where id=#{id}")
	Tab_user_info getWhereId(@Param("id")String id);

	@Select("select * from user_info where username=#{username} and pwd=#{pwd} ")
	Tab_user_info getWhereNamePwd(@Param("username")String username,@Param("pwd")String pwd);
	
	//保存用户信息
	@Insert("INSERT  INTO user_info "
			+ "(`id`,openid,`username`,`realname`,`avatar`,`relo`,"
			+ "`pwd`,`tel`,`email`,`mobile`,`qq`,`balance`,`lastTime`,`state`,`idCard`,`provinceCode`,`provinceName`,"
			+ "`cityCode`,`cityName`,`regionCode`,`regionName`,`address`,`remark`,"
			+ "`cdate`,`mdate`,`creator`,`modify`,`version`) "
			+ " VALUES  "
			+ "(#{id},#{openid},#{username},#{realname},#{avatar},#{relo},"
			+ "#{pwd},#{tel},#{email},#{mobile},#{qq},#{balance},#{lastTime},#{state},#{idCard},#{provinceCode},#{provinceName},"
			+ "#{cityCode},#{cityName},#{regionCode},#{regionName},#{address},#{remark},"
			+ "#{cdate},#{mdate},#{creator},#{modify},#{version})")
	int save(Tab_user_info tab_user_info);
	
	//根据openid查询用户
	@Select("select * FROM user_info where openid=#{openid}")
	Tab_user_info getWhereOpenid(@Param("openid") String openid);
	
	//修改用户的角色
	@Update("update user_info set relo=#{relo} where openid=#{openid}")
	int update_relo(@Param("relo")int relo,
			@Param("openid")String openid);
	
	//修改用户信息，细到可以修改每一个字段
	@UpdateProvider(type=IuserinfoMapperProvider.class,method="update_info")
	Integer update_info(Map<String, Object> map);
	
	//根据用户id查询用户
	@Select("select * from user_info where id=#{userid}")
	Tab_user_info getWhereUserID(@Param("userid")String userid);
}
