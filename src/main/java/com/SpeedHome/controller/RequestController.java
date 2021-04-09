package com.SpeedHome.controller;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.SpeedHome.model.AuthenticationRequest;
import com.SpeedHome.model.AuthenticationResponse;
import com.SpeedHome.service.SpeedHomeService;
import com.SpeedHome.service.MyUserDetailsService;
import com.SpeedHome.util.JwtUtil;

@RestController
public class RequestController {

	@Autowired
	SpeedHomeService speedHomeService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	private Logger logger = Logger.getLogger(RequestController.class);

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	@RequestMapping(value = "/getActiveUser")
	public ResponseEntity<Object> getActiveUser() throws IOException {
		logger.info("Function: " + "getActiveUser");
		return new ResponseEntity<Object>(speedHomeService.getActiveUser(), HttpStatus.OK);
	}

	@RequestMapping(value = "/createProperty/{type}/{address}")
	public ResponseEntity<Object> createProperty(
			@PathVariable(value = "type", required = true) String type,
			@PathVariable(value = "address", required = true) String address) {
		logger.info("Function: " + "createProperty");
		return new ResponseEntity<Object>(speedHomeService.createProperty(type,address), HttpStatus.OK);
	}

	@RequestMapping(value = "/updateProperty/{type}/{address}")
	public ResponseEntity<Object> getNewCasesCountrySort(
			@PathVariable(value = "type", required = true) String type,
			@PathVariable(value = "address", required = true) String address) {
		logger.info("Function: " + "updateProperty");
		 return new ResponseEntity<Object>(speedHomeService.updateProperty(type, address),HttpStatus.OK);
	}

	@RequestMapping(value = "/searchProperty/{address}")
	public ResponseEntity<Object> searchProperty(@PathVariable(value = "address", required = true) String address) {
		logger.info("Function: " + "searchProperty");
		 return new ResponseEntity<Object>(speedHomeService.searchProperty(address),HttpStatus.OK);
	}

	@RequestMapping(value = "/aprroveProperty/{address}")
	public ResponseEntity<Object> aprroveProperty(@PathVariable(value = "address", required = true) String address) {
		logger.info("Function: " + "aprroveProperty");
		 return new ResponseEntity<Object>(speedHomeService.aprroveProperty(address), HttpStatus.OK);
	}
}
