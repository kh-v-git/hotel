package com.hotel.impl.admin.user;

import com.hotel.utils.DataBaseManager;
import com.hotel.utils.QueryGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositorySQLImpl implements UserRepository {
    private static final Logger log = LogManager.getLogger(UserRepositorySQLImpl.class);

    private static final String SELECT_ALL_FROM_USER = "SELECT * FROM user";
    private static final String INSERT_NEW_USER = "INSERT INTO user VALUES (DEFAULT, ?, ?, ?, ?, ?, ?,?,?)";
    private static final String GET_USER_BY_ID = "SELECT * FROM user WHERE userID='%d'";
    private static final String DELETE_USER_BY_ID = "DELETE FROM user WHERE userID='%d'";
    private static final String UPDATE_USER_BY_ID = "UPDATE user SET email='%s', password='%s', firstName='%s', lastName='%s', phone='%s', role='%s', status='%s', about='%s' WHERE userID='%d'";
    public static final String FIND_ALL_FROM_USER_EMAIL = "SELECT * FROM user WHERE userEmail='%s'";

    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private User user;
    String sqlInsertion = null;

    @Override
    public List<User> getUserList(String searchText, List<String> searchParametersList) {
        con = null;
        pstmt = null;
        rs = null;
        List<User> userList = new ArrayList<>();
        try {
            con = DataBaseManager.getInstance().getConnection();

            String queryPart = QueryGenerator.makeQuery(searchText, searchParametersList);
            sqlInsertion = SELECT_ALL_FROM_USER + queryPart;
            //sqlInsertion = SELECT_ALL_FROM_USER;
            pstmt = con.prepareStatement(sqlInsertion);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setUserID(rs.getInt("userID"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setPhone(rs.getString("phone"));
                user.setRole(rs.getString("role"));
                user.setStatus(rs.getString("status"));
                user.setAbout(rs.getString("about"));
                userList.add(user);
            }
        } catch (
                SQLException ex) {
            log.log(Level.ERROR, "Failed to read DB data while getting User List", ex);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return userList;
    }

    @Override
    public boolean setUser(User user) {
        con = null;
        pstmt = null;
        try {
            con = DataBaseManager.getInstance().getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(INSERT_NEW_USER);
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getFirstName());
            pstmt.setString(4, user.getLastName());
            pstmt.setString(5, user.getPhone());
            pstmt.setString(6, user.getRole());
            pstmt.setString(7, user.getStatus());
            pstmt.setString(8, user.getAbout());
            pstmt.executeUpdate();
            con.commit();
            return true;
        } catch (SQLException ex) {
            log.log(Level.ERROR, "Failed add new User to  DB", ex);
            rollback(con);
        } finally {
            close(pstmt);
            close(con);
        }
        return false;
    }

    @Override
    public User getUser(int id) {
        con = null;
        pstmt = null;
        rs = null;
        user = new User();
        try {
            con = DataBaseManager.getInstance().getConnection();
            con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            sqlInsertion = String.format(GET_USER_BY_ID, id);
            pstmt = con.prepareStatement(sqlInsertion);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user.setUserID(rs.getInt("userID"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setPhone(rs.getString("phone"));
                user.setRole(rs.getString("role"));
                user.setStatus(rs.getString("status"));
                user.setAbout(rs.getString("about"));
            }
        } catch (SQLException ex) {
            log.log(Level.ERROR, "Failed get Room by ID from DB", ex);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return user;
    }

    @Override
    public boolean deleteUser(int id) {
        con = null;
        pstmt = null;
        try {
            con = DataBaseManager.getInstance().getConnection();
            con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            sqlInsertion = String.format(DELETE_USER_BY_ID, id);
            pstmt = con.prepareStatement(sqlInsertion);
            pstmt.executeUpdate();
            con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            return true;
        } catch (SQLException ex) {
            log.log(Level.ERROR, "Failed delete User by ID from DB", ex);
        } finally {
            close(pstmt);
            close(con);
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        con = null;
        pstmt = null;
        try {
            con = DataBaseManager.getInstance().getConnection();
            con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            sqlInsertion = String.format(UPDATE_USER_BY_ID, user.getEmail(), user.getPassword(), user.getFirstName(),
                    user.getLastName(), user.getPhone(), user.getRole(), user.getStatus(), user.getAbout(), user.getUserID());
            pstmt = con.prepareStatement(sqlInsertion);
            pstmt.executeUpdate();
            con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            return true;
        } catch (SQLException ex) {
            log.log(Level.ERROR, "Failed delete User by ID from DB", ex);
        } finally {
            close(pstmt);
            close(con);
        }
        return false;
    }

    @Override
    public Optional<User> authUser(String email) {
        con = null;
        pstmt = null;
        rs = null;
        sqlInsertion = null;
        user = new User();
        boolean found = false;
        try {
            con = DataBaseManager.getInstance().getConnection();
            sqlInsertion = String.format(FIND_ALL_FROM_USER_EMAIL, email);
            pstmt = con.prepareStatement(sqlInsertion);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                user.setUserID(rs.getInt("userID"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setPhone(rs.getString("phone"));
                user.setRole(rs.getString("role"));
                user.setStatus(rs.getString("status"));
                user.setAbout(rs.getString("about"));
                found = true;
            }
        } catch (
                SQLException ex) {
            log.log(Level.ERROR, "Failed to read DB data for user", ex);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        if (found) {
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    private static void close(AutoCloseable ac) {
        if (ac != null) {
            try {
                ac.close();
            } catch (Exception e) {
                log.log(Level.ERROR, "DB close failed in UserRepositorySQLImpl", e);
            }
        }
    }

    private static void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (SQLException e) {
                log.log(Level.ERROR, "DB rollback failed UserRepositorySQLImpl", e);
            }
        }
    }
}
