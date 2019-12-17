package com.hf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hf.entity.DictEntity;
import com.hf.entity.IpsEntity;
@Mapper
public interface IpsDao {
	 
    @Insert({
         "<script>",
         "insert into t_ips(ip,createtime,state,type,datafrom) values ",
         "<foreach collection='ipsEntities' item='item' index='index' separator=','>",
         "(#{item.ip},now(),#{item.state},#{item.type},#{item.datafrom})",
         "</foreach>",
         "ON DUPLICATE KEY UPDATE ip=values(ip)",
         "</script>"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int batchAddIps(@Param(value="ipsEntities") List<IpsEntity> ipsEntities);
}
