package com.hotel.impl.admin.order;

import java.util.List;

public class OrderRepositorySQLImpl implements OrderRepository {
    @Override
    public List<Order> getOrderList(String searchText) {
        return null;
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
}
