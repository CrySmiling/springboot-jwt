package com.lzh.jwt.security.mapper;

import com.lzh.jwt.security.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/*
 * @author H
 *
 * @date 2019/7/29
 *
 */
@Mapper
public interface SysUserMapper {
	@Select("select * from sys_user where id = #{id}")
	SysUser selectById(Integer id);

	@Select("select * from sys_user where username = #{username}")
	SysUser selectByUsername(String username);
}
