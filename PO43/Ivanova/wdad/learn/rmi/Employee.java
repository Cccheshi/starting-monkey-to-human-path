package PO43.Ivanova.wdad.learn.rmi;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ирина on 30.10.2016.
 */
public class Employee implements Serializable {
    String firstName;
    String secondName;
    Date hireDate;
    int salary;
    JobTitle jobTitle;
    public Employee(String firstName, String secondName, Date hireDate, int salary, JobTitle jobTitle){
        this.firstName = firstName;
        this.secondName = secondName;
        this.hireDate = hireDate;
        this.salary=salary;
        this.jobTitle=jobTitle;
    }
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    public String getFirstName(){
        return firstName;
    }
    public void setSecondName(String secondName){
        this.secondName=secondName;
    }
    public String getSecondName(){
        return secondName;
    }
    public void setHireDate(Date hireDate){
        this.hireDate=hireDate;
    }
    public Date getHireDate(){
        return hireDate;
    }
    public void setSalary(int salary){
        this.salary=salary;
    }
    public int getSalary(){
        return salary;
    }
    public void setJobTitle(JobTitle jobTitle){
        this.jobTitle=jobTitle;
    }
    public JobTitle getJobTitle(){
        return jobTitle;
    }
}
