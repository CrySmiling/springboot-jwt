package com.lzh.jwt.security.service;

import com.lzh.jwt.security.mapper.SysUserRoleMapper;
import com.lzh.jwt.security.pojo.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @author H
 *
 * @date 2019/7/29
 *
 */
@Service
public class SysUserRoleService {
	@Autowired
	private SysUserRoleMapper userRoleMapper;

	public List<SysUserRole> listByUserId(Integer userId){
		return userRoleMapper.listByUserId(userId);
	}
}
