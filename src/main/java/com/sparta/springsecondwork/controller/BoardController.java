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

    @GetMapping("/board/{username}")
    public List<BoardResponseDto> getBoards(@PathVariable String username) {
        System.out.println(username);
        return boardService.getBoards(username);
    }

//  @GetMapping("/board")
//    public String getTemplates() {
//        List<BoardResponseDto> board = boardService.getBoards();
//        model.addAttribute("board", board);
//        System.out.println(model);
//        return "board";
//    }
//    @GetMapping("/board/{id}")
//    public BoardResponseDto getABoard(@PathVariable Long id){
//        return boardService.getABoard(id);
//    }
//    @GetMapping("/board/{id}")
//    public BoardResponseDto getABoard(@PathVariable Long id){
//        return boardService.getABoard(id);
//    }
    @PutMapping("/board/{id}")
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.updateBoard(id, requestDto);
    }

    @DeleteMapping("/board/{id}")
    public Long deleteBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.deleteBoard(id, requestDto);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> NotFoundExceptionHandler(NullPointerException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> IllegalExceptionHandler(IllegalArgumentException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}

