package com.codecool.projectq.projectqbackend.model;

import java.util.Arrays;
import java.util.List;

public enum CaseType {
    VEHICLE,
    PASSPORT,
    MEDICAL,
    REAL_ESTATE;

    private List<CaseType> caseTypeList = Arrays.asList(CaseType.values());

    public List<CaseType> getCaseTypeList() {
        return caseTypeList;
    }
}
