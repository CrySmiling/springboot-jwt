package com.lzh.jwt.security.mapper;

import com.lzh.jwt.security.pojo.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/*
 * @author H
 *
 * @date 2019/7/29
 *
 */
@Mapper
public interface SysRoleMapper {
	@Select("select * from sys_role where id = #{id}")
	SysRole selectById(Integer id);
}
