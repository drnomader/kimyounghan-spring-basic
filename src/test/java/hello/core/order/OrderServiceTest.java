package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

	MemberService memberService;
	OrderService orderService;

	//MemberService memberService = new MemberServiceImpl(); //DIP 위반
	//OrderService orderService = new OrderServiceImpl(); //DIP 위반

	@BeforeEach
	public void beforeEach() {
		AppConfig appConfig = new AppConfig();
		memberService = appConfig.memberService();
		orderService = appConfig.orderService();
	}

	@Test
	void createOrder() {
		//given
		Long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP);
		memberService.join(member);

		//when
		Order order = orderService.createOrder(memberId, "itemA", 10000);

		//then
		Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
	}

	@Test
	void fieldInjectionTest() {
		//필드 주입 시, 내가 원하는 값(repository)으로 변경할 수 없음
		//setter 별도로 추가해줘야 함
		//OrderServiceImpl orderService1 = new OrderServiceImpl();
		//orderService.setMemberRepository(new MemoryMemberRepository());
		//orderService.setDiscountPolicy(new FixDiscountPolicy());
		//orderService.createOrder(1L, "itemA", 10000); //NPE 발생
	}
}
