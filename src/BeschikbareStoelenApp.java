import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Klasse BeschikbareStoelenApp
public class BeschikbareStoelenApp {
    public static void main(String[] args) {
        StoelenData stoelenData = new StoelenData();
        List<Gebruiker> gebruikers = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Registreer gebruiker");
            System.out.println("2. Stel aantal beschikbare stoelen in");
            System.out.println("3. Stoppen");
            System.out.print("Voer uw keuze in: ");
            int keuze = scanner.nextInt();

            switch (keuze) {
                case 1:
                    System.out.print("Voer gebruikersnaam in: ");
                    String gebruikersnaam = scanner.next();

                    System.out.println("Kies stoelvoorkeur:");
                    System.out.println("1. Altijd melden");
                    System.out.println("2. Melden bij beschikbaarheid");
                    System.out.print("Voer uw voorkeur in: ");
                    int voorkeurKeuze = scanner.nextInt();
                    StoelVoorkeur voorkeur;

                    switch (voorkeurKeuze) {
                        case 1:
                            voorkeur = StoelVoorkeur.ALTIJD;
                            break;
                        case 2:
                            voorkeur = StoelVoorkeur.BEREIK;
                            break;
                        default:
                            System.out.println("Ongeldige voorkeur. Standaardinstelling is altijd melden.");
                            voorkeur = StoelVoorkeur.ALTIJD;
                            break;
                    }

                    Gebruiker nieuweGebruiker;
                    if (voorkeur == StoelVoorkeur.ALTIJD) {
                        nieuweGebruiker = new AltijdMeldenGebruiker(gebruikersnaam);
                    } else {
                        nieuweGebruiker = new BereikMeldenGebruiker(gebruikersnaam);
                    }

                    gebruikers.add(nieuweGebruiker);
                    stoelenData.registreerWaarnemer(nieuweGebruiker);
                    nieuweGebruiker.registreer(); // Gebruiker registreren
                    System.out.println("Gebruiker succesvol geregistreerd!");
                    break;
                case 2:
                    if (gebruikers.isEmpty()) {
                        System.out.println("Er zijn nog geen gebruikers geregistreerd.");
                    } else {
                        System.out.print("Voer aantal beschikbare stoelen in: ");
                        int beschikbareStoelen = scanner.nextInt();
                        stoelenData.setBeschikbareStoelen(beschikbareStoelen);
                    }
                    break;
                case 3:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Ongeldige keuze. Probeer het opnieuw.");
            }
        }
    }
}
