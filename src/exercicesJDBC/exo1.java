package exercicesJDBC;

import myconnections.DBConnection;

import java.sql.*;

public class exo1 {

    public void exo() {
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }

        String query = "SELECT * FROM APIMedecin WHERE nom = ?";

        try (PreparedStatement stmt = dbConnect.prepareStatement(query)) {
            stmt.setString(1, "Goulet");

            ResultSet rs = stmt.executeQuery();

            boolean trouve = false;

            if (rs.next()) {
                trouve = true;

                String prenom = rs.getString("prenom");
                String nom = rs.getString("nom");
                String tel = rs.getString("tel");

                System.out.println("Medecin trouvé " + prenom + " " + nom + " tel " + tel);
            }

            if (!trouve) {
                System.out.println("Médecin introuvable");
            }

        } catch (SQLException e) {
            System.out.println("erreur SQL =" + e);
        }

        DBConnection.closeConnection();
    }

    public static void main(String[] args) {
        exo1 exo = new exo1();
        exo.exo();
    }

}
