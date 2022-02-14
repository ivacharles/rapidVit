package com.rapid.vit.listing.model;

import java.util.Arrays;
import java.util.function.Supplier;

public enum EmploymentType {
    FullTime("Full Time"),
    PartTime("Part Time"),
    InternShip("Intern Ship"),
    Contract("Contract");

    private final String employmentTypeString;

    EmploymentType(String employmentTypeString) {
        this.employmentTypeString= employmentTypeString;
    }

    public String getEmploymentTypeString(){
        return employmentTypeString;
    }

    public static EmploymentType returnEnumStringValue(String employmentTypeEnum) throws Throwable {
        return Arrays.stream(EmploymentType.values())
                .filter(s -> s.employmentTypeString.equals(employmentTypeEnum)).findFirst()
                .orElseThrow(new Supplier<Throwable>() {
                    @Override
                    public Throwable get() {
                        return null;
                    }
                });
    }
}
