import static javax.swing.JOptionPane.*;
import java.util.*;

public class KarakterBeregner {

    // Metode for å be brukeren om å skrive inn en gyldig poengsum
    static int poengInput() {
        do {
            String poengTkst = showInputDialog("Sett inn poengsummen:");
            if (poengTkst == null) {System.exit(ABORT);}

            try {
                int poeng = Integer.parseInt(poengTkst);

                if (0 <= poeng && poeng <= 100) {
                    return poeng;
                } else {
                    showMessageDialog(null, "Poengsummen må være mellom 0 og 100.");
                }
            } catch (NumberFormatException e) {
                showMessageDialog(null, "Det du skrev inn er ikke et gyldig heltall.");
            }
        } while (true);
    }

    // Metode for å beregne karakter basert på poengsum
    static char beregnKarakter(int poeng) {
        if (100 >= poeng && poeng >= 90) {
            return 'A';
        } else if (89 >= poeng && poeng >= 80) {
            return 'B';
        } else if (79 >= poeng && poeng >= 60) {
            return 'C';
        } else if (59 >= poeng && poeng >= 50) {
            return 'D';
        } else if (49 >= poeng && poeng >= 40) {
            return 'E';
        } else {
            return 'F'; // Returnerer 'F' for alle poeng under 40
        }
    }

    public static void main(String[] args) {
        int antallStudenter = 10;
        List<Character> karakterListe = new ArrayList<>();

        // Loop for å beregne karakterer for hver student og legge dem til i listen
        for (int i = 1; i <= antallStudenter; i++) {
            char karakter = beregnKarakter(poengInput());
            karakterListe.add(karakter);
        }

        // Konverterer listen med karakterer til en streng
        StringBuilder karakterTekst = new StringBuilder("Karakterliste:\n");
        for (int i = 0; i < karakterListe.size(); i++) {
            karakterTekst.append("Student ").append(i + 1).append(": ").append(karakterListe.get(i)).append("\n");
        }

        // Viser karakterlisten i en dialogboks
        showMessageDialog(null, karakterTekst.toString(), "Karakterliste", PLAIN_MESSAGE);
    }
}