package pharmacie.mvp.model;

import myconnections.DBConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pharmacie.metier.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionModel implements DAO<Prescription> {

    private final Connection dbConnect;

    private static final Logger logger = LogManager.getLogger(PrescriptionModel.class);

    public PrescriptionModel() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            logger.error("erreur de connexion");
            System.exit(1);
        }

        logger.info("connexion établie");
    }

    @Override
    public Prescription add(Prescription prescription) {
        String queryInsert = "INSERT INTO APIPRESCRIPTION(dateprescription, id_patient, id_medecin) VALUES (?, ?, ?)";
        String querySelectID = "SELECT ID_PRESCRIPTION FROM APIPRESCRIPTION WHERE dateprescription = ? AND id_patient = ? AND id_medecin = ?";

        try (PreparedStatement preparedStatementInsert = dbConnect.prepareStatement(queryInsert);
             PreparedStatement preparedStatementSelect = dbConnect.prepareStatement(querySelectID);
        ) {
            preparedStatementInsert.setDate(1, Date.valueOf(prescription.getDatePrescription()));
            preparedStatementInsert.setInt(2, prescription.getPatient().getId());
            preparedStatementInsert.setInt(3, prescription.getMedecin().getId());

            int n = preparedStatementInsert.executeUpdate();

            if (n == 1) {
                preparedStatementSelect.setDate(1, Date.valueOf(prescription.getDatePrescription()));
                preparedStatementSelect.setInt(2, prescription.getPatient().getId());
                preparedStatementSelect.setInt(3, prescription.getMedecin().getId());

                ResultSet rs = preparedStatementSelect.executeQuery();

                if (rs.next()) {
                    int idPrescription = rs.getInt(1);

                    return new Prescription(idPrescription, prescription.getDatePrescription(), prescription.getMedecin(), prescription.getPatient());
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
    public Prescription read(int id) {
        String querySelect = "SELECT * FROM APIPRES_DETAIL WHERE id_prescription = ?";

        try (PreparedStatement statementSelect = dbConnect.prepareStatement(querySelect)) {
            statementSelect.setInt(1, id);

            ResultSet rs = statementSelect.executeQuery();

            if (rs.next()) {
                int idPrescription = rs.getInt("id_prescription");
                LocalDate datePrescription = rs.getDate("dateprescription").toLocalDate();

                int idPatient = rs.getInt("id_patient");
                String nss = rs.getString("nss");
                String nomPatient = rs.getString("nom_pat");
                String prenomPatient = rs.getString("prenom_pat");
                LocalDate dateNaissance = rs.getDate("datenaissance").toLocalDate();

                Patient patient = new Patient(idPatient, nss, nomPatient, prenomPatient, dateNaissance);

                int idMedecin = rs.getInt("id_medecin");
                String matricule = rs.getString("matricule");
                String nomMedecin = rs.getString("nom_med");
                String prenomMedecin = rs.getString("prenom_med");
                String tel = rs.getString("tel");

                Medecin medecin = new Medecin(idMedecin, matricule, nomMedecin, prenomMedecin, tel);

                Prescription prescription = new Prescription(idPrescription, datePrescription, medecin, patient);

                prescription.setInfos(getInfosFromPrescription(prescription));

                return prescription;
            }

        } catch (SQLException e) {
            logger.error("erreur getAll :" + e);
        }

        return null;
    }

    @Override
    public boolean remove(Prescription prescription) {
        String queryDelete = "DELETE FROM APIPRESCRIPTION WHERE ID_PRESCRIPTION = ?";

        try (PreparedStatement preparedStatementDelete = dbConnect.prepareStatement(queryDelete)) {
            preparedStatementDelete.setInt(1, prescription.getId());

            int n = preparedStatementDelete.executeUpdate();

            return n != 0;
        } catch (SQLException e) {
            logger.error("erreur remove :" + e);
        }

        return false;
    }

    @Override
    public List<Prescription> getAll() {
        List<Prescription> prescriptions = new ArrayList<>();

        String querySelect = "SELECT * FROM APIPRES_DETAIL";

        try (Statement statementSelect = dbConnect.createStatement()) {
            ResultSet rs = statementSelect.executeQuery(querySelect);

            while (rs.next()) {
                int idPrescription = rs.getInt("id_prescription");
                LocalDate datePrescription = rs.getDate("dateprescription").toLocalDate();

                int idPatient = rs.getInt("id_patient");
                String nss = rs.getString("nss");
                String nomPatient = rs.getString("nom_pat");
                String prenomPatient = rs.getString("prenom_pat");
                LocalDate dateNaissance = rs.getDate("datenaissance").toLocalDate();

                Patient patient = new Patient(idPatient, nss, nomPatient, prenomPatient, dateNaissance);

                int idMedecin = rs.getInt("id_medecin");
                String matricule = rs.getString("matricule");
                String nomMedecin = rs.getString("nom_med");
                String prenomMedecin = rs.getString("prenom_med");
                String tel = rs.getString("tel");

                Medecin medecin = new Medecin(idMedecin, matricule, nomMedecin, prenomMedecin, tel);

                Prescription prescription = new Prescription(idPrescription, datePrescription, medecin, patient);
                prescriptions.add(prescription);
            }

        } catch (SQLException e) {
            logger.error("erreur getAll :" + e);
        }

        return prescriptions;
    }

    @Override
    public Prescription update(Prescription prescription, String key, Object value) {
        String queryUpdateNSS = "UPDATE APIPRESCRIPTION SET " + key.toUpperCase() + " = ? WHERE ID_PRESCRIPTION = ?";

        try (PreparedStatement preparedStatementUpdate = dbConnect.prepareStatement(queryUpdateNSS)) {
            preparedStatementUpdate.setObject(1, value);
            preparedStatementUpdate.setInt(2, prescription.getId());

            int n = preparedStatementUpdate.executeUpdate();

            return prescription;
        } catch (SQLException e) {
            logger.error("erreur update :" + e);
        }

        return null;
    }

    public List<Infos> getInfosFromPrescription(Prescription prescription) {
        String query = "SELECT * FROM INFOS_PRES WHERE id_prescription = ?";

        List<Infos> infos = new ArrayList<>();

        try (PreparedStatement preparedStatement = dbConnect.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, prescription.getId());

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                do {
                    int medId = rs.getInt("id_medicament");
                    int quantite = rs.getInt("quantite");
                    String code = rs.getString("code");
                    String nom = rs.getString("nom");
                    String description = rs.getString("description");
                    double prixUnitaire = rs.getDouble("prixunitaire");

                    Medicament medicament = new Medicament(medId, code, nom, description, prixUnitaire);

                    Infos info = new Infos(quantite, medicament, prescription);

                    infos.add(info);
                } while (rs.next());

                return infos;
            }
        } catch (SQLException e) {
            logger.error("erreur getInfosFromPrescription :" + e);
        }

        return infos;
    }

}
