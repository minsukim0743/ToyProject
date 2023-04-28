package com.toyproject.springsecurity.freeBoard.model.dao;

import com.toyproject.springsecurity.common.comment.dto.CommentLikeDTO;
import com.toyproject.springsecurity.common.util.SelectCriteria;
import com.toyproject.springsecurity.freeBoard.model.dto.FreeBoardDTO;
import com.toyproject.springsecurity.common.comment.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FreeBoardMapper {
    List<FreeBoardDTO> freeBoardList(SelectCriteria selectCriteria);

    int freeBoardCount(Map<String, String> searchMap);

    void freeBoardInsert(FreeBoardDTO freeBoard);

    FreeBoardDTO freeBoardDetail(int no);

    void freeBoardDetailCount(int no);

    int freeBoardUpdate(FreeBoardDTO freeBoard);

    List<CommentDTO> freeBoardDetailCommentList(SelectCriteria selectCriteria);

    int freeBoardCommentInsert(Map<String, String> paramMap);

    int freeBoardCommentDelete(int commentNo);

    int freeBoardCommentLike(int commentNo, int no, String loginUser);

    int freeBoardCommentLikeCount(int commentNo, int no);

    List<CommentLikeDTO> freeBoardSelectCommentLike(int no, String loginUser);

    int freeBoardCommentLikeCancle(int commentNo, int no, String loginUser);

    int freeBoardCommentLikeMinusCount(int commentNo, int no);

    int freeBoardCommentsCount(int no);
}
