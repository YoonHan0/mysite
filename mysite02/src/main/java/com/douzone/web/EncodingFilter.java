package com.douzone.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;


public class EncodingFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;
	private String encoding;
	
	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");	//web.xml의 init-param에서 지정했던 이름을 사용
		if(encoding == null) {
			encoding = "utf-8";								// default값 지정
		}
	}
	/* Request를 처리하고 다음 Filter를 실행 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/* Request 처리 */
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);	// 재귀형식으로 호출
		
		/* Response 처리 */
	}

	public void destroy() {
		
	}

}
