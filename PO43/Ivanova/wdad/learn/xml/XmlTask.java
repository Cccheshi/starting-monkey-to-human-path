package PO43.Ivanova.wdad.learn.xml;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlTask {
    private String path="C:/Users/User/IdeaProjects/starting-monkey-to-human-path/src/PO43/Ivanova/wdad/learn/xml/Appropriate.xml";
    Document document;

    public XmlTask() throws ParserConfigurationException, IOException, SAXException {
       document= createDocument();
    }
    private Document createDocument() throws ParserConfigurationException, IOException, SAXException {
        File file = new File(path);
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
        return document;
    }
    private int countSalaryAverage(NodeList nodeList) {
        int salary;
        int salarySum = 0;
        int count = 0;
        for (int j = 0; j < nodeList.getLength(); j++) {
            salary = Integer.valueOf(nodeList.item(j).getTextContent());
            salarySum += salary;
            count++;
        }
        return salarySum / count;
    }

    //возвращает среднюю заработную плату сотрудников организации.
    public int salaryAverage() {
        NodeList salaries = document.getElementsByTagName("salary");
        return countSalaryAverage(salaries);
    }

    //возвращает среднюю заработную плату сотрудников заданного департамента.
    public int salaryAverage(String departmentName) {
        NodeList departments = document.getElementsByTagName("department");
        Node department;
        NodeList departmentSalaries = null;
        for (int i = 0; i < departments.getLength(); i++) {
            department = departments.item(i);
            if (department.getAttributes().item(0).getNodeValue().equals(departmentName)) {
                departmentSalaries = ((Element) department).getElementsByTagName("salary");
            }
        }
        return countSalaryAverage(departmentSalaries);
    }

    private Node findEmployee(String firstName, String secondName) {
        String tegEmployee = "employee";
        String attrFirstName = "firstname";
        String attrSecondName = "secondname";
        NodeList employee = document.getElementsByTagName(tegEmployee);
        Node node = null;
        for (int i = 0; i < employee.getLength(); i++) {
            if (employee.item(i).getAttributes().getNamedItem(attrFirstName).getNodeValue().equals(firstName) && employee.item(i).getAttributes().getNamedItem(attrSecondName).getNodeValue().equals(secondName)) {
                node = employee.item(i);
                return node;
            }

        }
        return node;
    }
    private void writeDoc() throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File("C:/Users/User/IdeaProjects/starting-monkey-to-human-path/src/PO43/Ivanova/wdad/learn/xml/Appropriate.xml"));
        transformer.transform(domSource, streamResult);
    }

    // изменяет должность сотрудника.
    public void setJobTitle(String firstName, String secondName, String newJobTitle) throws TransformerException {
        String tegJobTitle = "jobtitle";
        String attrValue="value";
        NodeList employee = findEmployee(firstName, secondName).getChildNodes();
        for (int j = 0; j < employee.getLength(); j++) {
            if (employee.item(j).getNodeName().equals(tegJobTitle)) {
                employee.item(j).getAttributes().getNamedItem(attrValue).setNodeValue(newJobTitle);
            }
        }
        writeDoc();
    }
    //изменяет размер зароботной платы сотрудника.
    public void setSalary(String firstName, String secondName, int newSalary) throws TransformerException {
        String tegSalary = "salary";
        Node employee=findEmployee(firstName,secondName);
        NodeList employeeChild = employee.getChildNodes();
        for (int i = 0; i < employeeChild.getLength(); i++) {
            if (employeeChild.item(i).getNodeName().equals(tegSalary)) {
                employeeChild.item(i).setTextContent(String.valueOf(newSalary));
            }
        }
        writeDoc();
    }

    // удаляющий информацию о сотруднике.
    public void fireEmployee(String firstName, String secondName) throws TransformerException {
        Node employee = findEmployee(firstName, secondName);
        Node parent=employee.getParentNode();
        parent.removeChild(employee);
        writeDoc();
    }
    public void setHireDate(String firstName, String secondName, Date date){
        String tagHiredate="hiredate";
        NodeList employee=document.getElementsByTagName(tagHiredate);
        for (int j = 0; j < employee.getLength(); j++) {
            if (employee.item(j).getNodeName().equals(tagHiredate)) {
                employee.item(j).setNodeValue(String.valueOf(date));
            }
        }
    }
}