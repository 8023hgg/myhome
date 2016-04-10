package home.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionInterceptor implements HandlerInterceptor {
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
//      log.info("==============执行顺序: 1、preHandle================");  
		String url=request.getRequestURL().toString();
		request.getSession().setAttribute("contextPath", request.getContextPath());
		if(url.contains("/login")
				||url.contains("/resources/")
				||url.contains("/timer")
				||url.contains("/api")
				||url.contains("/image")
				||url.contains("/msg/receive")){
			return true;
		}
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		return true;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
//        log.info("==============执行顺序: 2、postHandle================");
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
//        log.info("==============执行顺序: 3、afterCompletion================"); 
	}  
}