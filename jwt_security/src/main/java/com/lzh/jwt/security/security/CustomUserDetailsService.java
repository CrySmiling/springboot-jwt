package com.lzh.jwt.security.security;

import com.lzh.jwt.security.pojo.SysRole;
import com.lzh.jwt.security.pojo.SysUser;
import com.lzh.jwt.security.pojo.SysUserRole;
import com.lzh.jwt.security.service.SysRoleService;
import com.lzh.jwt.security.service.SysUserRoleService;
import com.lzh.jwt.security.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
 * @author H
 *
 * @date 2019/7/29
 *
 */
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private SysUserService userService;

	@Autowired
	private SysRoleService roleService;

	@Autowired
	private SysUserRoleService userRoleService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		SysUser user = userService.selectByUsername(username);

		//判断用户是否存在
		if (user == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}

		//添加权限
		List<SysUserRole> userRoles = userRoleService.listByUserId(user.getId());
		for (SysUserRole userRole : userRoles) {
			SysRole role = roleService.selectById(userRole.getRoleId());
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		//返回UserDetails实现类
		return new User(user.getUsername(), user.getPassword(), authorities);
	}
}
