package PO43.Ivanova.wdad.learn.xml;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class TestXmlTask {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        XmlTask salaryAverage=new XmlTask();
        System.out.println(salaryAverage.salaryAverage());
        System.out.println(salaryAverage.salaryAverage("Продаж"));
        salaryAverage.setJobTitle("Александр","Перчиков","asd" );
        salaryAverage.setSalary("Александр","Перчиков",50000);
        salaryAverage.fireEmployee("Александр","Перчиков");
    }
}

