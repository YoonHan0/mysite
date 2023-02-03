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
	
	public Map<String, Object> getContetsList(int pageNo, String keyword) {
		// 현재 페이지 정보를 가지고 begin, end 페이지 계산
		PageVo vo = new PageVo();
		int size = 0;
		int beginNo = 1;
		int endNo = 5;
		
		if(pageNo == 0 || pageNo == 1) {
			beginNo = 1;
			endNo = 5;
		} else {
			beginNo = 1 + ((pageNo-1)/vo.getW_size()) * vo.getW_size();
			endNo = vo.getW_size() + ((pageNo-1)/vo.getW_size()) * vo.getW_size();
		}
		// 리스트 불러오기
		List<BoardVo> list = null;
		
		if(keyword.equals("")) {
			list = boardRepository.findAll();
		} else {
			list = boardRepository.findAllbyKeyWord(keyword);
		}
		System.out.println(":-" + keyword + "-:");
		
		int totalRows = list.size();		// 총 리스트의 수
		int temp = totalRows / vo.getAmount();	// amount: Limit = 5 리스트의 출력 수
		int temp2 = totalRows % vo.getAmount();
		
		if(temp2 != 0) {
			size = temp + 1;
		} else {
			size = temp;
		}
		
		// PageVo에 정보 담기
		vo.setBegin(beginNo);
		vo.setEnd(endNo);
		vo.setNo(pageNo);
		vo.setSize(size);
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
	
	public BoardVo getContents (int no, Long userNo) {		// 수정을 위한, 보안을 위한 acces 제어 추가
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
	
	public void addReply(BoardVo vo, Long no) {							// no, title, contents
		BoardVo vo2 = boardRepository.findInForReply(vo);		// g_no, o_no, depth, userNo
		
		vo.setG_no(vo2.getG_no());
		vo.setO_no(vo2.getO_no());
		vo.setDepth(vo2.getDepth());
		vo.setUserNo(no);
		
		System.out.println("Service ========= " + vo);
		boardRepository.insertReplt(vo);
	}
	
	public List<BoardVo> getContentsList(int page, String keyword) {
		return boardRepository.findAllbyKeyWord(keyword);
	
		
		
//		// view에서 게시판 리스트를 렌더링 하기 위한 데이터 값 계산
//		int beginPage = 0;
//		int prePage = 0;
//		int nextPage = 0;
//		int endPage = 0;
//		
//		// 리스트 가져오기
//		// List<BoardVo> list = boardRepository.findAllByPageAndKeyWord(page, keyword, LIST_SIZE);
//		
//		// 리스트 정보를 Map에 저장
//		Map<String, Object> map = new HashMap<>();
//		
//		return map;
	}

	

	
}
