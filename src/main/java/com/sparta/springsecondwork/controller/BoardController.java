package com.sparta.springsecondwork.controller;

import com.sparta.springsecondwork.dto.BoardRequestDto;
import com.sparta.springsecondwork.dto.BoardResponseDto;
import com.sparta.springsecondwork.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }
    @PostMapping("/board")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto) {
        return boardService.createBoard(requestDto);
    }

    @GetMapping("/board")
    public List<BoardResponseDto> getBoards() {
        return boardService.getBoards();
    }

    @GetMapping("/board/{id}")
    public BoardResponseDto getABoard(@PathVariable Long id){
        return boardService.getABoard(id);
    }
    @PutMapping("/board/{id}")
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.updateBoard(id, requestDto);
    }

    @DeleteMapping("/board/{id}")
    public Long deleteBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.deleteBoard(id, requestDto);
    }
}

