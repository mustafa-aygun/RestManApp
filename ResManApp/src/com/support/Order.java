package com.support;

import java.io.Serializable;
import java.util.Date;

/** It is our abstract order class. We will extends it in OnlineOrder and InRestrOrder. It has a static orderID to keep track it and provide it as unique
 */
public abstract class Order implements Payment, Serializable {
    protected static int helperOrderID = 0;
    protected int orderID = 0;
    protected Date orderDate;
    protected String orderDetails;
    protected String extraNotes;

    /** It is our default constructor. Here, I assume if date is not specified, new Date() gives current date to Order as order date. Also, everytime an order created,
     * I increment my static orderID by one. With this way, I am aiming having unique orderIDs.
     */
    public Order(){
        helperOrderID++;
        orderID = helperOrderID;
        orderDate = new Date();
        orderDetails = "not specified";
        extraNotes = "not specified";
    }

    /** It is our constructor with variables.
     * @param vOrderDate - Our order order date.
     * @param vOrderDetails - Our order details.
     * @param vExtraNotes - Our order extra notes.
     */
    public Order(Date vOrderDate, String vOrderDetails, String vExtraNotes) {

        helperOrderID++;
        orderID = helperOrderID;
        orderDate = vOrderDate;
        orderDetails = vOrderDetails;
        extraNotes = vExtraNotes;
    }

    /** It is simple getter method for order ID.
     * @return - It is returning int orderID.
     */
    public int getOrderID(){return orderID;}
    /** It is simple getter method for order date
     * @return - It is returning Date orderDate.
     */
    public Date getOrderDate(){return orderDate;}
    /** It is simple getter method for order details
     * @return - It is returning String orderDetails.
     */
    public String getOrderDetails(){return orderDetails;}
    /** It is simple getter method for order extra notes
     * @return - It is returning String extraNotes  .
     */
    public String getExtraNotes(){return extraNotes;}

    /** It is simple setter method for order ID. However, I have never use this because it's a static variable and processing with constructor.
     * @param vOrderID - We are putting it into orderID:
     */
    public void setOrderID(int vOrderID){orderID = vOrderID;}
    /** It is simple setter method for order date.
     * @param vOrderDate - We are putting it into orderDate:
     */
    public void setOrderDate(Date vOrderDate){orderDate = vOrderDate;}
    /** It is simple setter method for order details.
     * @param vOrderDetails - We are putting it into orderDetails:
     */
    public void setOrderDetails(String vOrderDetails){orderDetails = vOrderDetails;}
    /** It is simple setter method for order extra notes.
     * @param vExtraNotes - We are putting it into extraNotes:
     */
    public void setExtraNotes(String vExtraNotes){extraNotes = vExtraNotes;}

    /** It is our abstract method to print order details. We will override it at OnlineOrder and InRestrOrder.
     */
    public abstract void printOrderDetails();

    /** It is our abstract method to print payment. We will override it at OnlineOrder and InRestrOrder.
     */
    @Override
    public void processPayment() {

    }
}
