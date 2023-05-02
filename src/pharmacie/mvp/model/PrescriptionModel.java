package pharmacie.mvp.model;

import myconnections.DBConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pharmacie.designpatterns.builder.*;

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
             PreparedStatement preparedStatementSelect = dbConnect.prepareStatement(querySelectID)
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

                    prescription.setId(idPrescription);

                    for (Infos infos : prescription.getInfos()) {
                        addPrescriptionInfo(infos);
                    }

                    return prescription;
                } else {
                    logger.error("record introuvable");
                }
            }
        } catch (SQLException e) {
            logger.error("erreur ajout :" + e);
        }

        return null;
    }

    public void addPrescriptionInfo(Infos infos) {
        String queryInsert = "INSERT INTO APIINFOS(id_prescription, id_medicament, quantite) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatementInsert = dbConnect.prepareStatement(queryInsert)
        ) {
            preparedStatementInsert.setInt(1, infos.getPrescription().getId());
            preparedStatementInsert.setInt(2, infos.getMedicament().getId());
            preparedStatementInsert.setInt(3, infos.getQuantite());

            int n = preparedStatementInsert.executeUpdate();
        } catch (SQLException e) {
            logger.error("erreur ajout :" + e);
        }
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

                Patient patient = new Patient.PatientBuilder()
                        .setId(idPatient)
                        .setNss(nss)
                        .setNom(nomPatient)
                        .setPrenom(prenomPatient)
                        .setDateNaissance(dateNaissance)
                        .build();

                int idMedecin = rs.getInt("id_medecin");
                String matricule = rs.getString("matricule");
                String nomMedecin = rs.getString("nom_med");
                String prenomMedecin = rs.getString("prenom_med");
                String tel = rs.getString("tel");

                Medecin medecin = new Medecin.MedecinBuilder()
                        .setId(idMedecin)
                        .setMatricule(matricule)
                        .setNom(nomMedecin)
                        .setPrenom(prenomMedecin)
                        .setTel(tel)
                        .build();

                Prescription prescription = new Prescription.PrescriptionBuilder()
                        .setId(idPrescription)
                        .setDatePrescription(datePrescription)
                        .setPatient(patient)
                        .setMedecin(medecin)
                        .build();

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
        String deleteInfos = "DELETE FROM APIINFOS WHERE ID_PRESCRIPTION = ?";
        String queryDelete = "DELETE FROM APIPRESCRIPTION WHERE ID_PRESCRIPTION = ?";

        try (
                PreparedStatement preparedStatementDeleteInfos = dbConnect.prepareStatement(deleteInfos);
                PreparedStatement preparedStatementDelete = dbConnect.prepareStatement(queryDelete)
        ) {
            preparedStatementDeleteInfos.setInt(1, prescription.getId());
            preparedStatementDelete.setInt(1, prescription.getId());

            int ok = preparedStatementDeleteInfos.executeUpdate();

            if(ok != 0) {
                int n = preparedStatementDelete.executeUpdate();

                return n != 0;
            }
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

                Patient patient = new Patient.PatientBuilder()
                        .setId(idPatient)
                        .setNss(nss)
                        .setNom(nomPatient)
                        .setPrenom(prenomPatient)
                        .setDateNaissance(dateNaissance)
                        .build();

                int idMedecin = rs.getInt("id_medecin");
                String matricule = rs.getString("matricule");
                String nomMedecin = rs.getString("nom_med");
                String prenomMedecin = rs.getString("prenom_med");
                String tel = rs.getString("tel");

                Medecin medecin = new Medecin.MedecinBuilder()
                        .setId(idMedecin)
                        .setMatricule(matricule)
                        .setNom(nomMedecin)
                        .setPrenom(prenomMedecin)
                        .setTel(tel)
                        .build();

                Prescription prescription = new Prescription.PrescriptionBuilder()
                        .setId(idPrescription)
                        .setDatePrescription(datePrescription)
                        .setPatient(patient)
                        .setMedecin(medecin)
                        .build();

                prescriptions.add(prescription);
            }

        } catch (SQLException e) {
            logger.error("erreur getAll :" + e);
        }

        return prescriptions;
    }

    @Override
    public Prescription update(Prescription obj) {
        String queryUpdateNSS = "UPDATE APIPRESCRIPTION SET dateprescription = ? WHERE ID_MEDECIN = ?";

        try (PreparedStatement preparedStatementUpdate = dbConnect.prepareStatement(queryUpdateNSS)) {
            preparedStatementUpdate.setDate(1, Date.valueOf(obj.getDatePrescription()));

            int n = preparedStatementUpdate.executeUpdate();

            return obj;
        } catch (SQLException e) {
            logger.error("erreur update :" + e);
        }

        return null;
    }

    public List<Infos> getInfosFromPrescription(Prescription prescription) {
        String query = "SELECT * FROM INFOS_PRES WHERE id_prescription = ?";

        List<Infos> infos = new ArrayList<>();

        try (PreparedStatement preparedStatement = dbConnect.prepareStatement(query)
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

                    Medicament medicament = new Medicament.MedicamentBuilder()
                            .setId(medId)
                            .setCode(code)
                            .setNom(nom)
                            .setDescription(description)
                            .setPrixUnitaire(prixUnitaire)
                            .build();

                    Infos info = new Infos.InfosBuilder()
                            .setQuantite(quantite)
                            .setMedicament(medicament)
                            .setPrescription(prescription)
                            .build();

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
