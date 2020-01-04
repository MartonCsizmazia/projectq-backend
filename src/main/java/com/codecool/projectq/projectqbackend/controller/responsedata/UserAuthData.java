package com.codecool.projectq.projectqbackend.controller.responsedata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class UserAuthData {
    String username;
    List<String> roles;
    String token;
}
