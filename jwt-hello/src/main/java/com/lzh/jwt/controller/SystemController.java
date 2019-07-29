package com.lzh.jwt.controller;

import com.lzh.jwt.util.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/*
 * @author H
 *
 * @date 2019/7/23
 *
 */
@RestController
public class SystemController {
	private final String USER_NAME_KEY = "USER_NAME";
	private final Long EXP_IMT = 3600_000L;

	@GetMapping("/api/test")
	public Object helloWorld(@RequestAttribute(value = USER_NAME_KEY) String username){
		return "Welcome! Your USER_NAME : " + username;
	}

	@PostMapping("/login")
	public Object login(String name, String password){
		if (isValidPassword(name, password)) {
			// 将用户名传入并生成jwt
			Map<String, Object> map = new HashMap<>();
			map.put(USER_NAME_KEY, name);
			String jwt = JwtUtils.sign(map, EXP_IMT);

			// 将jwt返回给客户端
			return new HashMap<String, String>(){{
				put("token", jwt);
			}};
		}else {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * 验证密码是否正确
	 * @param name
	 * @param password
	 * @return
	 */
	private boolean isValidPassword(String name, String password){
		return "admin".equals(name) && "admin".equals(password);
	}
}
