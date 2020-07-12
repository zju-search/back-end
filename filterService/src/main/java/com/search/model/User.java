package com.search.model;

import java.io.Serializable;
import lombok.Data;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {
    private String email;

    private String password;

    private String username;

    private static final long serialVersionUID = 1L;
}