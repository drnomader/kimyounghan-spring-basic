package hello.core.singleton;

public class SingletonService {

	//static 영역에 객체를 딱 1개만 생성
	private static final SingletonService instance = new SingletonService();

	//public 으로 열어서 객체 인스턴스 필요하면 이 static 메서드 통해서만 조회하도록 허용
	public static SingletonService getInstance() {
		return instance;
	}

	//생성자를 private 으로 선언 -> 외부에서 new 키워드로 객체 인스턴스 생성 불가
	private SingletonService() {
	}

	public void logic() {
		System.out.println("싱글톤 객체 로직 호출");
	}
}
