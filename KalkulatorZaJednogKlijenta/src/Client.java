import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable {

	public static void main(String[] args) {

		BufferedReader odServera;
		PrintStream kaServeru;

		BufferedReader konzola = new BufferedReader(new InputStreamReader(System.in));

		Socket soketZaKomunikaciju;

		while (true) {
			try {
				soketZaKomunikaciju = new Socket("localhost", 8889);

				new Thread(new Client()).start();

				odServera = new BufferedReader(new InputStreamReader(soketZaKomunikaciju.getInputStream()));

				kaServeru = new PrintStream(soketZaKomunikaciju.getOutputStream());

				String izbor = odServera.readLine();

				System.out.println(izbor);

				String izz = konzola.readLine();
				kaServeru.println(izz);

				int iz = Integer.parseInt(izz);
				switch (iz) {
				case 1:

					String prviBroj1;

					System.out.println(odServera.readLine());
					kaServeru.println(konzola.readLine());

					System.out.println(odServera.readLine());
					kaServeru.println(konzola.readLine());

					do {
						System.out.println("Unesite prvi broj: ");
						prviBroj1 = konzola.readLine();

						System.out.println("Unesite drugi broj: ");
						String drugiBroj2 = konzola.readLine();

						System.out.println("Unesite operaciju: ");
						String operacija2 = konzola.readLine();

						kaServeru.println(prviBroj1);
						kaServeru.println(drugiBroj2);
						kaServeru.println(operacija2);

						String rezultat2 = odServera.readLine();

						System.out.println(rezultat2);
					} while (!prviBroj1.equals("***quit"));

					break;

				case 2: {

					String prviBroj;

					System.out.println(odServera.readLine());
					kaServeru.println(konzola.readLine());

					String ispravnaSifra = "";

					do {
						System.out.println(odServera.readLine());
						kaServeru.println(konzola.readLine());
						ispravnaSifra = odServera.readLine();
						System.out.println(ispravnaSifra);
					} while (!(ispravnaSifra.equals("Sifra je u redu")));

					while (true) {
						System.out.println("Unesite prvi broj: ");

						prviBroj = konzola.readLine();

						if (prviBroj.equals("***kraj")) {

							String porukaOdServera = odServera.readLine();

							System.out.println(porukaOdServera);

							break;
						}

						System.out.println("Unesite drugi broj: ");
						String drugiBroj = konzola.readLine();

						System.out.println("Unesite operaciju: ");
						String operacija = konzola.readLine();

						kaServeru.println(prviBroj);
						kaServeru.println(drugiBroj);
						kaServeru.println(operacija);

						String rezultat = odServera.readLine();

						System.out.println(rezultat);
					}

				}

					break;
				default:
					break;
				}

				soketZaKomunikaciju.close();
			} catch (UnknownHostException e) {
				System.out.println("Unknown host");
			} catch (IOException e) {
				System.out.println("Server is down");
			}
		}
	}

	@Override
	public void run() {

	}
}