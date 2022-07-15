package hi.core.member;

import hi.core.MemberRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryMemberRepository implements MemberRepository {

  //  private static Map<Long, Member> store = new HashMap<>();
    //동시성 이슈 해결
    private static Map<Long, Member> store = new ConcurrentHashMap<>();

    @Override
    public void save(Member member) {
            store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
       return store.get(memberId);

    }
}
