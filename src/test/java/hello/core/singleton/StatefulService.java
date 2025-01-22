package hello.core.singleton;

public class StatefulService {

//	private int price; //상태 유지 필드 10000 -> 20000

	public int order(String name, int price) {
		System.out.println("name = " + name + " price = " + price);
//		this.price = price; //문제 코드!
		return price; //위 코드처럼 공유되지 않게 해야
	}

//	public int getPrice() {
//		return price;
//	}
}
