package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.PhoneService;
import com.javaex.vo.PersonVo;

@Controller
/* @RequestMapping(value="/guest") */
public class PhoneController {

	// 필드
	@Autowired
	private PhoneService phoneService;

	// 생성자

	// 메소드 gs

	// 메소드 일반

	// 전화번호 리스트
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("list");

		// Service를 통해서 personList(주소)를 가져온다
		// PhoneService phoneService = new PhoneService();
		List<PersonVo> personList = phoneService.getPersonList();
		System.out.println(personList);
		// ds데이터 보내기 --> request attribute에 넣는다
		model.addAttribute("personList", personList);

		return "list";
	}

	// 전화번호 등록 폼

	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {

		System.out.println("writeForm");
		return "writeForm";
	}

	// 전화번호등록
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("write");

		// 서비스로 저장
		phoneService.personInsert(personVo);

		int count = phoneService.personInsert(personVo);
		
		System.out.println(count);
		// 리다이렉트

		return "redirect:/list";
	}

	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete2(@RequestParam("no") int no) {

		// 파라미터 꺼내기
		System.out.println(no);

		// 서비스로 저장
		int count = phoneService.personDelete(no);

		System.out.println(count);

		return "redirect:/list";
	}

	// 전화번호 수정폼
	@RequestMapping(value = "/updateForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateForm(Model model, @RequestParam("no") int no) {

		// 주소에서 값 꺼내기
		System.out.println(no);

		// 서비스로 저장
		PersonVo personVo = phoneService.getPerson(no);

		// ds데이터 보내기 --> request attribute에 넣는다
		model.addAttribute("personVo", personVo);

		return "updateForm";
	}

	// 전화번호 수정폼
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(Model model, @ModelAttribute PersonVo personVo) {

		

		// 서비스로 저장
		int count = phoneService.personUpdate(personVo);
		System.out.println(count);
		
		return "redirect:/list";
	}

}