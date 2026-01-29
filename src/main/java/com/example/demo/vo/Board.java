package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {
    private int id;
    private String regDate;
    private String updateDate;
    private String code;
    private String name;
    private boolean delStatus;
    private LocalDateTime delDate;
}
