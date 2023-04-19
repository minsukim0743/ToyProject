package com.toyproject.springsecurity.freeBoard.model.service;

import com.toyproject.springsecurity.freeBoard.model.dao.FreeBoardMapper;
import com.toyproject.springsecurity.freeBoard.model.dto.FreeBoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FreeBoardServiceImpl implements FreeBoardService {

    private final FreeBoardMapper freeBoardMapper;

    @Autowired
    public FreeBoardServiceImpl(FreeBoardMapper freeBoardMapper) {
        this.freeBoardMapper = freeBoardMapper;
    }

    @Override
    public List<FreeBoardDTO> freeBoardList() {
        return freeBoardMapper.freeBoardList();
    }

    @Override
    public int freeBoardCount() {
        return freeBoardMapper.freeBoardCount();
    }
}
