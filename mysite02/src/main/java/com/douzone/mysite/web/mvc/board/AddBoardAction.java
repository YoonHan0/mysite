package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class AddBoardAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("no") != null) {	// 댓글
			int no = Integer.parseInt(request.getParameter("no"));
			String title = request.getParameter("title");
			String reply = request.getParameter("reply");
			
			BoardVo vo = new BoardVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContent(reply);
			
			new BoardDao().insertReply(vo);
			
		}
		else {			// 새 글
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int userNo = Integer.parseInt(request.getParameter("userNo"));		
			
			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setContent(content);
			vo.setUserNo(userNo);
					
			new BoardDao().insert(vo);
		
		}
		
		MvcUtil.redirect(request.getContextPath() + "/board?a=list&&page=1", request, response);
	}

}
