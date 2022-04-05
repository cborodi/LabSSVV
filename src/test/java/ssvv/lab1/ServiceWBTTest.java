package ssvv.lab1;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;
import org.junit.jupiter.api.Test;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class ServiceWBTTest {


    @AfterAll
    public static void clearFile(){
        try {
            String filename = "src/test/java/fisiere_test/Teme.xml";
            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .newDocument();
            Element root  = document.createElement("inbox");
            document.appendChild(root);

            //write Document to file
            Transformer transformer = TransformerFactory.
                    newInstance().newTransformer();
            transformer.transform(new DOMSource(document),
                    new StreamResult(filename));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void tc7_addAssignment_path5_primireBiggerThan14() {
        Tema tema = new Tema("id1", "tema", 2, 15);

        String filename = "src/test/java/fisiere_test/Teme.xml";
        TemaValidator temaValidator = new TemaValidator();

        TemaXMLRepo temaXMLRepo = new TemaXMLRepo(filename);

        Service service = new Service(null, null, temaXMLRepo, temaValidator, null, null);

        try{
            service.addTema(tema);
            Assertions.fail();
        }
        catch (ValidationException e){
            Assertions.assertTrue(true);
        }
        catch (Exception e){
            Assertions.fail();
        }
    }

    @Test
    public void tc6_addAssignment_path5_primireSmallerThan1() {
        Tema tema = new Tema("id1", "tema", 2, 0);

        String filename = "src/test/java/fisiere_test/Teme.xml";
        TemaValidator temaValidator = new TemaValidator();

        TemaXMLRepo temaXMLRepo = new TemaXMLRepo(filename);

        Service service = new Service(null, null, temaXMLRepo, temaValidator, null, null);

        try{
            service.addTema(tema);
            Assertions.fail();
        }
        catch (ValidationException e){
            Assertions.assertTrue(true);
        }
        catch (Exception e){
            Assertions.fail();
        }
    }

    @Test
    public void tc4_addAssignment_path3_deadlinebiggerthan14() {
        Tema tema = new Tema("id1", "tema", 15, 3);

        String filename = "src/test/java/fisiere_test/Teme.xml";
        TemaValidator temaValidator = new TemaValidator();

        TemaXMLRepo temaXMLRepo = new TemaXMLRepo(filename);

        Service service = new Service(null, null, temaXMLRepo, temaValidator, null, null);

        try{
            service.addTema(tema);
            Assertions.fail();
        }
        catch (ValidationException e){
            Assertions.assertTrue(true);
        }
        catch (Exception e){
            Assertions.fail();
        }
    }

    @Test
    public void tc3_addAssignment_path3_deadlineSmallerThan1() {
        Tema tema = new Tema("id1", "tema", 0, 3);

        String filename = "src/test/java/fisiere_test/Teme.xml";
        TemaValidator temaValidator = new TemaValidator();

        TemaXMLRepo temaXMLRepo = new TemaXMLRepo(filename);

        Service service = new Service(null, null, temaXMLRepo, temaValidator, null, null);

        try{
            service.addTema(tema);
            Assertions.fail();
        }
        catch (ValidationException e){
            Assertions.assertTrue(true);
        }
        catch (Exception e){
            Assertions.fail();
        }
    }

    @Test
    public void tc1_addAssignment_path1() {
        Tema tema = new Tema(null, "descriere", 1, 3);

        String filename = "src/test/java/fisiere_test/Teme.xml";
        TemaValidator temaValidator = new TemaValidator();

        TemaXMLRepo temaXMLRepo = new TemaXMLRepo(filename);

        Service service = new Service(null, null, temaXMLRepo, temaValidator, null, null);

        try{
            service.addTema(tema);
            Assertions.fail();
        }
        catch (ValidationException e){
            Assertions.assertTrue(true);
        }
        catch (Exception e){
            Assertions.fail();
        }
    }

    @Test
    public void tc5_addAssignment_path4_accepted() {
        Tema tema = new Tema("id1", "tema", 6, 3);

        String filename = "src/test/java/fisiere_test/Teme.xml";
        TemaValidator temaValidator = new TemaValidator();

        TemaXMLRepo temaXMLRepo = new TemaXMLRepo(filename);

        Service service = new Service(null, null, temaXMLRepo, temaValidator, null, null);

        try {
            service.addTema(tema);
            Assertions.assertTrue(true);
        }
        catch (Exception e){
            Assertions.fail();
        }
    }

    @Test
    public void tc2_addAssignment_path2() {
        Tema tema = new Tema("id1", "", 1, 3);

        String filename = "src/test/java/fisiere_test/Teme.xml";
        TemaValidator temaValidator = new TemaValidator();

        TemaXMLRepo temaXMLRepo = new TemaXMLRepo(filename);

        Service service = new Service(null, null, temaXMLRepo, temaValidator, null, null);

        try{
            service.addTema(tema);
            Assertions.fail();
        }
        catch (ValidationException e){
            Assertions.assertTrue(true);
        }
        catch (Exception e){
            Assertions.fail();
        }
    }

}

