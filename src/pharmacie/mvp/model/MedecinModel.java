package pharmacie.mvp.model;

import myconnections.DBConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pharmacie.designpatterns.builder.Medecin;

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
        try (CallableStatement cs = dbConnect.prepareCall("call APIINSERERMEDECIN(?, ?, ?, ?, ?)")) {
            cs.setString(1, medecin.getMatricule());
            cs.setString(2, medecin.getPrenom());
            cs.setString(3, medecin.getTel());
            cs.setString(4, medecin.getNom());

            cs.registerOutParameter(5, Types.INTEGER);

            cs.execute();

            int idMedecin = cs.getInt(5);

            return new Medecin.MedecinBuilder()
                    .setId(idMedecin)
                    .setMatricule(medecin.getMatricule())
                    .setNom(medecin.getNom())
                    .setPrenom(medecin.getPrenom())
                    .setTel(medecin.getTel())
                    .build();
        } catch (SQLException e) {
            logger.error("erreur ajout :" + e);
        }

        return null;
    }

    @Override
    public Medecin read(int id) {
        String query = "SELECT * FROM APIMEDECIN WHERE id_medecin = ?";

        try (PreparedStatement preparedStatement = dbConnect.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String matricule = rs.getString("matricule");
                String tel = rs.getString("tel");
                String nom_medecin = rs.getString("nom");
                String prenom_medecin = rs.getString("prenom");

                return new Medecin.MedecinBuilder()
                        .setId(id)
                        .setMatricule(matricule)
                        .setNom(nom_medecin)
                        .setPrenom(prenom_medecin)
                        .setTel(tel)
                        .build();
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

                Medecin medecin = new Medecin.MedecinBuilder()
                        .setId(idMedecin)
                        .setMatricule(matricule)
                        .setNom(nom)
                        .setPrenom(prenom)
                        .setTel(tel)
                        .build();

                medecins.add(medecin);
            }

        } catch (SQLException e) {
            logger.error("erreur getAll :" + e);
        }

        return medecins;
    }

    @Override
    public Medecin update(Medecin obj) {
        String queryUpdateNSS = "UPDATE APIMEDECIN SET matricule = ?, nom = ?, prenom = ?, tel = ? WHERE ID_MEDECIN = ?";

        try (PreparedStatement preparedStatementUpdate = dbConnect.prepareStatement(queryUpdateNSS)) {
            preparedStatementUpdate.setString(1, obj.getMatricule());
            preparedStatementUpdate.setString(2, obj.getNom());
            preparedStatementUpdate.setString(3, obj.getPrenom());
            preparedStatementUpdate.setString(4, obj.getTel());
            preparedStatementUpdate.setInt(5, obj.getId());

            int n = preparedStatementUpdate.executeUpdate();

            return obj;
        } catch (SQLException e) {
            logger.error("erreur update :" + e);
        }

        return null;
    }

}
