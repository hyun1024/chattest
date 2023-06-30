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
    public List<BoardResponseDto> getUsersBoards(String username) {
        return boardRepository.findAllByUsername(username).stream().map(BoardResponseDto::new).toList();
    }

    public List<BoardResponseDto> getAllBoard() {
        return boardRepository.findAll().stream().map(BoardResponseDto::new).toList();
    }
    @Transactional
    public String updateBoard(Long postId, BoardRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Board board= findBoard(postId);
        // board 내용 수정
        if(board.getUsername().equals(requestDto.getUsername())) {
            board.update(requestDto);
            return "업데이트가 완료되었습니다.";
        } else {
            throw new IllegalArgumentException("본인의 글이 아닙니다.");
        }

    }
    public String deleteBoard(Long postId, BoardRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Board board = findBoard(postId);
        if(board.getUsername().equals(requestDto.getUsername())){
            boardRepository.delete(board);
            return "성공적으로 삭제되었습니다.";
        } else {
            throw new IllegalArgumentException("본인의 글이 아닙니다.");
        }
    }

    private Board findBoard(Long postId){
        return boardRepository.findById(postId).orElseThrow(()-> new NullPointerException("선택한 게시글은 존재하지 않습니다"));
    }
}
