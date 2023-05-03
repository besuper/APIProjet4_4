package pharmacie.designpatterns.composite;

public class Pharmacie {

    public static void main(String[] args) {
        Medicament m1 = new Medicament(1, "4512", "Medrol", "", 15);
        Medicament m2 = new Medicament(2, "8945", "Gaviscon", "", 15);
        Medicament m3 = new Medicament(3, "1524", "Doliprane", "", 15);
        Medicament m4 = new Medicament(4, "2745", "Paracétamol", "", 15);

        Categorie c1 = new Categorie(1, "Anti inflammatoire");
        Categorie c2 = new Categorie(2, "Troubles gastriques");
        Categorie c3 = new Categorie(3, "Antalgique");

        c1.getElements().add(m1);

        c2.getElements().add(m2);

        c3.getElements().add(m3);
        c3.getElements().add(m4);

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
    }

}
