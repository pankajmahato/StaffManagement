package com.mindtree.staffmanagement.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MEMBER")
@SequenceGenerator(name = "member_seq", sequenceName = "MEMBER_SEQ", allocationSize = 1, initialValue = 1)
public class Member implements Serializable {

	private static final long serialVersionUID = -5545033439747341975L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(generator = "member_seq", strategy = GenerationType.TABLE)
	private long id;

	@Column(name = "MID",unique=true)
	private String mid;

	@Column(name = "NAME")
	private String name;

	@Column(name = "AGE")
	private int age;

	@Column(name = "GENDER")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "COMPETENCY")
	@Enumerated(EnumType.STRING)
	private Competency competency;

	@Column(name = "ROLE")
	@Enumerated(EnumType.STRING)
	private Role role;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Competency getCompetency() {
		return competency;
	}

	public void setCompetency(Competency competency) {
		this.competency = competency;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
