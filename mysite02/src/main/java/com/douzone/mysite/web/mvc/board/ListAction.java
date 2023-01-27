package com.douzone.mysite.web.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.PageVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String page = request.getParameter("page");
// 		System.out.println(page);
		/*===============================================================*/
//		if(request.getParameter("page") != null) {		// 페이지 클릭! 일단은 이렇게 하고 나중에 다 수정하자
//			int pageNo = Integer.parseInt(request.getParameter("page"));
//			int startNo = (pageNo - 1) *5;
//			int endNo = startNo + 5;
//			
//			List<BoardVo> list = new BoardDao().findByPageNo(startNo, endNo);
//			request.setAttribute("list", list);
//		}
//		else {
//			List<BoardVo> list = new BoardDao().findAll();
//			request.setAttribute("list", list);
//		}
		int pageNo = Integer.parseInt(request.getParameter("page"));
		
		
		PageVo pageVo = new BoardDao().pageAll(pageNo);
		List<BoardVo> list = new BoardDao().findAll();
		// List<BoardVo> list = new BoardDao().findByPageNo(pageVo);
		
		
		request.setAttribute("list", list);
		request.setAttribute("pageVo", pageVo);
				
		MvcUtil.forward("board/list", request, response);
	}

}