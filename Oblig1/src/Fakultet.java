import static java.awt.image.ImageObserver.ABORT;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class Fakultet {
	
	static int tallInput() {
        do {
            String tallTkst = showInputDialog("Skriv et heltall st�rre enn 0 � beregne fakultet p�.");
            if (tallTkst == null) {System.exit(ABORT);}

            try {
                int tall = Integer.parseInt(tallTkst);

                if (0 < tall) {
                    return tall;
                } else {
                    showMessageDialog(null, "Tallet m� v�re mellom st�rre enn 0.");
                }
            } catch (NumberFormatException e) {
                showMessageDialog(null, "Det du skrev inn er ikke et gyldig heltall.");
            }
        } while (true);
    }
	
	static int fakultet(int fak) {
		
		int i = fak-1;
		while(i > 0) {
			fak *= i;
			i--;
		}
		return fak;
	}
	
	public static void main(String[] args) {
		int tall = tallInput();
		int fak = fakultet(tall);
		showMessageDialog(null, tall + "! = " + fak);
	}
}
