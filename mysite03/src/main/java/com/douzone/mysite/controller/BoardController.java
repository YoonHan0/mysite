package com.douzone.mysite.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("")
	public String index(Model model) {
		System.out.println("메인페이지입니다!");
		Map<String, Object> map = boardService.getContetsList();
		
		model.addAllAttributes(map);	// map -> model로 풀어주는 방법
		
		return "board/list";
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
