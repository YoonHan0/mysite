package com.douzone.mysite.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.mysite.vo.PageVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "", method=RequestMethod.GET)
	public String index(Model model, int page, @RequestParam(value = "kwd", required=false, defaultValue = "") String keyword) {
		System.out.println("메인페이지!");
	
		Map<String, Object> map = boardService.getContetsList(page, keyword);
		
		model.addAttribute("list", map.get("list"));	// map -> model로 풀어주는 방법 / pageVo, list
		model.addAttribute("pageVo", map.get("pageVo"));
		model.addAttribute("keyword", keyword);
				
		return "board/list";	// UserVo(vo), List형태로 담긴 UserVo = list, pageVo
	}
	
//	@RequestMapping(value = "", method=RequestMethod.POST)
//	public String index(BoardVo vo, PageVo pagevo, Model model) {		// 키워드, 페이지 넘버
//		System.out.println("========== 키워드 검색 후 메인페이지!==========");
//		
//		List<BoardVo> list = boardService.getContentsList(pagevo.getNo(), vo.getKwd());
//		model.addAttribute("list", list);
//				
//		return "board/list";
//	}
	
	@Auth
	@RequestMapping(value = "/write", method=RequestMethod.GET)
	public String wirteform() {		
		return "board/write";	// redirect로 수정해야하나?
	}
	
	@RequestMapping(value = "/write", method=RequestMethod.POST)
	public String write(BoardVo vo) {		// <!-- userNo(=no), title, content,  -->
		
		boardService.addContents(vo);
		return "redirect:/";			// Add하면 끗
	}
	
	@RequestMapping("/viewpage")		// no, userNo
	public String viewpage(@RequestParam("no") int no, Model model) {
		BoardVo vo = boardService.getContents(no);	// DB는 contents이고 Vo는 content임 's' -> vo를 수정했음!
				
		model.addAttribute("list", vo);
		return "board/view";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)		// no, userNo
	public String update(BoardVo vo2, Model model) {
		BoardVo vo = boardService.getContents(vo2.getNo(), vo2.getUserNo());
		if(vo != null) {
			model.addAttribute("vo", vo);
			return "board/modify";
		} else {
			return "board?page=1";
		}
		
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)		
	public String update(BoardVo vo) {		// no, title, contents
		
		boardService.updateContents(vo);
		
		return "redirect:/board?page=1";		// update 완료 후 게시판으로 이동!
	}
	
	@RequestMapping("/delete")
	public String delete(Long no, HttpSession session) {
		UserVo vo = (UserVo)session.getAttribute("authUser");
		boardService.deleteContents(no, vo.getNo());
		return "redirect:/board?page=1";
	}
	
	@RequestMapping(value= "/reply", method=RequestMethod.GET)
	public String reply(Long no, Model model) {
		model.addAttribute("no", no);
		return "board/replyform";
	}
	
	@RequestMapping(value= "/reply", method=RequestMethod.POST)
	public String reply(BoardVo vo, HttpSession session) {		// no, title, contents
		UserVo authUserVo = (UserVo)session.getAttribute("authUser");
		boardService.addReply(vo, authUserVo.getNo());
		
		return "redirect:/board?page=1";
	}
		
//	
//	@RequestMapping("/add")
//	public String add(GuestbookVo vo) {		// name, password, message
//		System.out.println("1===============" + vo);
//		guestbookService.addMessage(vo);
//		
//		System.out.println("2===============" + vo);
//		return "redirect:/guestbook";	// add 했으니까 작업 끝 Redirect!
//	}
//	
//	@RequestMapping(value = "/delete", method=RequestMethod.GET)
//	public String delete(GuestbookVo vo, HttpSession session, Model model) {
//		
//		model.addAttribute("no", vo.getNo());
//		return "/guestbook/delete";
//	}
//	
//	@RequestMapping(value="/delete", method=RequestMethod.POST)
//	public String delete(GuestbookVo vo) {	// no, password
//		guestbookService.deleteMessage(vo);
//		return "redirect:/guestbook";		// delete 끗
//	}

}
