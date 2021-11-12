package com.hotel.utils.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum UserRequestStatusEnum {
    REQUESTED, ASSIGNED, DECLINED, APPROVED, EXPIRED;

    public static List<String> getUserRequestStatusList() {
        return Arrays.stream(UserRequestStatusEnum.values())
                .map(UserRequestStatusEnum::name)
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }
}
