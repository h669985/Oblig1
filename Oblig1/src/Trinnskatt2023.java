import static javax.swing.JOptionPane.*;
import java.text.*;

public class Trinnskatt2023 {

	static int inntektInput() {
		do {
			String bruttolonn = showInputDialog("Hvor mye tjente du brutto i 2023?");
			if (bruttolonn == null) {
				System.exit(ABORT);
			}

			try {
				bruttolonn = bruttolonn.replaceAll("kr", "");
				bruttolonn = bruttolonn.replaceAll(",-", "");
				bruttolonn = bruttolonn.replaceAll(" ", "");
				bruttolonn = bruttolonn.replaceAll("'", "");
				int tall = Integer.parseInt(bruttolonn);

				if (0 <= tall) {
					return tall;
				} else {
					showMessageDialog(null, "Inntekten må være 0 eller mer.");
				}
			} catch (NumberFormatException e) {
				showMessageDialog(null, "Det du skrev inn er ikke gyldig.");
			}
		} while (true);
	}

	public static void main(String[] args) {
		// Skaffe bruttoinntekt fra bruker
		int brutto = inntektInput();

		// Forhåndsdeklarering
		double skatt;
		String skattMelding;

		// Finner ut hvilket skattetrinn brukeren er på
		if (brutto <= 198349) {
			skatt = brutto * (0.0 / 100);
		} else if (brutto <= 279149) {
			skatt = brutto * (1.7 / 100);
		} else if (brutto <= 642949) {
			skatt = brutto * (4.0 / 100);
		} else if (brutto <= 926799) {
			// Sjekker om brukeren er bosatt i Troms og Finnmark
			int svar = showConfirmDialog(null, "Er du bosatt i Troms og Finnmark?");
			if (svar == 0) {
				skatt = brutto * (11.5 / 100);
			} else {
				skatt = brutto * (13.5 / 100);
			}
		} else if (brutto <= 1499999) {
			skatt = brutto * (16.5 / 100);
		} else {
			skatt = brutto * (17.5 / 100);
		}

		// Formatterer output tallet til å være lett lesbart for brukeren
		DecimalFormat formatter = new DecimalFormat("#0.00");
		formatter.setGroupingUsed(true);
		formatter.setGroupingSize(3);

		// Lager output meldingen
		skattMelding = "I 2023 er trinnskatten din på: " + formatter.format(skatt) + "kr.";


		// Skriver ut til brukeren
		showMessageDialog(null, skattMelding);
	}
}