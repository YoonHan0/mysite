package com.douzone.mysite.web.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class BoardPageViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		// int userNo = Integer.parseInt(request.getParameter("userNo"));
 		// System.out.println(no);
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		// vo.setUserNo(userNo);
		
		BoardVo list = new BoardDao().findViewPage(vo);

		request.setAttribute("list", list);

		MvcUtil.forward("board/view", request, response);
	}

}
