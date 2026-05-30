package com.smartparking.smartparking.dto;

import lombok.Data;

@Data
public class SlotDTO {
    private Long id;
    private String slotName;
    private boolean available;
    private String carNumber;
    private Long areaId;
}
