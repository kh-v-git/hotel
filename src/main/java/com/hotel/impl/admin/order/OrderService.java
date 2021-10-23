package com.hotel.impl.admin.order;

import java.util.List;

public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrderList(String searchText, List<String> searchParametersList) {
        return orderRepository.getOrderList(searchText, searchParametersList);
    }

    public boolean setOrder(Order order) {
        return orderRepository.setOrder(order);
    }

    public boolean deleteOrder(int id) {
        return orderRepository.deleteOrder(id);
    }

    public boolean updateOrder(Order order) {
        return orderRepository.updateOrder(order);
    }

    public Order getOrder(int id) {
        return orderRepository.getOrder(id);
    }
}
