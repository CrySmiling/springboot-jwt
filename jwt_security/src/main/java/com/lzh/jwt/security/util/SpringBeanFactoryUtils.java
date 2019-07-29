package com.lzh.jwt.security.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/*
 * @author H
 *
 * @date 2019/7/29
 *
 */
@Component
public class SpringBeanFactoryUtils implements ApplicationContextAware {
	private static ApplicationContext context = null;

	public static <T> T getBean(Class<T> type){
		return context.getBean(type);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (SpringBeanFactoryUtils.context == null) {
			SpringBeanFactoryUtils.context = applicationContext;
		}
	}
}
