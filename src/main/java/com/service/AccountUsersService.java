package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class AccountUsersService implements IAccountUsersService{
	
	@Autowired
    private AccountUsersService repository;

	
	@Override
    public String userAccounts(String uname, String pass) {

        System.out.println("In Account Service " +uname +"  " +pass);

        return "Hello Test";
    }
}
