package com.mindtree.staffmanagement.webapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.util.UriComponentsBuilder;

import com.mindtree.staffmanagement.model.dto.ErrorDto;
import com.mindtree.staffmanagement.model.dto.MemberDto;
import com.mindtree.staffmanagement.model.entity.Role;
import com.mindtree.staffmanagement.service.impl.ServiceImpl;
import com.mindtree.staffmanagement.service.interfaces.Service;
import com.mindtree.staffmanagement.service.interfaces.exception.ServiceException;
import com.mindtree.staffmanagement.util.json.JsonUtil;
import com.mindtree.staffmanagement.webapp.controller.exception.EmptyResultSetException;

@Controller
@ControllerAdvice
public class FrontController {

	Service service = new ServiceImpl();

	@RequestMapping(value = "/rest/all", method = RequestMethod.GET)
	public ResponseEntity<List<MemberDto>> getAllMembers(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServiceException, EmptyResultSetException {

		List<MemberDto> membersDtoList = service.getAllMembers();

		if (membersDtoList == null || membersDtoList.isEmpty()) {
			throw new EmptyResultSetException("No records present");
		}

		return new ResponseEntity<List<MemberDto>>(membersDtoList, HttpStatus.OK);

	}

	@RequestMapping(value = "/rest/role", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getMembers(@RequestParam(value = "role", required = true) String roleUrl)
			throws IOException, ServiceException {

		Role role = null;
		try {
			role = Role.valueOf(roleUrl.toUpperCase());
		} catch (Exception exception) {
			ErrorDto errorDto = new ErrorDto();
			errorDto.setError("Invalid role provided");
			return new ResponseEntity<String>(JsonUtil.serialize(errorDto), HttpStatus.BAD_REQUEST);
		}

		List<MemberDto> membersDtoList = service.getMembers(role);

		return new ResponseEntity<String>(JsonUtil.serialize(membersDtoList), HttpStatus.OK);

	}

	@RequestMapping(value = "/rest/{mid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getMember(@PathVariable("mid") String mid)
			throws IOException, ServiceException, EmptyResultSetException {

		MemberDto membersDto = service.getMember(mid);

		if (membersDto == null) {
			throw new EmptyResultSetException("No records present");
		}

		return new ResponseEntity<String>(JsonUtil.serialize(membersDto), HttpStatus.OK);

	}

	@RequestMapping(value = "/rest/create", method = RequestMethod.POST)
	public ResponseEntity<Void> createStaff(@RequestBody MemberDto memberDto, UriComponentsBuilder ucBuilder)
			throws ServiceException {

		memberDto = service.addMember(memberDto);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/rest/{mid}").buildAndExpand(memberDto.getMid()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/rest/update", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateStaff(@RequestBody MemberDto memberDto, UriComponentsBuilder ucBuilder)
			throws ServiceException {

		memberDto = service.updateMember(memberDto);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/rest/{mid}").buildAndExpand(memberDto.getMid()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/rest/{mid}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> updateStaff(@PathVariable("mid") String mid) throws ServiceException {

		service.deleteMember(mid);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<ErrorDto> rulesForInternalServerError(HttpServletRequest req,
			ServiceException serviceException) {
		ErrorDto error = new ErrorDto();
		error.setError(serviceException.getMessage());
		return new ResponseEntity<ErrorDto>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(EmptyResultSetException.class)
	public ResponseEntity<ErrorDto> rulesForEmptyResultSet(HttpServletRequest req,
			EmptyResultSetException emptyResultSetException) {
		ErrorDto error = new ErrorDto();
		error.setError(emptyResultSetException.getMessage());
		return new ResponseEntity<ErrorDto>(error, HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDto> handleNoHandlerFoundException(HttpServletRequest req,
			NoHandlerFoundException noHandlerFoundException) {
		ErrorDto error = new ErrorDto();
		error.setError("Invalid URL provided");
		return new ResponseEntity<ErrorDto>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorDto> handleHttpRequestMethodNotSupported(HttpServletRequest req,
			HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException) {
		ErrorDto error = new ErrorDto();
		error.setError("Invalid HTTP Method");
		return new ResponseEntity<ErrorDto>(error, HttpStatus.METHOD_NOT_ALLOWED);
	}

	/*-@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorDto handleHttpRequestMethodNotSupportedException(HttpServletRequest req, HttpRequestMethodNotSupportedException ex) {
		ErrorDto error = new ErrorDto();
		error.setError("Invalid HTTP Method");
		return error;
	}*/

}
