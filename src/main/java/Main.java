public class Main {
    public static void main(String[] args) throws Exception {

        DataConverter dataConverter = new DataConverterImpl();

        dataConverter.xmlToObject(DataConverterImpl.xmlPath);
        dataConverter.xmlToJson();
        dataConverter.jsonToXml();
    }
}