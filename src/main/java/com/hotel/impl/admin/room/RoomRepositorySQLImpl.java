package com.hotel.impl.admin.room;

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

public class RoomRepositorySQLImpl implements RoomRepository {
    private static final Logger log = LogManager.getLogger(RoomRepositorySQLImpl.class);

    private static final String SELECT_ALL_FROM_ROOM = "SELECT * FROM room";
    private static final String INSERT_NEW_ROOM = "INSERT INTO room VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ROOM_BY_ID = "SELECT * FROM room WHERE roomID='%d'";
    private static final String DELETE_ROOM_BY_ID = "DELETE FROM room WHERE roomID='%d'";
    private static final String UPDATE_ROOM_BY_ID = "UPDATE room SET number='%d', adultCapacity='%d', childCapacity='%d', price='%.2f', level='%s', badSize='%s' WHERE roomID='%d'";

    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private Room room;
    String sqlInsertion = null;

    @Override
    public List<Room> getRoomList(String searchText, List<String> searchParametersList) {
        con = null;
        pstmt = null;
        rs = null;
        List<Room> roomList = new ArrayList<>();
        try {
            con = DataBaseManager.getInstance().getConnection();
            String queryPart = QueryGenerator.makeQuery(searchText, searchParametersList);
            sqlInsertion = SELECT_ALL_FROM_ROOM + queryPart;
            pstmt = con.prepareStatement(sqlInsertion);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                room = new Room();
                room.setRoomID(rs.getInt("roomID"));
                room.setNumber(rs.getInt("number"));
                room.setAdultCapacity(rs.getInt("adultCapacity"));
                room.setChildCapacity(rs.getInt("childCapacity"));
                room.setPrice(rs.getBigDecimal("price").doubleValue());
                room.setLevel(rs.getString("badSize"));
                roomList.add(room);
            }
        } catch (
                SQLException ex) {
            log.log(Level.ERROR, "Failed to read DB data while getting Room List", ex);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return roomList;
    }

    @Override
    public boolean setRoom(Room room) {
        con = null;
        pstmt = null;
        try {
            con = DataBaseManager.getInstance().getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(INSERT_NEW_ROOM);
            pstmt.setInt(1, room.getNumber());
            pstmt.setInt(2, room.getAdultCapacity());
            pstmt.setInt(3, room.getChildCapacity());
            pstmt.setDouble(4, room.getPrice());
            pstmt.setString(5, room.getLevel());
            pstmt.setString(6, room.getBadSize());
            pstmt.executeUpdate();
            con.commit();
            return true;
        } catch (SQLException ex) {
            log.log(Level.ERROR, "Failed add new Room to  DB", ex);
            rollback(con);
        } finally {
            close(pstmt);
            close(con);
        }
        return false;
    }

    @Override
    public Room getRoom(int id) {
        con = null;
        pstmt = null;
        rs = null;
        room = new Room();
        try {
            con = DataBaseManager.getInstance().getConnection();
            con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            sqlInsertion = String.format(GET_ROOM_BY_ID, id);
            pstmt = con.prepareStatement(sqlInsertion);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                room.setRoomID(rs.getInt("roomID"));
                room.setNumber(rs.getInt("number"));
                room.setAdultCapacity(rs.getInt("adultCapacity"));
                room.setChildCapacity(rs.getInt("childCapacity"));
                room.setPrice(rs.getBigDecimal("price").doubleValue());
                room.setLevel(rs.getString("badSize"));
            }
        } catch (SQLException ex) {
            log.log(Level.ERROR, "Failed get Room by ID from DB", ex);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return room;
    }

    @Override
    public boolean deleteRoom(int id) {
        con = null;
        pstmt = null;
        try {
            con = DataBaseManager.getInstance().getConnection();
            con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            sqlInsertion = String.format(DELETE_ROOM_BY_ID, id);
            pstmt = con.prepareStatement(sqlInsertion);
            pstmt.executeUpdate();
            con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            return true;
        } catch (SQLException ex) {
            log.log(Level.ERROR, "Failed delete Room by ID from DB", ex);
        } finally {
            close(pstmt);
            close(con);
        }
        return false;
    }

    @Override
    public boolean updateRoom(Room room) {
        con = null;
        pstmt = null;
        try {
            con = DataBaseManager.getInstance().getConnection();
            con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            sqlInsertion = String.format(UPDATE_ROOM_BY_ID, room.getNumber(), room.getAdultCapacity(), room.getChildCapacity(), room.getPrice(), room.getLevel(), room.getBadSize(), room.getRoomID());
            pstmt = con.prepareStatement(sqlInsertion);
            pstmt.executeUpdate();
            con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            return true;
        } catch (SQLException ex) {
            log.log(Level.ERROR, "Failed delete Room by ID from DB", ex);
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
