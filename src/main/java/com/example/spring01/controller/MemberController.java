package com.example.spring01.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.naming.factory.BeanFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring01.model.dao.MemberDAO;
import com.example.spring01.model.dto.MemberDTO;
import com.example.spring01.service.MemberService;

@Controller // 현재 클래스를 Controller Bean으로 등록함
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Inject // MemberService 객체가 주입됨
	MemberService memberService;

	// @Inject // 스프링 컨테이너가 만든 DAO 객체가 연결됨 (의존관계 주입)
	// MemberDAO memberDao; // 주소를 가지고있음 null값이 아님

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main() {
		return "main";
	}

	@RequestMapping(value = "member/list.do", method = RequestMethod.GET) // 사용자가 요청하는 주소(get 방식으로 전송)
	public String memberList(Model model) {
		//List<MemberDTO> list = memberService.memberList(); // 데이터베이스에서 data를 꺼내옴
		// List<MemberDTO> list = memberDao.memberList();
		//logger.info("회원목록 : " + list);
		logger.info("회원목록 : " + memberService.memberList());
		//model.addAttribute("list", list); // Model 객체를 이용해서, view로 Data전달
		model.addAttribute("list", memberService.memberList()); 
		return "member/member_list"; // 출력 페이지로 포워딩 (뷰 파일 리턴)
	}

	@RequestMapping(value = "member/write.do", method = RequestMethod.GET)
	public String write() {
		return "member/write";
	}

	@RequestMapping(value = "member/insert.do", method = RequestMethod.GET)
	public String insert(@ModelAttribute MemberDTO dto) {
		memberService.insertMember(dto);
		// memberDao.insertMember(dto);
		return "redirect:/member/list.do"; // redirect를 안붙이면 포워드방식이라 주소가안바뀜
	}

	@RequestMapping(value = "member/view.do", method = RequestMethod.GET)
	public String insert(@RequestParam String userid, Model model) {
		logger.info("클릭한 아이디 : " + userid);
		model.addAttribute("dto", memberService.viewMember(userid));
		return "member/view";
	}

	@RequestMapping(value = "member/update.do")
	public String update(@ModelAttribute MemberDTO dto, Model model) {
		boolean result = memberService.checkPw(dto.getUserid(), dto.getPasswd());
		logger.info("비밀번호 확인 : " + result);
		
		if(result) {
			memberService.updateMember(dto);
			return "redirect:/member/list.do";
		} else {
			MemberDTO dto2 = memberService.viewMember(dto.getUserid());
			dto.setJoin_date(dto2.getJoin_date());
			model.addAttribute("dto", dto);
			model.addAttribute("message", "비밀번호가 일치하지 않습니다");
			return "member/view";
		}
		
	}
	
	@RequestMapping("member/delete.do")
	public String delete(@RequestParam String userid, 
			@RequestParam String passwd, Model model) {
		boolean result = memberService.checkPw(userid, passwd);
		
		if(result) {
			memberService.deleteMember(userid);
			return "redirect:/member/list.do";
		} else {
			model.addAttribute("message", "비밀번호가 일치하지 않습니다");
			model.addAttribute("dto", memberService.viewMember(userid));
			return "member/view";
		}
	}
}
