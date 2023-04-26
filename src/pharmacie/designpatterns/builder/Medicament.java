package pharmacie.designpatterns.builder;

import java.util.Objects;

public class Medicament {

    /**
     * Identifiant unique du médicament
     */
    protected final int id;

    /**
     * Code du médicament
     */
    protected String code;

    /**
     * Nom du médicament
     */
    protected String nom;

    /**
     * Description du médicament
     */
    protected String description;

    /**
     * Prix unitaire
     */
    protected double prixUnitaire;

    private Medicament(MedicamentBuilder builder) {
        this.id = builder.id;
        this.code = builder.code;
        this.nom = builder.nom;
        this.description = builder.description;
        this.prixUnitaire = builder.prixUnitaire;
    }

    /**
     * Retourne l'identifiant unique du médicament
     *
     * @return Identifiant unique du médicament
     */
    public int getId() {
        return id;
    }

    /**
     * Retourne le code du médicament
     *
     * @return Code du médicament
     */
    public String getCode() {
        return code;
    }


    /**
     * Retourne le nom du médicament
     *
     * @return Nom du médicament
     */
    public String getNom() {
        return nom;
    }


    /**
     * Retourne la description du médicament
     *
     * @return Description du médicament
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retourne le prix unitaire du médicament
     *
     * @return Prix unitaire du médicament
     */
    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicament that = (Medicament) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", prixUnitaire=" + prixUnitaire +
                '}';
    }

    public static class MedicamentBuilder {

        protected int id;
        protected String code;
        protected String nom;
        protected String description;
        protected double prixUnitaire;

        public MedicamentBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public MedicamentBuilder setCode(String code) {
            this.code = code;
            return this;
        }

        public MedicamentBuilder setNom(String nom) {
            this.nom = nom;
            return this;
        }

        public MedicamentBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public MedicamentBuilder setPrixUnitaire(double prixUnitaire) {
            this.prixUnitaire = prixUnitaire;
            return this;
        }

        public Medicament build() {
            return new Medicament(this);
        }

    }


}
