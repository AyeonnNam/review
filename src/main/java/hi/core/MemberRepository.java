package hi.core;

import hi.core.member.Member;

public interface MemberRepository {

    //์ ์ฅ , ์กฐํ


    void save(Member member);

    Member findById(Long memberId);


}
