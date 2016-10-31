package PO43.Ivanova.wdad.learn.rmi;

import java.util.Date;

/**
 * Created by Ирина on 30.10.2016.
 */
public class Employee {
    String firstName;
    String secondName;
    Date hiredate;
    int salary;
    JobTitle jobTitle;
    public Employee(String firstName, String secondName, Date hiredate, int salary, JobTitle jobTitle){
        this.firstName = firstName;
        this.secondName = secondName;
        this.hiredate = hiredate;
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
    public void setHiredate(Date hiredate){
        this.hiredate=hiredate;
    }
    public Date getHiredate(){
        return hiredate;
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
