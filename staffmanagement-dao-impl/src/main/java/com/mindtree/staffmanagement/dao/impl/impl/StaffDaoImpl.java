package com.mindtree.staffmanagement.dao.impl.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.mindtree.staffmanagement.dao.interfaces.StaffDao;
import com.mindtree.staffmanagement.model.entity.Member;
import com.mindtree.staffmanagement.model.entity.Role;

public class StaffDaoImpl implements StaffDao {

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistence");

	@Override
	public Member addMember(Member member) {

		EntityManager entityManager = entityManagerFactory.createEntityManager();

		if (entityManager != null) {

			try {

				entityManager.getTransaction().begin();
				entityManager.persist(member);
				entityManager.getTransaction().commit();
			} catch (PersistenceException persistenceException) {
				entityManager.getTransaction().rollback();
				persistenceException.printStackTrace();
				member = null;
			} finally {
				if (entityManager != null) {
					entityManager.close();
				}
			}
		}
		return member;
	}

	@Override
	public boolean addMember(List<Member> memberList) {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> find(String hqlQuery, String[] parameters, String[] values) {

		List<Member> memberList = new ArrayList<>();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery(hqlQuery, Member.class);

		if (entityManager != null) {
			try {

				if (parameters != null && values != null && parameters.length == values.length) {

					for (int i = 0; i < parameters.length; i++) {
						query.setParameter(parameters[i], values[i]);
					}

				}
				memberList = query.getResultList();
			} catch (PersistenceException persistenceException) {
				entityManager.getTransaction().rollback();
				persistenceException.printStackTrace();
				memberList = null;
			} finally {
				if (entityManager != null) {
					entityManager.close();
				}
			}
		}
		return memberList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> getAllMembers() {

		String getAllQuery = "SELECT m FROM Member m";

		List<Member> memberList = new ArrayList<>();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery(getAllQuery);

		if (entityManager != null) {
			try {

				memberList = query.getResultList();
			} catch (PersistenceException persistenceException) {
				persistenceException.printStackTrace();
				memberList = null;
			} finally {
				if (entityManager != null) {
					entityManager.close();
				}
			}
		}
		return memberList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> getMembers(Role role) {
		String getAllQuery = "SELECT m FROM Member m WHERE m.role=:role";

		List<Member> memberList = new ArrayList<>();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery(getAllQuery);
		query.setParameter("role", role);

		if (entityManager != null) {
			try {

				memberList = query.getResultList();
			} catch (PersistenceException persistenceException) {
				persistenceException.printStackTrace();
				memberList = null;
			} finally {
				if (entityManager != null) {
					entityManager.close();
				}
			}
		}
		return memberList;
	}

}
