package com.lzh.jwt.security.pojo;

import lombok.Data;

import java.io.Serializable;

/*
 * @author H
 *
 * @date 2019/7/29
 *
 */
@Data
public class SysUser implements Serializable {
	private Integer id;
	private String username;
	private String password;
}
