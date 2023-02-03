package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	
	@Autowired
	private DataSource dataSouce;
	@Autowired
	private SqlSession sqlSession;
	
//	public List<BoardVo> findAllByPageAndKeyWord(int page, String keyword, int size){
//		Map<String, Object> map = new HashMap();
//		map.put("page", page);
//		map.put("size", size);
//		map.put("keyword", keyword);
//		
//		return sqlSession.selectOne("board.findAllByPageAndKeyWord", map);
//	}

	public int getTotalCount(String keyword) {
		sqlSession.selectOne("board.getTota", keyword);
		return 0;
	}

	/* ========== 전체 페이지 가지고 오기 ========== */
	public List<BoardVo> findAll() {
		return sqlSession.selectList("board.findAll");
	}

	public void insert(BoardVo vo) {
		sqlSession.insert("board.insert", vo);
	}
	
	public BoardVo viewPageByNo(int no) {
		return sqlSession.selectOne("board.viewPageByNo", no);
	}
	public BoardVo getContents(int no) {
		return sqlSession.selectOne("board.getContents", no);
	}
	public void update(BoardVo vo) {
		sqlSession.update("board.update", vo);
	}

	public void delete(Long no, Long userNo) {
		Map<String, Object> map = Map.of("no", no, "userNo", userNo);
		sqlSession.delete("board.delete", map);
	}

	public BoardVo findInForReply(BoardVo vo) {
		return sqlSession.selectOne("board.findInForReply", vo.getNo());
	}

	public void insertReplt(BoardVo vo) {
		sqlSession.update("board.updateReply", vo);
		sqlSession.insert("board.insertReply", vo);
	}

	public List<BoardVo> findAllbyKeyWord(String keyword) {
		return sqlSession.selectList("board.findAllbyKeyWord", keyword);
	}

}
