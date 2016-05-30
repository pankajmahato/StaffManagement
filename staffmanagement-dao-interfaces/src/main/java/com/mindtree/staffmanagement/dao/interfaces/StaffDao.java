package com.mindtree.staffmanagement.dao.interfaces;

import java.util.List;

import com.mindtree.staffmanagement.dao.interfaces.exception.DaoException;
import com.mindtree.staffmanagement.model.entity.Member;
import com.mindtree.staffmanagement.model.entity.Role;

public interface StaffDao {

	Member addMember(Member member) throws DaoException;

	void deleteMember(String mid) throws DaoException;

	List<Member> find(String hqlQuery, String[] parameters, String[] values) throws DaoException;

	List<Member> getAllMembers() throws DaoException;

	Member getMember(String mid) throws DaoException;

	List<Member> getMembers(Role role) throws DaoException;

	Member updateMember(Member member) throws DaoException;
}
