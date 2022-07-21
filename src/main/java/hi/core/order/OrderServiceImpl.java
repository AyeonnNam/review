package hi.core.order;

import hi.core.MemberRepository;
import hi.core.annotation.MainDiscountPolicy;
import hi.core.discount.DiscountPolicy;
import hi.core.member.Member;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class OrderServiceImpl implements OrderService{

    //private final은 왜 붙는 걸까 = 값 할당 필수
//    @Autowired
    private final MemberRepository memberRepository;
    // 인터페이스에만 의존
//@Autowired
    private  final DiscountPolicy discountPolicy;
    //DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();



    //setter 수정자 의존관계
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        //System.out.println("OrderServiceImpl.setDiscountPolicy");
//        this.discountPolicy = discountPolicy;
//    }
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        //System.out.println("OrderServiceImpl.setMemberRepository");
//        this.memberRepository = memberRepository;
//    }
//
        @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
          //  System.out.println("OrderServiceImpl.OrderServiceImpl");
        // System.out.println(" emberRepository = " + memberRepository);
        //System.out.println(" discountPolicy = " + discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    //orderService에 구현체(구체객체)를 누군가 생성, 주입해줘야 합니다.

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member findMember = memberRepository.findById(memberId);

        //grade만 넘길지 Member를 통으로 넘길지는 상황에 따라서 다르다, 둘 다 가능
        int TotaldiscountPrice = discountPolicy.discount(findMember, itemPrice);


        return new Order(memberId,itemName,itemPrice,TotaldiscountPrice);
    }

    //Test 용도 메서드
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
