package com.hotel.impl.admin.room;

import java.util.Objects;

public class Room {
    private int roomID;
    private int number;
    private int adultCapacity;
    private int childCapacity;
    private double price;
    private String level;
    private String badSize;

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomId) {
        this.roomID = roomId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getAdultCapacity() {
        return adultCapacity;
    }

    public void setAdultCapacity(int adultCapacity) {
        this.adultCapacity = adultCapacity;
    }

    public int getChildCapacity() {
        return childCapacity;
    }

    public void setChildCapacity(int childCapacity) {
        this.childCapacity = childCapacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getBadSize() {
        return badSize;
    }

    public void setBadSize(String badSize) {
        this.badSize = badSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return getNumber() == room.getNumber() && getAdultCapacity() == room.getAdultCapacity() && getChildCapacity() == room.getChildCapacity() && Double.compare(room.getPrice(), getPrice()) == 0 && getLevel().equals(room.getLevel()) && getBadSize().equals(room.getBadSize());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getAdultCapacity(), getChildCapacity(), getPrice(), getLevel(), getBadSize());
    }

    @Override
    public String toString() {
        return "Room{" +
                "number=" + number +
                ", adultCapacity=" + adultCapacity +
                ", childCapacity=" + childCapacity +
                ", price=" + price +
                ", level='" + level + '\'' +
                ", badSize='" + badSize + '\'' +
                '}';
    }
}
