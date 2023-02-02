package com.douzone.mysite.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
