package com.hotel.impl.admin.request;

import com.hotel.utils.DataBaseManager;
import com.hotel.utils.QueryGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestRepositorySQLImpl implements RequestRepository {
    private static final Logger log = LogManager.getLogger(RequestRepositorySQLImpl.class);

    private static final String SELECT_ALL_FROM_REQUEST = "SELECT * FROM user_request";
    private static final String INSERT_NEW_REQUEST = "INSERT INTO user_request VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_REQUEST_BY_ID = "SELECT * FROM user_request WHERE orderRequest_id='%d'";
    private static final String DELETE_REQUEST_BY_ID = "DELETE FROM user_request WHERE orderRequest_id='%d'";
    private static final String UPDATE_REQUEST_BY_ID = "UPDATE user_request SET user_id='%d', " +
            "status='%s', bed_size='%s', adult_capacity='%d', child_capacity='%d', arrival_date='%s', departure_date='%s' WHERE user_request_id='%d'";

    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private Request request;
    String sqlInsertion = null;

    @Override
    public List<Request> getRequestList(String searchText, List<String> searchParametersList) {
        con = null;
        pstmt = null;
        rs = null;
        List<Request> requestList = new ArrayList<>();
        try {
            con = DataBaseManager.getInstance().getConnection();

            String queryPart = QueryGenerator.makeQuery(searchText, searchParametersList);
            sqlInsertion = SELECT_ALL_FROM_REQUEST + queryPart;
            pstmt = con.prepareStatement(sqlInsertion);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                request = new Request();
                request.setUserRequestID(rs.getInt("user_request_id"));
                request.setUserID(rs.getInt("user_id"));
                request.setStatus(rs.getString("status"));
                request.setBedSize(rs.getString("bed_size"));
                request.setAdultCapacity(rs.getInt("adult_capacity"));
                request.setChildCapacity(rs.getInt("child_capacity"));
                request.setArrivalDay(rs.getTimestamp("arrival_day").toLocalDateTime());
                request.setDepartureDay(rs.getTimestamp("departure_day").toLocalDateTime());
                requestList.add(request);
            }
        } catch (
                SQLException ex) {
            log.log(Level.ERROR, "Failed to read DB data while getting Request List", ex);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return requestList;
    }

    @Override
    public boolean setRequest(Request request) {
        con = null;
        pstmt = null;
        try {
            con = DataBaseManager.getInstance().getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(INSERT_NEW_REQUEST);
            pstmt.setInt(1, request.getUserID());
            pstmt.setString(2, request.getStatus());
            pstmt.setString(3, request.getBedSize());
            pstmt.setInt(4, request.getAdultCapacity());
            pstmt.setInt(5, request.getChildCapacity());
            pstmt.setTimestamp(6, Timestamp.valueOf(request.getArrivalDay()));
            pstmt.setTimestamp(7, Timestamp.valueOf(request.getDepartureDay()));
            pstmt.executeUpdate();
            con.commit();
            return true;
        } catch (SQLException ex) {
            log.log(Level.ERROR, "Failed add new Request to  DB", ex);
            rollback(con);
        } finally {
            close(pstmt);
            close(con);
        }
        return false;
    }

    @Override
    public Request getRequest(int id) {
        con = null;
        pstmt = null;
        rs = null;
        request = new Request();
        try {
            con = DataBaseManager.getInstance().getConnection();
            con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            sqlInsertion = String.format(GET_REQUEST_BY_ID, id);
            pstmt = con.prepareStatement(sqlInsertion);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                request.setUserRequestID(rs.getInt("user_request_id"));
                request.setUserID(rs.getInt("user_id"));
                request.setStatus(rs.getString("status"));
                request.setBedSize(rs.getString("bed_size"));
                request.setAdultCapacity(rs.getInt("adult_capacity"));
                request.setChildCapacity(rs.getInt("child_capacity"));
                request.setArrivalDay(rs.getTimestamp("arrival_day").toLocalDateTime());
                request.setDepartureDay(rs.getTimestamp("departure_day").toLocalDateTime());
            }
        } catch (SQLException ex) {
            log.log(Level.ERROR, "Failed get Request by ID from DB", ex);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return request;
    }

    @Override
    public boolean deleteRequest(int id) {
        con = null;
        pstmt = null;
        try {
            con = DataBaseManager.getInstance().getConnection();
            con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            sqlInsertion = String.format(DELETE_REQUEST_BY_ID, id);
            pstmt = con.prepareStatement(sqlInsertion);
            pstmt.executeUpdate();
            con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            return true;
        } catch (SQLException ex) {
            log.log(Level.ERROR, "Failed delete Request by ID from DB", ex);
        } finally {
            close(pstmt);
            close(con);
        }
        return false;
    }

    @Override
    public boolean updateRequest(Request request) {
        con = null;
        pstmt = null;
        try {
            con = DataBaseManager.getInstance().getConnection();
            con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            sqlInsertion = String.format(UPDATE_REQUEST_BY_ID, request.getUserID(), request.getStatus(), request.getBedSize(),
                    request.getAdultCapacity(), request.getChildCapacity(), request.getArrivalDay(),
                    request.getDepartureDay(), request.getUserRequestID());
            pstmt = con.prepareStatement(sqlInsertion);
            pstmt.executeUpdate();
            con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            return true;
        } catch (SQLException ex) {
            log.log(Level.ERROR, "Failed delete Request by ID from DB", ex);
        } finally {
            close(pstmt);
            close(con);
        }
        return false;
    }

    private static void close(AutoCloseable ac) {
        if (ac != null) {
            try {
                ac.close();
            } catch (Exception e) {
                log.log(Level.ERROR, "DB close failed in RoomRepositorySQLImpl", e);
            }
        }
    }

    private static void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (SQLException e) {
                log.log(Level.ERROR, "DB rollback failed RoomRepositorySQLImpl", e);
            }
        }
    }
}
