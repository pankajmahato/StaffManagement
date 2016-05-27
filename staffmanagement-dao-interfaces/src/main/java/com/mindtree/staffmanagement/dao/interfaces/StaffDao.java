package com.mindtree.staffmanagement.dao.interfaces;

import java.util.List;

import com.mindtree.staffmanagement.model.entity.Member;
import com.mindtree.staffmanagement.model.entity.Role;

public interface StaffDao {

	Member addMember(Member member);

	boolean addMember(List<Member> memberList);

	List<Member> find(String hqlQuery, String[] parameters, String[] values);

	List<Member> getAllMembers();

	List<Member> getMembers(Role role);
}
