package com.toyproject.springsecurity.login.model.dto;

public class CommentLikeDTO {

    private int commentNo;
    private String memberNickName;
    private String stateYn;

    public CommentLikeDTO(){}

    public CommentLikeDTO(int commentNo, String memberNickName, String stateYn){
        this.commentNo = commentNo;
        this.memberNickName = memberNickName;
        this.stateYn = stateYn;
    }

    public int getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(int commentNo) {
        this.commentNo = commentNo;
    }

    public String getMemberNickName() {
        return memberNickName;
    }

    public void setMemberNickName(String memberNickName) {
        this.memberNickName = memberNickName;
    }

    public String getStateYn() {
        return stateYn;
    }

    public void setStateYn(String stateYn) {
        this.stateYn = stateYn;
    }

    @Override
    public String toString() {
        return "CommentLikeDTO{" +
                "commentNo=" + commentNo +
                ", memberNickName='" + memberNickName + '\'' +
                ", stateYn='" + stateYn + '\'' +
                '}';
    }
}
