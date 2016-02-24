import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import java.util.Arrays;
import java.util.List;

public class DoctorTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Doctor.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame() {
    Doctor firstDoctor = new Doctor("Dr. Sir Mix Alot", "Pediatrician");
    Doctor secondDoctor = new Doctor("Dr. Sir Mix Alot", "Pediatrician");
    assertTrue(firstDoctor.equals(secondDoctor));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Doctor myDoctor = new Doctor("Dr. Cool", "ER Doctor");
    myDoctor.save();
    Doctor savedDoctor = Doctor.find(myDoctor.getId());
    assertTrue(myDoctor.equals(savedDoctor));
  }
}
