package pharmacie.utilitaires;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class Utilitaire {

    private static final Scanner sc = new Scanner(System.in);

    public static int choixListe(List<?> l) {
        affListe(l);
        return choixElt(l, 1);
    }

    public static int choixListeM(List<?> l) {
        affListe(l);
        return choixElt(l, -1);
    }

    public static void affListe(List<?> l) {
        int i = 1;
        for (Object o : l) {
            System.out.println((i++) + "." + o);
        }
    }

    public static int choixElt(List<?> l, int min) {
        int choix;
        do {
            choix = saisie("choix: ", Integer::parseInt);
        } while (choix < min || choix > l.size());
        return choix;
    }

    public static String modifyIfNotBlank(String label, String oldValue) {
        System.out.println(label + " : " + oldValue);
        System.out.print("nouvelle valeur (enter si pas de changement) : ");
        String newValue = sc.nextLine();
        if (newValue.isBlank()) return oldValue;
        return newValue;
    }

    public static String getDateFrench(LocalDate d){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dtf.format(d);
    }

    public static <T> T saisie(String message, Function<String, T> parser) {
        System.out.println(message);

        String value;
        T realValue;

        do {
            try{
                value = sc.nextLine();
                realValue = parser.apply(value);
                break;
            }catch(Exception e){
                System.out.println("Erreur de saisie! Recommencez");
                System.out.println(message);
            }
        }while(true);

        return realValue;
    }

}
