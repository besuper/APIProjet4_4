package pharmacie.designpatterns.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Medecin {

    /**
     * Identifiant unique du médecin
     */
    protected final int id;

    /**
     * Matricule
     */
    protected String matricule;

    /**
     * Nom
     */
    protected String nom;

    /**
     * Prénom
     */
    protected String prenom;

    /**
     * Numéro de téléphone
     */
    protected String tel;

    /**
     * Liste des prescriptions données
     */
    protected List<Prescription> prescription = new ArrayList<>();

    private Medecin(MedecinBuilder builder) {
        this.id = builder.id;
        this.matricule = builder.matricule;
        this.nom = builder.nom;
        this.prenom = builder.prenom;
        this.tel = builder.tel;
    }

    /**
     * Retourne l'identifiant unique du médecin
     *
     * @return Identifiant unique du médecin
     */
    public int getId() {
        return id;
    }

    /**
     * Retourne le matricule
     *
     * @return Le matricule
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     * Retourne le nom du médecin
     *
     * @return Nom du médecin
     */
    public String getNom() {
        return nom;
    }


    /**
     * Retourne le prénom du médecin
     *
     * @return Prénom du médecin
     */
    public String getPrenom() {
        return prenom;
    }


    /**
     * Retourne le numéro de téléphone
     *
     * @return Numéro de téléphone
     */
    public String getTel() {
        return tel;
    }

    /**
     * Retourne la liste des prescriptions données
     *
     * @return Liste des prescriptions données
     */
    public List<Prescription> getPrescription() {
        return prescription;
    }

    /**
     * Définir la liste des prescriptions
     *
     * @param prescription Liste de prescriptions
     */
    public void setPrescription(List<Prescription> prescription) {
        this.prescription = prescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medecin medecin = (Medecin) o;
        return Objects.equals(matricule, medecin.matricule);
    }

    @Override
    public String toString() {
        return "Medecin{" +
                "id=" + id +
                ", matricule='" + matricule + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricule);
    }

    public static class MedecinBuilder {

        protected int id;
        protected String matricule;
        protected String nom;
        protected String prenom;
        protected String tel;

        public MedecinBuilder setMatricule(String matricule) {
            this.matricule = matricule;
            return this;
        }

        public MedecinBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public MedecinBuilder setNom(String nom) {
            this.nom = nom;
            return this;
        }

        public MedecinBuilder setPrenom(String prenom) {
            this.prenom = prenom;
            return this;
        }

        public MedecinBuilder setTel(String tel) {
            this.tel = tel;
            return this;
        }

        public Medecin build() {
            return new Medecin(this);
        }
    }

}
