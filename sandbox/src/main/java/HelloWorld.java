public class HelloWorld{

	public static void main(String[] args) {
		int a = 55;
		int b = 88;
		a += (b - 3 ^ 4 << 2 | 8 - b >>> 8 * 2) - (a & a - 1);
		System.out.println(a);
		int c = 2;
		c&=3;
		System.out.println("Przesuniecie bitowe: " + c);

	}

}
