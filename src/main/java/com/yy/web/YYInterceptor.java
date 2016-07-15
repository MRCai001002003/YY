package com.yy.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yy.common.exception.CustomException;
import com.yy.domain.entity.Customer;
import com.yy.web.utils.StringUtil;
/** 
 * @ClassName: YYInterceptor 
 * @Description: 用户有效性验证
 * @author caizhen
 * @date 2016年7月15日 下午2:37:35  
 */
public class YYInterceptor extends HandlerInterceptorAdapter{
	
	@Resource
	private ObjectMapper objectMapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url=request.getRequestURL().toString();
		System.out.println("YYInterceptor---url-------------"+url);
		Customer user = (Customer) StringUtil.getSession(request, "customer");
		// 会话失效，重新登录
		if (user == null) {
			throw new CustomException("login failed");
		}
		return true;
	}
}
