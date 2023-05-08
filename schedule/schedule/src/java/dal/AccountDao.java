/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Accounts;
import model.Students;

/**
 *
 * @author nguyn
 */
public class AccountDao extends DBContext {

    Connection cnn;
    PreparedStatement st;
    ResultSet rs;

    public AccountDao() {
        connect();
    }

    public void connect() {
        try {
            cnn = super.connection;
            if (cnn != null) {
                System.out.println("Connect success");
            }
            System.out.println("Connect succesful");
        } catch (Exception e) {
            System.out.println("Connect fail: " + e.getMessage());
        }
    }

    public List<Accounts> selectAllAccounts() {
        List<Accounts> myListOfAccounts = new ArrayList();
        String sql = "Select * from Accounts";
        try {
            st = cnn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Accounts acc = new Accounts();
                acc.setAccountId(rs.getInt(1));
                acc.setEmail(rs.getString(2));
                acc.setPassword(rs.getString(3));
                acc.setRoll(rs.getInt(4));
                acc.setStudentId(rs.getInt(5));
                acc.setProfessorId(rs.getInt(6));
                myListOfAccounts.add(acc);
            }
        } catch (SQLException e) {
            System.out.println("getFullListOfStudents: " + e.getMessage());
        }
        return myListOfAccounts;
    }

    public void createAccount(Accounts acc) {
        String sql = "insert into Accounts(account_id, email, password, roll, student_id, professor_id) values (?, ? ,? ,? ,? ,?)";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, acc.getAccountId());
            st.setString(2, acc.getEmail());
            st.setString(3, acc.getPassword());
            st.setInt(4, acc.getRoll());
            if (acc.getStudentId() == 0) {
                st.setNull(5, 0);
            } else {
                st.setInt(5, acc.getStudentId());
            }
            if (acc.getProfessorId()== 0) {
                st.setNull(6, 0);
            } else {
                st.setInt(6, acc.getProfessorId());
            }

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("createAccount" + e.getMessage());
        }
    }

    public Accounts selectAccountByUserId(int id) {
        String sql = "Select * from Accounts where account_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                Accounts user = new Accounts();
                user.setAccountId(rs.getInt(1));
                user.setEmail(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setRoll(rs.getInt(4));
                user.setStudentId(rs.getInt(5));
                user.setProfessorId(rs.getInt(6));
                return user;
            }
        } catch (SQLException e) {
            System.out.println("selectAccountByUserId: " + e.getMessage());
        }
        return null;
    }

    public boolean isExit(int id) {
        String sql = "Select * from Accounts where id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("isExit: " + e.getMessage());
        }
        return false;
    }

    public Accounts login(String account, String password) {
        String sql = "Select * from Accounts where email = ? and password = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, account);
            st.setString(2, password);
            rs = st.executeQuery();
            while (rs.next()) {
                int accId = rs.getInt(1);
                String email = rs.getString(2);
                String pass = rs.getString(3);
                int roll = rs.getInt(4);
                int stuId = rs.getInt(5);
                int proId = rs.getInt(6);
                Accounts acc = new Accounts(accId, email, password, roll, stuId, proId);
                return acc;
            }
        } catch (SQLException e) {
            System.out.println("login: " + e.getMessage());
        }
        return null;
    }
    public boolean checkEmail(String account){
        String sql = "Select * from Accounts where email = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, account);
            rs = st.executeQuery();
            if (rs.next()) {
              return true; 
            }
        } catch (SQLException e) {
            System.out.println("login: " + e.getMessage());
        }
        return false;
    }
    public static void main(String[] args) {
        System.out.println(java.sql.Date.valueOf("14-04-2002"));
    }
}
