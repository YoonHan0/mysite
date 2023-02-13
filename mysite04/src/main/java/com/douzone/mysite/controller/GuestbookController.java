package com.douzone.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping("")
	public String list(Model model) {
		List<GuestbookVo> list = guestbookService.getMessageList();
		
		model.addAttribute("list", list);
		return "/guestbook/list";
	}
	
	@RequestMapping("/add")
	public String add(GuestbookVo vo) {		// name, password, message
		System.out.println("1===============" + vo);
		guestbookService.addMessage(vo);
		
		System.out.println("2===============" + vo);
		return "redirect:/guestbook";	// add 했으니까 작업 끝 Redirect!
	}
	
	@RequestMapping(value = "/delete", method=RequestMethod.GET)
	public String delete(GuestbookVo vo, HttpSession session, Model model) {
		
		model.addAttribute("no", vo.getNo());
		return "/guestbook/delete";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(GuestbookVo vo) {	// no, password
		guestbookService.deleteMessage(vo);
		return "redirect:/guestbook";		// delete 끗
	}
	
	
}
