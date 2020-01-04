package com.codecool.projectq.projectqbackend.controller.responsedata;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WelcomePageData {
    List<String> caseTypeList;
    List<String> offices;
}
