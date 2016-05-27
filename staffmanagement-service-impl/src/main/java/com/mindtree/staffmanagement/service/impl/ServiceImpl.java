package com.mindtree.staffmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.mindtree.staffmanagement.dao.impl.impl.StaffDaoImpl;
import com.mindtree.staffmanagement.dao.interfaces.StaffDao;
import com.mindtree.staffmanagement.model.dto.MemberDto;
import com.mindtree.staffmanagement.model.entity.Member;
import com.mindtree.staffmanagement.service.interfaces.Service;
import com.mindtree.staffmanagement.util.DtoMapper;

public class ServiceImpl implements Service {

	StaffDao staffDao = new StaffDaoImpl();

	@Override
	public List<MemberDto> getAllMembers() {

		List<Member> memberList = null;
		List<MemberDto> memberDtoList = null;
		MemberDto memberDto = null;

		memberList = staffDao.getAllMembers();

		if (memberList != null) {

			memberDtoList = new ArrayList<>();
			for (Member m : memberList) {

				memberDto = DtoMapper.EntityDtoTo(m);
				memberDtoList.add(memberDto);
			}
		}

		return memberDtoList;
	}

}
