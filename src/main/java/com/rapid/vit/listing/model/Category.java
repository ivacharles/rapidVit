package com.rapid.vit.listing.model;

import java.util.Arrays;
import java.util.function.Supplier;

public enum Category {
    JOB("Job"),
    HOUSING("Housing"),
    SERVICE("Service"),
    SALE("Sale"),
    COMMUNITY("Community");

    private final String categoryTypeString;

    Category(String categoryTypeString) {
        this.categoryTypeString = categoryTypeString;
    }

    public String getCategoryTypeString(){
        return categoryTypeString;
    }

    public static Category returnCategoryEnum(String categoryTypeString) {
        return Arrays.stream(Category.values())
                .filter(s -> s.getCategoryTypeString().equalsIgnoreCase(categoryTypeString)).findFirst()
                .orElse(null);
    }
}
