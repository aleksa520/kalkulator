import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.BrokenBarrierException;

public class ClientHandler extends Thread {

	BufferedReader odKlijenta = null;
	PrintStream kaKlijentu = null;
	Socket soketZaKomunikaciju = null;

	public ClientHandler(Socket s) {
		soketZaKomunikaciju = s;
	}

	@Override
	public void run() {

		try {
			odKlijenta = new BufferedReader(new InputStreamReader(soketZaKomunikaciju.getInputStream()));
			kaKlijentu = new PrintStream(soketZaKomunikaciju.getOutputStream());
			double rezultat;
			String vrati = "";

			String izaberi = "1. Logovanje 2. Registracija";

			kaKlijentu.println(izaberi);

			String odgovor = odKlijenta.readLine();

			int izbor = Integer.parseInt(odgovor);

			switch (izbor) {
			case 1:

				String username1, sifra1;

				kaKlijentu.println("Unesite username");
				username1 = odKlijenta.readLine();

				kaKlijentu.println("Unesite sifru");
				sifra1 = odKlijenta.readLine();

				String imeFajla1 = "";
				imeFajla1 += username1 + ".txt";

				FileInputStream fstream = new FileInputStream(imeFajla1);
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

				String sifraIzFajla;

				br.readLine();
				sifraIzFajla = br.readLine();

				br.close();

				if (!(sifra1.equals(sifraIzFajla))) {
					System.out.println("Pogresna sifra");
					return;
				}

				File log1 = new File(imeFajla1);
				FileWriter fr1 = new FileWriter(log1, true);

				fr1.write(System.getProperty("line.separator"));

				fr1.close();
				String broj6;
				do {

					FileWriter fr6 = new FileWriter(log1, true);
					broj6 = odKlijenta.readLine();
					String broj7 = odKlijenta.readLine();
					String broj8 = odKlijenta.readLine();

					double a1 = Double.parseDouble(broj6);
					double b1 = Double.parseDouble(broj7);
					char operacija2 = broj8.charAt(0);

					rezultat = Kalkulator.kalkulator(a1, b1, operacija2);

					fr6.write(System.getProperty("line.separator"));
					fr6.write(broj6 + " " + broj8 + " " + broj7 + " = " + rezultat);

					vrati = "Vas rezultat je: " + rezultat;
					kaKlijentu.println(vrati);
					fr6.close();
				} while (!broj6.equals("***kraj"));

				break;

			case 2: {

				String username, sifra;

				kaKlijentu.println("Unesite username");
				username = odKlijenta.readLine();

				do {
					kaKlijentu.println("Unesite sifru");
					sifra = odKlijenta.readLine();
					if (Sifra.proveraSifre(sifra) == 0) {
						kaKlijentu.println("Sifra je u redu");
					} else if (Sifra.proveraSifre(sifra) == 1) {
						kaKlijentu.println("Sifra mora imati bar 8 karaktera");
					} else if (Sifra.proveraSifre(sifra) == 2) {
						kaKlijentu.println("Sifra mora imati bar jedno veliko slovo");
					} else if (Sifra.proveraSifre(sifra) == 3) {
						kaKlijentu.println("Sifra mora imati bar jedan broj");
					}
				} while (Sifra.proveraSifre(sifra) != 0);

				String imeFajla = "";
				imeFajla += username + ".txt";

				File log = new File(imeFajla);
				FileWriter fr = new FileWriter(log, true);

				fr.write(username);
				fr.write(System.getProperty("line.separator"));
				fr.write(sifra);

				fr.close();
				String broj1;

				while (true) {
					FileWriter fr2 = new FileWriter(log, true);
					broj1 = odKlijenta.readLine();
					if (broj1.equals("***kraj")) {
						
						kaKlijentu.println("Dovidjenja" + username);
						
						break;

					}
					String broj2 = odKlijenta.readLine();
					String broj3 = odKlijenta.readLine();

					double a = Double.parseDouble(broj1);
					double b = Double.parseDouble(broj2);
					char operacija = broj3.charAt(0);

					rezultat = Kalkulator.kalkulator(a, b, operacija);

					fr2.write(System.getProperty("line.separator"));
					fr2.write(broj1 + " " + broj3 + " " + broj2 + " = " + rezultat);

					vrati = "Vas rezultat je: " + rezultat;
					kaKlijentu.println(vrati);
					fr2.close();
				}

			}
				break;
			default:
				break;
			}

			soketZaKomunikaciju.close();

		} catch (

		IOException e) {
			System.out.println("Klijent se odjavio");
			System.out.println("Cekam na konekciju");
		}

	}

}
