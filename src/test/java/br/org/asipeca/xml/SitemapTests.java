package br.org.asipeca.xml;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SitemapTests {

    @Test
    public void test00_TouchFile() throws IOException {
        FileReader fr = new FileReader("src/main/resources/templates/sitemap.xml");
        String xmlContent = fr.toString();
        System.out.println(xmlContent);
        fr.close();
    }

    @Test
    public void test01_LerXml_GerarListaDeUrl() throws ParserConfigurationException, SAXException, IOException {
        List<String> urls = new ArrayList<>(); 
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("src/main/resources/templates/sitemap.xml"));

        // Element root = document.getDocumentElement();
        NodeList nodes = document.getElementsByTagName("loc");
        for(int i = 0 ; i < nodes.getLength() ; i++ ) {
            Node node = nodes.item(i);
            Element e = (Element) node;
            String nodeValue = e.getTextContent();
            System.out.println(nodeValue);
            urls.add(nodeValue);
        }
        
        assertEquals(10, urls.size());
    }

    @Test
    public void test02_LerXml_GerarListaDeUrls_GerarCodigoFonte() throws ParserConfigurationException, SAXException, IOException {
        List<String> urls = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("src/main/resources/templates/sitemap.xml"));

        // Element root = document.getDocumentElement();
        NodeList nodes = document.getElementsByTagName("loc");
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            Element e = (Element) node;
            String nodeValue = e.getTextContent();
            urls.add(nodeValue);
        }

        for(String link : urls) {
            String localhostLink = link.replaceAll("https://asipeca.org.br", "http://localhost:3000");
            URL url = new URL(localhostLink);
            InputStream is = url.openStream();

            StringBuilder textBuilder = new StringBuilder();
            try (Reader reader = new BufferedReader(
                    new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))) {
                int c = 0;
                while ((c = reader.read()) != -1) {
                    textBuilder.append((char) c);
                }
            }

            String fileName = link.replaceAll("https://asipeca.org.br", "");
            String fileNameSemBarras = fileName.replaceAll("/", "");
            FileWriter file = new FileWriter("src/main/resources/static-templates/" + fileNameSemBarras + ".html");
            file.write(textBuilder.toString());
            file.flush();
            file.close();
        }
    }
}