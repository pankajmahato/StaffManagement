package com.mindtree.bugtracker.webapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mindtree.staffmanagement.model.dto.MemberDto;
import com.mindtree.staffmanagement.service.impl.ServiceImpl;
import com.mindtree.staffmanagement.service.interfaces.Service;
import com.mindtree.staffmanagement.util.json.JsonUtil;

@Controller
public class FrontController {

	Service service = new ServiceImpl();

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public void getAllMembers(HttpServletRequest request, HttpServletResponse response) throws IOException {

		List<MemberDto> membersDtoList = service.getAllMembers();

		response.getWriter().write(JsonUtil.serialize(membersDtoList));

	}

}
