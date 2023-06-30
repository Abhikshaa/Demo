package com.pagination.payload;

import lombok.Data;

@Data
public class PostDTO {
    private long id;
    private String name;
    private String email;
    private long mobile;
    private String passwword;
}
