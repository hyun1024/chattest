package com.sparta.springsecondwork.controller;

import com.sparta.springsecondwork.dto.BoardRequestDto;
import com.sparta.springsecondwork.dto.BoardResponseDto;
import com.sparta.springsecondwork.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/board/all")
    public List<BoardResponseDto> getAllBoards() {
        return boardService.getAllBoard();
    }
    @GetMapping("/board/{username}")
    public List<BoardResponseDto> getUsersBoards(@PathVariable String username) {
        if ("all".equals(username)) {
            return boardService.getAllBoard();
        }
        return boardService.getUsersBoards(username);
    }

    @PutMapping("/board/{postId}")
    public String updateBoard(@PathVariable Long postId, @RequestBody BoardRequestDto requestDto) {
        return boardService.updateBoard(postId, requestDto);
    }

    @DeleteMapping("/board/{postId}")
    public String deleteBoard(@PathVariable Long postId, @RequestBody BoardRequestDto requestDto) {
        return boardService.deleteBoard(postId, requestDto);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> NotFoundExceptionHandler(NullPointerException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> IllegalExceptionHandler(IllegalArgumentException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}

