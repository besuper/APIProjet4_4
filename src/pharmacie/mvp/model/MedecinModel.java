package pharmacie.mvp.model;

import myconnections.DBConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pharmacie.metier.Medecin;
import pharmacie.metier.Patient;
import pharmacie.metier.Prescription;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedecinModel implements DAO<Medecin> {

    private final Connection dbConnect;

    private static final Logger logger = LogManager.getLogger(MedecinModel.class);

    public MedecinModel() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            logger.error("erreur de connexion");
            System.exit(1);
        }

        logger.info("connexion établie");
    }

    @Override
    public Medecin add(Medecin medecin) {
        try {
            CallableStatement cs = dbConnect.prepareCall("call APIINSERERMEDECIN(?, ?, ?, ?, ?)");

            cs.setString(1, medecin.getMatricule());
            cs.setString(2, medecin.getPrenom());
            cs.setString(3, medecin.getTel());
            cs.setString(4, medecin.getNom());

            cs.registerOutParameter(5, Types.INTEGER);

            cs.execute();

            int idMedecin = cs.getInt(5);

            return new Medecin(idMedecin, medecin.getMatricule(), medecin.getNom(), medecin.getPrenom(), medecin.getTel());
        } catch (SQLException e) {
            logger.error("erreur ajout :" + e);
        }

        return null;
    }

    @Override
    public Medecin read(int id) {
        String query = "SELECT * FROM APIREADMEDECIN WHERE id_medecin = ?";

        try (PreparedStatement preparedStatement = dbConnect.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String matricule = rs.getString("matricule");
                String tel = rs.getString("tel");
                String nom_medecin = rs.getString("nom_medecin");
                String prenom_medecin = rs.getString("prenom_medecin");
                Medecin medecin = new Medecin(id, matricule, nom_medecin, prenom_medecin, tel);

                int idPatient = rs.getInt("id_patient");
                String nss = rs.getString("nss");
                String nom_patient = rs.getString("nom_patient");
                String prenom_patient = rs.getString("prenom_patient");
                LocalDate dateNaissance = rs.getDate("datenaissance").toLocalDate();
                Patient patient = new Patient(idPatient, nss, nom_patient, prenom_patient, dateNaissance);

                List<Prescription> prescriptions = new ArrayList<>();

                int idPrescription = rs.getInt("id_prescription");

                do {
                    if (idPrescription == 0) {
                        break;
                    }

                    LocalDate datePrescription = rs.getDate("dateprescription").toLocalDate();

                    Prescription prescription = new Prescription(idPrescription, datePrescription, medecin, patient);

                    prescriptions.add(prescription);
                } while (rs.next());

                medecin.setPrescription(prescriptions);

                return medecin;
            }
        } catch (SQLException e) {
            logger.error("erreur read :" + e);
        }

        return null;
    }

    @Override
    public boolean remove(Medecin medecin) {
        String queryDelete = "DELETE FROM APIMEDECIN WHERE ID_MEDECIN = ?";

        try (PreparedStatement preparedStatementDelete = dbConnect.prepareStatement(queryDelete)) {
            preparedStatementDelete.setInt(1, medecin.getId());

            int n = preparedStatementDelete.executeUpdate();

            return n != 0;
        } catch (SQLException e) {
            logger.error("erreur remove :" + e);
        }

        return false;
    }

    @Override
    public List<Medecin> getAll() {
        List<Medecin> medecins = new ArrayList<>();

        String querySelect = "SELECT * FROM APIMEDECIN";

        try (Statement statementSelect = dbConnect.createStatement()) {
            ResultSet rs = statementSelect.executeQuery(querySelect);

            while (rs.next()) {
                int idMedecin = rs.getInt(1);
                String matricule = rs.getString("matricule");
                String prenom = rs.getString("prenom");
                String nom = rs.getString("nom");
                String tel = rs.getString("tel");

                Medecin medecin = new Medecin(idMedecin, matricule, nom, prenom, tel);
                medecins.add(medecin);
            }

        } catch (SQLException e) {
            logger.error("erreur getAll :" + e);
        }

        return medecins;
    }

    @Override
    public Medecin update(Medecin medecin, String key, Object value) {
        String queryUpdateNSS = "UPDATE APIMEDECIN SET " + key.toUpperCase() + " = ? WHERE ID_MEDECIN = ?";

        try (PreparedStatement preparedStatementUpdate = dbConnect.prepareStatement(queryUpdateNSS)) {
            preparedStatementUpdate.setObject(1, value);
            preparedStatementUpdate.setInt(2, medecin.getId());

            int n = preparedStatementUpdate.executeUpdate();

            return medecin;
        } catch (SQLException e) {
            logger.error("erreur update :" + e);
        }

        return null;
    }

}
