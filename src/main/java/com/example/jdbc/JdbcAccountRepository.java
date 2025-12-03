package com.example.jdbc;

import com.example.repositorys.AccountRepository;

public class JdbcAccountRepository implements AccountRepository {
    @Override
    public void createAccount(String firstName, String lastName, String ssn, String password) {

    }

    @Override
    public void updatePassword(int userId, String newPassword) {

    }

    @Override
    public void deleteAccount(int userId) {

    }
}
