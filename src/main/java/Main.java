import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.xml.bind.*;
import javax.xml.stream.*;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    static String xmlPath = "src/main/resources/item-good-test.xml";
    static String newXmlPath = "src/main/resources/item-good-test1.xml";
    static String jsonPath = "src/main/resources/item-good-test.json";

    public static void main(String[] args) throws Exception {


        xmlToObject(xmlPath);
        xmlToJson();
        jsonToXml();
    }

    public static Product xmlToObject(String filename) throws Exception {
        XMLInputFactory xif = XMLInputFactory.newFactory();
        StreamSource xml = new StreamSource(xmlPath);
        XMLStreamReader xsr = xif.createXMLStreamReader(xml);
        xsr.nextTag();
        while (!xsr.getLocalName().equals("items")) {
            xsr.nextTag();
        }

        JAXBContext jc = JAXBContext.newInstance(Product.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        JAXBElement<Product> jb = unmarshaller.unmarshal(xsr, Product.class);
        xsr.close();

        Product product = jb.getValue();

        System.out.println(product.toString());
        return product;
    }

    public static void xmlToJson() throws XMLStreamException, IOException {
        XMLInputFactory xif = XMLInputFactory.newFactory();
        StreamSource xml = new StreamSource(xmlPath);
        XMLStreamReader xsr = xif.createXMLStreamReader(xml);
        XmlMapper xmlMapper = new XmlMapper();
        Product productXml = xmlMapper.readValue(xsr, Product.class);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(productXml);

        FileWriter writer = new FileWriter(jsonPath);
        writer.write(json);
        writer.flush();
        System.out.println(json);
    }

    public static void jsonToXml() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        File file = new File(jsonPath);
        Product productJson = mapper.readValue(file, Product.class);

        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(productJson);
        FileWriter writer = new FileWriter(newXmlPath);
        writer.write(xml);
        writer.flush();
        System.out.println(xml);


    }
}