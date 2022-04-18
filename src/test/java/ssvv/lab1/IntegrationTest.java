package ssvv.lab1;
import curent.Curent;
import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import org.junit.jupiter.api.Test;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class IntegrationTest {

    private Service service;

    private StudentXMLRepo studentXMLRepo;
    private TemaXMLRepo temaXMLRepo;
    private NotaXMLRepo notaXMLRepo;

    private static void createSingleXML(String filePath) {
        File xml = new File(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(xml))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" + "<inbox>" + "</inbox>");
            writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initRepositories() {
        this.studentXMLRepo = new StudentXMLRepo("src/test/java/fisiere_test/Studenti.xml");
        this.temaXMLRepo = new TemaXMLRepo("src/test/java/fisiere_test/Teme.xml");
        this.notaXMLRepo = new NotaXMLRepo("src/test/java/fisiere_test/Note.xml");
    }

    @BeforeEach
    void setup() {
        createSingleXML("src/test/java/fisiere_test/Studenti.xml");
        createSingleXML("src/test/java/fisiere_test/Teme.xml");
        createSingleXML("src/test/java/fisiere_test/Note.xml");

        this.initRepositories();
        this.service = new Service(this.studentXMLRepo, new StudentValidator(),
                this.temaXMLRepo, new TemaValidator(),
                this.notaXMLRepo, new NotaValidator(this.studentXMLRepo, this.temaXMLRepo));
    }

    private static void deleteXML() {
        new File("src/test/java/fisiere_test/Studenti.xml").delete();
        new File("src/test/java/fisiere_test/Teme.xml").delete();
        new File("src/test/java/fisiere_test/Note.xml").delete();
    }

    @AfterEach
    void clearXMLFiles() {
        deleteXML();
    }


    @Test
    public void TestAddStudent_ValidStudent() {
        Student newStudent = new Student("1113", "alex", 999, "st@yahoo.com");
        this.service.addStudent(newStudent);
        Assertions.assertEquals(this.service.getAllStudenti().iterator().next(), newStudent);
    }

    @Test
    public void TestAddStudentAndAddAssignment() {
        Student newStudent = new Student("1113", "cristi", 999, "st@yahoo.com");
        this.service.addStudent(newStudent);
        Assertions.assertEquals(this.service.getAllStudenti().iterator().next(), newStudent);
        Tema newTema = new Tema("1", "asd", 1, 1);
        this.service.addTema(newTema);
        Assertions.assertEquals(this.service.getAllTeme().iterator().next(), newTema);
    }

    @Test
    public void TestAddStudentAndAddAssignmentAndAddNota() {
        Student newStudent = new Student("1113", "alex", 999, "st@yahoo.com");
        this.service.addStudent(newStudent);
        Tema newTema = new Tema("3", "asd", 5, 14);
        this.service.addTema(newTema);
        Nota nota = new Nota("1111", "1113", "3", 9, LocalDate.now());
        this.service.addNota(nota, "bun");
        Assertions.assertEquals(this.service.getAllNote().iterator().next(), nota);
    }
}
