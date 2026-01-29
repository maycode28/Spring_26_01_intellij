package com.example.demo.service;


import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.vo.Board;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardRepository boardRepository;
    public BoardService(BoardRepository boardRepository){
        this.boardRepository=boardRepository;
    }

    public List<Board> getBoards() {
        return boardRepository.getBoards();
    }

    public Board getBoardById(String id) { return boardRepository.getBoardById(id);
    }
}
