package PO43.Ivanova.wdad.learn.rmi;

import java.io.Serializable;
import java.util.List;
/**
 * Created by Ирина on 30.10.2016.
 */
public class Department implements Serializable {
    String name;
    List<Employee> employees;
    public Department(String name, List<Employee> employees){
        this.name=name;
        this.employees=employees;
    }
    public Department(String name){
        new Department(name,null);
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){return name;
    }
    public void setEmployees(List<Employee> employees){
        this.employees=employees;
    }
    public List<Employee> getEmployees(){
        return employees;
    }
    public void addEmployee(Employee employee){
        employees.add(employee);
    }
    public void addEmployees(List<Employee> employees){
        for (int i=0;i<employees.size();i++){
            addEmployee(employees.get(i));
        }
    }
}
