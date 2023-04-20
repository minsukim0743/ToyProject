package com.toyproject.springsecurity.freeBoard.model.service;

import com.toyproject.springsecurity.common.util.SelectCriteria;
import com.toyproject.springsecurity.freeBoard.model.dao.FreeBoardMapper;
import com.toyproject.springsecurity.freeBoard.model.dto.FreeBoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class FreeBoardServiceImpl implements FreeBoardService {

    private final FreeBoardMapper freeBoardMapper;

    @Autowired
    public FreeBoardServiceImpl(FreeBoardMapper freeBoardMapper) {
        this.freeBoardMapper = freeBoardMapper;
    }

    @Override
    public List<FreeBoardDTO> freeBoardList(SelectCriteria selectCriteria) {
        return freeBoardMapper.freeBoardList(selectCriteria);
    }

    @Override
    public int freeBoardCount(Map<String, String> searchMap) {
        return freeBoardMapper.freeBoardCount(searchMap);
    }

    @Override
    @Transactional
    public void freeBoardInsert(FreeBoardDTO freeBoard) {

        freeBoardMapper.freeBoardInsert(freeBoard);
    }

    @Override
    public FreeBoardDTO freeBoardDetail(int no) {

        return freeBoardMapper.freeBoardDetail(no);
    }

    @Override
    public void freeBoardDetailCount(int no) {

        freeBoardMapper.freeBoardDetailCount(no);
    }
}
