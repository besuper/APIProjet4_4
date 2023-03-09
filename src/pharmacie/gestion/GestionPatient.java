package pharmacie.gestion;

import myconnections.DBConnection;
import pharmacie.metier.Medecin;
import pharmacie.metier.Patient;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class GestionPatient {

    private Scanner scanner = new Scanner(System.in);
    private Connection dbConnect;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void gestion() {
        dbConnect = DBConnection.getConnection();

        if (dbConnect == null) {
            System.exit(1);
        }

        System.out.println("connexion établie");

        do {
            System.out.println("1.Ajout\n2.Recherche\n3.Modification\n4.Suppression\n5.Tous\n6.Fin");
            System.out.println("choix : ");

            int ch = scanner.nextInt();
            scanner.skip("\n");

            switch (ch) {
                case 1 -> ajout();
                case 2 -> recherche();
                case 3 -> modification();
                case 4 -> suppression();
                case 5 -> tous();
                case 6 -> System.exit(0);
                default -> System.out.println("choix invalide recommencez ");
            }
        } while (true);

    }

    public void ajout() {
        System.out.println("NSS: ");
        String nss = scanner.next();

        System.out.println("Nom: ");
        String nom = scanner.next();

        System.out.println("Prénom: ");
        String prenom = scanner.next();

        System.out.println("Date de naissance (jj/mm/aaaa): ");
        String date = scanner.next();
        LocalDate dateNaissance = LocalDate.parse(date, formatter);

        String queryInsert = "INSERT INTO APIPATIENT(nss, nom, prenom, datenaissance) VALUES (?, ?, ?, ?)";
        String querySelectID = "SELECT ID_PATIENT FROM APIPATIENT WHERE NSS = ?";

        try (PreparedStatement preparedStatementInsert = dbConnect.prepareStatement(queryInsert);
             PreparedStatement preparedStatementSelect = dbConnect.prepareStatement(querySelectID);
        ) {
            preparedStatementInsert.setString(1, nss);
            preparedStatementInsert.setString(2, nom);
            preparedStatementInsert.setString(3, prenom);
            preparedStatementInsert.setDate(4, Date.valueOf(dateNaissance));

            int n = preparedStatementInsert.executeUpdate();

            System.out.println(n + " ligne(s) insérée");

            if (n == 1) {
                preparedStatementSelect.setString(1, nss);

                ResultSet rs = preparedStatementSelect.executeQuery();

                if (rs.next()) {
                    int idPatient = rs.getInt(1);
                    System.out.println("id patient = " + idPatient);
                } else {
                    System.out.println("record introuvable");
                }
            }
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
        }
    }

    public void recherche() {
        System.out.println("ID du patient recherché: ");
        int idPatient = scanner.nextInt();

        String querySelect = "SELECT * FROM APIPATIENT WHERE ID_PATIENT = ?";

        try (PreparedStatement preparedStatementSelect = dbConnect.prepareStatement(querySelect)) {
            preparedStatementSelect.setInt(1, idPatient);

            ResultSet rs = preparedStatementSelect.executeQuery();

            if (rs.next()) {
                int id = rs.getInt(1);
                String nss = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                LocalDate dateNaissance = rs.getDate(5).toLocalDate();

                Patient patient = new Patient(id, nss, nom, prenom, dateNaissance);
                System.out.println(patient);

                opSpeciales(patient);
            } else {
                System.out.println("record introuvable");
            }
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
        }
    }

    public void modification() {
        System.out.println("id du patient recherché: ");
        int idPatient = scanner.nextInt();
        scanner.skip("\n");

        System.out.println("1.modifier nss\n2.modifier nom\n3.modifier prenom\n4.modifier date de naissance\n5.Retour");
        System.out.println("choix : ");

        int ch = scanner.nextInt();
        scanner.skip("\n");

        switch (ch) {
            case 1:
                modifierPatientInfo("nss", idPatient);
                break;
            case 2:
                modifierPatientInfo("nom", idPatient);
                break;
            case 3:
                modifierPatientInfo("prenom", idPatient);
                break;

            case 4:
                String queryUpdateDateNaissance = "UPDATE APIPATIENT SET DATENAISSANCE = ? WHERE ID_PATIENT = ?";

                try (PreparedStatement preparedStatementUpdate = dbConnect.prepareStatement(queryUpdateDateNaissance)) {
                    System.out.println("Date de naissance (jj/mm/aaaa): ");
                    String date = scanner.next();
                    LocalDate dateNaissance = LocalDate.parse(date, formatter);

                    preparedStatementUpdate.setDate(1, Date.valueOf(dateNaissance));
                    preparedStatementUpdate.setInt(2, idPatient);

                    int n = preparedStatementUpdate.executeUpdate();

                    if (n != 0) {
                        System.out.println(n + " ligne mise à jour");
                    } else {
                        System.out.println("record introuvable");
                    }
                } catch (SQLException e) {
                    System.out.println("erreur sql :" + e);
                }

                return;
            case 5:
                break;
            default:
                System.out.println("choix invalide recommencez ");
        }
    }

    public void modifierPatientInfo(String info, int idPatient) {
        String queryUpdateNSS = "UPDATE APIPATIENT SET " + info.toUpperCase() + " = ? WHERE ID_PATIENT = ?";

        try (PreparedStatement preparedStatementUpdate = dbConnect.prepareStatement(queryUpdateNSS)) {
            System.out.println(info + ": ");
            String information = scanner.next();

            preparedStatementUpdate.setString(1, information);
            preparedStatementUpdate.setInt(2, idPatient);

            int n = preparedStatementUpdate.executeUpdate();

            if (n != 0) {
                System.out.println(n + " ligne mise à jour");
            } else {
                System.out.println("record introuvable");
            }
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
        }
    }

    public void suppression() {
        System.out.println("id du patient recherché: ");
        int idPatient = scanner.nextInt();
        scanner.skip("\n");

        String queryDelete = "DELETE FROM APIPATIENT WHERE ID_PATIENT = ?";

        try (PreparedStatement preparedStatementDelete = dbConnect.prepareStatement(queryDelete)) {
            preparedStatementDelete.setInt(1, idPatient);

            int n = preparedStatementDelete.executeUpdate();

            if (n != 0) {
                System.out.println(n + " ligne supprimée");
            } else {
                System.out.println("record introuvable");
            }
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
        }
    }

    public void tous() {
        String querySelect = "SELECT * FROM APIPATIENT";

        try (Statement statementSelect = dbConnect.createStatement()) {
            ResultSet rs = statementSelect.executeQuery(querySelect);

            while (rs.next()) {
                int idPatient = rs.getInt(1);
                String nss = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                LocalDate dateNaissance = rs.getDate(5).toLocalDate();

                Patient patient = new Patient(idPatient, nss, nom, prenom, dateNaissance);
                System.out.println(patient);
            }

        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
        }
    }

    private void opSpeciales(Patient patient) {
        do {
            System.out.println("1.liste des médecins\n2.montant prescriptions\n3.prescriptions\n4.menu principal");
            System.out.println("choix : ");

            int ch = scanner.nextInt();
            scanner.skip("\n");

            switch (ch) {
                case 1:
                    listeMedecin(patient);
                    break;
                case 2:
                    prescriptionTotal(patient);
                    break;
                case 3:
                    prescriptionDate(patient);
                    break;

                case 4:
                    return;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);

    }

    public void listeMedecin(Patient patient) {
        String queryMedecinPatient = "SELECT DISTINCT apimedecin.*  FROM apipatient " +
                "JOIN apiprescription ON apipatient.id_patient = apiprescription.id_patient " +
                "JOIN apimedecin ON apiprescription.id_medecin = apimedecin.id_medecin " +
                "WHERE apipatient.nss = ?";

        try (PreparedStatement preparedStatementMedPat = dbConnect.prepareStatement(queryMedecinPatient)) {
            preparedStatementMedPat.setString(1, patient.getNss());

            ResultSet rs = preparedStatementMedPat.executeQuery();

            boolean trouverMedecin = false;

            while (rs.next()) {
                trouverMedecin = true;

                int idMedecin = rs.getInt(1);
                String matricule = rs.getString(2);
                String prenom = rs.getString(3);
                String tel = rs.getString(4);
                String nom = rs.getString(5);

                Medecin medecin = new Medecin(idMedecin, matricule, prenom, tel, nom);
                System.out.println(medecin);
            }

            if (!trouverMedecin) {
                System.out.println("Aucun médecin trouvé");
            }
        } catch (SQLException e) {
            System.out.println("erreur sql: " + e);
        }
    }

    public void prescriptionTotal(Patient patient) {
        String queryTotal = "SELECT * FROM APIPRES_TOTAL WHERE ID_PATIENT = ?";

        try (PreparedStatement preparedStatementTotal = dbConnect.prepareStatement(queryTotal)) {
            preparedStatementTotal.setInt(1, patient.getId());

            ResultSet rs = preparedStatementTotal.executeQuery();
            boolean trouverPrescription = false;

            while (rs.next()) {
                trouverPrescription = true;

                int idPrescription = rs.getInt(2);
                LocalDate date = rs.getDate(3).toLocalDate();
                double total = rs.getDouble(4);

                System.out.println("idPrescription=" + idPrescription + " , date=" + date + " ,total=" + total);
            }

            if (!trouverPrescription) {
                System.out.println("Aucune prescription trouvée");
            }
        } catch (SQLException e) {
            System.out.println("erreur sql: " + e);
        }
    }

    public void prescriptionDate(Patient patient) {
        String queryPrescription = "SELECT * FROM apiprescription WHERE dateprescription BETWEEN ? AND ? AND ID_PATIENT = ?";

        try (PreparedStatement preparedStatementPrescription = dbConnect.prepareStatement(queryPrescription)) {
            System.out.println("Date de début (jj/mm/aaaa): ");
            String dateD = scanner.next();
            LocalDate dateDebut = LocalDate.parse(dateD, formatter);

            System.out.println("Date de fin (jj/mm/aaaa): ");
            String dateF = scanner.next();
            LocalDate dateFin = LocalDate.parse(dateF, formatter);

            preparedStatementPrescription.setDate(1, Date.valueOf(dateDebut));
            preparedStatementPrescription.setDate(2, Date.valueOf(dateFin));
            preparedStatementPrescription.setInt(3, patient.getId());

            ResultSet rs = preparedStatementPrescription.executeQuery();
            boolean trouverPrescription = false;

            while (rs.next()) {
                trouverPrescription = true;

                int idPrescription = rs.getInt(1);
                LocalDate date = rs.getDate(2).toLocalDate();
                int idMedecin = rs.getInt(4);

                System.out.println("idPrescription=" + idPrescription + " ,date=" + date + " ,idMedecin=" + idMedecin);
            }

            if (!trouverPrescription) {
                System.out.println("Aucune prescription trouvée");
            }
        } catch (SQLException e) {
            System.out.println("erreur sql: " + e);
        }
    }

}
