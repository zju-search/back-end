package com.search.model;

import java.io.Serializable;
import lombok.Data;

/**
 * realtime
 * @author 
 */
@Data
public class Realtime implements Serializable {
    private String tsCode;

    private String time;

    private String value;

    private static final long serialVersionUID = 1L;
}