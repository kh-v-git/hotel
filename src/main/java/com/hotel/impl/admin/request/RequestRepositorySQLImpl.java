package com.hotel.impl.admin.request;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RequestRepositorySQLImpl implements RequestRepository{
    private static final Logger log = LogManager.getLogger(RequestRepositorySQLImpl.class);

    @Override
    public List<Request> getRequestList(String searchText) {
        return null;
    }

    @Override
    public boolean setRequest(Request request) {
        return false;
    }

    @Override
    public Request getRequest(int id) {
        return null;
    }

    @Override
    public boolean deleteRequest(int id) {
        return false;
    }

    @Override
    public boolean updateRequest(Request request) {
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
