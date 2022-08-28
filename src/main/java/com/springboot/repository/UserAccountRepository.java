package com.springboot.repository;

import com.springboot.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

}