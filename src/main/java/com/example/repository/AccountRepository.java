package com.example.repository;

import com.example.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findByUsername(String username);
    Account save(Account account);
    Account findByUsernameAndPassword(String username, String password);
    Account findById(int id);
}
