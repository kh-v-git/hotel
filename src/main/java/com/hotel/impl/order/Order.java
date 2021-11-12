package com.hotel.impl.order;

import java.time.LocalDateTime;
import java.util.Objects;

public class Order {
    private int roomOrderID;
    private int userID;
    private int roomID;
    private int userRequestID;
    private String orderStatus;
    private LocalDateTime orderDate;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;

    public int getRoomOrderID() {
        return roomOrderID;
    }

    public void setRoomOrderID(int roomOrderID) {
        this.roomOrderID = roomOrderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getUserRequestID() {
        return userRequestID;
    }

    public void setUserRequestID(int userRequestID) {
        this.userRequestID = userRequestID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return getUserID() == order.getUserID() && getRoomID() == order.getRoomID() && getUserRequestID() == order.getUserRequestID() && getOrderStatus().equals(order.getOrderStatus()) && getOrderDate().equals(order.getOrderDate()) && getArrivalDate().equals(order.getArrivalDate()) && getDepartureDate().equals(order.getDepartureDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoomID(), getUserRequestID(), getOrderStatus(), getOrderDate(), getArrivalDate(), getDepartureDate());
    }

    @Override
    public String toString() {
        return "Order{" +
                "userID=" + userID +
                ", roomID=" + roomID +
                ", userRequestID=" + userRequestID +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderDate=" + orderDate +
                ", arrivalDate=" + arrivalDate +
                ", departureDate=" + departureDate +
                '}';
    }
}
