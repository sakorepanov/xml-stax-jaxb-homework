import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import domain.Product;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class Main {

    static String xmlPath = "src/main/resources/item-good-test.xml";
    static String newXmlPath = "src/main/resources/item-good-test1.xml";
    static String jsonPath = "src/main/resources/item-good-test.json";
    static String jsonString;

    public static void main(String[] args) throws Exception {


        xmlToObject(xmlPath);
        xmlToJson();
        jsonToXml();
    }

    public static void xmlToObject(String filename) throws Exception {
        // Create XMLStreamReader
        XMLInputFactory xif = XMLInputFactory.newFactory();
        StreamSource xml = new StreamSource(filename);
        XMLStreamReader xsr = xif.createXMLStreamReader(xml);

        // Advance the XMLStreamReader to the item element
        xsr.nextTag();
        while (!xsr.getLocalName().equals("item")) {
            xsr.nextTag();
        }

        // Unmarshal an instance of Product from the XMLStreamReader
        JAXBContext jc = JAXBContext.newInstance(Product.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        JAXBElement<Product> jb = unmarshaller.unmarshal(xsr, Product.class);
        xsr.close();
        Product product = jb.getValue();

        System.out.println(product.toString());
    }

    public static void xmlToJson() throws XMLStreamException, IOException, TransformerException {
        // Create XMLStreamReader
        XMLInputFactory xif = XMLInputFactory.newFactory();
        StreamSource xml = new StreamSource(xmlPath);
        XMLStreamReader xsr = xif.createXMLStreamReader(xml);

        // Convert XMLStreamReader to String with one line xml
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        StringWriter stringWriter = new StringWriter();
        transformer.transform(new StAXSource(xsr), new StreamResult(stringWriter));
        String fileString = stringWriter.toString();

        // Mapping xmlString to jsonString
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode productXml = xmlMapper.readTree(fileString.getBytes());
        ObjectMapper mapper = new ObjectMapper();
        jsonString = mapper.writeValueAsString(productXml);

        // Write json file
        FileWriter writer = new FileWriter(jsonPath);
        writer.write(jsonString);
        writer.flush();
        System.out.println(jsonString);
    }

    public static void jsonToXml() throws IOException {

        // Map json to JsonNode(treeModel)
        ObjectMapper mapper = new ObjectMapper();
        JsonNode productJson = mapper.readTree(jsonString.getBytes());

        // Map JsonNode(treeModel) to xml
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JaxbAnnotationModule());
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
        String xml = xmlMapper.writeValueAsString(productJson);

        // Write xml file
        FileWriter writer = new FileWriter(newXmlPath);
        writer.write(xml);
        writer.flush();
        System.out.println(xml);


    }
}