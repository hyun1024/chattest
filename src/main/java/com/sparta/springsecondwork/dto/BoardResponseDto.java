package com.sparta.springsecondwork.dto;

import com.sparta.springsecondwork.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class BoardResponseDto {
    private final Long id;
    private final String subject;
    private final String username;
    private final String contents;
    private final String password;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.subject=board.getSubject();
        this.username=board.getUsername();
        this.contents=board.getContents();
        this.password=board.getPassword();
        this.createdAt=board.getCreatedAt();
        this.modifiedAt=board.getModifiedAt();
    }
}