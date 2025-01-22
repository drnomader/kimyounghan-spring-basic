package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//client code
@Component //for component scan
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	/**
	 * for component scan
	 */
	@Autowired //ac.getBean(MemberRepository.class) 같은 역할
	//AutoAppConfig 에 의존관계 주입 코드 없음, 자동 주입 애너테이션
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	/**
	 * DIP, OCP 위반
	 *
	 * private final MemberRepository memberRepository = new MemoryMemberRepository();
	 */

	/**
	 * 생성자 주입
	 * 추상화에만 의존
	 *
	 * public MemberServiceImpl(MemberRepository memberRepository) {
	 *     this.memberRepository = memberRepository;
	 * }
	 */

	@Override
	public void join(Member member) {
		memberRepository.save(member);
	}

	@Override
	public Member findMember(Long memberId) {
		return memberRepository.findById(memberId);
	}

	//for test
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
}
