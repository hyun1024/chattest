package com.sparta.springsecondwork.service;

import com.sparta.springsecondwork.dto.BoardRequestDto;
import com.sparta.springsecondwork.dto.BoardResponseDto;
import com.sparta.springsecondwork.entity.Board;
import com.sparta.springsecondwork.jwt.JwtUtil;
import com.sparta.springsecondwork.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final JwtUtil jwtUtil;

    public BoardService(BoardRepository boardRepository, JwtUtil jwtUtil){
        this.boardRepository=boardRepository;
        this.jwtUtil=jwtUtil;
    }
    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        // RequestDto -> Entity
        Board board = new Board(requestDto);
        boardRepository.save(board);


        // Entity -> ResponseDto
        BoardResponseDto boardResponseDto = new BoardResponseDto(board);

        return boardResponseDto;
    }
//    public List<BoardResponseDto> getBoards() {
//        return boardRepository.findAllByOrderByCreatedAtDesc().stream().map(BoardResponseDto::new).toList();
//
//    }
    public List<BoardResponseDto> getBoards(String username) {
        return boardRepository.findAllByUsername(username).stream().map(BoardResponseDto::new).toList();
    }

    public List<BoardResponseDto> getAllBoard() {
        return boardRepository.findAll().stream().map(BoardResponseDto::new).toList();
    }
    @Transactional
    public Long updateBoard(Long id, BoardRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Board board= findBoard(id);
        // board 내용 수정
        if(checkPassword(board, requestDto)) {
            board.update(requestDto);
            return id;
        } else{
            throw new IllegalArgumentException("비밀 번호가 틀립니다.");
        }

    }
    public Long deleteBoard(Long id, BoardRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Board board = findBoard(id);
        if(board != null && checkPassword(board, requestDto)) {
            boardRepository.delete(board);
            return id;
        } else {
            throw new IllegalArgumentException("없는 게시글입니다.");
        }
    }

    private Board findBoard(Long id){
        return boardRepository.findById(id).orElseThrow(()-> new NullPointerException("선택한 게시글은 존재하지 않습니다"));
    }

    private boolean checkPassword(Board board, BoardRequestDto requestDto) {
       String inputPassword = requestDto.getPassword();
       String dataPassword = board.getPassword();
       if(inputPassword.equals(dataPassword)){
           return true;
       } else {throw new IllegalArgumentException("비밀 번호가 틀립니다.");}
    }
}
