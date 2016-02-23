import org.sql2o.*;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Patient {
  private int id;
  private String name;
  private LocalDate birthdate; // will be like this: 1988-10-27
  private int doctorId;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public LocalDate getBirthdate() {
    return birthdate;
  }

  public Patient(String name, String birthdate, int doctorId) {
    this.name = name;
    this.birthdate = birthdate;
    this.doctorId = doctorId;
  }

}
