package pharmacie;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Medecin {

    private int id;
    private String matricule;
    private String nom;
    private String tel;

    private List<Prescription> prescription = new ArrayList<>();

    public Medecin(int id, String matricule, String nom, String tel) {
        this.id = id;
        this.matricule = matricule;
        this.nom = nom;
        this.tel = tel;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getNom() {
        return nom;
    }

    public String getTel() {
        return tel;
    }

    public List<Prescription> getPrescription() {
        return prescription;
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
