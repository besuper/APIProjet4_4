package pharmacie;

public class Infos {

    private int quantite;

    private Medicament medicament;
    private Prescription prescription;

    public Infos(int quantite, Medicament medicament, Prescription prescription) {
        this.quantite = quantite;
        this.medicament = medicament;
        this.prescription = prescription;
    }

    public int getQuantite() {
        return quantite;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public Prescription getPrescription() {
        return prescription;
    }
}
