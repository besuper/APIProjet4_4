package pharmacie.designpatterns.builder;

import java.time.LocalDate;

public class Pharmacie {

    public static void main(String[] args) {
        try {
            Prescription p1 = new Prescription.PrescriptionBuilder()
                    .setId(1)
                    .setMedecin(new Medecin.MedecinBuilder().build())
                    .setPatient(new Patient.PatientBuilder().build())
                    .setDatePrescription(LocalDate.now())
                    .build();

            System.out.println(p1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            // ICI throw une exception
            Prescription p2 = new Prescription.PrescriptionBuilder()
                    .setId(1)
                    .setMedecin(new Medecin.MedecinBuilder().build())
                    .setDatePrescription(LocalDate.now())
                    .build();

            System.out.println(p2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
