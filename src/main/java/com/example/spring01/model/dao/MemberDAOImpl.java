package com.example.spring01.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.spring01.model.dto.MemberDTO;

@Repository // 현재클래스가 서버가 올라올때 이 클래스를 메모리에 자동으로 올려줌(스프링에서 관리하는 객체가 됨)
public class MemberDAOImpl implements MemberDAO {
	
	// 로깅 처리를 위한 객체 선언
	private static final Logger logger=
			LoggerFactory.getLogger(MemberDAOImpl.class);
	
	// SqlSession 객체를 개발자가 직접 생성하지 않고 스프링에서 연결시켜줌(mybatis 호출)
	// 의존관계 주입 (mybatis가 바뀌면 다 바뀌어야 함)(root-context.xml - SqlSession 객체주입)
	@Inject 
	SqlSession sqlSession;

	@Override
	public List<MemberDTO> memberList() {
		logger.info("memberList called...");
		// sql mapper에 작성된 sql 코드가 실행됨(auto commit & close)
		return sqlSession.selectList("member.memberList");
	}

	@Override
	public void insertMember(MemberDTO vo) {
		//auto commit & close
		sqlSession.insert("member.insertMember", vo);
	
	}

	@Override
	public MemberDTO viewMember(String userid) {
		return sqlSession.selectOne("member.viewMember", userid);
	}

	@Override
	public void deleteMember(String userid) {
		sqlSession.delete("member.deleteMember", userid);
	}

	@Override
	public void updateMember(MemberDTO vo) {
		sqlSession.update("member.updateMember", vo);
	}

	@Override
	public boolean checkPw(String userid,  String passwd) {
		boolean result = false;
		Map<String, String> map = new HashMap<>();
		map.put("userid", userid);
		map.put("passwd", passwd);
		int count = sqlSession.selectOne("member.checkPw", map);
		//리턴 값이 1이면 true, 0이면 false
		if(count==1) result=true;
		return result;
	}

}

