package PO43.Ivanova.wdad.learn.rmi;

import PO43.Ivanova.wdad.learn.rmi.Department;
import PO43.Ivanova.wdad.learn.rmi.Employee;
import PO43.Ivanova.wdad.learn.rmi.JobTitle;
import PO43.Ivanova.wdad.learn.rmi.XmlDataManager;
import PO43.Ivanova.wdad.learn.xml.XmlTask;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.List;

public class XmlDataManagerImpl implements XmlDataManager {
    // возвращает среднюю заработную плату сотрудников организации.
    XmlTask xmlTask = new XmlTask();

    public XmlDataManagerImpl() throws IOException, SAXException, ParserConfigurationException {
    }

    public int salaryAverage() throws IOException, SAXException, ParserConfigurationException, RemoteException {
        int salaryAve = xmlTask.salaryAverage();
        return salaryAve;
    }

    // возвращает среднюю заработную плату сотрудников заданного департамента.
    public int salaryAverage(String departmentName) throws IOException, SAXException, ParserConfigurationException, RemoteException {
        int salaryAve = xmlTask.salaryAverage(departmentName);
        return salaryAve;
    }

    // изменяет должность сотрудника.
    public void setJobTitle(Employee employee, JobTitle newJobTitle) throws RemoteException {
        try {
            String firstName = employee.getFirstName();
            String secondName = employee.getSecondName();
            String jobTitle = newJobTitle.name();
            xmlTask.setJobTitle(firstName, secondName, jobTitle);
        } catch (TransformerException e) {
            throw new RemoteException();
        }
    }

    //изменяет размер заработной платы сотрудника.
    public void setSalary(Employee employee, int newSalary) throws TransformerException, RemoteException {
        String firstName = employee.getFirstName();
        String secondName = employee.getSecondName();
        xmlTask.setSalary(firstName, secondName, newSalary);
    }

    //удаляющий информацию о сотруднике.
    public void fireEmployee(Employee employee) throws TransformerException, RemoteException {
        String firstName = employee.getFirstName();
        String secondName = employee.getSecondName();
        xmlTask.fireEmployee(firstName, secondName);
    }

    // добавляющий информацию о департаменте.
    // Если такой департамент уже есть, добавляет (или заменяет) в него информацию по сотрудникам.
    public void add(Department department) throws ParseException, RemoteException {
        String name=department.getName();
        List<Employee> employees=department.getEmployees();
        xmlTask.add(name, employees);
    }

}
