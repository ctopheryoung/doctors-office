import org.sql2o.*;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Patient {
  private int id;
  private String name;
  private String date_of_birth; // will be like this: 1988-10-27
  private int doctorId;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getBirthDate() {
    return date_of_birth;
  }

  public int getDoctorId() {
    return doctorId;
  }

  public Patient(String name, String date_of_birth, int doctorId) {
    this.name = name;
    this.date_of_birth = date_of_birth;
    this.doctorId = doctorId;
  }

  @Override
  public boolean equals(Object otherPatient) {
    if (!(otherPatient instanceof Patient)) {
      return false;
    } else {
      Patient newPatient = (Patient) otherPatient;
      return this.getName().equals(newPatient.getName()) &&
              this.getId() == newPatient.getId() &&
              this.getDoctorId() == newPatient.getDoctorId() &&
              this.getBirthDate().equals(newPatient.getBirthDate());
    }
  }


  public static List<Patient> all() {
    String sql = "SELECT id, name, date_of_birth, doctorId FROM patients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .executeAndFetch(Patient.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO patients (name, date_of_birth, doctorId) VALUES (:name, :date_of_birth, :doctorId)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("date_of_birth", this.date_of_birth)
        .addParameter("doctorId", this.doctorId)
        .executeUpdate()
        .getKey();
    }
  }

  public static Patient find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM patients where id=:id";
      Patient patient = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Patient.class);
      return patient;
    }
  }



}
