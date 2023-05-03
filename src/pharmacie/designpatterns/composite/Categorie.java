package pharmacie.designpatterns.composite;

import java.util.HashSet;
import java.util.Set;

public class Categorie extends Element {
    private Set<Element> elements = new HashSet<>();

    public Categorie(int id, String nom) {
        super(id, nom);
    }

    @Override
    public String toString() {
        int countMedicament = 0;
        StringBuilder aff = new StringBuilder(getId() + " | " + getNom() + "\n");

        for (Element e : elements) {
            if (e instanceof Medicament) {
                countMedicament += 1;
            }

            aff.append("- ").append(e).append("\n");
        }
        return aff + "Nombre de médicament pour cette catégorie: " + countMedicament + "\n";
    }

    public Set<Element> getElements() {
        return elements;
    }

}

