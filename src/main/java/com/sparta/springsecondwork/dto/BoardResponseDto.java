package com.sparta.springsecondwork.dto;

import com.sparta.springsecondwork.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class BoardResponseDto {
    private Long id;
    private String subject;
    private String username;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.subject=board.getSubject();
        this.username=board.getUsername();
        this.contents=board.getContents();
        this.createdAt=board.getCreatedAt();
        this.modifiedAt=board.getModifiedAt();
    }
}