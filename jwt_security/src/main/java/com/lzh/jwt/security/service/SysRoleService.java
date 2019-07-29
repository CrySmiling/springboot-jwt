package com.lzh.jwt.security.service;

import com.lzh.jwt.security.mapper.SysRoleMapper;
import com.lzh.jwt.security.pojo.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * @author H
 *
 * @date 2019/7/29
 *
 */
@Service
public class SysRoleService {
	@Autowired
	private SysRoleMapper roleMapper;

	public SysRole selectById(Integer id){
		return roleMapper.selectById(id);
	}
}
