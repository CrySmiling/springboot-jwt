package com.lzh.jwt.filter;

import com.lzh.jwt.util.JwtUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/*
 * JWT过滤器
 * @author H
 *
 * @date 2019/7/26
 *
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private final String  USER_NAME_KEY = "USER_NAME";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		try{
		    // 从请求头中取token, key一般为Authenorization
			String token = request.getHeader("Authorization");
			Map map = JwtUtils.unSign(token);
			// 将用户名存入request属性
			request.setAttribute(USER_NAME_KEY, map.get(USER_NAME_KEY));
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
			return;
		}

		filterChain.doFilter(request, response);
	}
}
