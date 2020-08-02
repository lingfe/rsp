package com.rsp.mapper;

import org.apache.ibatis.annotations.SelectProvider;

import com.rsp.inter.Ipublic;
import com.rsp.mapperProvider.PublicMapperProvider;

/**
 * 
  * 文件名：Ipublic.java
  * 描述： 公共调用接口，数据操作接口
  * 修改人： lingfe
  * 修改时间：2019年5月23日 下午3:57:38
  * 修改内容：
 */
public interface IpublicMapper<T> extends Ipublic<T>{

	@SelectProvider(type=PublicMapperProvider.class,method="save")
	int save(T tab);
}
