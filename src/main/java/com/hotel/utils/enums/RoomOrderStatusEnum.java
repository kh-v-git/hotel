package com.hotel.utils.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum RoomOrderStatusEnum {
    FREE, RESERVED, BOOKED, EXPIRED, ENDED, INACCESSIBLE;

    public static List<String> getRoomOrderStatusList() {
        return Arrays.stream(RoomOrderStatusEnum.values())
                .map(RoomOrderStatusEnum::name)
                .map(java.lang.String::toLowerCase)
                .collect(Collectors.toList());
    }
}
