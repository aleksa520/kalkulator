
public class Kalkulator {

	public static double kalkulator(double a, double b, char operacija) {

		double resenje = 0;

		switch (operacija) {
		case '+':
			resenje = a + b;
			break;

		case '-':
			resenje = a - b;
			break;

		case '*':
			resenje = a * b;
			break;

		case '/':
			resenje = a / b;
			break;
		default:
			break;
		}

		return resenje;
	}

}
