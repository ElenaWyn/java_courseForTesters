package myFirstPakage;

public class HelloWorld{

	public static void main(String[] args) {

		Point a = new Point(4, 1);
		Point b = new Point(5, -3);


		System.out.println("Вычисление длины отрезка функцией static. Длина отрезка: " + len(a, b));
		System.out.println("Вычисление длины отрезка методом класса Point. Длина отрезка: " + a.len(b));

	}

	public static int len(Point b, Point a)
	{

		int d = Math.abs(a.x - b.x);
		int c = Math.abs(a.y - b.y);

		return (int) Math.sqrt(d*d + c*c);

	}

}
