package com.toyproject.springsecurity.photoBoard.model.dao;

import com.toyproject.springsecurity.common.util.SelectCriteria;
import com.toyproject.springsecurity.photoBoard.model.dto.PhotoBoardDTO;
import com.toyproject.springsecurity.photoBoard.model.dto.PhotoBoardFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PhotoBoardMapper {
    void photoBoardFileInsert(PhotoBoardFileDTO photoBoardFile);

    void photoBoardInsert(PhotoBoardDTO photoBoard);

    int photoBoardCount(Map<String, String> searchMap);

    List<PhotoBoardDTO> photoBoardList(SelectCriteria selectCriteria);

    void photoBoardIncreaseCount(int no);

    PhotoBoardDTO photoBoardDetail(int no);
}
