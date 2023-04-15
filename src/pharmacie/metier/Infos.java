package pharmacie.metier;

/**
 * Classe qui représente une information pour une precription
 *
 * @author Jayson
 */
public class Infos {

    /**
     * Quantité de médicament
     */
    private int quantite;

    /**
     * Le médicament concerné
     */
    private Medicament medicament;

    /**
     * Precription qui contient cette information
     */
    private Prescription prescription;

    /**
     * Constructeur de la classe Infos
     *
     * @param quantite     Quantité du médicament
     * @param medicament   Medicament concerné
     * @param prescription Prescription
     */
    public Infos(int quantite, Medicament medicament, Prescription prescription) {
        this.quantite = quantite;
        this.medicament = medicament;
        this.prescription = prescription;
    }

    /**
     * Retourne la quantité du médicament
     *
     * @return Quantité
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * Définir la quantité du médicament
     *
     * @param quantite Quantité
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /**
     * Retourne le médicament concerné
     *
     * @return Medicament
     */
    public Medicament getMedicament() {
        return medicament;
    }

    /**
     * Définir le médicament concerné
     *
     * @param medicament Medicament
     */
    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }

    /**
     * Retourne la prescription qui contient cette information
     *
     * @return Prescription
     */
    public Prescription getPrescription() {
        return prescription;
    }

    /**
     * Définir la precription
     *
     * @param prescription Precription
     */
    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    @Override
    public String toString() {
        return "Infos{" +
                "quantite=" + quantite +
                ", medicament=" + medicament +
                '}';
    }
}
