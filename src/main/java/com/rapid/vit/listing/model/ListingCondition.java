package com.rapid.vit.listing.model;

import java.util.Arrays;
import java.util.function.Supplier;

public enum ListingCondition {
    NEW("New"),
    LIKENEW("Like New"),
    EXCELLENT("Excellent"),
    GOOD("Good"),
    FAIR("Fair"),
    SALVAGE("Salvage");

    private final String listingConditionStringValue;

    ListingCondition(String listingConditionStringValue) {
        this.listingConditionStringValue=listingConditionStringValue;
    }

    public String getListingConditionStringValue(){
        return listingConditionStringValue;
    }

    public static ListingCondition returnEnumStringValue(String listingConditionStringValue) throws Throwable {
        return Arrays.stream(ListingCondition.values())
                .filter(s -> s.listingConditionStringValue.equals(listingConditionStringValue)).findFirst()
                .orElseThrow(new Supplier<Throwable>() {
                    @Override
                    public Throwable get() {
                        return null;
                    }
                });
    }

}
