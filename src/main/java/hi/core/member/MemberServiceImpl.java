package hi.core.member;

import hi.core.MemberRepository;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository m ;

    public MemberServiceImpl(MemberRepository m) {
        this.m = m;
    }


    @Override
    public void join(Member member) {

         m.save(member);



    }

    @Override
    public Member findMember(Long memberId) {
        return m.findById(memberId);
    }
}
