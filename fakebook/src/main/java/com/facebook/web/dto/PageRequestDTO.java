package com.facebook.web.dto;

import lombok.Data;

@Data
public class PageRequestDTO {
    int page;
    int size;

    public PageRequestDTO() {
        this.page = 1;
        this.size = 5;
    }
}
