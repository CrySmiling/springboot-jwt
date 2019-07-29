package com.lzh.jwt.security.mapper;

import com.lzh.jwt.security.pojo.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*
 * @author H
 *
 * @date 2019/7/29
 *
 */
@Mapper
public interface SysUserRoleMapper {
	@Select("select * from sys_user_role where user_id = #{userId}")
	List<SysUserRole> listByUserId(Integer userId);
}
