package com.toyproject.springsecurity.freeBoard.model.service;

import com.toyproject.springsecurity.freeBoard.model.dto.FreeBoardDTO;

import java.util.List;

public interface FreeBoardService {
    List<FreeBoardDTO> freeBoardList();

    int freeBoardCount();
}
