package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

	@Test
	void prototypeFind() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

		PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
		prototypeBean1.addCount();
		assertThat(prototypeBean1.getCount()).isEqualTo(1);

		PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
		prototypeBean2.addCount();
		assertThat(prototypeBean2.getCount()).isEqualTo(1);
	}

	@Test
	void singletonClientUsePrototype() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

		ClientBean clientBean1 = ac.getBean(ClientBean.class);
		int count1 = clientBean1.logic();
		assertThat(count1).isEqualTo(1);

		ClientBean clientBean2 = ac.getBean(ClientBean.class);
		int count2 = clientBean2.logic();
		assertThat(count2).isEqualTo(1);
	}

	@Scope("singleton")
	static class ClientBean {
		//CASE1
		//private final PrototypeBean prototypeBean; //생성시점에 주입
		//@Autowired
		//public ClientBean(PrototypeBean prototypeBean) {
		//	this.prototypeBean = prototypeBean;
		//}

		//CASE2
		//@Autowired
		//ApplicationContext applicationContext;

		//CASE3
		//@Autowired
		//private ObjectProvider<PrototypeBean> prototypeBeanObjectProvider;

		//CASE4
		@Autowired
		private Provider<PrototypeBean> prototypeBeanProvider;

		public int logic() {
			//CASE2
			//PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class); //logic() 메서드 호출 때마다 새로운 인스턴스 생성

			//CASE3
			//DL
			//PrototypeBean prototypeBean = prototypeBeanObjectProvider.getObject(); //getObject() 메서드 실행 시점에서 항상 새로 생성된 프로토타입 빈을 조회해줌(스프링 컨테이너까지 가지 않고, 이 메서드가 대신 조회해주는 역할)

			//CASE4
			//DL
			PrototypeBean prototypeBean = prototypeBeanProvider.get();
			prototypeBean.addCount();
			int count = prototypeBean.getCount();
			return count;
		}
	}

	@Scope("prototype")
	static class PrototypeBean {
		private int count = 0;

		public void addCount() {
			count++;
		}

		public int getCount() {
			return count;
		}

		@PostConstruct
		public void init() {
			System.out.println("PrototypeBean.init" + this);
		}

		@PreDestroy
		public void destroy() {
			System.out.println("PrototypeBean.destroy");
		}
	}
}
