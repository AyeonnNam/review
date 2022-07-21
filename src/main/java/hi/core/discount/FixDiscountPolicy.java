package hi.core.discount;

import hi.core.member.Grade;
import hi.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy{

    private  int discountFixAmount= 1000;

    @Override
    public int discount(Member member, int price) {

        if(member.getGrade() == Grade.VIP){
            return discountFixAmount;
        }else{
            return 0;
        }

    }
}
