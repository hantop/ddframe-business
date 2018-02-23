package com.ddframe.database.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.ResultHandler;

import com.ddframe.beans.BaseObject;

@Mapper
public interface SelectMapper<Bean, Condition> {

	@Select("${select}")
	HashMap<String, Object> select(@Param("select") String select, Condition condition, ResultHandler<BaseObject> handle);

	@Select("${select}")
	List<HashMap<String, Object>> listByHandle(@Param("select") String select, ResultHandler<HashMap<String, Object>> handle);

	@Select("${select}")
	List<HashMap<String, Object>> list(@Param("select") String select, @Param("conditions") HashMap<String, Object> conditions);
}
