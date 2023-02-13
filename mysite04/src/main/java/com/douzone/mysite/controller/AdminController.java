package com.douzone.mysite.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.FileuploadService;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;

@Auth(role="ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private SiteService siteService;
	@Autowired
	private FileuploadService fileuploadService;
	
	@RequestMapping("")
	public String main(Model model) {
		SiteVo vo = siteService.getSite();
		model.addAttribute("siteVo", vo);
		
		return "admin/main";
	}
	
	@RequestMapping("main/update")
	public String update(
			SiteVo vo,
			@RequestParam("file") MultipartFile file) {		// title, welcome, profile, description 데이터 받아와야 함
		
		String url = fileuploadService.restore(file);		// 잘 들어감ㅇㅇ
		
		vo.setProfile(url);		// url을 담고		
		siteService.updateSite(vo);			// 이제 update 가즈아
		
		SiteVo result = siteService.getSite();	// update하고 select 다시해서 가져옴
		
		// 여기서 변경된 vo값들을 servletContext에 다시 set해줘야 title이 다 변경됨
		servletContext.setAttribute("siteVo", result);
		return "redirect:/admin";
	}
	
//	@RequestMapping("/main/update")	// 강사님 코드
//	public String update(SiteVo vo, MultipartFile file) {
//		String profile = fileuploadService.restore(file);
//		if(profile != null) {
//			vo.setProfile(profile);
//		}
//		
//		SiteVo site = applicationContext.getBean(SiteVo.class);
//		
//		siteService.updateSite(vo);
//		servletContext.setAttribute("sitevo", vo);
////		site.setTitle(vo.getTitle());
////		site.setWelcome(vo.getWelcome());
////		site.setProfile(vo.getProfile());
////		site.setDescription(vo.getDescription());
//		BeanUtils.copyProperties(vo, site);
//		
//		return "redirect:/admin";
//	}
	
	@RequestMapping("/guestbook")
	public String guestbook() {
		
		return "admin/guestbook";
	}
	
	@RequestMapping("/board")
	public String board() {
		
		return "admin/board";
	}
	
	@RequestMapping("/user")
	public String user() {
		
		return "admin/user";
	}
}
