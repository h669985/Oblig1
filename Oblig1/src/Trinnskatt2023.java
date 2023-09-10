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
		boolean svar = false;
		double skatt;
		String skattMelding;

		//Grenseverdier for trinnskatt
		int g1 = 198350; int g2 = 279150; int g3 = 642950; int g4 = 926088; int g5 = 1500000;

		// Sjekker om brukeren er bosatt i Troms og Finnmark
		if (brutto >= g3) {
			int dialogResult = showConfirmDialog(null, "Er du bosatt i Troms og Finnmark?");
			svar = (dialogResult == YES_OPTION);
		}

		// Forhånsberegninger: tx er trinnskatt i prosent og sx er hvor mye brukeren skal betale per trinn
		double t1 = 1.7 / 100;  double s1 = t1*(brutto - g1);
		double t2 = 4.0 / 100;  double s2 = t2*(brutto - g2);
		double t4 = 16.5 / 100; double s4 = t4*(brutto - g4);
		double t5 = 17.5 / 100; double s5 = t5*(brutto - g5);

		// Bruker (var = bool? x : y) siden trinnskatten er forskjellig dersom brukeren bor i troms/finnmark
		double t3 = svar? 11.5 / 100 : 13.5 / 100; double s3 = t3*(brutto - g3);

		// Finner ut hvor mye brukeren skal betale i toppskatt
		if (brutto < g1) {
			skatt = 0;
		} else if (brutto < g2) {
			skatt = s1;
		} else if (brutto < g3) {
			skatt = s1 + s2;
		} else if (brutto < g4) {
			skatt = s1 + s2 + s3;
		} else if (brutto < g5) {
			skatt = s1 + s2 + s3 + s4;
		} else {
			skatt = s1 + s2 + s3 + s4 + s5;
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