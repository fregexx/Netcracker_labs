package xml;

import models.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import repositories.PersonRepository;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XMLWorker {

    private static final Logger LOGGER = LogManager.getLogger(XMLWorker.class);
    private static final String PATH = "file.xml";

    public static void toXML(PersonRepository repository) {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        Document doc;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("persons");
            doc.appendChild(rootElement);

            for(Person person: repository) {

                Element node = doc.createElement("person");
                node.setAttribute("id", person.getId().toString());

                Element name = doc.createElement("name");
                name.appendChild(doc.createTextNode(person.getName()));
                node.appendChild(name);

                Element birthdate = doc.createElement("birthdate");
                birthdate.appendChild(doc.createTextNode(person.getDateOfBirth().toString()));
                node.appendChild(birthdate);

                rootElement.appendChild(node);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(PATH));

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(source, result);

            LOGGER.info("Repository data has been sucessfully exported to file " + PATH);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }


    public static PersonRepository fromXML() {

        PersonRepository repository = new PersonRepository();

        File fXmlFile = new File("file.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("person");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String id = eElement.getAttribute("id");
                    String name = eElement.getElementsByTagName("name").item(0).getTextContent();
                    String birthdate = eElement.getElementsByTagName("birthdate").item(0).getTextContent();
                    Person p = new Person(id, name, birthdate);
                    repository.add(p);
                }
            }
            LOGGER.info("Data has been succesfully imported from file " + PATH);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repository;
    }

}
