package com.hotel.utils.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum RoomBadSizeEnum {
    KING,
    QUEEN,
    TWIN,
    DOUBLE,
    COT;

    public static List<String> getRoomBadSizeList() {
        return Arrays.stream(RoomBadSizeEnum.values())
                .map(RoomBadSizeEnum::name)
                .map(java.lang.String::toLowerCase)
                .collect(Collectors.toList());
    }
}
