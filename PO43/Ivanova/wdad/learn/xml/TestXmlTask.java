package PO43.Ivanova.wdad.learn.xml;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class TestXmlTask {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        XmlTask xmlTask=new XmlTask();
        System.out.println(xmlTask.salaryAverage());
        System.out.println(xmlTask.salaryAverage("Продаж"));
        xmlTask.setJobTitle("Александр","Перчиков","asd" );
        xmlTask.setSalary("Александр","Перчиков",50000);
        xmlTask.fireEmployee("Александр","Перчиков");
    }
}

