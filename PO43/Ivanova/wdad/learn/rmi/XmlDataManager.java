package PO43.Ivanova.wdad.learn.rmi;

import PO43.Ivanova.wdad.learn.rmi.Department;
import PO43.Ivanova.wdad.learn.rmi.Employee;
import PO43.Ivanova.wdad.learn.rmi.JobTitle;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.ParseException;

/**
 * Created by Ирина on 30.10.2016.
 */
public interface XmlDataManager extends Remote  {
    public int salaryAverage() throws IOException, SAXException, ParserConfigurationException, RemoteException; // возвращает среднюю заработную плату сотрудников организации.
    public int salaryAverage(String departmentName) throws IOException, SAXException, ParserConfigurationException, RemoteException;// возвращает среднюю заработную плату сотрудников заданного департамента.
    public void setJobTitle(Employee employee, JobTitle newJobTitle) throws TransformerException, RemoteException; // изменяет должность сотрудника.
    public void setSalary(Employee employee, int newSalary) throws TransformerException, RemoteException;//изменяет размер заработной платы сотрудника.
    public void fireEmployee(Employee employee) throws TransformerException, RemoteException;//удаляющий информацию о сотруднике.
    public void add(Department department) throws ParseException, RemoteException;// добавляющий информацию о департаменте.
    // Если такой департамент уже есть, добавляет (или заменяет) в него информацию по сотрудникам.
}
