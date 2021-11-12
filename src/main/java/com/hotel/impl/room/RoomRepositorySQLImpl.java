package com.hotel.impl.room;

import com.hotel.model.RoomImage;
import com.hotel.utils.DataBaseManager;
import com.hotel.utils.QueryGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomRepositorySQLImpl implements RoomRepository {
    private static final Logger log = LogManager.getLogger(RoomRepositorySQLImpl.class);

    private static final String SELECT_ALL_FROM_ROOM = "SELECT * FROM room";
    private static final String SELECT_ALL_FROM_ROOM_LIMIT = " LIMIT %d, %d";
    private static final String INSERT_NEW_ROOM = "INSERT INTO room VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ROOM_BY_ID = "SELECT * FROM room WHERE room_id='%d'";
    private static final String GET_ROOM_AMOUNT = "SELECT COUNT(room_id) FROM room";
    private static final String DELETE_ROOM_BY_ID = "DELETE FROM room WHERE room_id='%d'";
    private static final String UPDATE_ROOM_BY_ID = "UPDATE room SET number='%d', adult_capacity='%d', child_capacity='%d', price='%.2f', bed_size='%s', about='%s' WHERE room_id='%d'";
    private static final String INSERT_NEW_ROOM_IMAGE = "INSERT INTO room_image VALUES (default, ?, ?, ?, ?)";
    private static final String GET_IMAGE_LIST_BY_ROOM_ID = "SELECT * FROM room_image WHERE room_id='%d'";
    private static final String DELETE_IMAGE_ROOM_BY_ID = "DELETE FROM room_image WHERE room_image_id='%d'";
    private static final String GET_ROOM_IMAGE_BY_CAPTION = "SELECT * FROM room_image WHERE room_id='%d' AND caption='%s'";
    private static final String UPDATE_IMAGE_BY_ID = "UPDATE room_image SET image=? Where room_image_id=?";

    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;
    String sqlInsertion = null;

    @Override
    public List<Room> getRoomList(String searchText, List<String> searchParametersList) {
        con = null;
        pstmt = null;
        rs = null;
        Room room;
        List<Room> roomList = new ArrayList<>();
        try {
            con = DataBaseManager.getInstance().getConnection();
            String queryPart = QueryGenerator.makeQuery(searchText, searchParametersList);
            sqlInsertion = SELECT_ALL_FROM_ROOM + queryPart;
            pstmt = con.prepareStatement(sqlInsertion);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                room = new Room();
                room.setRoomID(rs.getInt("room_id"));
                room.setNumber(rs.getInt("number"));
                room.setAdultCapacity(rs.getInt("adult_capacity"));
                room.setChildrenCapacity(rs.getInt("child_capacity"));
                room.setPrice(rs.getBigDecimal("price").doubleValue());
                room.setBedSize(rs.getString("bed_size"));
                room.setAbout(rs.getString("about"));
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
    public List<Room> getRoomList(String searchText, List<String> searchParametersList, int start, int perPage) {
        con = null;
        pstmt = null;
        rs = null;
        Room room;
        List<Room> roomList = new ArrayList<>();
        try {
            con = DataBaseManager.getInstance().getConnection();
            String queryPart = QueryGenerator.makeQuery(searchText, searchParametersList);
            sqlInsertion = SELECT_ALL_FROM_ROOM  + queryPart + String.format(SELECT_ALL_FROM_ROOM_LIMIT, start, perPage);
            pstmt = con.prepareStatement(sqlInsertion);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                room = new Room();
                room.setRoomID(rs.getInt("room_id"));
                room.setNumber(rs.getInt("number"));
                room.setAdultCapacity(rs.getInt("adult_capacity"));
                room.setChildrenCapacity(rs.getInt("child_capacity"));
                room.setPrice(rs.getBigDecimal("price").doubleValue());
                room.setBedSize(rs.getString("bed_size"));
                room.setAbout(rs.getString("about"));
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
    public int getRoomAmount() {
        con = null;
        pstmt = null;
        rs = null;
        int amount = 0;
        try {
            con = DataBaseManager.getInstance().getConnection();
            sqlInsertion = GET_ROOM_AMOUNT;
            pstmt = con.prepareStatement(sqlInsertion);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                amount = rs.getInt(1);
            } else {
                amount = 0;
            }
        } catch (
                SQLException ex) {
            log.log(Level.ERROR, "Failed to read DB data while getting Room List", ex);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return amount;
    }

    @Override
    public int getRoomAmount(String searchText, List<String> searchParametersList) {
        con = null;
        pstmt = null;
        rs = null;
        int amount = 0;
        try {
            con = DataBaseManager.getInstance().getConnection();
            String queryPart = QueryGenerator.makeQuery(searchText, searchParametersList);
            sqlInsertion = GET_ROOM_AMOUNT + queryPart;
            pstmt = con.prepareStatement(sqlInsertion);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                amount = rs.getInt(1);
            } else {
                amount = 0;
            }
        } catch (
                SQLException ex) {
            log.log(Level.ERROR, "Failed to read DB data while getting Room List", ex);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return amount;
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
            pstmt.setInt(3, room.getChildrenCapacity());
            pstmt.setDouble(4, room.getPrice());
            pstmt.setString(5, room.getBedSize());
            pstmt.setString(6, room.getAbout());
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
    public Optional<Room> getRoom(int id) {
        con = null;
        pstmt = null;
        rs = null;
        Room room = new Room();
        boolean roomExists = false;
        try {
            con = DataBaseManager.getInstance().getConnection();
            con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            sqlInsertion = String.format(GET_ROOM_BY_ID, id);
            pstmt = con.prepareStatement(sqlInsertion);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                room.setRoomID(rs.getInt("room_id"));
                room.setNumber(rs.getInt("number"));
                room.setAdultCapacity(rs.getInt("adult_capacity"));
                room.setChildrenCapacity(rs.getInt("child_capacity"));
                room.setPrice(rs.getBigDecimal("price").doubleValue());
                room.setBedSize(rs.getString("bed_size"));
                room.setAbout(rs.getString("about"));
                roomExists = true;
            }
        } catch (SQLException ex) {
            log.log(Level.ERROR, "Failed get Room by ID from DB", ex);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        if (roomExists) {
            return Optional.of(room);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteRoom(int id) {
        con = null;
        pstmt = null;
        try {
            con = DataBaseManager.getInstance().getConnection();
            sqlInsertion = String.format(DELETE_ROOM_BY_ID, id);
            pstmt = con.prepareStatement(sqlInsertion);
            pstmt.executeUpdate();
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
            sqlInsertion = String.format(UPDATE_ROOM_BY_ID, room.getNumber(), room.getAdultCapacity(), room.getChildrenCapacity(), room.getPrice(), room.getBedSize(), room.getAbout(), room.getRoomID());
            pstmt = con.prepareStatement(sqlInsertion);
            pstmt.executeUpdate();
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
    public boolean uploadNewRoomImage(int roomId, String caption, String mimeType, InputStream roomImage) {
        con = null;
        pstmt = null;
        try {
            con = DataBaseManager.getInstance().getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(INSERT_NEW_ROOM_IMAGE);
            pstmt.setInt(1, roomId);
            pstmt.setString(2, caption);
            pstmt.setString(3, mimeType);
            pstmt.setBlob(4, roomImage);
            pstmt.executeUpdate();
            con.commit();
            return true;
        } catch (SQLException ex) {
            log.log(Level.ERROR, "Failed add new Room Image to  DB", ex);
            rollback(con);
        } finally {
            close(pstmt);
            close(con);
        }
        return false;
    }

    @Override
    public boolean uploadRoomImage(int imageId, int roomId, String caption, String mimeType, InputStream roomImage) {
        con = null;
        pstmt = null;
        try {
            con = DataBaseManager.getInstance().getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(UPDATE_IMAGE_BY_ID);
            pstmt.setBlob(1, roomImage);
            pstmt.setInt(2, imageId);
            pstmt.executeUpdate();
            con.commit();
            return true;
        } catch (SQLException ex) {
            log.log(Level.ERROR, "Failed add Room Image to  DB", ex);
            rollback(con);
        } finally {
            close(pstmt);
            close(con);
        }
        return false;
    }

    @Override
    public List<RoomImage> getRoomImageList(int roomId) {
        con = null;
        pstmt = null;
        rs = null;
        List<RoomImage> imageRoomList = new ArrayList<>();
        try {
            con = DataBaseManager.getInstance().getConnection();
            sqlInsertion = String.format(GET_IMAGE_LIST_BY_ROOM_ID, roomId);
            pstmt = con.prepareStatement(sqlInsertion);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                RoomImage image = new RoomImage();
                image.setRoomImageID(rs.getInt("room_image_id"));
                image.setRoomID(rs.getInt("room_id"));
                image.setCaption(rs.getString("caption"));
                image.setMime(rs.getString("mime"));
                image.setImage(rs.getBlob("image"));
                imageRoomList.add(image);
            }
        } catch (SQLException ex) {
            log.log(Level.ERROR, "Failed get Image by ID from DB", ex);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return imageRoomList;
    }

    @Override
    public boolean deleteRoomImage(int roomImageID) {
        con = null;
        pstmt = null;
        try {
            con = DataBaseManager.getInstance().getConnection();
            sqlInsertion = String.format(DELETE_IMAGE_ROOM_BY_ID, roomImageID);
            pstmt = con.prepareStatement(sqlInsertion);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            log.log(Level.ERROR, "Failed delete Image Room by ID from DB", ex);
        } finally {
            close(pstmt);
            close(con);
        }
        return false;
    }

    @Override
    public Optional<RoomImage> getRoomImageByCaption(int roomId, String roomImageCaption) {
        con = null;
        pstmt = null;
        rs = null;
        RoomImage image = new RoomImage();
        boolean roomImageExists = false;
        try {
            con = DataBaseManager.getInstance().getConnection();
            sqlInsertion = String.format(GET_ROOM_IMAGE_BY_CAPTION, roomId,  roomImageCaption);
            pstmt = con.prepareStatement(sqlInsertion);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                image.setRoomImageID(rs.getInt("room_image_id"));
                image.setRoomID(rs.getInt("room_id"));
                image.setCaption(rs.getString("caption"));
                image.setMime(rs.getString("mime"));
                image.setImage(rs.getBlob("image"));
                roomImageExists = true;
            }
        } catch (SQLException ex) {
            log.log(Level.ERROR, "Failed get Image by Caption from DB", ex);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        if (roomImageExists) {
            return Optional.of(image);
        } else {
            return Optional.empty();
        }
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
