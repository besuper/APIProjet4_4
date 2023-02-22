package exercicesJDBC;

import myconnections.DBConnection;

import java.sql.*;

public class exo2 {

    public void exo() {
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }

        String query = "SELECT * FROM APIMED_PATIENT";

        try (Statement stmt = dbConnect.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String patient = rs.getString("patient");
                String medecin = rs.getString("medecin");

                System.out.println("Patient : " + patient + " medecin : " + medecin);
            }

        } catch (SQLException e) {
            System.out.println("erreur SQL =" + e);
        }

        DBConnection.closeConnection();
    }

    public static void main(String[] args) {
        exo2 exo = new exo2();
        exo.exo();
    }

}
