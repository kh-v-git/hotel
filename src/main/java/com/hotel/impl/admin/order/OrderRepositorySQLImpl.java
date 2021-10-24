package com.hotel.impl.admin.order;

import com.hotel.utils.DataBaseManager;
import com.hotel.utils.QueryGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositorySQLImpl implements OrderRepository {
    private static final Logger log = LogManager.getLogger(OrderRepositorySQLImpl.class);

    private static final String SELECT_ALL_FROM_ORDER = "SELECT * FROM room_order";
    private static final String INSERT_NEW_ORDER = "INSERT INTO room_order VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ORDER_BY_ID = "SELECT * FROM room_order WHERE orderOrderID='%d'";
    private static final String DELETE_ORDER_BY_ID = "DELETE FROM room_order WHERE orderOrderID='%d'";
    private static final String UPDATE_ORDER_BY_ID = "UPDATE room_order SET userID='%d', roomID='%d', " +
            "userRequestID='%d', orderStatus='%s', orderDate='%s', arrivalDate='%s', departureDate='%s' WHERE roomOrderID='%d'";

    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private Order order;
    String sqlInsertion = null;

    @Override
    public List<Order> getOrderList(String searchText, List<String> searchParametersList) {
        con = null;
        pstmt = null;
        rs = null;
        List<Order> orderList = new ArrayList<>();
        try {
            con = DataBaseManager.getInstance().getConnection();

            String queryPart = QueryGenerator.makeQuery(searchText, searchParametersList);
            sqlInsertion = SELECT_ALL_FROM_ORDER + queryPart;
            pstmt = con.prepareStatement(sqlInsertion);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                order = new Order();
                order.setRoomOrderID(rs.getInt("roomOrderID"));
                order.setUserID(rs.getInt("userID"));
                order.setRoomID(rs.getInt("roomID"));
                order.setUserRequestID(rs.getInt("userRequestID"));
                order.setOrderStatus(rs.getString("orderStatus"));
                order.setOrderDate(rs.getTimestamp("orderDate").toLocalDateTime());
                order.setArrivalDate(rs.getTimestamp("arrivalDay").toLocalDateTime());
                order.setDepartureDate(rs.getTimestamp("departureDay").toLocalDateTime());
                orderList.add(order);
            }
        } catch (
                SQLException ex) {
            log.log(Level.ERROR, "Failed to read DB data while getting Order List", ex);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return orderList;
    }

    @Override
    public boolean setOrder(Order order) {
        con = null;
        pstmt = null;
        try {
            con = DataBaseManager.getInstance().getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(INSERT_NEW_ORDER);
            pstmt.setInt(1, order.getUserID());
            pstmt.setInt(2, order.getRoomID());
            pstmt.setInt(3, order.getUserRequestID());
            pstmt.setString(4, order.getOrderStatus());
            pstmt.setTimestamp(5, Timestamp.valueOf(order.getOrderDate()));
            pstmt.setTimestamp(6, Timestamp.valueOf(order.getArrivalDate()));
            pstmt.setTimestamp(7, Timestamp.valueOf(order.getDepartureDate()));
            pstmt.executeUpdate();
            con.commit();
            return true;
        } catch (SQLException ex) {
            log.log(Level.ERROR, "Failed add new Order to  DB", ex);
            rollback(con);
        } finally {
            close(pstmt);
            close(con);
        }
        return false;
    }

    @Override
    public Order getOrder(int id) {
        con = null;
        pstmt = null;
        rs = null;
        order = new Order();
        try {
            con = DataBaseManager.getInstance().getConnection();
            con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            sqlInsertion = String.format(GET_ORDER_BY_ID, id);
            pstmt = con.prepareStatement(sqlInsertion);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                order.setRoomOrderID(rs.getInt("roomOrderID"));
                order.setUserID(rs.getInt("userID"));
                order.setRoomID(rs.getInt("roomID"));
                order.setUserRequestID(rs.getInt("userRequestID"));
                order.setOrderStatus(rs.getString("orderStatus"));
                order.setOrderDate(rs.getTimestamp("orderDate").toLocalDateTime());
                order.setArrivalDate(rs.getTimestamp("arrivalDay").toLocalDateTime());
                order.setDepartureDate(rs.getTimestamp("departureDay").toLocalDateTime());
            }
        } catch (SQLException ex) {
            log.log(Level.ERROR, "Failed get Order by ID from DB", ex);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return order;
    }

    @Override
    public boolean deleteOrder(int id) {
        con = null;
        pstmt = null;
        try {
            con = DataBaseManager.getInstance().getConnection();
            con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            sqlInsertion = String.format(DELETE_ORDER_BY_ID, id);
            pstmt = con.prepareStatement(sqlInsertion);
            pstmt.executeUpdate();
            con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            return true;
        } catch (SQLException ex) {
            log.log(Level.ERROR, "Failed delete Order by ID from DB", ex);
        } finally {
            close(pstmt);
            close(con);
        }
        return false;
    }

    @Override
    public boolean updateOrder(Order order) {
        con = null;
        pstmt = null;
        try {
            con = DataBaseManager.getInstance().getConnection();
            con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            sqlInsertion = String.format(UPDATE_ORDER_BY_ID, order.getUserID(), order.getRoomID(), order.getUserRequestID(),
                    order.getOrderStatus(), order.getOrderDate(), order.getArrivalDate(), order.getDepartureDate(), order.getRoomOrderID());
            pstmt = con.prepareStatement(sqlInsertion);
            pstmt.executeUpdate();
            con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            return true;
        } catch (SQLException ex) {
            log.log(Level.ERROR, "Failed delete Order by ID from DB", ex);
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
