package com.Mehdi.RestApis.dto;

import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String email;
    private String displayName;

    public UserDto() {}
    public UserDto(String id, String email, String displayName) {
        this.id = id; this.email = email; this.displayName = displayName;
    }
}
