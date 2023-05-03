package pharmacie.designpatterns.observer;

import java.time.LocalDate;

public class Pharmacie {

    public static void main(String[] args) {
        Medecin m1 = new Medecin(1, "AA", "Van", "John", "044");
        Medecin m2 = new Medecin(2, "BB", "Van", "Paul", "066");

        Patient p1 = new Patient(1, "AA", "Paul", "Paul", "0478", LocalDate.now());
        Patient p2 = new Patient(1, "AA", "Marc", "Marc", "0684", LocalDate.now());

        p1.addObserver(m1);
        p1.addObserver(m2);

        p2.addObserver(m1);

        p1.setTel("02451");
        p2.setTel("78945");
    }

}
