package com.toyproject.springsecurity.photoBoard.model.service;

import com.toyproject.springsecurity.common.comment.dto.CommentDTO;
import com.toyproject.springsecurity.common.util.SelectCriteria;
import com.toyproject.springsecurity.photoBoard.model.dto.PhotoBoardDTO;
import com.toyproject.springsecurity.photoBoard.model.dto.PhotoBoardFileDTO;

import java.util.List;
import java.util.Map;

public interface PhotoBoardService {

    int photoBoardCount(Map<String, String> searchMap);

    void photoBoardInsert(PhotoBoardDTO photoBoard);

    void photoBoardFileInsert(PhotoBoardFileDTO photoBoardFileDTO);

    PhotoBoardDTO photoBoardDetail(int no);

    List<PhotoBoardDTO> photoBoardList(SelectCriteria selectCriteria);

    int photoBoardCommentCount(int no);

    List<CommentDTO> photoBoardCommentList(SelectCriteria selectCriteria);

    void photoBoardIncreaseCount(int no);
}
