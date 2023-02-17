package pharmacie;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Medecin {

    private int id;
    private String matricule, nom, prenom, tel;

    private List<Prescription> prescription = new ArrayList<>();

    public Medecin(int id, String matricule, String nom, String prenom, String tel) {
        this.id = id;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
    }

    public int getId() {
        return id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public List<Prescription> getPrescription() {
        return prescription;
    }

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
    public int hashCode() {
        return Objects.hash(matricule);
    }
}
