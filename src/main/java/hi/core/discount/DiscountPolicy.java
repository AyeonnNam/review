package hi.core.discount;

import hi.core.member.Member;

public interface DiscountPolicy {

    int discount(Member member, int price);

    /*return = 대상의 할인금액 */
}
