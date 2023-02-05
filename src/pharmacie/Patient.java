package pharmacie;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Patient {

    private int id;
    private String nss;
    private String nom;
    private String prenom;
    private String dateNaissance;

    private List<Prescription> prescription = new ArrayList<>();

    public Patient(int id, String nss, String nom, String prenom, String dateNaissance) {
        this.id = id;
        this.nss = nss;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    public String getNom() {
        return nom;
    }

    public String getNss() {
        return nss;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public List<Prescription> getPrescription() {
        return prescription;
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
