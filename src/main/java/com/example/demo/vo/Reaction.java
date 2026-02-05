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
public class Reaction {
    private int id;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private int memberId;
    private String relDataTypeCode;
    private int relId;
    private int reactionStatus;

    public Reaction(int memberId, String relDataTypeCode, int relId, int reactionStatus) {
        this.memberId=memberId;
        this.relDataTypeCode=relDataTypeCode;
        this.relId=relId;
        this.reactionStatus=reactionStatus;
    }
}
