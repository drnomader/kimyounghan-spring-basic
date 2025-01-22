package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDefinitionTest {

	//factory bean 통해 등록
	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

//	제공되는 정보값이 AnnotationConfigApplicationContext 와 약간 다름
//	GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

	@Test
	@DisplayName("빈 설정 메타정보 확인")
	void findApplicationBean() {
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

			if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
				System.out.println("beanDefinitionName = " + beanDefinitionName + " beanDefinition = " + beanDefinition);
			}
		}
	}
}
