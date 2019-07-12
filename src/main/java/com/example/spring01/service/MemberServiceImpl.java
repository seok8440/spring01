package com.example.spring01.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring01.model.dao.MemberDAO;
import com.example.spring01.model.dto.MemberDTO;

@Service //시작할 때 스프링이 메모리에 올려줌(없으면 실행안됨)
public class MemberServiceImpl implements MemberService {
	
	@Inject // 스프링 컨테이너가 만든 DAO 객체가 연결됨 (의존관계 주입)
	MemberDAO memberDao; //주소를 가지고있음 null값이 아님
	
	@Override
	public List<MemberDTO> memberList() {
		System.out.println();
		return memberDao.memberList();
	}

	@Override
	public void insertMember(MemberDTO dto) {
		memberDao.insertMember(dto);

	}

	@Override
	public MemberDTO viewMember(String userid) {
		return memberDao.viewMember(userid);
	}

	@Override
	public void deleteMember(String userid) {
		memberDao.deleteMember(userid);
	}

	@Override
	public void updateMember(MemberDTO dto) {
		memberDao.updateMember(dto);
	}

	@Override
	public boolean checkPw(String userid, String passwd) {
		return memberDao.checkPw(userid, passwd);
	}

}
