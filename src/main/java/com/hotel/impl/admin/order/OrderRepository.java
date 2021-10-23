package com.hotel.impl.admin.order;


import java.util.List;

public interface OrderRepository {
    List<Order> getOrderList(String searchText, List<String> searchParametersList);

    boolean setOrder(Order order);

    Order getOrder(int id);

    boolean deleteOrder(int id);

    boolean updateOrder(Order order);
}
