package com.douzone.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.SiteRepository;
import com.douzone.mysite.vo.SiteVo;

@Service
public class SiteService {
	
	@Autowired
	private SiteRepository siteRepository;

	public SiteVo getSite() {				// DB에서 리스트 불러옴
		return siteRepository.find();
	}
	
	public SiteVo updateSite(SiteVo vo) {	// 업데이트 하는 부분 -과제
		return null;
	}

}
