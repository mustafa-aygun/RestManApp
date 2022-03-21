package com.core;

import java.util.ArrayList;
import java.util.Date;


/**It is our Senior class which extends Staff.
 */
public class Senior extends Staff {
    private int grossSalaryYearly;
    private ArrayList<Junior> responsibleFrom;

    /** It is our default constructor. Here, I prefer give directly zero as sign of not specified.
     */
    public Senior(){
        super();
        responsibleFrom = new ArrayList<Junior>();
        grossSalaryYearly = 0;
    }

    /** It is our constructor with variables except responsible from. For variables which belong to super class, I call super class's constructor. I will generally use this constructor
     * because I do not want to directly specify responsible from.
     * @param vID - Our person ID.
     * @param vName - Our person name.
     * @param vGender - Our person gender.
     * @param vDateOfBirth - Our person date of birth.
     * @param vStartDate - Our staff start date.
     * @param vGrossSalaryYearly - Our senior yearly gross salary.
     */
    public Senior(int vID, String vName, char vGender, Date vDateOfBirth, Date vStartDate, int vGrossSalaryYearly){

        super(vID, vName, vGender, vDateOfBirth, vStartDate);
        responsibleFrom = new ArrayList<Junior>();
        grossSalaryYearly = vGrossSalaryYearly;
    }
    /** It is our constructor with variables. For variables which belong to super class, I call super class's constructor.
     * @param vID - Our person ID.
     * @param vName - Our person name.
     * @param vGender - Our person gender.
     * @param vDateOfBirth - Our person date of birth.
     * @param vStartDate - Our staff start date.
     * @param vGrossSalaryYearly - Our senior yearly gross salary.
     * @param vResponsibleFrom - Our senior responsible from list.
     */
    public Senior(int vID, String vName, char vGender, Date vDateOfBirth, Date vStartDate, int vGrossSalaryYearly, ArrayList<Junior> vResponsibleFrom){

        super(vID, vName, vGender, vDateOfBirth, vStartDate);

        grossSalaryYearly = vGrossSalaryYearly;
        responsibleFrom = new ArrayList<Junior>(vResponsibleFrom);

    }

    /** It is simple getter method for yearly gross salary.
     * @return - We are returning int grossSalaryYearly.
     */
    public int getGrossSalaryYearly(){return grossSalaryYearly;}
    /** It is simple getter method for responsible from list.
     * @return - We are returning Junior ArrayList responsibleFrom.
     */
    public ArrayList<Junior> getResponsibleFrom(){return responsibleFrom;}

    /** It is simpe setter method for yearly gross salary.
     * @param vGrossSalaryYearly - We are putting it into grossSalaryYearly.
     */
    public void setGrossSalaryYearly(int vGrossSalaryYearly){grossSalaryYearly = vGrossSalaryYearly;}
    /** It is simpe setter method for responsible from list.
     * @param vResponsibleFrom - We are putting it into responsibleFrom.
     */
    public void setResponsibleFrom(ArrayList<Junior> vResponsibleFrom){responsibleFrom = vResponsibleFrom;}

    /** It is our method where we override Staff Class's printDetails and also print senior's details.
     */
    @Override public void printDetails(){
        int i;
        super.printDetails();
        System.out.println("Staff Yearly Gross Salary: " + this.getGrossSalaryYearly());
        for(i = 0; i < this.getResponsibleFrom().size(); i++){

                System.out.println((i+1) + ". Responsible Junior Staff Name: " + this.getResponsibleFrom().get(i).getName());

        }
    }

    /** It is our method where we override Staff Class's getSalary and also calculate senior's details.
     */
    @Override
    public double getSalary(){
        Date currentDate = new java.util.Date();
        double monthlySalary;

        int difference = currentDate.getYear()-this.getStartDate().getYear();

        monthlySalary = (this.getGrossSalaryYearly() + (this.getGrossSalaryYearly() * 0.1 * difference)) / 12;
        /*System.out.println("Monthly Salary is: " + monthlySalary);*/
        return monthlySalary;
    }
}
