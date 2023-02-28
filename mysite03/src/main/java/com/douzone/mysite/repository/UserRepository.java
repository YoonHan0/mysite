package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.exception.UserRepositoryException;
import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(UserVo vo) {
		sqlSession.insert("user.insert", vo);
	}

	public UserVo findByEmailAndPassword(String email, String password) {
		Map<String, Object> map = new HashMap<>();
		map.put("e", email);
		map.put("p", password);
		
		return sqlSession.selectOne("user.findByEmailAndPassword", map);
	}
//	public UserVo findByEmailAndPassword(UserVo vo) {
//		return sqlSession.selectOne("user.findByEmailAndPassword", vo);	//namespace.id, vo type이랑 user.xml에서의 ParameterType이랑 같아야함
//	}	

	public UserVo findByNo(Long no) {
		return sqlSession.selectOne("user.findByNo", no);
	}

	public void update(UserVo vo) {
		sqlSession.update("user.update", vo);
	}

	public UserVo findByEmail(String email) {
		return sqlSession.selectOne("user.findByEmail", email);
	}
}


