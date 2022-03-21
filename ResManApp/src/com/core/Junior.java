package com.core;

import java.util.Date;

/**It is our Junior class which extends Staff.
 */
public class Junior extends Staff {
    private int monthlySalary;
    private Date expectedEndDate;

    /** It is our default constructor. I assume default expected end date the beginning of next year and for the salary, I prefer give directly zero as sign of not specified.
     */
    public Junior(){
        super();
        monthlySalary = 0;
        expectedEndDate = new Date(01/01/2021);
    }

    /** It is our constructor with variables. For variables which belong to super class, I call super class's constructor.
     * @param vID - Our person ID.
     * @param vName - Our person name.
     * @param vGender - Our person gender.
     * @param vDateOfBirth - Our person date of birth.
     * @param vStartDate - Our staff start date.
     * @param vMonthlySalary - Our junior monthly salary.
     * @param vExpectedEndDate - Our junior expected end date.
     */
    public Junior(int vID, String vName, char vGender, Date vDateOfBirth, Date vStartDate, int vMonthlySalary, Date vExpectedEndDate){

        super(vID, vName, vGender, vDateOfBirth, vStartDate);

        monthlySalary = vMonthlySalary;
        expectedEndDate = vExpectedEndDate;
    }

    /** It is simple getter method for monthly salary.
     * @return - We are returning int monthlySalary.
     */
    public int getMonthlySalary(){return monthlySalary;}
    /** It is simple getter method for expected end date.
     * @return - We are returning Date expectedEndDate.
     */
    public Date getExpectedEndDate(){return expectedEndDate;}

    /** It is simple setter method  for monthly salary.
     * @param vMonthlySalary - We are putting it into monthlySalary.
     */
    public void setMonthlySalary(int vMonthlySalary){monthlySalary = vMonthlySalary;}
    /** It is simple setter method  for expected end date.
     * @param vExpectedEndDate - We are putting it expectedEndDate.
     */
    public void setExpectedEndDate(Date vExpectedEndDate){expectedEndDate = vExpectedEndDate;}

    /** It is our method where we override Staff Class's printDetails and also print junior's details.
     */
    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Staff Monthly Salary: " + this.getMonthlySalary() + "\nStaff Expected End Date: " + this.getExpectedEndDate());
    }

    /** It is our method where we override Staff Class's getSalary and also print junior's details.
     */
    @Override
    public double getSalary(){
        /*System.out.println("Monthly Salary is: " + this.getMonthlySalary());*/
        return this.getMonthlySalary();
    }
}
