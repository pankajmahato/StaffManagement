package com.mindtree.staffmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.mindtree.staffmanagement.dao.impl.StaffDaoImpl;
import com.mindtree.staffmanagement.dao.interfaces.StaffDao;
import com.mindtree.staffmanagement.dao.interfaces.exception.DaoException;
import com.mindtree.staffmanagement.model.dto.MemberDto;
import com.mindtree.staffmanagement.model.entity.Member;
import com.mindtree.staffmanagement.model.entity.Role;
import com.mindtree.staffmanagement.service.interfaces.Service;
import com.mindtree.staffmanagement.service.interfaces.exception.ServiceException;
import com.mindtree.staffmanagement.util.DtoMapper;
import com.mindtree.staffmanagement.util.exception.InvalidDtoException;

public class ServiceImpl implements Service {

	private StaffDao staffDao = null;

	public StaffDao getStaffDao() {

		if (staffDao == null) {
			staffDao = new StaffDaoImpl();
		}
		return staffDao;
	}

	@Override
	public MemberDto addMember(MemberDto memberDto) throws ServiceException {

		Member member = null;
		if (memberDto != null) {

			try {
				member = DtoMapper.dtoToEntity(memberDto);
				member = getStaffDao().addMember(member);
				memberDto = DtoMapper.entityToDto(member);
			} catch (InvalidDtoException invalidDtoException) {
				throw new ServiceException(invalidDtoException.getMessage(), invalidDtoException.getCause());
			} catch (DaoException daoException) {
				throw new ServiceException(daoException.getMessage(), daoException.getCause());
			}
		}

		return memberDto;
	}

	@Override
	public void deleteMember(String mid) throws ServiceException {

		if (mid != null && mid.length() != 0) {

			try {
				getStaffDao().deleteMember(mid);
			} catch (DaoException daoException) {
				throw new ServiceException(daoException.getMessage(), daoException.getCause());
			}
		}
	}

	@Override
	public List<MemberDto> getAllMembers() throws ServiceException {

		List<Member> memberList = null;
		List<MemberDto> memberDtoList = null;
		MemberDto memberDto = null;

		try {
			memberList = getStaffDao().getAllMembers();
		} catch (DaoException daoException) {
			throw new ServiceException(daoException.getMessage(), daoException.getCause());
		}

		if (memberList != null) {

			memberDtoList = new ArrayList<>();
			/*-memberList.stream().forEach(member ->{
				
			});*/
			for (Member m : memberList) {

				try {
					memberDto = DtoMapper.entityToDto(m);
				} catch (InvalidDtoException invalidDtoException) {
					throw new ServiceException(invalidDtoException.getMessage(), invalidDtoException.getCause());
				}
				memberDtoList.add(memberDto);
			}
		}

		return memberDtoList;
	}

	@Override
	public MemberDto getMember(String mid) throws ServiceException {

		Member member = null;
		MemberDto memberDto = null;

		try {
			member = getStaffDao().getMember(mid);
		} catch (DaoException daoException) {
			throw new ServiceException(daoException.getMessage(), daoException.getCause());
		}

		if (member != null) {

			try {
				memberDto = DtoMapper.entityToDto(member);
			} catch (InvalidDtoException invalidDtoException) {
				throw new ServiceException(invalidDtoException.getMessage(), invalidDtoException.getCause());
			}
		}

		return memberDto;
	}

	@Override
	public List<MemberDto> getMembers(Role role) throws ServiceException {

		List<Member> memberList = null;
		List<MemberDto> memberDtoList = null;
		MemberDto memberDto = null;

		try {
			memberList = getStaffDao().getMembers(role);
		} catch (DaoException daoException) {
			throw new ServiceException(daoException.getMessage(), daoException.getCause());
		}

		if (memberList != null) {

			memberDtoList = new ArrayList<>();
			for (Member m : memberList) {

				try {
					memberDto = DtoMapper.entityToDto(m);
				} catch (InvalidDtoException invalidDtoException) {
					throw new ServiceException(invalidDtoException.getMessage(), invalidDtoException.getCause());
				}
				memberDtoList.add(memberDto);
			}
		}

		return memberDtoList;
	}

	@Override
	public MemberDto updateMember(MemberDto memberDto) throws ServiceException {

		Member member = null;
		if (memberDto != null) {
			try {
				member = DtoMapper.dtoToEntity(memberDto);
				member = getStaffDao().updateMember(member);
				memberDto = DtoMapper.entityToDto(member);
			} catch (InvalidDtoException invalidDtoException) {
				throw new ServiceException(invalidDtoException.getMessage(), invalidDtoException.getCause());
			} catch (DaoException daoException) {
				throw new ServiceException(daoException.getMessage(), daoException.getCause());
			}
		}

		return memberDto;
	}
}
