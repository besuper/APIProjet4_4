package pharmacie.designpatterns.composite;

public abstract class Element {

    private int id;
    private String nom;

    public Element(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

}
