package com.hf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hf.entity.IpsEntity;
@Mapper
public interface IpsDao {

	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into t_ips(ip,useport,datafrom,createtime) values(#{ip},#{useport},#{datafrom},now())")
	int save(IpsEntity ipsEntity);
	
	@Delete("delete from t_ips where id = #{id}")
	int delete(Integer id);
	
	@Update("update t_ips set updatetime = now(), ip = #{ip}, useport = #{useport},datafrom = #{datafrom},state = #{state}, type = #{type} where id = #{id}")
	int update(IpsEntity IpsEntity);
	
	@Select("select * from ${tablename} limit #{page},#{pageSize}")
	List<IpsEntity> findPage(String tablename,Integer page,Integer pageSize);
}
