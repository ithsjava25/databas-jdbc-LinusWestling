package com.example.repositorys;

public interface AccountRepository {
    void createAccount(String firstName, String lastName, String ssn, String password);
    void updatePassword(int userId, String newPassword);
    void deleteAccount(int userId);
}
