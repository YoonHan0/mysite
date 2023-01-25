package com.douzone.mysite.web.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.UserDao;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web2.mvc.Action;
import com.douzone.web2.util.MvcUtil;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long no = Long.parseLong(request.getParameter("no"));
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");

		UserVo vo = new UserVo();
		vo.setNo(no);
		vo.setName(name);
		vo.setGender(gender);
		
		// System.out.println(vo);
		
		new UserDao().updateFunction(vo);

		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", vo);	// mapping
		
		MvcUtil.redirect(request.getContextPath() + "/user?a=updateform", request, response);	// main화면으로
		
	}

}