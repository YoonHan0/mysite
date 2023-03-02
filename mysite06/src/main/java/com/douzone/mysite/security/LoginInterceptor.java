package com.douzone.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		UserVo vo = new UserVo();
		vo.setEmail(email);
		vo.setPassword(password);

		
		UserVo authUser = userService.getUser(vo);		// 이렇게 new 를 쓰면 container안의 UserService가 아닌 밖의 새로운 객체를 만들어서 null인 상태로 접근하게 된다!

		if (authUser == null) {
			request.setAttribute("email", vo.getEmail());
			request
				.getRequestDispatcher("/WEB-INF/views/user/login.jsp")
				.forward(request, response);
			
			return false;
		}
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		
		response.sendRedirect(request.getContextPath());
		
		return false;
	}

}
