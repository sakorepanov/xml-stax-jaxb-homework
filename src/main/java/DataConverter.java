import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public interface DataConverter {


    void xmlToObject(String filename) throws Exception;

    void xmlToJson() throws IOException;

    void jsonToXml() throws IOException;
}
