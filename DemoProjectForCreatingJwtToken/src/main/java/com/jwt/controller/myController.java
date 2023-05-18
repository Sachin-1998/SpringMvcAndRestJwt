package com.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.generatetoken.JwtUtil;
import com.jwt.model.authenticationRequest;
import com.jwt.model.authenticationResponse;
import com.jwt.security.CustomUserDetailsService;

@RestController
public class myController 
{
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
		
	@RequestMapping("/hello")
	public String sayHello()
	{
		return "Hello World";
	}
    
	@RequestMapping(value="/authenticate",method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody authenticationRequest request) throws Exception
	{
		try 
		{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			
		} catch (BadCredentialsException e)
		{
			e.printStackTrace();
			throw new Exception("Bad credintials");
			
		}
		final UserDetails userDetails=customUserDetailsService.loadUserByUsername(request.getUsername());
		final String jwt=jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new authenticationResponse(jwt));
		
	}
}
