package com.hotel.utils.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum RoomLevelEnum {
    LUX,
    GOLD,
    STANDARD;

    public static List<String> getRoomLevelList() {
        return Arrays.stream(RoomLevelEnum.values())
                .map(RoomLevelEnum::name)
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }
}
