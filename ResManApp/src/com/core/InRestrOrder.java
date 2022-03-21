package com.core;

import com.support.Order;

import java.util.Date;

/** It is our InRestrOrder class which extends Order.
 */
public class InRestrOrder extends Order {
    private int tableNumber;
    private Booking orderBooking;

    /** It is our default constructor. I prefer to put -1 to table number if it is not specified.
     */
    public InRestrOrder(){
        super();
        tableNumber = -1;
    }

    /** It is our constructor with variables expect table number and order booking. For variables which belong to super class, I call super class's constructor.
     * I will generally use this constructor in my program. It is easy to create a order then set it's table number and booking if we have any.
     * @param vOrderDate - Our order date.
     * @param vOrderDetails - Our order details.
     * @param vExtraNotes - Our order extra notes.
     */
    public InRestrOrder(Date vOrderDate, String vOrderDetails, String vExtraNotes) {

        super(vOrderDate,vOrderDetails,vExtraNotes);

    }

    /** It is our constructor with variables. For variables which belong to super class, I call super class's constructor.
     * @param vOrderDate - Our order date.
     * @param vOrderDetails - Our order details.
     * @param vExtraNotes - Our order extra notes.
     * @param vTableNumber - Our InRestrOrder table number.
     * @param vBookingOrder - Our InRestrOrder booking order.
     */
    public InRestrOrder(Date vOrderDate, String vOrderDetails, String vExtraNotes, int vTableNumber, Booking vBookingOrder) {

        super(vOrderDate,vOrderDetails,vExtraNotes);
        tableNumber = tableNumber;
        orderBooking = vBookingOrder;
    }

    /** It is simple getter for table number.
     * @return - We are returning int tableNumber.
     */
    public int getTableNumber(){return tableNumber;}
    /** It is simple getter for order booking.
     * @return - We are returning Booking orderBooking.
     */
    public Booking getBookingOrder(){return orderBooking;}

    /** It is simple setter function for table number.
     * @param vTableNumber - We are putting it into tableNumber.
     */
    public void setTableNumber(int vTableNumber){tableNumber = vTableNumber;}
    /** It is simple setter function for order booking.
     * @param vbookingOrder - We are putting it into orderBooking.
     */
    public void setBookingOrder(Booking vbookingOrder){orderBooking = vbookingOrder;}

    /** It is our printOrderDetails method which is abstract method and we have to implement it. It is basically prints this InRestrOrder Object's details and make exception handling.
     */
    public void printOrderDetails(){
        System.out.println("Here is the Order's details! \n--------------------------------\nOrder ID: " +this.getOrderID() + "\nOder Date: " + this.getOrderDate() +
                "\nOrder Details: " + this.getOrderDetails() + "\nOrder Extra Notes: " + this.getExtraNotes() + "\nOrder Table Number: " + this.getTableNumber());
        try{
            System.out.println("Booking ID: " + this.orderBooking.getBookingID() + "\nBooking Date: " + this.orderBooking.getBookingDate());
        }
        catch(NullPointerException e){}
    }

    /** It is our processPayment method which we override it from Order to process payment and print it.
     */
    @Override
    public void processPayment() {
        System.out.println("The payment for this order: 'Payment Amount' is taken from customer ");
    }

}
