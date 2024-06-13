package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.exception.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account registerAccount(Account account) throws HttpException
    {
        if (account.getUsername().isEmpty()) throw new BadRequestException("Username cannot be blank");
        if (account.getPassword().length() < 4 ) throw new BadRequestException("Password must be at least 4 characters long");
        if (accountRepository.findByUsername(account.getUsername()) != null) throw new ConflictException("An account with that username already exists");
        return accountRepository.save(account);
    }

    public Account login(Account account) throws HttpException
    {
        Account matchingAccount = accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
        if (matchingAccount == null) throw new UnauthorizedException("Invalid username or password");
        return matchingAccount;
    }
}
