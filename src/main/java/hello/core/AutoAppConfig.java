package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//		basePackages = "hello.core",
//		basePackageClasses = AutoAppConfig.class,
		excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
//@Configuration 애너테이션 붙은 케이스들 제외시켜줌
public class AutoAppConfig {

	/**
	 * Autowired 사용 가능하긴 함
	 * 테스트나 스프링 설정만을 위한 곳이면
	 * 그러나 역시 권장되진 않음
	 *
	 * @Autowired MemberRepository memberRepository;
	 * @Autowired DiscountPolicy discountPolicy;
	 *
	 * @Bean
	 * OrderService orderService() {
	 *     return new OrderServiceImpl(memberRepository, discountPolicy);
	 * }
	 */

	//spring boot 에서는 자동 빈과 수동 빈 이름이 중복되면 오류 발생
/*
	@Bean(name = "memoryMemberRepository")
	MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}
*/
}
