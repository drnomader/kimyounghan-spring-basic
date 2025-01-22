package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//client code
@Component //for component scan
//@RequiredArgsConstructor //final 붙은 필드들 가진 생성자 자동 생성
public class OrderServiceImpl implements OrderService {

	private final MemberRepository memberRepository;
	private final DiscountPolicy discountPolicy;

	/**
	 * for component scan
	 * 생성자 주입
	 */
	@Autowired
	public OrderServiceImpl(MemberRepository memberRepository, /*@Qualifier("mainDiscountPolicy")*/ @MainDiscountPolicy DiscountPolicy discountPolicy) {
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}

	/**
	 * DIP, OCP 위반
	 *
	 * private final MemberRepository memberRepository = new MemoryMemberRepository();
	 * private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
	 * private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
	 */

	/**
	 * DIP
	 * 생성자 주입
	 * 필드에 final 키워드 적용 가능
	 *
	 * 	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
	 * 	    this.memberRepository = memberRepository;
	 * 		this.discountPolicy = discountPolicy;
	 * 	}
	 */

	/**
	 * setter 주입 방식
	 * final 제거
	 * 자바 프로퍼티 규약에 맞춰 메서드명 지정
	 *
	 * 	private MemberRepository memberRepository;
	 * 	private DiscountPolicy discountPolicy;
	 *
	 * 	@Autowired(required = false) //선택적 주입
	 *  public void setMemberRepository(MemberRepository memberRepository) {
	 * 		System.out.println("memberRepository = " + memberRepository);
	 * 		this.memberRepository = memberRepository
	 *  }
	 *
	 *  @Autowired
	 *  public void setDiscountPolicy(DiscountPolicy discountPolicy) {
	 * 		System.out.println("discountPolicy = " + discountPolicy);
	 * 		this.discountPolicy = discountPolicy;
	 *  }
	 */

	/**
	 * 필드 주입
	 * 안티 패턴 - 외부 변경 불가
	 *
	 * @Autowired private final MemberRepository memberRepository;
	 * @Autowired private final DiscountPolicy discountPolicy;
	 */

	/**
	 * 일반 메서드 주입
	 * final 제거
	 *
	 * private MemberRepository memberRepository;
	 * private DiscountPolicy discountPolicy;
	 *
	 * @Autowired
	 * public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
	 *     this.memberRepository = memberRepository;
	 * 	    this.discountPolicy = discountPolicy;
	 * }
	 */

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);
		//할인 정책 분리 : 단일 책임 원칙 좋은 사례
		int discountPrice = discountPolicy.discount(member, itemPrice);

		return new Order(memberId, itemName, itemPrice, discountPrice);
	}

	//for test
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
}
