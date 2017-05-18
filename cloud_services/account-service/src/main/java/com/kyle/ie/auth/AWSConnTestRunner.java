package com.kyle.ie.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.kyle.ie.auth.repositories.IUserRepository;

@Component
public class AWSConnTestRunner implements CommandLineRunner {
	
	@Autowired
	private IUserRepository _repo;

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(_repo.findAll());
		
	}

}
