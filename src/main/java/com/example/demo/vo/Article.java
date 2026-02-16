package com.example.demo.vo;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article {
    private int id;
    private String title;
    private String body;
    private String regDate;
    private String updateDate;
    private String boardId;
    private int memberId;
    private int hitCount;

    private String likePoint;
    private String dislikePoint;

    private String extra__sumReactionPoint;

    private String author;
    private String board;
    private boolean userCanModify;
    private boolean userCanDelete;
}
