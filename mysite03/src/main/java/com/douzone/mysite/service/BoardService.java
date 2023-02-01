package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {
	private static final int LIST_SIZE = 5;
	private static final int PAGE_SIZE = 5;
	private BoardRepository boardRepository;
	
	public void addContents(BoardVo vo) {
		
	}
	
	public BoardVo getContents (Long no) {
		return null;
	}
	
	public BoardVo getContents (Long no, Long UserNo) {		// 수정을 위한, 보안을 위한 acces 제어 추가
		return null;
	}
	
	public void updateContents(BoardVo vo) {	// 실제로 update를 작업하는 메소드
		
	}
	
	public void deleteContents(Long no, Long userNo) {	// Delete
		
	}
	public Map<String, Object> getContentsList(int page, String keyword) {
		int toTalCount = boardRepository.getTotalCount(keyword);
		
		// view에서 게시판 리스트를 렌더링 하기 위한 데이터 값 계산
		int beginPage = 0;
		int prePage = 0;
		int nextPage = 0;
		int endPage = 0;
		
		// 리스트 가져오기
		List<BoardVo> list = boardRepository.findAllByPageAndKeyWord(page, keyword, LIST_SIZE);
		
		// 리스트 정보를 Map에 저장
		Map<String, Object> map = new HashMap<>();
		
		return map;
	}

	public Map<String, Object> getContetsList() {
		
		return null;
	}
}
