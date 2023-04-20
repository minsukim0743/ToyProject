package com.toyproject.springsecurity.freeBoard.model.service;

import com.toyproject.springsecurity.common.util.SelectCriteria;
import com.toyproject.springsecurity.freeBoard.model.dto.FreeBoardDTO;

import java.util.List;
import java.util.Map;

public interface FreeBoardService {
    List<FreeBoardDTO> freeBoardList(SelectCriteria selectCriteria);

    int freeBoardCount(Map<String, String> searchMap);

    void freeBoardInsert(FreeBoardDTO freeBoard);

    FreeBoardDTO freeBoardDetail(int no);

    void freeBoardDetailCount(int no);
}
