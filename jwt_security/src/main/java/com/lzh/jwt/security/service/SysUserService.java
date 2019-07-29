package com.lzh.jwt.security.service;

import com.lzh.jwt.security.mapper.SysUserMapper;
import com.lzh.jwt.security.pojo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

/*
 * @author H
 *
 * @date 2019/7/29
 *
 */
@Service
public class SysUserService {
	@Autowired
	private SysUserMapper userMapper;

	public SysUser selectById(Integer id){
		return userMapper.selectById(id);
	}

	public SysUser selectByUsername(String username){
		return userMapper.selectByUsername(username);
	}
}
