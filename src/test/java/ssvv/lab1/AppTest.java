package ssvv.lab1;

import domain.Student;
import org.junit.*;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    static Service service;

    @BeforeClass
    public static void initialize() {
        StudentValidator studentValidator = new StudentValidator();
        String studenti = "fisiere/Studenti.xml";
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(studenti);

        // Declared for service constructor
        String teme = "fisiere/Teme.xml";
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(teme);
        String note = "fisiere/Note.xml";
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(note);

        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    @AfterClass
    public static void clearRepo() {
        service.deleteStudent("999");
        service.deleteStudent("998");
        service.deleteStudent("997");
    }

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testAddStudent1() {
        Student student = new Student("999", "boro", 931, "boro@yahoo.com");
        assertNull(service.addStudent(student));
    }

    @Test
    public void testAddStudent2() {
        // Duplicate
        Student student = new Student("999", "boro", 931, "boro@yahoo.com");
        assertNotNull(service.addStudent(student));
    }

    @Test
    public void testAddStudent3() {
        // Validation Error
        Student student = new Student("", "boro", 931, "boro@yahoo.com");
        Exception exception = assertThrows(ValidationException.class, ()->{service.addStudent(student);});

        String expectedMessage = "Id incorect!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testAddStudent4() {
        // Validation Error
        Student student = new Student("999", "", 931, "boro@yahoo.com");
        Exception exception = assertThrows(ValidationException.class, ()->{service.addStudent(student);});

        String expectedMessage = "Nume incorect!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testAddStudent5() {
        // Validation Error
        Student student = new Student("999", "boro", -1, "boro@yahoo.com");
        Exception exception = assertThrows(ValidationException.class, ()->{service.addStudent(student);});

        String expectedMessage = "Grupa incorecta!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testAddStudent6() {
        // Validation Error
        Student student = new Student("999", "boro", 931, null);
        Exception exception = assertThrows(ValidationException.class, ()->{service.addStudent(student);});

        String expectedMessage = "Email incorect!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testAddStudent7() {
        // Validation Error
        Student student = new Student("999", null, 931, "boro@yahoo.com");
        Exception exception = assertThrows(ValidationException.class, ()->{service.addStudent(student);});

        String expectedMessage = "Nume incorect!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testAddStudent8() {
        // Validation Error
        Student student = new Student("999", "boro", 931, "");
        Exception exception = assertThrows(ValidationException.class, ()->{service.addStudent(student);});

        String expectedMessage = "Email incorect!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testAddStudent9() {
        // BVA
        Student student = new Student("998", "boro", 0, "boro@yahoo.com");
        assertNull(service.addStudent(student));
    }

    @Test
    public void testAddStudent10() {
        // BVA
        Student student = new Student("997", "boro", 1, "boro@yahoo.com");
        assertNull(service.addStudent(student));
    }

}
