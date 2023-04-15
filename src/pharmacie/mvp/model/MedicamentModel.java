package pharmacie.mvp.model;

import myconnections.DBConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pharmacie.metier.Medicament;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicamentModel implements DAO<Medicament> {

    private final Connection dbConnect;

    private static final Logger logger = LogManager.getLogger(MedicamentModel.class);

    public MedicamentModel() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            logger.error("erreur de connexion");
            System.exit(1);
        }

        logger.info("connexion établie");
    }

    @Override
    public Medicament add(Medicament medicament) {
        String queryInsert = "INSERT INTO APIMEDICAMENT(code, nom, description, prixunitaire) VALUES (?, ?, ?, ?)";
        String querySelectID = "SELECT ID_MEDICAMENT FROM APIMEDICAMENT WHERE code = ?";

        try (PreparedStatement preparedStatementInsert = dbConnect.prepareStatement(queryInsert);
             PreparedStatement preparedStatementSelect = dbConnect.prepareStatement(querySelectID);
        ) {
            preparedStatementInsert.setString(1, medicament.getCode());
            preparedStatementInsert.setString(2, medicament.getNom());
            preparedStatementInsert.setString(3, medicament.getDescription());
            preparedStatementInsert.setDouble(4, medicament.getPrixUnitaire());

            int n = preparedStatementInsert.executeUpdate();

            if (n == 1) {
                preparedStatementSelect.setString(1, medicament.getCode());

                ResultSet rs = preparedStatementSelect.executeQuery();

                if (rs.next()) {
                    int idMedicament = rs.getInt(1);

                    return new Medicament(idMedicament, medicament.getCode(), medicament.getNom(), medicament.getDescription(), medicament.getPrixUnitaire());
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
    public Medicament read(int id) {
        String querySelect = "SELECT * FROM APIMEDICAMENT WHERE id_medicament = ?";

        try (PreparedStatement statementSelect = dbConnect.prepareStatement(querySelect)) {
            statementSelect.setInt(1, id);

            ResultSet rs = statementSelect.executeQuery();

            if (rs.next()) {
                String code = rs.getString("code");
                String nom = rs.getString("nom");
                String description = rs.getString("description");
                double prixUnitaire = rs.getDouble("prixunitaire");

                Medicament medicament = new Medicament(id, code, nom, description, prixUnitaire);

                return medicament;
            }

        } catch (SQLException e) {
            logger.error("erreur getAll :" + e);
        }

        return null;
    }

    @Override
    public boolean remove(Medicament medicament) {
        String queryDelete = "DELETE FROM APIMEDICAMENT WHERE ID_MEDICAMENT = ?";

        try (PreparedStatement preparedStatementDelete = dbConnect.prepareStatement(queryDelete)) {
            preparedStatementDelete.setInt(1, medicament.getId());

            int n = preparedStatementDelete.executeUpdate();

            return n != 0;
        } catch (SQLException e) {
            logger.error("erreur remove :" + e);
        }

        return false;
    }

    @Override
    public List<Medicament> getAll() {
        List<Medicament> medicaments = new ArrayList<>();

        String querySelect = "SELECT * FROM APIMEDICAMENT";

        try (Statement statementSelect = dbConnect.createStatement()) {
            ResultSet rs = statementSelect.executeQuery(querySelect);

            while (rs.next()) {
                int idMedicament = rs.getInt("id_medicament");
                String code = rs.getString("code");
                String nom = rs.getString("nom");
                String description = rs.getString("description");
                double prixUnitaire = rs.getDouble("prixunitaire");

                Medicament medicament = new Medicament(idMedicament, code, nom, description, prixUnitaire);
                medicaments.add(medicament);
            }

        } catch (SQLException e) {
            logger.error("erreur getAll :" + e);
        }

        return medicaments;
    }

    @Override
    public Medicament update(Medicament medicament, String key, Object value) {
        String queryUpdateNSS = "UPDATE APIMEDICAMENT SET " + key.toUpperCase() + " = ? WHERE ID_MEDICAMENT = ?";

        try (PreparedStatement preparedStatementUpdate = dbConnect.prepareStatement(queryUpdateNSS)) {
            preparedStatementUpdate.setObject(1, value);
            preparedStatementUpdate.setInt(2, medicament.getId());

            int n = preparedStatementUpdate.executeUpdate();

            return medicament;
        } catch (SQLException e) {
            logger.error("erreur update :" + e);
        }

        return null;
    }

}
