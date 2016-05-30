package com.mindtree.staffmanagement.dao.impl;

import java.util.List;

import com.mindtree.staffmanagement.dao.interfaces.StaffDao;
import com.mindtree.staffmanagement.dao.interfaces.exception.DaoException;
import com.mindtree.staffmanagement.model.dto.MemberDto;
import com.mindtree.staffmanagement.model.entity.Competency;
import com.mindtree.staffmanagement.model.entity.Gender;
import com.mindtree.staffmanagement.model.entity.Member;
import com.mindtree.staffmanagement.model.entity.Role;
import com.mindtree.staffmanagement.util.DtoMapper;
import com.mindtree.staffmanagement.util.exception.InvalidDtoException;
import com.mindtree.staffmanagement.util.json.JsonUtil;

public class Main {

	public static void main(String[] args) throws DaoException, InvalidDtoException {

		StaffDao staffDao = new StaffDaoImpl();
		Member member = new Member();
		member.setAge(23);
		member.setCompetency(Competency.C1);
		member.setGender(Gender.MALE);
		member.setMid("M1035002");
		member.setName("ABCDEF");
		member.setRole(Role.ADMINISTRATIVE);

		// staffDao.addMember(member);

		/*-String hqlQuery = "SELECT name From Member";
		List<Member> memberList = staffDao.find(hqlQuery, null, null);
		
		for (Member m : memberList) {
			System.out.println(m.getName());
		}*/

		List<Member> memberList = staffDao.getAllMembers();
		for (Member m : memberList) {
			MemberDto memberDto = DtoMapper.entityToDto(m);
			System.out.println(JsonUtil.serialize(memberDto));
		}

		System.out.println(JsonUtil.serialize(memberList));
	}

}
