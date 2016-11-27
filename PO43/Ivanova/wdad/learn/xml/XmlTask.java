package PO43.Ivanova.wdad.learn.xml;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import PO43.Ivanova.wdad.learn.rmi.Department;
import PO43.Ivanova.wdad.learn.rmi.Employee;
import PO43.Ivanova.wdad.learn.rmi.JobTitle;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class XmlTask {
    private String path = "C:/Users/User/IdeaProjects/starting-monkey-to-human-path/src/PO43/Ivanova/wdad/learn/xml/Appropriate.xml";
    Document document;

    public XmlTask() throws ParserConfigurationException, IOException, SAXException {
        document = createDocument();
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

    private void writeDoc() throws RemoteException {
        try {

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("C:/Users/User/IdeaProjects/starting-monkey-to-human-path/src/PO43/Ivanova/wdad/learn/xml/Appropriate.xml"));
            transformer.transform(domSource, streamResult);
        } catch (TransformerException ex) {
            throw new RemoteException();
        }
    }

    // изменяет должность сотрудника.
    public void setJobTitle(String firstName, String secondName, String newJobTitle) throws TransformerException, RemoteException {
        String tegJobTitle = "jobtitle";
        String attrValue = "value";
        NodeList employee = findEmployee(firstName, secondName).getChildNodes();
        for (int j = 0; j < employee.getLength(); j++) {
            if (employee.item(j).getNodeName().equals(tegJobTitle)) {
                employee.item(j).getAttributes().getNamedItem(attrValue).setNodeValue(newJobTitle);
            }
        }
        writeDoc();
    }

    //изменяет размер зароботной платы сотрудника.
    public void setSalary(String firstName, String secondName, int newSalary) throws TransformerException, RemoteException {
        String tegSalary = "salary";
        Node employee = findEmployee(firstName, secondName);
        NodeList employeeChild = employee.getChildNodes();
        for (int i = 0; i < employeeChild.getLength(); i++) {
            if (employeeChild.item(i).getNodeName().equals(tegSalary)) {
                employeeChild.item(i).setTextContent(String.valueOf(newSalary));
            }
        }
        writeDoc();
    }
    // удаляющий информацию о сотруднике.
    public void fireEmployee(String firstName, String secondName) throws TransformerException, RemoteException {
        Node employee = findEmployee(firstName, secondName);
        Node parent = employee.getParentNode();
        parent.removeChild(employee);
        writeDoc();
    }
    private Node findDepartment(String departmentName) {
        String tagDepartment = "department";
        NodeList departments = document.getElementsByTagName(tagDepartment);
        String departmentNameInXml;
        Node department;
        for (int i = 0; i < departments.getLength(); i++) {
            department = departments.item(i);
            departmentNameInXml = department.getAttributes().getNamedItem("name").getNodeValue();
            if (departmentName.equals(departmentNameInXml)) {
                return department;
            }
        }
        return null;
    }

    private void createEmployee(Node employeeInXml, Employee employee) throws ParseException, RemoteException {
        Date dateInXml;
        Date hireDateInList = employee.getHireDate();
        int salaryInList = employee.getSalary();
        JobTitle attrJobTitleInList = employee.getJobTitle();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String hireDateInXml;
        int salaryInXml;
        String attrJobTitleInXml;
        Node attr;
        Element element = (Element) employeeInXml;
        Node hireDate = element.getElementsByTagName("hiredate").item(0), jobTitle = element.getElementsByTagName("jobtitle").item(0),
                salary = element.getElementsByTagName("salary").item(0);
        hireDateInXml = hireDate.getTextContent();
        dateInXml = simpleDateFormat.parse(hireDateInXml);
        if (!dateInXml.equals(hireDateInList)) {
            hireDate.setTextContent(simpleDateFormat.format(hireDateInList));
        }
        attr = jobTitle.getAttributes().getNamedItem("value");
        attrJobTitleInXml = attr.getNodeValue();
        if (!attrJobTitleInXml.equals(attrJobTitleInList)) {
            attr.setTextContent(attrJobTitleInXml);
        }
        salaryInXml = Integer.valueOf(salary.getTextContent());
        if (salaryInXml != salaryInList) {
           salary.setTextContent(String.valueOf(salaryInList));
        }
    }
    public  void add(String departmentName, List<Employee> employees) throws ParseException, RemoteException {
            NodeList employeesInXml;
            Employee employee;
            Node employeeInXml;
            String fName, sName,fNameInXml,sNameInXml;
            NamedNodeMap attr;
        Element element;
            Node departmentInXml= findDepartment(departmentName);
            if (!departmentInXml.equals(null)){
                element=(Element)departmentInXml;
                employeesInXml=element.getElementsByTagName("employee");
                for (int i=0;i<employees.size();i++){
                    employee=employees.get(i);
                    fName=employee.getFirstName();
                    sName=employee.getSecondName();
                    for (int j=0;j<employeesInXml.getLength();j++){
                        employeeInXml=employeesInXml.item(j);
                        attr=employeeInXml.getAttributes();
                        sNameInXml=attr.getNamedItem("secondname").getNodeValue();
                        fNameInXml=attr.getNamedItem("firstname").getNodeValue();
                        if (fName.equals(fNameInXml)&& sName.equals(sNameInXml)){
                            createEmployee(employeeInXml,employee);
                        } else {
                            System.out.println("Сотрудник не найден: "+fName+" "+sName);
                        }
                    }

                }
            }else {
                System.out.println("Департамент не найден");
            }
            writeDoc();

        }
    }