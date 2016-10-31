package PO43.Ivanova.wdad.learn.rmi;

import PO43.Ivanova.wdad.learn.xml.XmlTask;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by Ирина on 30.10.2016.
 */
public class XmlDataManagerImpl implements XmlDataManager {
    // возвращает среднюю заработную плату сотрудников организации.
    XmlTask xmlTask = new XmlTask();
    public XmlDataManagerImpl() throws IOException, SAXException, ParserConfigurationException {
    }
    public int salaryAverage() throws IOException, SAXException, ParserConfigurationException {
        int salaryAve=xmlTask.salaryAverage();
    return salaryAve;
    }
    // возвращает среднюю заработную плату сотрудников заданного департамента.
    public int salaryAverage(String departmentName) throws IOException, SAXException, ParserConfigurationException {
        int salaryAve=xmlTask.salaryAverage(departmentName);
        return salaryAve;
    }
    // изменяет должность сотрудника.
    public void setJobTitle(Employee employee, JobTitle newJobTitle) throws TransformerException {
        String firstName=employee.getFirstName();
        String secondName=employee.getSecondName();
        String jobTitle=newJobTitle.name();
        xmlTask.setJobTitle(firstName, secondName, jobTitle);
    }
    //изменяет размер заработной платы сотрудника.
    public void setSalary(Employee employee, int newSalary) throws TransformerException {
        String firstName=employee.getFirstName();
        String secondName=employee.getSecondName();
        xmlTask.setSalary(firstName, secondName, newSalary);
    }
    //удаляющий информацию о сотруднике.
    public void fireEmployee(Employee employee) throws TransformerException {
        String firstName=employee.getFirstName();
        String secondName=employee.getSecondName();
        xmlTask.fireEmployee(firstName,secondName);
    }

    private Node findDepartment(Document doc, String name) {
        String tagDepartment = "department";
        NodeList departments = doc.getElementsByTagName(tagDepartment);
        String nameDepartment;
        Node dep=null;
        for (int i = 0; i < departments.getLength(); i++) {
            dep = departments.item(i);
            nameDepartment = dep.getNodeValue();
            if (nameDepartment.equals(name)) {
                return dep;
            }
        }
        return dep;
    }
    // добавляющий информацию о департаменте.
    // Если такой департамент уже есть, добавляет (или заменяет) в него информацию по сотрудникам.
    public void add(Department department) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        String name=department.getName();
        List<Employee> employees=department.getEmployees();
        Employee employee;
        String path="C:/Users/User/IdeaProjects/starting-monkey-to-human-path/src/PO43/Ivanova/wdad/learn/xml/Appropriate.xml";
        File file=new File(path);
        Document document= DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
        Node dep=null, node;
        NodeList nodeList, employeeInf;;
        String firstName,secondName,attrFN,attrSN,fN="firstname",sN="secondname",jobTitle;
        NamedNodeMap attr;
        int salaryInList;
        int salary;
        Date date, date1;
        try {
            dep = findDepartment(document, name);
        }catch (NullPointerException ex){
            System.out.println("Департамент не найден!");
        }
        if (!dep.equals(null)){
            nodeList=dep.getChildNodes();
            for (int i=0;i<employees.size();i++){
                employee=employees.get(i);
                firstName=employee.getFirstName();
                secondName=employee.getSecondName();
                for (int j=0;j<nodeList.getLength();j++){
                    node=nodeList.item(i);
                    attr=node.getAttributes();
                    attrFN=attr.getNamedItem(fN).getNodeValue();
                    attrSN=attr.getNamedItem(sN).getNodeValue();
                    if (firstName.equals(attrFN)&& secondName.equals(attrSN)){
                        jobTitle=employee.getJobTitle().name();
                        employeeInf=node.getChildNodes();
                        salaryInList=employee.getSalary();
                        date=employee.getHiredate();
                        if (!jobTitle.equals(employeeInf.item(2).getAttributes().item(0).getNodeValue())){
                            xmlTask.setJobTitle(attrFN,attrSN,jobTitle);
                        }
                        salary=Integer.valueOf(employeeInf.item(1).getNodeValue());
                        if (salaryInList!=salary){
                            xmlTask.setSalary(attrFN,attrSN,salaryInList);
                        }
                        if (!date.equals(employeeInf.item(0).getNodeValue())){

                        }
                    }
                }
            }
        }
    }

}
