package com.toyproject.springsecurity.photoBoard.model.service;

import com.toyproject.springsecurity.photoBoard.model.dto.PhotoBoardDTO;
import com.toyproject.springsecurity.photoBoard.model.dto.PhotoBoardFileDTO;

public interface PhotoBoardService {
    void photoBoardInsert(PhotoBoardDTO photoBoard);

    void photoBoardFileInsert(PhotoBoardFileDTO photoBoardFileDTO);
}
