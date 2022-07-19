package hi.core.member;

import hi.core.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository ;

    @Autowired //@Autowired는 디폴트라 굳이 명시 안해도 돼.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public void join(Member member) {

         memberRepository.save(member);



    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //test 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }


}

