package pharmacie;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Patient {

    private int id;
    private String nss, nom, prenom;
    private String dateNaissance;

    private List<Prescription> prescription = new ArrayList<>();

    public Patient(int id, String nss, String nom, String prenom, String dateNaissance) {
        this.id = id;
        this.nss = nss;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    public int getId() {
        return id;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
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

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
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
        Patient patient = (Patient) o;
        return Objects.equals(nss, patient.nss);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nss);
    }
}
