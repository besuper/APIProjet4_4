package pharmacie.mvp.model;

import myconnections.DBConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pharmacie.metier.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientModelDB implements DAO<Patient>, PatientSpecial {

    private final Connection dbConnect;

    private static final Logger logger = LogManager.getLogger(PatientModelDB.class);

    public PatientModelDB() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            logger.error("erreur de connexion");
            System.exit(1);
        }

        logger.info("connexion établie");
    }

    @Override
    public Patient add(Patient patient) {
        String queryInsert = "INSERT INTO APIPATIENT(nss, nom, prenom, datenaissance) VALUES (?, ?, ?, ?)";
        String querySelectID = "SELECT ID_PATIENT FROM APIPATIENT WHERE NSS = ?";

        try (PreparedStatement preparedStatementInsert = dbConnect.prepareStatement(queryInsert);
             PreparedStatement preparedStatementSelect = dbConnect.prepareStatement(querySelectID)
        ) {
            preparedStatementInsert.setString(1, patient.getNss());
            preparedStatementInsert.setString(2, patient.getNom());
            preparedStatementInsert.setString(3, patient.getPrenom());
            preparedStatementInsert.setDate(4, Date.valueOf(patient.getDateNaissance()));

            int n = preparedStatementInsert.executeUpdate();

            if (n == 1) {
                preparedStatementSelect.setString(1, patient.getNss());

                ResultSet rs = preparedStatementSelect.executeQuery();

                if (rs.next()) {
                    int idPatient = rs.getInt(1);

                    return new Patient(idPatient, patient.getNss(), patient.getNom(), patient.getPrenom(), patient.getDateNaissance());
                } else {
                    logger.error("record introuvable");
                }
            }
        } catch (SQLException e) {
            logger.error("erreur ajout :" + e);
        }

        return null;
    }

    @Override
    public Patient read(int idPatient) {
        String query = "SELECT * FROM APIPATIENT WHERE id_patient = ?";

        try (PreparedStatement preparedStatement = dbConnect.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, idPatient);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String nss = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                LocalDate datenaissance = rs.getDate(5).toLocalDate();

                return new Patient(idPatient, nss, nom, prenom, datenaissance);
            }
        } catch (SQLException e) {
            logger.error("erreur read :" + e);
        }

        return null;
    }

    @Override
    public boolean remove(Patient patient) {
        String queryDelete = "DELETE FROM APIPATIENT WHERE ID_PATIENT = ?";

        try (PreparedStatement preparedStatementDelete = dbConnect.prepareStatement(queryDelete)) {
            preparedStatementDelete.setInt(1, patient.getId());

            int n = preparedStatementDelete.executeUpdate();

            return n != 0;
        } catch (SQLException e) {
            logger.error("erreur remove :" + e);
        }

        return false;
    }

    @Override
    public List<Patient> getAll() {
        List<Patient> patients = new ArrayList<>();

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
                patients.add(patient);
            }

        } catch (SQLException e) {
            logger.error("erreur getAll :" + e);
        }

        return patients;
    }

    @Override
    public Patient update(Patient obj) {
        String queryUpdateNSS = "UPDATE APIPATIENT SET nss = ?, nom = ?, prenom = ?, datenaissance = ? WHERE ID_MEDECIN = ?";

        try (PreparedStatement preparedStatementUpdate = dbConnect.prepareStatement(queryUpdateNSS)) {
            preparedStatementUpdate.setString(1, obj.getNom());
            preparedStatementUpdate.setString(2, obj.getNom());
            preparedStatementUpdate.setString(3, obj.getPrenom());
            preparedStatementUpdate.setDate(4, Date.valueOf(obj.getDateNaissance()));

            int n = preparedStatementUpdate.executeUpdate();

            return obj;
        } catch (SQLException e) {
            logger.error("erreur update :" + e);
        }

        return null;
    }

    @Override
    public List<Medecin> getMedecins(Patient p) {
        List<Medecin> medecins = new ArrayList<>();

        String queryMedecinPatient = "SELECT DISTINCT apimedecin.*  FROM apipatient " +
                "JOIN apiprescription ON apipatient.id_patient = apiprescription.id_patient " +
                "JOIN apimedecin ON apiprescription.id_medecin = apimedecin.id_medecin " +
                "WHERE apipatient.nss = ?";

        try (PreparedStatement preparedStatementMedPat = dbConnect.prepareStatement(queryMedecinPatient)) {
            preparedStatementMedPat.setString(1, p.getNss());

            ResultSet rs = preparedStatementMedPat.executeQuery();

            while (rs.next()) {
                int idMedecin = rs.getInt(1);
                String matricule = rs.getString(2);
                String prenom = rs.getString(3);
                String tel = rs.getString(4);
                String nom = rs.getString(5);

                Medecin medecin = new Medecin(idMedecin, matricule, prenom, tel, nom);
                medecins.add(medecin);
            }
        } catch (SQLException e) {
            logger.error("erreur get medecins: " + e);
        }

        return medecins;
    }

    public double calcTot(Patient p) {
        double tot = 0.0;

        String queryTotal = "SELECT * FROM APIPRES_TOTAL WHERE ID_PATIENT = ?";

        try (PreparedStatement preparedStatementTotal = dbConnect.prepareStatement(queryTotal)) {
            preparedStatementTotal.setInt(1, p.getId());

            ResultSet rs = preparedStatementTotal.executeQuery();

            while (rs.next()) {
                double total = rs.getDouble(5);

                tot += total;
            }
        } catch (SQLException e) {
            logger.error("erreur calcTot: " + e);
        }

        return tot;
    }

    public List<Prescription> prescriptionsDate(Patient patient, LocalDate debut, LocalDate fin) {
        List<Prescription> prescriptions = new ArrayList<>();

        String queryPrescription = "SELECT * FROM apiprescription WHERE dateprescription BETWEEN ? AND ? AND ID_PATIENT = ?";

        try (PreparedStatement preparedStatementPrescription = dbConnect.prepareStatement(queryPrescription)) {

            preparedStatementPrescription.setDate(1, Date.valueOf(debut));
            preparedStatementPrescription.setDate(2, Date.valueOf(fin));
            preparedStatementPrescription.setInt(3, patient.getId());

            ResultSet rs = preparedStatementPrescription.executeQuery();

            while (rs.next()) {
                int idPrescription = rs.getInt(1);
                LocalDate date = rs.getDate(2).toLocalDate();
                //int idMedecin = rs.getInt(4);

                Prescription pres = new Prescription(idPrescription, date, null, patient);
                prescriptions.add(pres);
            }
        } catch (SQLException e) {
            logger.error("erreur prescriptionsDate: " + e);
        }

        return prescriptions;
    }

}
