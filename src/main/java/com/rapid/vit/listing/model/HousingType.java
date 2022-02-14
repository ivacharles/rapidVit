package com.rapid.vit.listing.model;

import java.util.Arrays;
import java.util.function.Supplier;

public enum HousingType {
    APARTMENT("Apartment"),
    HOUSE("House"),
    BUSINESS("Business"),
    LAND("Land"),
    ROOM("Room");

    private final String housingTypeStringValue;

    HousingType(String housingTypeStringValue) {
        this.housingTypeStringValue=housingTypeStringValue;
    }

    public String getHousingTypeStringValue(){
        return housingTypeStringValue;
    }

    public static HousingType returnHousingType(String housingTypeStringValue) throws Throwable {
        return Arrays.stream(HousingType.values())
                .filter(s -> s.getHousingTypeStringValue().equals(housingTypeStringValue)).findFirst()
                .orElse(null);
    }
}
