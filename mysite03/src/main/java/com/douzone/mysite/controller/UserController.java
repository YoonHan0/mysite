package com.douzone.mysite.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "user/join";
	}

	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@Valid UserVo vo, BindingResult result, Model model) {	// Validation한 결과가 result로 넘어옴
		if(result.hasErrors()) {
//			List<ObjectError> list = result.getAllErrors();
//			for(ObjectError error : list) {
//				System.out.println(error);
//			}
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		
		// userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(HttpSession session, UserVo vo, Model model) {
		UserVo authUser = userService.getUser(vo);
		
		if(authUser == null) {
			model.addAttribute("email", vo.getEmail());
			return "user/login";
		}
		
		session.setAttribute("authUser", authUser);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(HttpSession session, Model model) {
		
		// Access Control
		UserVo authUser = (UserVo)session.getAttribute("authUser"); // authUser로 mapping된 session을 get해서
		if(authUser == null) {										// 로그인된 상태에서 들어왔는지 확인
			return "redirect:/";
		}
		//////////////////////////////////////////////////
		
		
		UserVo vo = userService.getUserByNo(authUser.getNo());
		
		model.addAttribute("userVo", vo);
		return "user/update";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(HttpSession session, Model model, UserVo vo) {	//name, email, password, gender
		// Access Control
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		///////////////////////////////////////////////////
		vo.setNo(authUser.getNo());
		userService.updateUser(vo);
		
		//session.setAttribute("authUser", vo);	// 변경된 정보로 mapping, session을 수정해줌으로써 바로 변경된 정보로 세션 mapping
		authUser.setName(vo.getName());			// 위와 같은 방식인데 name만 변경해주는 방식
		
		return "redirect:/user/update";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
	
}