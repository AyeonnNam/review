package hi.core;

import hi.core.member.Member;

public interface MemberRepository {

    //저장 , 조회


    void save(Member member);

    Member findById(Long memberId);


}
