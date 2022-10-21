package com.toyproject.springsecurity.login.model.service;

import com.toyproject.springsecurity.login.model.dao.MemberMapper;
import com.toyproject.springsecurity.login.model.dto.MemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService{

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final MemberMapper memberMapper;

    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    @Transactional
    public void registMember(MemberDTO member) {

        log.info("[MemberService] Insert Member : " + member);
        int result = memberMapper.insertMember(member);

        if(result > 0){
            int result2 = memberMapper.insertMemberRole(member.getMemberId());
        }
    }
}
