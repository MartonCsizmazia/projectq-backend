package com.codecool.projectq.projectqbackend.model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum CaseType {
    VEHICLE(5, "Vehicle"),
    PASSPORT(20, "Passport"),
    MEDICAL(10, "Medical"),
    REAL_ESTATE(50, "Real Estate");

    long avgWaitTimeInMinutes;
    String displayName;

    CaseType(long avgWaitTimeInMinutes, String displayName) {
        this.avgWaitTimeInMinutes = avgWaitTimeInMinutes;
        this.displayName = displayName;
    }

    public long getAvgWaitTimeInMinutes() {
        return avgWaitTimeInMinutes;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Optional<CaseType> getByDisplayName(String displayName) {
        return Stream.of(CaseType.values())
                .filter(caseType -> caseType.displayName.contentEquals(displayName))
                .findFirst();
    }

    public static List<String> getAllDisplayNames() {
        return Stream.of(CaseType.values())
                .map(caseType -> caseType.displayName)
                .collect(Collectors.toList());

    }

//if the methods are not in comment, program runs to error:
    //Could not create DynamicParameterizedType for type: org.hibernate.type.EnumType

    //METHODS SHOULD BE IN SERVICE LAYER, it solves the problem
    //private List<CaseType> caseTypeList = Arrays.asList(CaseType.values());


}
