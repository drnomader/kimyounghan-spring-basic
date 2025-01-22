package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component //for component scan
//@Qualifier("mainDiscountPolicy") //상세 케이스 구현, @Primary 보다 우선순위 높음, 컴파일 타임에 문자열 오류 못 잡음
//@Primary //기본값으로 지정해주는 애너테이션
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {
	private int discountPercent = 10;

	@Override
	public int discount(Member member, int price) {
		if (member.getGrade() == Grade.VIP) {
			return price * discountPercent / 100;
		} else {
			return 0;
		}
	}
}
