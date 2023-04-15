package pharmacie.mvp.model;

import myconnections.DBConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pharmacie.metier.Medecin;

import java.sql.*;
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
        System.out.println(medecin);

        String queryInsert = "INSERT INTO APIMEDECIN(matricule, prenom, tel, nom) VALUES (?, ?, ?, ?)";
        String querySelectID = "SELECT ID_MEDECIN FROM APIMEDECIN WHERE matricule = ?";

        try (PreparedStatement preparedStatementInsert = dbConnect.prepareStatement(queryInsert);
             PreparedStatement preparedStatementSelect = dbConnect.prepareStatement(querySelectID);
        ) {
            preparedStatementInsert.setString(1, medecin.getMatricule());
            preparedStatementInsert.setString(2, medecin.getPrenom());
            preparedStatementInsert.setString(3, medecin.getTel());
            preparedStatementInsert.setString(4, medecin.getNom());

            int n = preparedStatementInsert.executeUpdate();

            if (n == 1) {
                preparedStatementSelect.setString(1, medecin.getMatricule());

                ResultSet rs = preparedStatementSelect.executeQuery();

                if (rs.next()) {
                    int idMedecin = rs.getInt(1);

                    return new Medecin(idMedecin, medecin.getMatricule(), medecin.getNom(), medecin.getPrenom(), medecin.getTel());
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
    public Medecin read(int id) {
        // TODO: implement

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