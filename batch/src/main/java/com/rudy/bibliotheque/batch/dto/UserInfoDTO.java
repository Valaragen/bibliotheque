package com.rudy.bibliotheque.batch.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoDTO {

    private String id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private String address;
}
