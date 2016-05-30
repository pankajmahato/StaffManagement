package com.mindtree.staffmanagement.util;

import com.mindtree.staffmanagement.model.dto.MemberDto;
import com.mindtree.staffmanagement.model.entity.Member;
import com.mindtree.staffmanagement.util.exception.InvalidDtoException;

public class DtoMapper {

	public static Member dtoToEntity(MemberDto memberDto) throws InvalidDtoException {

		if (memberDto == null) {
			throw new InvalidDtoException("Invalid DtoSupplied");
		}

		Member member = new Member();

		member.setAge(memberDto.getAge());
		member.setCompetency(memberDto.getCompetency());
		member.setGender(memberDto.getGender());
		member.setId(memberDto.getId());
		member.setMid(memberDto.getMid());
		member.setName(memberDto.getName());
		member.setRole(memberDto.getRole());

		return member;

	}

	public static MemberDto entityToDto(Member member) throws InvalidDtoException {

		if (member == null) {
			throw new InvalidDtoException("Invalid DtoSupplied");
		}

		MemberDto memberDto = new MemberDto();

		memberDto.setAge(member.getAge());
		memberDto.setCompetency(member.getCompetency());
		memberDto.setGender(member.getGender());
		memberDto.setId(member.getId());
		memberDto.setMid(member.getMid());
		memberDto.setName(member.getName());
		memberDto.setRole(member.getRole());

		return memberDto;

	}
}