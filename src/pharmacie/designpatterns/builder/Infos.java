package pharmacie.designpatterns.builder;

public class Infos {

    /**
     * Quantité de médicament
     */
    protected int quantite;

    /**
     * Le médicament concerné
     */
    protected Medicament medicament;

    /**
     * Precription qui contient cette information
     */
    protected Prescription prescription;

    private Infos(InfosBuilder builder) {
        this.quantite = builder.quantite;
        this.medicament = builder.medicament;
        this.prescription = builder.prescription;
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
     * Retourne le médicament concerné
     *
     * @return Medicament
     */
    public Medicament getMedicament() {
        return medicament;
    }

    /**
     * Retourne la prescription qui contient cette information
     *
     * @return Prescription
     */
    public Prescription getPrescription() {
        return prescription;
    }

    @Override
    public String toString() {
        return "Infos{" +
                "quantite=" + quantite +
                ", medicament=" + medicament +
                '}';
    }

    public static class InfosBuilder {
        protected int quantite;
        protected Medicament medicament;
        protected Prescription prescription;

        public InfosBuilder setMedicament(Medicament medicament) {
            this.medicament = medicament;
            return this;
        }

        public InfosBuilder setPrescription(Prescription prescription) {
            this.prescription = prescription;
            return this;
        }

        public InfosBuilder setQuantite(int quantite) {
            this.quantite = quantite;
            return this;
        }

        public Infos build() {
            return new Infos(this);
        }
    }

}
