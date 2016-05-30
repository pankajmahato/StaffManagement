package com.mindtree.staffmanagement.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mindtree.staffmanagement.model.entity.Competency;
import com.mindtree.staffmanagement.model.entity.Gender;
import com.mindtree.staffmanagement.model.entity.Role;

@JsonPropertyOrder({ "id", "mid", "name", "age", "gender", "competency", "role" })
public class MemberDto {

	@JsonProperty("id")
	private long id;

	@JsonProperty("mid")
	private String mid;

	@JsonProperty("name")
	private String name;

	@JsonProperty("age")
	private int age;

	@JsonProperty("gender")
	private Gender gender;

	@JsonProperty("competency")
	private Competency competency;

	@JsonProperty("role")
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

}
