package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	@Autowired
	private GuestbookRepository guestbookRepository;
	
	public List<GuestbookVo> getMessageList() {
		return guestbookRepository.findAll();		// 전체 결과 list 반환
	}
	
	public void deleteMessage(GuestbookVo vo) {
		guestbookRepository.deleteByNoAndPassword(vo.getNo(), vo.getPassword());
	}
	
	public void addMessage(GuestbookVo vo) {
		guestbookRepository.insert(vo);
	}
}
