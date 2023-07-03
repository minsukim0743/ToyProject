package com.toyproject.springsecurity.photoBoard.model.dao;

import com.toyproject.springsecurity.photoBoard.model.dto.PhotoBoardDTO;
import com.toyproject.springsecurity.photoBoard.model.dto.PhotoBoardFileDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PhotoBoardMapper {
    void photoBoardFileInsert(PhotoBoardFileDTO photoBoardFile);

    void photoBoardInsert(PhotoBoardDTO photoBoard);
}
