package PO43.Ivanova.wdad.learn.rmi;

import PO43.Ivanova.wdad.data.managers.PreferencesManager;
import PO43.Ivanova.wdad.utils.PreferencesConstantManager;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Ирина on 30.10.2016.
 */
public class Client {
    public static void main(String[] args) {
        try {
            PreferencesManager preferencesManager = PreferencesManager.getInstance();
            String getProperty=preferencesManager.getProperty(PreferencesConstantManager.REGISTRY_PORT);
            int port = Integer.valueOf(getProperty);
            String address = preferencesManager.getProperty(PreferencesConstantManager.REGISTRY_ADDRESS);
            Registry registry = LocateRegistry.getRegistry(address, port);
            XmlDataManager xmlDataManager = ((XmlDataManager) registry.lookup("xmlDataManager"));
            Date date;
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            date = format.parse("20-12-2015");
            JobTitle jobTitle = JobTitle.head;
            Employee employee4 = new Employee("Ирина", "Ксеновна", date, 500, jobTitle);
            xmlDataManager.setJobTitle(employee4, JobTitle.secretary);
            date=format.parse("29-04-2007");//29-04-2001
            Employee employee1=new Employee("Александр","Перчиков",date,74185,JobTitle.secretary);//20000
            date=format.parse("01-04-2004");//01-01-2001
            Employee employee2=new Employee("Василий","Васькович",date,50000,JobTitle.secretary);//head
            date=format.parse("01-02-2015");
            Employee employee3=new Employee("Александр","Хулиганов",date,5000,JobTitle.engineer);//01-01-2001
            date=format.parse("20-11-2016");
            jobTitle=JobTitle.manager;
            List<Employee> employees1=new ArrayList<>();
            employees1.add(employee1);
            employees1.add(employee2);
            employees1.add(employee3);
            Employee employee5= new Employee("Кристина","Ринатовна",date,25000,jobTitle);
            Department department1=new Department("Продаж", employees1);
            List<Employee> employees2=new ArrayList<>();
            employees2.add(employee4);
            employees2.add(employee5);
            Department department2=new Department("Финансовый",employees2);
           /* xmlDataManager.setSalary(employee1,100);
            System.out.println(xmlDataManager.salaryAverage());
            System.out.println(xmlDataManager.salaryAverage("Финансовый"));
            System.out.println(employee2.getJobTitle());
            xmlDataManager.setJobTitle(employee2,JobTitle.head);
            employee2.setJobTitle(JobTitle.head);
            //xmlDataManager.setSalary(employee3, 7523951);
            //xmlDataManager.fireEmployee(employee3);*/
           // employees1.remove(employee3);
            xmlDataManager.add(department1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
