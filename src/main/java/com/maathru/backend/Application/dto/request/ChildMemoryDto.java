package com.maathru.backend.Application.dto.request;

import lombok.Data;
import java.util.Date;

@Data
public class ChildMemoryDto {

    private int memoryId;

    private String title;
    private String description;
    private Date createDate;
    private long childId;
}
