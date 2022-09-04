package com.springboot.controller;

import com.springboot.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/UserAccount")
public class UserAccountController {
    @Autowired
    JdbcTemplate jdbcDriver;

    @PostMapping(path = "/", consumes = {"application/json"})
    public UserAccount handleNonBrowserSubmissions(@RequestBody UserAccount userAccount) {
        String sql = "INSERT INTO useraccount (user_name, pass_word, email, phone, birth_date) VALUES (?, ?, ?, ?, ?)";
        int result = jdbcDriver.update(sql, userAccount.user_name, userAccount.pass_word, userAccount.email, userAccount.phone, userAccount.birth_date);
        if (result > 0) {
            System.out.println("Insert successfully.");
        }
        return userAccount;
    }

    @GetMapping(path = "/GetUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserAccount> getUserAccount()
    {
        String sql = "SELECT * FROM UserAccount";
        return jdbcDriver.query(sql, BeanPropertyRowMapper.newInstance(UserAccount.class));
    }

    @GetMapping(path = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public UserAccount getUser(@PathVariable(value = "id") int id)
    {
        String sql = "SELECT * FROM UserAccount WHERE id = "+id;
        return jdbcDriver.queryForObject(sql, BeanPropertyRowMapper.newInstance(UserAccount.class));
    }

    @PutMapping(path = "/UpdateUser/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public UserAccount updateUser(@PathVariable(value = "id") int id, @RequestBody UserAccount userAccount)
    {
        String sql = "UPDATE UserAccount SET user_name=?, pass_word=?, email=?, phone=?, birth_date=? WHERE id="+id;
        int result = jdbcDriver.update(sql, userAccount.user_name, userAccount.pass_word, userAccount.email, userAccount.phone, userAccount.birth_date);
        if (result > 0) {
            System.out.println("Update successfully.");
            userAccount.setId(id);
        }
        return userAccount;
    }
}