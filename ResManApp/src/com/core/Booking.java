package com.core;

import com.support.Order;

import java.io.Serializable;
import java.util.Date;

/**It is our Booking class which keep tracking our bookings. It has a static bookingID to keep track it and provide it as unique.
 */
public class Booking implements Serializable {
    private Date bookingDate;
    private static int helperBookingID = 0;
    private int bookingID;
    private Order bookingOrder;

    /** It is our default constructor. Here, I assume if date is not specified, new Date() gives current date to Booking as booking date. Also, everytime a booking created,
     * I increment my static bookingID by one. With this way, I am aiming having unique bookingIDs. I will generally use this constructor in my program. It is easy to create a booking then
     * set it's date and order if we have any.
     */
    public Booking(){
        helperBookingID++;
        bookingID = helperBookingID;
        bookingDate = new Date();
    }

    /** It is our constructor with variable for booking date. Again, we increment it by one.
     * @param vBookingDate - Our booking booking date.
     */
    public Booking(Date vBookingDate) {
        bookingDate = vBookingDate;
        helperBookingID++;
        bookingID = helperBookingID;

    }

    /** It is our constructor with variables for every instances. Again, we increment it by one.
     * @param vBookingDate - Our booking booking date.
     * @param vBookingOrder - Our booking order.
     */
    public Booking(Date vBookingDate, Order vBookingOrder) {
        bookingDate = vBookingDate;
        helperBookingID++;
        bookingID = helperBookingID;;
        bookingOrder = vBookingOrder;
    }

    /** It is simple getter method for booking date.
     * @return - We are returning Date bookingDate.
     */
    public Date getBookingDate(){return bookingDate;}
    /** It is simple getter method for booking ID.
     * @return - We are returning int bookingID.
     */
    public int getBookingID(){return bookingID;}
    /** It is simple getter method  for booking Order.
     * @return - We are returning Order bookingOrder.
     */
    public Order getBookingOrder(){return bookingOrder;}

    /** It is simple setter method  for booking date.
     * @param vBookingDate - We are putting it into bookingDate.
     */
    public void setBookingDate(Date vBookingDate){bookingDate = vBookingDate;}
    /** It is simple setter method for booking ID. However, I have never use this because it's a static variable and processing with constructor.
     * @param vBookingID - We are putting it into bookingID.
     */
    public void setBookingID(int vBookingID){bookingID = vBookingID;}
    /** It is simple setter method for booking order. However, I have never use this because it's a static variable and processing with constructor.
     * @param vOrder - We are putting it into bookingOrder.
     */
    public void setBookingOrder(Order vOrder){bookingOrder = vOrder;}

    /** It is our method to print booking details. Instead of doing print operation RestManApp Class, I prefer do print and try catch operations here and call this function
     */
    public void printBookingDetails(){
        System.out.println("Booking ID: " + this.getBookingID() +
                "\nBooking Date: " + this.getBookingDate());
        try{
            System.out.println("Order ID: " +this.bookingOrder.getOrderID() + "\nOder Date: " + this.bookingOrder.getOrderDate() +
                    "\nOrder Details: " + this.bookingOrder.getOrderDetails() + "\nOrder Extra Notes: " + this.bookingOrder.getExtraNotes() + "\nOrder Table Number: " + ((InRestrOrder)this.bookingOrder).getTableNumber());
        }catch(NullPointerException e){}
    }
}
