package com.core;

import com.support.Order;

import java.util.Date;

/** It is our OnlineOrder class which extends Order.
 */
public class OnlineOrder extends Order {
    private String paymentType;
    private Junior deliveredBy;

    /** It is our default constructor.
     */
    public OnlineOrder(){
        super();
        paymentType = "not specified";
    }

    /** It is our constructor with variables expect payment type and delivered by. For variables which belong to super class, I call super class's constructor.
     * I will generally use this constructor in my program. It is easy to create a order then set it's payment type and delivered by if we have any instead of creating temp variables.
     * @param vOrderDate - Our order date.
     * @param vOrderDetails - Our order details.
     * @param vExtraNotes - Our order extra notes.
     */
    public OnlineOrder(Date vOrderDate, String vOrderDetails, String vExtraNotes) {

        super(vOrderDate,vOrderDetails,vExtraNotes);

    }

    /** It is our constructor with variables. For variables which belong to super class, I call super class's constructor.
     * @param vOrderDate - Our order date.
     * @param vOrderDetails - Our order details.
     * @param vExtraNotes - Our order extra notes.
     * @param vPaymentType - Our online order payment type.
     * @param vDeliveredBy - Our online order delivered by.
     */
    public OnlineOrder(Date vOrderDate, String vOrderDetails, String vExtraNotes, String vPaymentType, Junior vDeliveredBy) {

        super(vOrderDate,vOrderDetails,vExtraNotes);

        paymentType = vPaymentType;
        deliveredBy = vDeliveredBy;
    }

    /** It is simple getter for payment type.
     * @return - We are returning String paymentType.
     */
    public String getPaymentType(){return paymentType;}
    /** It is simple getter for delivered by.
     * @return - We are returning Junior deliveredBY.
     */
    public Junior getDeliveredBy(){return deliveredBy;}

    /** It is simple setter function for payment type.
     * @param vPaymentType - We are putting it into paymentType.
     */
    public void setPaymentType(String vPaymentType){paymentType = vPaymentType;}
    /** It is simple setter function for delivered by.
     * @param vDeliveredBy - We are putting it into deliveredBy.
     */
    public void setDeliveredBy(Junior vDeliveredBy){deliveredBy = vDeliveredBy;}

    /** It is our printOrderDetails method which is abstract method and we have to implement it. It is basically prints this OnlineOrder Object's details and make exception handling.
     */
    public void printOrderDetails(){
        System.out.println("Here is the Order's details! \n--------------------------------\nOrder ID: " +this.getOrderID() + "\nOder Date: " + this.getOrderDate() +
                "\nOrder Details: " + this.getOrderDetails() + "\nOrder Extra Notes: " + this.getExtraNotes() + "\nOrder Payment Type: " + this.getPaymentType() +
                "\nOrder Delivered By: " + this.getDeliveredBy().getName());
    }

    /** It is our processPayment method which we override it from Order to process payment and print it.
     */
    @Override
    public void processPayment() {
        System.out.println("The payment for this order: 'Payment Amount' is taken from customer ");
    }
}
