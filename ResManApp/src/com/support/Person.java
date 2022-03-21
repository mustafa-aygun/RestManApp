package com.support;

import java.io.Serializable;
import java.util.Date;

/** It is our abstract person class. We will extends it in Customer and Staff.
 */
public abstract class Person implements Serializable {
    protected int ID;
    protected String name;
    protected char gender;
    protected Date dateOfBirth;

    /** It's our default constructor. Here, I assume dateOfBirth 01/01/2002 as default value which makes person 18 years old.
     *
     */
    public Person() {
        ID = -1;
        name = "Not Specified";
        gender = '!';
        dateOfBirth = new Date(01/01/2002);
    }

    /** It is our constructor with variables.
     * @param vID - Our person ID.
     * @param vName - Our person name.
     * @param vGender - Our person gender.
     * @param vDateOfBirth - Our person date of birth.
     */
    public Person(int vID, String vName, char vGender, Date vDateOfBirth) {
        ID = vID;
        name = vName;
        gender = vGender;
        dateOfBirth = vDateOfBirth;
    }

    /** It is simple getter method  for ID.
     * @return - We are returning integer ID.
     */
    public int getID(){return ID;}
    /** It is simple getter method for name.
     * @return - We are returning String name.
     */
    public String getName(){return name;}
    /** It is simple getter method  for gender.
     * @return - We are returning char gender.
     */
    public char getGender(){return gender;}
    /** It is simple getter method  for date of birth.
     * @return - We are returning Date dateOfBirth.
     */
    public Date getDateOfBirth(){return dateOfBirth;}

    /** It is simple setter method  for ID.
     * @param vID - We are putting it into ID.
     */
    public void setID(int vID){ID = vID;}
    /** It is simple setter method  for name.
     * @param vName - We are putting it into name.
     */
    public void setName(String vName){name = vName;}
    /** It is simple setter method  for gender.
     * @param vGender - We are putting it into gender.
     */
    public void setGender(char vGender){gender = vGender;}
    /** It is simple setter method  for date of birth.
     * @param vDateOfBirth - We are putting it into dateOfBirth.
     */
    public void setDateOfBirth(Date vDateOfBirth){dateOfBirth = vDateOfBirth;}

    /** It is our abstract method to print details. We will override it at Customer and Staff.
     */
    public abstract void printDetails();
}



