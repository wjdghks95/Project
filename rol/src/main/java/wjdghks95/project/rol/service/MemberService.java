package wjdghks95.project.rol.service;

import wjdghks95.project.rol.domain.dto.MemberDto;
import wjdghks95.project.rol.domain.entity.Member;

public interface MemberService {

    Long join(MemberDto memberDto);
    void withdrawal(Long id);

    void follow(Member followingMember, Member followerMember);

    boolean isFollow(Member followingMember, Member followerMember);
}
