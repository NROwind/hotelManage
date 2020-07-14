package org.csu.hotel.service;

import org.csu.hotel.domain.Member;
import org.csu.hotel.persistence.MemeberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService {

    public Member getMemberById(int id);

    public List<Member> getMemberList();

    public int insertMember(Member member);

    public int deleteMember(Member member);

    public int updateMember(Member member);


}
