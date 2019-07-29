package com.lzh.jwt.security.security;

import com.lzh.jwt.security.filter.JwtLoginFilter;
import com.lzh.jwt.security.util.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * @author H
 *
 * @date 2019/7/29
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
			@Override
			public String encode(CharSequence rawPassword) {
				return rawPassword.toString();
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return encodedPassword.equals(rawPassword.toString());
			}
		});
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.anyRequest().authenticated()
				.and()
				//设置登录页
				.formLogin().loginPage("/login")
				//设置登陆失败页
				.failureUrl("/login/error")
				//设置登录成功页
				.defaultSuccessUrl("/").permitAll()
				.and()
				.addFilter(new JwtLoginFilter(authenticationManager()))
				.addFilter(new JwtAuthenticationFilter(authenticationManager()))
				.logout().permitAll();

		http.csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		//设置拦截忽略文件夹，可以对静态资源放行
		web.ignoring().antMatchers("/css/**", "/js/**");
	}
}
