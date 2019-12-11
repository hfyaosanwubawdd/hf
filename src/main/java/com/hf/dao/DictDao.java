package com.hf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hf.entity.DictEntity;
@Mapper
public interface DictDao {
	@Select("select * from article_dict")
	List<DictEntity> findAll();
	
	@Select("select * from ${tablename}")
	List<DictEntity> findAllByTableName(@Param("tablename") String tablename);
	
	@Select({
		"<script>",
        "SELECT * ",
        " FROM article_dict where id in ( ",
        "<foreach collection='ids' item='item' index='index' separator=','>",
           "#{item}",
        "</foreach>",
        " )</script>"
	})
	List<DictEntity> findByIds(@Param("ids") List<Integer> ids);
	
	@Update({
     "<script>",
     "<foreach collection='dictList' item='item' index='index' open='' close='' separator=';'>",
        "update article_dict set dict_name=#{item.dict_name}  where id = ${item.id}",
     "</foreach>",
     "</script>"
    })
    Integer batchAuditApply(@Param("dictList") List<DictEntity> dictList);
	 
    @Insert({
         "<script>",
         "insert into article_dict(dict_name) values ",
         "<foreach collection='dictEntities' item='item' index='index' separator=','>",
         "(#{item.dict_name})",
         "</foreach>",
         "</script>"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int batchAddEventApply(@Param(value="dictEntities") List<DictEntity> dictEntities);
}
