package com.toyproject.springsecurity.login.model.service;

import com.toyproject.springsecurity.login.model.dao.MemberMapper;
import com.toyproject.springsecurity.login.model.dto.MemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService{

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final MemberMapper memberMapper;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    /* 회원가입 */
    @Override
    @Transactional
    public void registMember(MemberDTO member) {

        log.info("[MemberService] Insert Member : " + member);
        int result = memberMapper.insertMember(member);
    }

    /* 회원가입시 아이디 중복체크 */
    @Override
    public boolean selectMemberById(String memberId) {

        String result = memberMapper.selectMemberById(memberId);

        return result != null? true : false;
    }
}
