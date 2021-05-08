package com.elling.sys.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.elling.sys.model.ResultData;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
	
	@Override
	protected boolean onAccessDenied(ServletRequest request,ServletResponse response) throws Exception{
		
//		//判断请求是否是一个Ajax请求 
		Subject subject = getSubject(request,response);
		if(subject.getPrincipal()==null) {
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//			if (isAjax(request)) {
	            httpServletResponse.setCharacterEncoding("UTF-8");
	            httpServletResponse.setContentType("application/json");
	            httpServletResponse.setStatus(403);
	            ResultData resultData = new ResultData();
	            resultData.setResult("1");
	            resultData.setCode("403");
	            resultData.setMessage("登录认证失效，请重新登录!");
	            httpServletResponse.getWriter().write(JSON.toJSONString(resultData));
//	        }
			return false;
		}else {
			return true;
		}
		
	}
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request,ServletResponse response,Object mappedValue){
		boolean allowed = super.isAccessAllowed(request, response, mappedValue);
		if(!allowed) {
			HttpServletRequest httpRequest = (HttpServletRequest)request;
			HttpServletResponse httpResponse = (HttpServletResponse)response;
			String method = WebUtils.toHttp(request).getMethod();
			if(StringUtils.equalsIgnoreCase("OPTIONS", method)) {
				setHeader(httpRequest,httpResponse);
				return true;
			}
		}
		return allowed;
	}
	
//	下面是rememberMe的功能参考
//	@Override
//	protected boolean isAccessAllowed(ServletRequest request,ServletResponse response,Object mappedValue){
//		Subject subject = getSubject(request,response);
//		
//		//如果isAuthenticated 为false，证明不是登录过的，同时isRemember为true,证明是没登录直接通过记住我功能进来的
//		if(!subject.isAuthenticated()&&subject.isRemembered()) {
//			//获取session 看看是不是空的
//			Session session = subject.getSession(true);
//			
//			//随便拿一个session的属性来看session当前是否为空，这里用userId，也可以使用在他属性
//			if(session.getAttribute("userId") == null) {
//				//如果是空的才初始化，否则每次都要初始化，项目会卡
//				String username = subject.getPrincipal().toString();
//			}
//		}
//		
//		return subject.isAuthenticated() || subject.isRemembered();
//	}
	
	
	/**
	 * 为respones设置header，实现跨域
	 * @param request
	 * @param response
	 */
	private void setHeader(HttpServletRequest request,HttpServletResponse response) {
		
		response.setHeader("Access-control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-control-Allow-Methods", request.getMethod());
		response.setHeader("Access-control-Allow-Credentials", "true");
//		response.setHeader("Access-control-Allow-Headers", request.getHeader("Access-control-Allow-Headers"));
		response.setHeader("Access-control-Allow-Headers", "Content-Type,Access-Token");
//		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		response.setStatus(HttpStatus.OK.value());
		
		
	}
	
	 private boolean isAjax(ServletRequest request){
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
        if("XMLHttpRequest".equalsIgnoreCase(header)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
