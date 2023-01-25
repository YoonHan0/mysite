package com.douzone.mysite.web.mvc.guest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.GuestbookDao;
import com.douzone.web2.mvc.Action;

public class GuestDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String password = request.getParameter("password");
		
		new GuestbookDao().deleteByNoAndPassword(Long.parseLong(no), password);

		response.sendRedirect(request.getContextPath() + "/guestbook?a=list");
	}

}
