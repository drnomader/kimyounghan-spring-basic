package hello.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {AutoAppConfig.class})
class CoreApplicationTests {

	/**
	 * CoreApplicationTests 실행 시, 부트 애플리케이션이 자체적으로 ComponentScan 진행
	 * 자체적인 ComponentScan 에는 @Configuration 이 붙은 AppConfig 도 빈으로 등록됨 + 거기서 생성하는 빈도 모두 등록됨
	 * -> NoUniqueBeanDefinitionException 발생
	 * 통합 테스트 시 ApplicationContext 를 구성하는 Configuration class 를 직접 지정해 충돌 피해보자
	 */
	@Test
	void contextLoads() {
	}

}
