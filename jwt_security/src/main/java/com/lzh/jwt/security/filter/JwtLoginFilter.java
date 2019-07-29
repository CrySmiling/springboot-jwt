package com.lzh.jwt.security.filter;

import com.lzh.jwt.security.pojo.SysUser;
import com.lzh.jwt.security.service.SysUserService;
import com.lzh.jwt.security.util.JwtUtils;
import com.lzh.jwt.security.util.SpringBeanFactoryUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*
 * 该filter继承自UsernamePasswordAuthenticationFilter
 * 在验证用户名密码正确后，生成一个token，并将token返回给客户端
 * @author H
 *
 * @date 2019/7/29
 *
 */
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
	static final String USER_ID_KEY = "USER_ID";

	private AuthenticationManager authenticationManager;

	public JwtLoginFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	/**
	 * 该方法在Spring Security验证前调用
	 * 将用户信息从request中取出，并放入authenticationManager中
	 * @param request
	 * @param response
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		try{
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			//将用户信息放入authenticationManager中
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							username, password, Collections.emptyList())
			);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 该方法在Spring Security验证成功后调用
	 * 在这个方法里生成JWT token,并返回给用户
	 * @param request
	 * @param response
	 * @param chain
	 * @param authResult
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		String username = ((User) authResult.getPrincipal()).getUsername();

		//从数据库中取出用户信息
		SysUserService userService = SpringBeanFactoryUtils.getBean(SysUserService.class);
		SysUser user = userService.selectByUsername(username);

		//将用户id放入JWT token
		Map<String, Object> map = new HashMap<>();
		map.put(USER_ID_KEY, user.getId());
		String token = JwtUtils.sign(map, 3600_000);

		//将token放入响应头中
		response.addHeader("Authorization", token);
	}
}
