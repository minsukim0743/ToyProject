package com.toyproject.springsecurity.photoBoard.model.service;

import com.toyproject.springsecurity.photoBoard.model.dao.PhotoBoardMapper;
import com.toyproject.springsecurity.photoBoard.model.dto.PhotoBoardDTO;
import com.toyproject.springsecurity.photoBoard.model.dto.PhotoBoardFileDTO;
import org.springframework.stereotype.Service;

@Service
public class PhotoBoardServiceImpl implements PhotoBoardService {

    private PhotoBoardMapper photoBoardMapper;

    public PhotoBoardServiceImpl(PhotoBoardMapper photoBoardMapper){

        this.photoBoardMapper = photoBoardMapper;
    }

    @Override
    public void photoBoardInsert(PhotoBoardDTO photoBoard) {

        photoBoardMapper.photoBoardInsert(photoBoard);
    }

    @Override
    public void photoBoardFileInsert(PhotoBoardFileDTO photoBoardFile) {

        photoBoardMapper.photoBoardFileInsert(photoBoardFile);
    }
}
