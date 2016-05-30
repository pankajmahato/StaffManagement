package com.mindtree.staffmanagement.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "error" })
public class ErrorDto {

	@JsonProperty("error")
	private String error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
