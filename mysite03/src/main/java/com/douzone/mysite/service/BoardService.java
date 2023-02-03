package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.PageVo;

@Service
public class BoardService {
	private static final int LIST_SIZE = 5;
	private static final int PAGE_SIZE = 5;
	
	@Autowired
	private BoardRepository boardRepository;
	
	public Map<String, Object> getContetsList(int pageNo) {
		// 현재 페이지 정보를 가지고 begin, end 페이지 계산
		PageVo vo = new PageVo();
		int beginNo = 1;
		int endNo = 5;
		
		if(pageNo == 0 || pageNo == 1) {
			beginNo = 1;
			endNo = 5;
		} else {
			beginNo = 1 + ((pageNo-1)/vo.getW_size()) * vo.getW_size();
			endNo = 4 + ((pageNo-1)/vo.getW_size()) * vo.getW_size() + 1;
		}
		vo.setBegin(beginNo);
		vo.setEnd(endNo);
		vo.setNo(pageNo);

		// 리스트 불러오기
		List<BoardVo> list = boardRepository.findAll();
		
//		for(BoardVo vo2 : list) {		// 확인 ㅇㅇ
//			System.out.println(vo2);
//		}
	
		// Map에 담아서 return!
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("pageVo", vo);
		
		return map;
	}
	
	public void addContents(BoardVo vo) {
		boardRepository.insert(vo);
	}
	
	public BoardVo getContents (int no) {
		return boardRepository.viewPageByNo(no);	//글을 클릭했을 때 뜨는거
	}
	
	public BoardVo getContents (int no, int userNo) {		// 수정을 위한, 보안을 위한 acces 제어 추가
		BoardVo vo = boardRepository.getContents(no);
		
		// 넘겨받은 userNo랑 DB에서 온 userNo랑 같은지 비교해서 맞으면 return 아니면 음...
//		if(vo.getUserNo() != userNo) {
//			return null;
//		}
		System.out.println("Service: " + vo);
		return vo;
	}
	
	public void updateContents(BoardVo vo) {	// 실제로 update를 작업하는 메소드
		boardRepository.update(vo);
	}
	
	public void deleteContents(Long no, Long userNo) {	// Delete
		boardRepository.delete(no, userNo);
	}
//	public Map<String, Object> getContentsList(int page, String keyword) {
//		int toTalCount = boardRepository.getTotalCount(keyword);
//		
//		// view에서 게시판 리스트를 렌더링 하기 위한 데이터 값 계산
//		int beginPage = 0;
//		int prePage = 0;
//		int nextPage = 0;
//		int endPage = 0;
//		
//		// 리스트 가져오기
//		List<BoardVo> list = boardRepository.findAllByPageAndKeyWord(page, keyword, LIST_SIZE);
//		
//		// 리스트 정보를 Map에 저장
//		Map<String, Object> map = new HashMap<>();
//		
//		return map;
//	}

	
}
