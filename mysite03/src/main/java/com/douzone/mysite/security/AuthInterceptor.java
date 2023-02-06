package com.douzone.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.HandlerInterceptor;

import com.douzone.mysite.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor {

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
		// Auth adminRole = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		if(auth == null) {
			auth = handlerMethod.getBeanType().getAnnotation(Auth.class);
		}
		
		// 5. Type이나 Method에 @Auth가 없는 경우 
		if(auth == null) {
			return true;
		}
		
		//6. @Auth가 붙어 있기 때문에 인증(Authenfication) 여부 확인
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		// 7. 권한(Authorization) 체크를 위해 @Auth의 role 가져오기("ADMIN", "USER")
		String role = auth.role();	// 이렇게 하면 접속한 계정의 역할이 알아서 담길거고
		String authUserRole = authUser.getRole();	// 이거는 디비에 넣어서 vo에도 담고 해야할 거 같은데
		
		if(role.equals("USER")) {
			return true;
		}
		else if(role.equals("ADMIN") && role.equals(authUserRole)) {	// Type이 ADMIN이고 로그인한 사용자의 role 역시 ADMIN일 때!
			return true;
		}
		
		//6. 인증 확인
		response.sendRedirect(request.getContextPath() + "/");	// 아니면 홈으로
		return false;
	}

}