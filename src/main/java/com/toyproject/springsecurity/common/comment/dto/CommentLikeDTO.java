package com.toyproject.springsecurity.common.comment.dto;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentLikeDTO {

    private int commentNo;
    private String memberNickName;
    private int categoryNo;
    private int writeNo;
    private String stateYn;


}
