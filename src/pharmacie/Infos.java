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

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }
}
