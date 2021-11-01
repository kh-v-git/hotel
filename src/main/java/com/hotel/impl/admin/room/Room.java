package com.hotel.impl.admin.room;

import java.util.Objects;

public class Room {
    private int roomID;
    private int number;
    private int adultCapacity;
    private int childrenCapacity;
    private double price;
    private String bedSize;
    private String about;

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

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

    public int getChildrenCapacity() {
        return childrenCapacity;
    }

    public void setChildrenCapacity(int childrenCapacity) {
        this.childrenCapacity = childrenCapacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBedSize() {
        return bedSize;
    }

    public void setBedSize(String bedSize) {
        this.bedSize = bedSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return getRoomID() == room.getRoomID() && getNumber() == room.getNumber() && getAdultCapacity() == room.getAdultCapacity() && getChildrenCapacity() == room.getChildrenCapacity() && Double.compare(room.getPrice(), getPrice()) == 0 && getBedSize().equals(room.getBedSize()) && getAbout().equals(room.getAbout());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getAdultCapacity(), getChildrenCapacity(), getPrice(), getBedSize(), getAbout());
    }

    @Override
    public String toString() {
        return "Room{" +
                "number=" + number +
                ", adultCapacity=" + adultCapacity +
                ", childCapacity=" + childrenCapacity +
                ", price=" + price +
                ", bedSize='" + bedSize + '\'' +
                ", about='" + about + '\'' +
                '}';
    }
}
