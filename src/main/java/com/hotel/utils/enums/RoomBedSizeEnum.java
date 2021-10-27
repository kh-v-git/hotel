package com.hotel.utils.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum RoomBedSizeEnum {
    KING,
    QUEEN,
    TWIN,
    DOUBLE,
    COT;

    public static List<String> getRoomBedSizeList() {
        return Arrays.stream(RoomBedSizeEnum.values())
                .map(RoomBedSizeEnum::name)
                .map(java.lang.String::toLowerCase)
                .collect(Collectors.toList());
    }
}
