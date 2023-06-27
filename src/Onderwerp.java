import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface Onderwerp {
    void registreerWaarnemer(Waarnemer waarnemer);
    void verwijderWaarnemer(Waarnemer waarnemer);
    void informeerWaarnemers();
}

class StoelenData implements Onderwerp {
    private int beschikbareStoelen;
    private List<Waarnemer> waarnemers;

    public StoelenData() {
        waarnemers = new ArrayList<>();
    }

    public void setBeschikbareStoelen(int beschikbareStoelen) {
        this.beschikbareStoelen = beschikbareStoelen;
        informeerWaarnemers();
    }

    @Override
    public void registreerWaarnemer(Waarnemer waarnemer) {
        waarnemers.add(waarnemer);
    }

    @Override
    public void verwijderWaarnemer(Waarnemer waarnemer) {
        waarnemers.remove(waarnemer);
    }

    @Override
    public void informeerWaarnemers() {
        for (Waarnemer waarnemer : waarnemers) {
            waarnemer.update(beschikbareStoelen);
        }
    }
}

interface Waarnemer {
    void update(int beschikbareStoelen);
}

abstract class Gebruiker implements Waarnemer {
    private String naam;

    public Gebruiker(String naam) {
        this.naam = naam;
    }

    public void registreer() {
        valideer();
        voerVoorkeurenUit();
        voerRegistratieUit();
    }

    protected abstract void valideer();

    protected abstract void voerVoorkeurenUit();

    protected abstract void voerRegistratieUit();

    @Override
    public void update(int beschikbareStoelen) {
        if (moetMelden(beschikbareStoelen)) {
            System.out.println(naam + ": Aantal beschikbare stoelen gewijzigd naar " + beschikbareStoelen);
        }
    }

    protected abstract boolean moetMelden(int beschikbareStoelen);
}

class AltijdMeldenGebruiker extends Gebruiker {
    public AltijdMeldenGebruiker(String naam) {
        super(naam);
    }

    @Override
    protected void valideer() {
        System.out.println("Gebruiker wordt aangemaakt...");
    }

    @Override
    protected void voerVoorkeurenUit() {
        System.out.println("Voorkeuren verwerken...");
    }

    @Override
    protected void voerRegistratieUit() {
        System.out.println("Registratie wordt voltooid...");
    }

    @Override
    protected boolean moetMelden(int beschikbareStoelen) {
        return true; // Altijd melden, ongeacht het aantal beschikbare stoelen
    }
}

class BereikMeldenGebruiker extends Gebruiker {
    public BereikMeldenGebruiker(String naam) {
        super(naam);
    }

    @Override
    protected void valideer() {
        System.out.println("Gebruiker aanmaken...");
    }

    @Override
    protected void voerVoorkeurenUit() {
        System.out.println("Uw voorkeuren worden verwerkt...");
    }

    @Override
    protected void voerRegistratieUit() {
        System.out.println("Registratie uitvoeren...");
    }

    @Override
    protected boolean moetMelden(int beschikbareStoelen) {
        return beschikbareStoelen > 0; // Melden alleen als er 1 of meer stoelen beschikbaar zijn
    }
}

enum StoelVoorkeur {
    ALTIJD, BEREIK;

    public boolean moetMelden(int beschikbareStoelen) {
        switch (this) {
            case ALTIJD:
                return true;
            case BEREIK:
                return beschikbareStoelen > 0;
            default:
                return false;
        }
    }
}

