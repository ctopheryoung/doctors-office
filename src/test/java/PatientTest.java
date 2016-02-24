import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import java.util.ArrayList;
import java.util.List;

public class PatientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Patient.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfPatientsAreTheSame() {
   Patient firstPatient = new Patient("Jose", "1990-1-1", 1);
   Patient secondPatient = new Patient("Jose", "1990-1-1", 1);
   assertTrue(firstPatient.equals(secondPatient));
  }

  @Test
  public void save_returnsTrueIfPatientsAreTheSame() {
    Patient testPatient = new Patient("Jose", "1990-1-1", 1);
    testPatient.save();
    assertTrue(Patient.all().get(0).equals(testPatient));
  }

  @Test
  public void save_assisgnsIDToObject() {
    Patient testPatient = new Patient("Jose", "1990-1-1", 1);
    testPatient.save();
    Patient savedPatient = Patient.all().get(0);
    assertEquals(testPatient.getId(), savedPatient.getId());
  }

  @Test
  public void find_findsPatientInDatabase() {
    Patient testPatient = new Patient("Jose", "1990-1-1", 1);
    testPatient.save();
    Patient savedPatient = Patient.find(testPatient.getId());
    assertTrue(testPatient.equals(savedPatient));
  }

  @Test
  public void save_savesDoctorIdIntDB_true() {
    Doctor testDoctor = new Doctor("Dr. House", "Oral Surgeon");
    testDoctor.save();
    Patient testPatient = new Patient("Jose", "1990-1-1", testDoctor.getId());
    testPatient.save();
    Patient savedPatient = Patient.find(testPatient.getId());
    assertEquals(savedPatient.getDoctorId(), testDoctor.getId());
  }
}
