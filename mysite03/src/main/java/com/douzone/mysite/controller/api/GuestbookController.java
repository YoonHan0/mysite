package com.douzone.mysite.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;

@RestController("guestbookApiController")
@RequestMapping("/guestbook/api")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@GetMapping("")
	public JsonResult list(@RequestParam(value="sno", required=true, defaultValue="0") Long startNo) {
		
		List<GuestbookVo> list = new ArrayList<>();
		list = guestbookService.getMessageList();
		
		return JsonResult.success(list);
	}
	
	@PostMapping("/add") 
	public JsonResult add(@RequestBody GuestbookVo vo) {
		
		guestbookService.addMessage(vo);

		return JsonResult.success(vo);
	}
	
	@DeleteMapping("/delete")
	public String delete(
			@RequestParam(value="no", required=true, defaultValue="0") Long startNo,
			@RequestParam(value="pw", required=true, defaultValue="0") String password) {
		GuestbookVo vo = new GuestbookVo();
		// return guestbookService.deleteMessage(vo) ? JsonResult.success(vo) : JsonResult.fail("비밀번호 오류");
		
		System.out.println(startNo + " ====== " + password);
		vo.setNo(startNo);
		vo.setPassword(password);
		
		guestbookService.deleteMessage(vo);
		
		return "redirect:/guestbook/api";		// delete 끗
		
	}
	
	
	
	
}
