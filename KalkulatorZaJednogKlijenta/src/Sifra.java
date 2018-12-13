
public class Sifra {

	public static int proveraSifre(String sifra) {

		if (sifra.length() < 8) {
			return 1;
		}

		int k = 0;
		int r = 0;

		for (int i = 0; i < sifra.length(); i++) {
			char c = sifra.charAt(i);
			if (!(c >= 65 && c <= 90)) {
				k++;
			}
		}

		if (k == sifra.length())
			return 2;

		for (int i = 0; i < sifra.length(); i++) {
			char c = sifra.charAt(i);
			if (!(c >= 48 && c <= 57)) {
				r++;
			}
		}

		if (r == sifra.length())
			return 3;

		return 0;
	}

/*	public static void main(String[] args) {

		String sifra = "asdS3";

		if (Sifra.proveraSifre(sifra) == 0) {
			System.out.println("U redu je");
		} else if (Sifra.proveraSifre(sifra) == 1) {
			System.out.println("Mora imati bar 8 karaktera");
		} else if (Sifra.proveraSifre(sifra) == 2) {
			System.out.println("Mora imati bar jedno veliko slovo");
		} else if (Sifra.proveraSifre(sifra) == 3) {
			System.out.println("Mora imati bar jedan broj");
		}
	}
	*/

}
