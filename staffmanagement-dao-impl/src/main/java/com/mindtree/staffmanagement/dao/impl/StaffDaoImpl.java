package com.mindtree.staffmanagement.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.mindtree.staffmanagement.dao.interfaces.StaffDao;
import com.mindtree.staffmanagement.dao.interfaces.exception.DaoException;
import com.mindtree.staffmanagement.model.entity.Member;
import com.mindtree.staffmanagement.model.entity.Role;

public class StaffDaoImpl implements StaffDao {

	private EntityManagerFactory entityManagerFactory = null;

	public EntityManagerFactory getEntityManagerFactory() throws DaoException {

		if (entityManagerFactory == null) {
			try {
				entityManagerFactory = Persistence.createEntityManagerFactory("myPersistence");
			} catch (Exception exception) {
				throw new DaoException("Database is down or invalid credentials supplied");
			}
		}
		return entityManagerFactory;
	}

	@Override
	public Member addMember(Member member) throws DaoException {

		EntityManager entityManager = getEntityManagerFactory().createEntityManager();

		if (entityManager != null) {

			try {

				entityManager.getTransaction().begin();
				entityManager.persist(member);
				entityManager.getTransaction().commit();
			} catch (PersistenceException persistenceException) {
				entityManager.getTransaction().rollback();
				throw new DaoException(persistenceException.getMessage(), persistenceException.getCause());
			} finally {
				if (entityManager != null) {
					entityManager.close();
				}
			}
		}
		return member;
	}

	@Override
	public void deleteMember(String mid) throws DaoException {

		EntityManager entityManager = getEntityManagerFactory().createEntityManager();

		Member member = null;

		if (entityManager != null) {

			try {

				entityManager.getTransaction().begin();
				member = getMember(mid);
				if (entityManager.contains(member)) {
					entityManager.remove(member);
				} else {
					entityManager.remove(entityManager.merge(member));
				}
				entityManager.getTransaction().commit();
			} catch (PersistenceException persistenceException) {
				entityManager.getTransaction().rollback();
				throw new DaoException(persistenceException.getMessage(), persistenceException.getCause());
			} finally {
				if (entityManager != null) {
					entityManager.close();
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> find(String hqlQuery, String[] parameters, String[] values) throws DaoException {

		List<Member> memberList = new ArrayList<>();
		EntityManager entityManager = getEntityManagerFactory().createEntityManager();
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
				throw new DaoException(persistenceException.getMessage(), persistenceException.getCause());
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
	public List<Member> getAllMembers() throws DaoException {

		String getAllQuery = "SELECT m FROM Member m";

		List<Member> memberList = new ArrayList<>();
		EntityManager entityManager = getEntityManagerFactory().createEntityManager();
		Query query = entityManager.createQuery(getAllQuery);

		if (entityManager != null) {
			try {

				memberList = query.getResultList();
			} catch (PersistenceException persistenceException) {
				throw new DaoException(persistenceException.getMessage(), persistenceException.getCause());
			} finally {
				if (entityManager != null) {
					entityManager.close();
				}
			}
		}
		return memberList;
	}

	@Override
	public Member getMember(String mid) throws DaoException {

		String getAllQuery = "SELECT m FROM Member m WHERE m.mid=:mid";
		Member member = null;
		EntityManager entityManager = getEntityManagerFactory().createEntityManager();
		Query query = entityManager.createQuery(getAllQuery);
		query.setParameter("mid", mid);

		if (entityManager != null) {
			try {

				member = (Member) query.getSingleResult();
			} catch (PersistenceException persistenceException) {
				throw new DaoException(persistenceException.getMessage(), persistenceException.getCause());
			} finally {
				if (entityManager != null) {
					entityManager.close();
				}
			}
		}
		return member;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> getMembers(Role role) throws DaoException {
		String getAllQuery = "SELECT m FROM Member m WHERE m.role=:role";

		List<Member> memberList = new ArrayList<>();
		EntityManager entityManager = getEntityManagerFactory().createEntityManager();
		Query query = entityManager.createQuery(getAllQuery);
		query.setParameter("role", role);

		if (entityManager != null) {
			try {

				memberList = query.getResultList();
			} catch (PersistenceException persistenceException) {
				throw new DaoException(persistenceException.getMessage(), persistenceException.getCause());
			} finally {
				if (entityManager != null) {
					entityManager.close();
				}
			}
		}
		return memberList;
	}

	@Override
	public Member updateMember(Member member) throws DaoException {

		EntityManager entityManager = getEntityManagerFactory().createEntityManager();

		long id = 0;
		Member memberNew = null;

		if (entityManager != null) {

			try {

				entityManager.getTransaction().begin();
				memberNew = getMember(member.getMid());
				id = memberNew.getId();
				memberNew = member;
				memberNew.setId(id);
				entityManager.merge(memberNew);
				entityManager.getTransaction().commit();
			} catch (PersistenceException persistenceException) {
				entityManager.getTransaction().rollback();
				throw new DaoException(persistenceException.getMessage(), persistenceException.getCause());
			} finally {
				if (entityManager != null) {
					entityManager.close();
				}
			}
		}
		return memberNew;
	}
}
