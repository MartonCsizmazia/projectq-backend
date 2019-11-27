package com.codecool.projectq.projectqbackend.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public enum CaseType {
    VEHICLE,
    PASSPORT,
    MEDICAL,
    REAL_ESTATE;

    //if the methods are not in comment, program runs to error:
    //Could not create DynamicParameterizedType for type: org.hibernate.type.EnumType

    //METHODS SHOULD BE IN SERVICE LAYER, it solves the problem
    //private List<CaseType> caseTypeList = Arrays.asList(CaseType.values());


}
