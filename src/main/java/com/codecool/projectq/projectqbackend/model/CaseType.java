package com.codecool.projectq.projectqbackend.model;

public enum CaseType {
    VEHICLE(5),
    PASSPORT(20),
    MEDICAL(10),
    REAL_ESTATE(50);

    long avgWaitTimeInMinutes;

    CaseType(long avgWaitTimeInMinutes) {
        this.avgWaitTimeInMinutes = avgWaitTimeInMinutes;
    }

    public long getAvgWaitTimeInMinutes() {
        return avgWaitTimeInMinutes;
    }

//if the methods are not in comment, program runs to error:
    //Could not create DynamicParameterizedType for type: org.hibernate.type.EnumType

    //METHODS SHOULD BE IN SERVICE LAYER, it solves the problem
    //private List<CaseType> caseTypeList = Arrays.asList(CaseType.values());


}
