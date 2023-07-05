package com.toyproject.springsecurity.photoBoard.model.service;

import com.toyproject.springsecurity.common.comment.dto.CommentDTO;
import com.toyproject.springsecurity.common.util.SelectCriteria;
import com.toyproject.springsecurity.photoBoard.model.dao.PhotoBoardMapper;
import com.toyproject.springsecurity.photoBoard.model.dto.PhotoBoardDTO;
import com.toyproject.springsecurity.photoBoard.model.dto.PhotoBoardFileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PhotoBoardServiceImpl implements PhotoBoardService {

    private PhotoBoardMapper photoBoardMapper;

    @Autowired
    public PhotoBoardServiceImpl(PhotoBoardMapper photoBoardMapper){

        this.photoBoardMapper = photoBoardMapper;
    }

    @Override
    public int photoBoardCount(Map<String, String> searchMap) {

        return photoBoardMapper.photoBoardCount(searchMap);
    }

    @Override
    public void photoBoardInsert(PhotoBoardDTO photoBoard) {

        photoBoardMapper.photoBoardInsert(photoBoard);
    }

    @Override
    public void photoBoardFileInsert(PhotoBoardFileDTO photoBoardFile) {

        photoBoardMapper.photoBoardFileInsert(photoBoardFile);
    }

    @Override
    public void photoBoardIncreaseCount(int no) {

        photoBoardMapper.photoBoardIncreaseCount(no);
    }

    @Override
    public PhotoBoardDTO photoBoardDetail(int no) {

        return photoBoardMapper.photoBoardDetail(no);
    }

    @Override
    public List<PhotoBoardDTO> photoBoardList(SelectCriteria selectCriteria) {
        return photoBoardMapper.photoBoardList(selectCriteria);
    }

    @Override
    public int photoBoardCommentCount(int no) {

        return 0;
    }

    @Override
    public List<CommentDTO> photoBoardCommentList(SelectCriteria selectCriteria) {
        return null;
    }
}
