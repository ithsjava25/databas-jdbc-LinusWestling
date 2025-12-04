package com.example.jdbc;

import com.example.repositorys.AccountRepository;
import com.example.SimpleDriverManagerDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JdbcAccountRepository implements AccountRepository {

    SimpleDriverManagerDataSource ds = new SimpleDriverManagerDataSource();

    @Override
    public Boolean createAccount(String firstName, String lastName, String ssn, String password) {

        try (Connection connection = ds.getConnection()) {

            String sql = "insert into account (first_name, last_name, ssn, password) values (?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, ssn);
            ps.setString(4, password);

            int rs = ps.executeUpdate();

            return rs == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Boolean updatePassword(int userId, String newPassword) {

        try (Connection connection = ds.getConnection()){

            String sql = "update account set password = ? where userId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, newPassword);
            ps.setInt(2, userId);

            int rs = ps.executeUpdate();

            return rs == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Boolean deleteAccount(int userId) {

        try (Connection connection = ds.getConnection()){

            String sql = "delete from account where userId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, userId);
            int rs = ps.executeUpdate();

            return rs == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Boolean validateCredentials(String username, String password) {

        try (Connection connection = ds.getConnection()){

            String sql = "select * from account where name = ? and password = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
