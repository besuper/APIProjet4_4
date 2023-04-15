package pharmacie.utilitaires;

import java.util.List;
import java.util.Scanner;

public class Utilitaire {

    private static final Scanner sc = new Scanner(System.in);

    public static int choixListe(List l) {
        affListe(l);
        return choixElt(l);
    }

    public static int choixListeM(List l) {
        affListe(l);
        return choixEltM(l);
    }

    public static void affListe(List l) {
        int i = 1;
        for (Object o : l) {
            System.out.println((i++) + "." + o);
        }
    }

    public static int choixElt(List l) {
        int choix;
        do {
            System.out.println("choix :");
            choix = sc.nextInt();
            sc.skip("\n");
        } while (choix < 1 || choix > l.size());
        return choix;
    }

    public static int choixEltM(List l) {
        int choix;
        do {
            System.out.println("choix :");
            choix = sc.nextInt();
            sc.skip("\n");
        } while (choix < -1 || choix > l.size());
        return choix;
    }

    public static String modifyIfNotBlank(String label, String oldValue) {
        System.out.println(label + " : " + oldValue);
        System.out.print("nouvelle valeur (enter si pas de changement) : ");
        String newValue = sc.nextLine();
        if (newValue.isBlank()) return oldValue;
        return newValue;
    }

}
