package com.douzone.mysite.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.web.mvc.guest.GuestActionFactory;
import com.douzone.web2.mvc.Action;
import com.douzone.web2.mvc.ActionFactory;

public class GuestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GuestController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String actionName = request.getParameter("a");

		ActionFactory af = new GuestActionFactory();
		Action action = af.getAction(actionName);
		action.execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
