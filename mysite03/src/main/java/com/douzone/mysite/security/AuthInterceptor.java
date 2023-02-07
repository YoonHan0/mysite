package com.douzone.mysite.security;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.HandlerInterceptor;

import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.mysite.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor {

	@Autowired
	private SiteService siteService;		// Session에 정보를 담기 위해서 정보를 가져오기 위한 Service
	@Autowired
	private ServletContext servletContext;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//1. handler 종류 확인
		if(!(handler instanceof HandlerMethod)) {
			// DefaultServletHanlder가 처리하는 경우(정적 자원, /assets/**)
			return true;
		}
		
		//2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		//3. Hanlder Method의 @Auth 가져오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

		// 4. Hanlder Method에 @Auth가 없으면 Type(class)에 붙어 있는지 확인 -> TEST : 로그인을 하지 않고 admin페이지에 들어갈 수 없게 됨
		if(auth == null) {
//			auth = handlerMethod.getBeanType().getAnnotation(Auth.class);
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}
		
		// ======= 안되지만 Session에 담아서 보내보자 =======
		SiteVo vo = siteService.getSite();			// 필요한 값 불러옴 title, welcome, description
		servletContext = request.getServletContext();	// ServletContext() 사용할 수 있게 
		
		
		
		// 5. Type이나 Method에 @Auth가 없는 경우 
		if(auth == null) {
			servletContext.setAttribute("siteTitle", vo.getTitle());		//Context단에 set하기
			return true;
		}
		
		//6. @Auth가 붙어 있기 때문에 인증(Authenfication) 여부 확인
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser == null) {	// 로그인을 안 하고 admin에 접근할 때
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		// 7. 권한(Authorization) 체크를 위해 @Auth의 role 가져오기("ADMIN", "USER")
		String role = auth.role();	// 이렇게 하면 접속한 계정의 역할이 알아서 담길거고
		String authUserRole = authUser.getRole();	// 이거는 디비에 넣어서 vo에도 담고 해야할 거 같은데
		
		if(role.equals("USER")) {
			return true;
		}
//		else if(role.equals("ADMIN") && role.equals(authUserRole)) {	// Type이 ADMIN이고 로그인한 사용자의 role 역시 ADMIN일 때!
//			return true;
//		}	// 아래랑 같은 코드, 컴퓨팅적 사고로 아래처럼 작성(아닌 것을 먼저 처리)
		// 9. @Auth
		if(!"ADMIN".equals(authUser.getRole())) {
			response.sendRedirect(request.getContextPath());	// 로그인하고 admin 접속 시도 했는데 ADMIN 아니면 홈으로
		}
		
		// ADMIN 페이지로 이동하는 경우 ->
		
		
		return true;
	}

}