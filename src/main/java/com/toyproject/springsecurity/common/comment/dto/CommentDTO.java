package com.toyproject.springsecurity.common.comment.dto;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private int commentNo;
    private String content;
    private String writer;
    private String regDate;
    private int commentLike;
    private int categoryNo;
    private int writeNo;
    private CommentLikeDTO commentLike1;
}
