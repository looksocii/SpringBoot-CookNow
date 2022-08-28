package com.springboot.controller;

import com.springboot.model.UserAccount;
import com.springboot.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/UserAccount")
public class UserAccountController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private UserAccountRepository userAccountRepo;

    @PostMapping("/UserAccount")
    public String listAll(Model model) {
        String sql = "INSERT INTO useraccount (id, user_name, pass_word, email, phone, birth_date) VALUES (?, ?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql, 22, "TestSB02", "12345", "test@email.com", "0801800391", LocalDateTime.now());

        if (result > 0) {
            System.out.println("Insert successfully.");
        }
        return "Insert successfully.";
    }

    @GetMapping(path = "/GetUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserAccount> getUserAccount()
    {
        String sql = "SELECT * FROM UserAccount";
        List<UserAccount> listUsers = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(UserAccount.class));
        return listUsers;
    }
}