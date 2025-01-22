package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//SRP, DIP, OCP 충족
//구현 객체 생성
//생성한 인스턴스 참조를 생성자 통해 주입
@Configuration //spring
public class AppConfig {

	//싱글톤이 보장되는지 의문이 생길 수 있음
	//@Bean memberService -> new MemoryMemberRepository()
	//@Bean orderService -> new MemoryMemberRepository()

	@Bean //spring 컨테이너에 등록
	public MemberService memberService() {
		System.out.println("call AppConfig.memberService");
		return new MemberServiceImpl(memberRepository());
	}

	@Bean
	public MemberRepository memberRepository() {
		System.out.println("call AppConfig.memberRepository");
		return new MemoryMemberRepository();//구현
	}

	@Bean
	public OrderService orderService() {
		System.out.println("call AppConfig.orderService");
		return new OrderServiceImpl(memberRepository(), discountPolicy());
	}

	@Bean
	public DiscountPolicy discountPolicy() {
		//return new FixDiscountPolicy();//구현
		return new RateDiscountPolicy();
	}
}
