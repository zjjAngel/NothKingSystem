package com.NorthKingSys.jbf.cn;

import com.NorthKingSys.jbf.cn.config.filter.LogCostFilter;
import com.NorthKingSys.jbf.cn.util.JedisUtil;
import com.NorthKingSys.jbf.cn.util.SpringContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.NorthKingSys.jbf.cn.mapper")
public class NorthKingSysApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(NorthKingSysApplication.class, args);
		SpringContextUtil.setApplicationContext(context);
	}
	@Bean
	public FilterRegistrationBean setFilter(){
		FilterRegistrationBean filterBean = new FilterRegistrationBean();
		filterBean.setFilter(new LogCostFilter());
		filterBean.setName("FilterController");
		filterBean.addUrlPatterns("/*");
		return filterBean;
	}

	@Bean("jedisUtil")
	public JedisUtil jedisUtil(){
		return new JedisUtil();
	}
}
