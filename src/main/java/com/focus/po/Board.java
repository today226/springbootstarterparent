package com.focus.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class Board implements Serializable {

    private String id;
    private String title;
    private String content;
}
