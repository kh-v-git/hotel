package com.hotel.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum UserRolesEnum {
    USER, MANAGER, ADMIN;

    public static List<String> getUserRoles () {
        return  Arrays.stream(UserRolesEnum.values())
                .map(UserRolesEnum::name)
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }
}
