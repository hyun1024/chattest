package com.sparta.springsecondwork.service;

import com.sparta.springsecondwork.dto.BoardRequestDto;
import com.sparta.springsecondwork.dto.BoardResponseDto;
import com.sparta.springsecondwork.entity.Board;
import com.sparta.springsecondwork.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository){
        this.boardRepository=boardRepository;
    }
    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        // RequestDto -> Entity
        Board board = new Board(requestDto);

        Board saveBoard = boardRepository.save(board);


        // Entity -> ResponseDto
        BoardResponseDto boardResponseDto = new BoardResponseDto(board);

        return boardResponseDto;
    }
    public List<BoardResponseDto> getBoards() {
        return boardRepository.findAllByOrderByCreatedAtDesc().stream().map(BoardResponseDto::new).toList();

    }

    public BoardResponseDto getABoard(Long id) {
        Board board = findBoard(id);
        return new BoardResponseDto(board);
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
        return boardRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("선택한 게시글은 존재하지 않습니다"));
    }

    private boolean checkPassword(Board board, BoardRequestDto requestDto) {
       String inputPassword = requestDto.getPassword();
       String dataPassword = board.getPassword();
       if(inputPassword.equals(dataPassword)){
           return true;
       } else {throw new IllegalArgumentException("비밀 번호가 틀립니다.");}
    }
}
