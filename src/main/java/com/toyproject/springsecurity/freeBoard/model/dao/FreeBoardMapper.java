package com.toyproject.springsecurity.freeBoard.model.dao;

import com.toyproject.springsecurity.freeBoard.model.dto.FreeBoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FreeBoardMapper {
    List<FreeBoardDTO> freeBoardList();

    int freeBoardCount();
}
