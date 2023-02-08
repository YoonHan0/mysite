package com.douzone.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
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
		if(auth == null) {
//			auth = handlerMethod.getBeanType().getAnnotation(Auth.class);
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}
		
		
		// 5. Type이나 Method에 @Auth가 없는 경우 -> 권한 제어가 아무것도 없는 경우
		if(auth == null) {
			return true;
		}
		
		//6. @Auth가 붙어 있기 때문에 인증(Authenfication) 여부 확인
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser == null) {	// 로그인을 안 하고 admin에 접근할 때
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		// 로그인은 되어 있다
		// 7. 권한(Authorization) 체크를 위해 @Auth의 role 가져오기("ADMIN", "USER")
		String role = auth.role();	// auth의 역할 default값 USER
		String authUserRole = authUser.getRole();	// 이거는 디비에 넣어서 vo에도 담고 해야할 거 같은데
		
		if(role.equals("USER")) {	//@Auth()의 값이 USER이면 로그인 했으니까 그냥 통과
			return true;
		}
//		else if(role.equals("ADMIN") && role.equals(authUserRole)) {	// Type이 ADMIN이고 로그인한 사용자의 role 역시 ADMIN일 때!
//			return true;
//		}	// 아래랑 같은 코드, 컴퓨팅적 사고로 아래처럼 작성(아닌 것을 먼저 처리)
		// 9. @Auth
		if(!"ADMIN".equals(authUserRole)) {		// @Auth(role="ADMIN")인데 로그인한 유저의 role이 ADMIN이 아닐 때 홈으로 이동 시킴
			response.sendRedirect(request.getContextPath());	// 로그인하고 admin 접속 시도 했는데 ADMIN 아니면 홈으로
			return false;
		}
		
		// ADMIN인 경우 ->
		return true;
	}

}