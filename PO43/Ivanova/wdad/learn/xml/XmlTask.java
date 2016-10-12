package PO43.Ivanova.wdad.learn.xml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class XmlTask {
    Document document;
    String path="C:/Users/User/IdeaProjects/starting-monkey-to-human-path/src/PO43/Ivanova/wdad/learn/xml/Appropriate.xml";

    public XmlTask() throws ParserConfigurationException, IOException, SAXException {
        File file = new File(path);
        document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
    }

    private int countSalaryAverage(NodeList nodeList){
        int salary=0;
        int salarySum=0;
        for (int j = 0; j < nodeList.getLength(); j++) {
            try {
                salary = Integer.valueOf(nodeList.item(j).getTextContent());
                salarySum += salary;
        }catch (NumberFormatException e){
                System.out.println("Не верный формат числа");
                return 0;
            }
        }
        return salarySum / nodeList.getLength();
    }

    //возвращает среднюю заработную плату сотрудников организации.
    public int salaryAverage() {
        return average (document.getDocumentElement(), "salary");
    }

    private int average(Node node, String param) {
        if (node.getNodeType()==Node.ELEMENT_NODE) {
            NodeList salaries = ((Element) node).getElementsByTagName(param);
            return countSalaryAverage(salaries);
        } else
            return 0;
    }

    //возвращает среднюю заработную плату сотрудников заданного департамента.
    public int salaryAverage(String department) {
        NodeList departments = document.getElementsByTagName("department");
        String attrDepartment="name";
        Node departmentName;
        for (int i = 0; i < departments.getLength(); i++) {
            departmentName = departments.item(i).getAttributes().getNamedItem(attrDepartment);
            if (departmentName.getNodeValue().equals(department)) {
                return average(departments.item(i),"salary");
            }
        }
        return 0;
    }

    private Node findEmployee(String firstName, String secondName) {
        String tegEmployee = "employee";
        String attrFirstName = "firstname";
        String attrSecondName = "secondname";
        NodeList employee = document.getElementsByTagName(tegEmployee);
        Node node = null;
        NamedNodeMap attributes;
        for (int i = 0; i < employee.getLength(); i++) {
           attributes=employee.item(i).getAttributes();
            if (attributes.getNamedItem(attrFirstName).getNodeValue().equals(firstName)
                    && attributes.getNamedItem(attrSecondName).getNodeValue().equals(secondName)) {
                node = employee.item(i);
                return node;
            }

        }
        return null;
    }
    private void writeDoc() throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(path));
        transformer.transform(domSource, streamResult);
    }

    // изменяет должность сотрудника.
    public void setJobTitle(String firstName, String secondName, String newJobTitle) throws TransformerException {
        String tegJobTitle = "jobtitle";
        String attrValue = "value";
        NodeList employee = null;
        try {
            employee = findEmployee(firstName, secondName).getChildNodes();
            for (int j = 0; j < employee.getLength(); j++) {
                if (employee.item(j).getNodeName().equals(tegJobTitle)) {
                    employee.item(j).getAttributes().getNamedItem(attrValue).setNodeValue(newJobTitle);
                }
            }
            writeDoc();
        } catch (NullPointerException e) {
            System.out.println("Сотрудник не найден");
        }

    }
    //изменяет размер зароботной платы сотрудника.
    public void setSalary(String firstName, String secondName, int newSalary) throws TransformerException {
        String tegSalary = "salary";
        NodeList employee = null;
        String answer;
        try {
            employee = findEmployee(firstName, secondName).getChildNodes();
            for (int i = 0; i < employee.getLength(); i++) {
                if (employee.item(i).getNodeName().equals(tegSalary)) {
                    employee.item(i).setTextContent(String.valueOf(newSalary));
                }
            }
            writeDoc();
        } catch (NullPointerException e) {
            System.out.println("Сотрудник не найден");
        }
    }

    // удаляющий информацию о сотруднике.
    public void fireEmployee(String firstName, String secondName) throws TransformerException {
        try {
            Node employee = findEmployee(firstName, secondName);
            Node parent = employee.getParentNode();
            parent.removeChild(employee);
            writeDoc();
        }catch (NullPointerException e){
            System.out.println("Сотрудник не найден");
        }
    }
}