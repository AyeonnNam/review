package hi.core.discount;

import hi.core.annotation.MainDiscountPolicy;
import hi.core.member.Grade;
import hi.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@MainDiscountPolicy
//@Qualifier("mainDiscountPolicy")
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy{

    private static int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) {
                return price * discountPercent / 100;
        }else {
            return 0;
        }

    }
}
