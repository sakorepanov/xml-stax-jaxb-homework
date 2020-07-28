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
import javax.xml.transform.stream.StreamSource;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataConverterImpl implements DataConverter {

    static String xmlPath = "src/main/resources/item-good-test.xml";
    static String newXmlPath = "src/main/resources/item-good-test1.xml";
    static String jsonPath = "src/main/resources/item-good-test.json";
    static String jsonString;

    @Override
    public void xmlToObject(String filename) throws Exception {
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

    @Override
    public void xmlToJson() throws IOException {
        //Xml file to String
        String file = new String(Files.readAllBytes(Paths.get(xmlPath)));

        // Mapping xmlString to jsonString
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode productXml = xmlMapper.readTree(file.getBytes());
        ObjectMapper mapper = new ObjectMapper();
        jsonString = mapper.writeValueAsString(productXml);

        // Write json file
        FileWriter writer = new FileWriter(jsonPath);
        writer.write(jsonString);
        writer.flush();
        System.out.println(jsonString);

    }

    @Override
    public void jsonToXml() throws IOException {

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
