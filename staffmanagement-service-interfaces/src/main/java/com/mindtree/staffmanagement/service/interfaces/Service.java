package com.mindtree.staffmanagement.service.interfaces;

import java.util.List;

import com.mindtree.staffmanagement.model.dto.MemberDto;
import com.mindtree.staffmanagement.model.entity.Role;
import com.mindtree.staffmanagement.service.interfaces.exception.ServiceException;

public interface Service {

	MemberDto addMember(MemberDto memberDto) throws ServiceException;

	void deleteMember(String mid) throws ServiceException;

	List<MemberDto> getAllMembers() throws ServiceException;

	MemberDto getMember(String mid) throws ServiceException;

	List<MemberDto> getMembers(Role role) throws ServiceException;

	MemberDto updateMember(MemberDto memberDto) throws ServiceException;
}
