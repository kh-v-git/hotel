package com.hotel.impl.admin.order;

import com.hotel.impl.admin.room.Room;
import com.hotel.impl.admin.room.RoomRepositorySQLImpl;
import com.hotel.impl.admin.user.User;
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

public class OrderRepositorySQLImpl implements OrderRepository {
    private static final Logger log = LogManager.getLogger(OrderRepositorySQLImpl.class);

    private static final String SELECT_ALL_FROM_ORDER = "SELECT * FROM room_order";
    private static final String INSERT_NEW_ORDER = "INSERT INTO room_order VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ORDER_BY_ID = "SELECT * FROM room_order WHERE orderID='%d'";
    private static final String DELETE_ORDER_BY_ID = "DELETE FROM room_order WHERE orderID='%d'";
    private static final String UPDATE_ORDER_BY_ID = "UPDATE room_order SET number='%d', adultCapacity='%d', childCapacity='%d', price='%.2f', level='%s', badSize='%s' WHERE roomID='%d'";

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
            log.log(Level.ERROR, "Failed to read DB data while getting User List", ex);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return orderList;
    }

    @Override
    public boolean setOrder(Order order) {
        return false;
    }

    @Override
    public Order getOrder(int id) {
        return null;
    }

    @Override
    public boolean deleteOrder(int id) {
        return false;
    }

    @Override
    public boolean updateOrder(Order order) {
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
