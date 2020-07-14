package org.csu.hotel.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.csu.hotel.domain.Member;
import org.csu.hotel.domain.RoomType;
import org.csu.hotel.persistence.MemeberMapper;
import org.csu.hotel.persistence.RoomTypeMapper;
import org.csu.hotel.service.MemberService;
import org.csu.hotel.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberServiceImpl extends ServiceImpl<MemeberMapper, Member> implements MemberService {
    @Autowired
    private MemeberMapper memeberMapper;

    public Member getMemberById(int id) {
        return memeberMapper.selectById(id);
    }

    public List<Member> getMemberList() {
        return memeberMapper.selectList(null);
    }

    public int insertMember(Member member) {
        return memeberMapper.insert(member);
    }

    public int deleteMember(Member member) {
        return memeberMapper.deleteById(member.getId());
    }

    public int updateMember(Member member) {
        return memeberMapper.updateById(member);
    }
}
