package com.core;

import com.support.Order;
import com.support.Person;

import java.util.ArrayList;
import java.util.Date;

/**It is our Customer class which extends Person.
 */
public class Customer extends Person {
    private Date registrationDate;
    private String creditCardDetails;
    private ArrayList<Order> orders;
    private ArrayList<Booking> bookings;

    /** It is our default constructor. Here, I assume if date is not specified, new Date() gives current date to Customer as registration date.
     * I will use generally default constructor because instance of creating temp variables, I will directly call function to set variables.
     */
    public Customer(){
        super();
        orders = new ArrayList<Order>();
        bookings = new ArrayList<Booking>();
        registrationDate = new Date();
        creditCardDetails = "not specified";
    }

    /** It is our constructor with variables. For variables which belong to super class, I call super class's constructor. For others, I give my inputs to them.
     * @param vID - Our person ID.
     * @param vName - Our person name.
     * @param vGender - Our person gender.
     * @param vDateOfBirth - Our person date of birth.
     * @param vRegistrationDate - Our customer registration date.
     * @param vOrders - Our customer orders.
     * @param vCreditCardDetails - Our customer credit card details.
     * @param vBookings - Our customer bookings.
     */
    public Customer(int vID, String vName, char vGender, Date vDateOfBirth, Date vRegistrationDate, ArrayList<Order> vOrders, String vCreditCardDetails, ArrayList<Booking> vBookings){

        super(vID, vName, vGender, vDateOfBirth);

        registrationDate = vRegistrationDate;
        creditCardDetails = vCreditCardDetails;
        orders = new ArrayList<Order>(vOrders);
        bookings = new ArrayList<Booking>(vBookings);
    }

    /** It is simple getter method for registration date.
     * @return - We are returning Date registrationDate.
     */
    public Date getRegistrationDate(){return registrationDate;}
    /** It is simple getter method  for credit card details.
     * @return - We are returning String creditCardDetails.
     */
    public String getCreditCardDetails(){return creditCardDetails;}
    /** It is simple getter method  for orders.
     * @return - We are returning Order arraylist orders.
     */
    public ArrayList<Order> getOrders(){return orders;}
    /** It is simple getter method  for bookings.
     * @return - We are returning Booking arraylist bookings.
     */
    public ArrayList<Booking> getBookings(){return bookings;}

    /** It is simple setter method for registration date.
     * @param vRegistrationDate - We are putting it into registrationDate.
     */
    public void setRegistrationDate(Date vRegistrationDate){registrationDate = vRegistrationDate;}
    /** It is simple setter method  for credit card details.
     * @param vCreditCardDetails - We are putting it into creditCardDetails.
     */
    public void setCreditCardDetails(String vCreditCardDetails){creditCardDetails = vCreditCardDetails;}
    /** It is simple setter method  for orders.
     * @param vOrders - We are putting it into orders.
     */
    public void setOrders(ArrayList<Order> vOrders){orders = new ArrayList<Order>(vOrders);}
    /** It is simple setter method for bookings.
     * @param vBookings - We are putting it into bookings.
     */
    public void setBookings(ArrayList<Booking> vBookings){bookings = new ArrayList<Booking>(vBookings);}

    /** It is our printDetails method  which is abstract method and we have to implement it. It is basically prints this Customer Object's details.
     */
    public void printDetails(){
        System.out.println("Here is the Customer's details! \n--------------------------------\nCustomer Name: " +this.getName() + "\nCustomer Gender: " + this.getGender() +
                "\nCustomer ID: " + this.getID() + "\nCustomer Date of Birth: " + this.getDateOfBirth() + "\nCustomer Registration Date: " + this.getRegistrationDate() +
                "\nCustomer's Credit Card Details: " + this.getCreditCardDetails());
    }

}
