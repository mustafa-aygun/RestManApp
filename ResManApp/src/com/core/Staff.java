package com.core;

import com.support.Employee;
import com.support.Person;

import java.util.Date;

/**It is our Staff class which extends Person and implements Employee interface.
 */
public class Staff extends Person implements Employee {
    protected Date startDate;

    /** It is our default constructor. Here, I assume if date is not specified, new Date() gives current date to Staff as start date.
     */
    public Staff(){
        super();
        startDate = new Date();
    }

    /** It is our constructor with variables. For variables which belong to super class, I call super class's constructor.
     * @param vID - Our person ID.
     * @param vName - Our person name.
     * @param vGender - Our person gender.
     * @param vDateOfBirth - Our person date of birth.
     * @param vStartDate - Our staff start date.
     */
    public Staff(int vID, String vName, char vGender, Date vDateOfBirth, Date vStartDate){

        super(vID, vName, vGender, vDateOfBirth);

        startDate = vStartDate;
    }

    /** It is simple getter method  for start date.
     * @return - We are returning Date startDate.
     */
    public Date getStartDate(){return startDate;}

    /** It is simple setter method  for start date.
     * @param vStartDate - We are putting it into startDate.
     */
    public void setStartDate(Date vStartDate){startDate = vStartDate;}

    /** It is our printDetails method  which is abstract method and we have to implement it. It is basically prints this Staff Object's details. We will also override it again at
     * Senior and Junior.
     */
    public void printDetails(){
        System.out.println("Here is the staff's details! \n--------------------------------\nStaff Name: " +this.getName() + "\nStaff Gender: " + this.getGender() +
                "\nStaff ID: " + this.getID() + "\nStaff Date of Birth: " + this.getDateOfBirth() + "\nStaff Start Date: " + this.getStartDate());
    }

    /** It is our interface's method base version.
     */
    public double getSalary() {
        return 0;
    }
}
