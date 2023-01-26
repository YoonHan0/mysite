package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class UpdateForm implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session =request.getSession();
//		if(session == null) {
//			MvcUtil.redirect(request.getContextPath(), request, response);
//			return;
//		}
//		UserVo authUser = (UserVo)session.getAttribute("authUser");
//
//		if(authUser == null) {
//			MvcUtil.redirect(request.getContextPath(), request, response);
//			return;
//		}
		/*===========================================================================*/	// 보안을 위한 내용인듯
		int no = Integer.parseInt(request.getParameter("no"));
		
		BoardVo vo = new BoardDao().findByNo(no);	// title, contents
		
		request.setAttribute("vo", vo);
		MvcUtil.forward("board/modify", request, response);


	}

}
