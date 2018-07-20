package com.citic.zktd.smart.security.service;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;


/**
 *
 */
@Component("rbacService")
public class RbacServiceImpl {

	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		Object principal = authentication.getPrincipal();

		boolean hasPermission = false;

		if (principal instanceof UserDetails) {
			//如果用户名是admin，就永远返回true
			if (StringUtils.equals(((UserDetails) principal).getUsername(), "admin")) {
				hasPermission = true;
			} else {
				// 读取用户所拥有权限的所有URL
//				Set<String> urls = ((UserDetails) principal).getUrls();
//				for (String url : urls) {
//					if (antPathMatcher.match(url, request.getRequestURI())) {
//						hasPermission = true;
//						break;
//					}
//				}
			}
		}

		return hasPermission;
	}

}